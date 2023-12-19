package cosc202.andie;

import java.awt.Color;
import java.awt.image.*;
import java.util.*;

/**
 * <p>
 * ImageOperation to apply a median (noise reduction) filter.
 * </p>
 * 
 * <p>
 * A median filter reduces the noise in an image by replacing each pixel with
 * the median of the
 * pixels in a surrounding neighbourhood, and can be implemented by a
 * convoloution.
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA
 * 4.0</a>
 * </p>
 * 
 * @see java.awt.image.ConvolveOp
 * @author James Higgins/Andrew Booth
 * @version 2.0
 */
public class MedianFilter implements ImageOperation, java.io.Serializable {

    /**
     * The size of filter to apply. A radius of 1 is a 3x3 filter, a radius of 2 a
     * 5x5 filter, and so forth.
     */
    private int radius;

    /**
     * <p>
     * Construct a median filter with the given size.
     * </p>
     * 
     * <p>
     * The size of the filter is the 'radius' of the convolution kernel used.
     * A size of 1 is a 3x3 filter, 2 is 5x5, and so on.
     * Larger filters give a greater noise reducing effect.
     * </p>
     * 
     * @param radius The radius of the newly constructed MedianFilter
     */
    MedianFilter(int radius) {
        this.radius = radius;
    }

    /**
     * <p>
     * Construct a median filter with the default size.
     * </p
     * >
     * <p>
     * By default, a mean filter has radius 1.
     * </p>
     * 
     * @see MeanFilter(int)
     */
    MedianFilter() {
        this(1);
    }

    /**
     * <p>
     * Apply a median filter to an image.
     * </p>
     * 
     * <p>
     * As with many filters, the median filter is implemented via convolution.
     * The size of the convolution kernel is specified by the {@link radius}.
     * Larger radii lead to greater noise reduction.
     * </p>
     * 
     * @param output The image to apply the median filter to.
     * @return The resulting image.
     */
    public BufferedImage apply(BufferedImage output) {

        BufferedImage result = new BufferedImage(output.getColorModel(), output.copyData(null),
                output.isAlphaPremultiplied(), null);

        int size = (2 * radius + 1) * (2 * radius + 1);

        float[] rMedian = new float[size];
        float[] gMedian = new float[size];
        float[] bMedian = new float[size];
        float[] aMedian = new float[size];

        int mid = size / 2;

        int kCounter = 0;

        for (int y = 0; y < output.getHeight(); ++y) {
            for (int x = 0; x < output.getWidth(); ++x) {
                for (int dy = -radius; dy <= radius; ++dy) {
                    for (int dx = -radius; dx <= radius; ++dx) {
                        int rowX = x + dx;
                        int colY = y + dy;
                        if (rowX >= output.getWidth())
                            rowX = output.getWidth() - 1;
                        if (colY >= output.getHeight())
                            colY = output.getHeight() - 1;
                        if (rowX < 0)
                            rowX = 0;
                        if (colY < 0)
                            colY = 0;
                        // System.out.println(k);
                        Color col = new Color(output.getRGB(rowX, colY));
                        rMedian[kCounter] = col.getRed();
                        gMedian[kCounter] = col.getGreen();
                        bMedian[kCounter] = col.getBlue();
                        aMedian[kCounter] = col.getTransparency();
                        kCounter++;

                        // System.out.println("R = " + r + " G = " + g + " B = " + b);
                    }
                }
                kCounter = 0;
                Arrays.sort(rMedian);
                Arrays.sort(gMedian);
                Arrays.sort(bMedian);
                Arrays.sort(aMedian);
                float r = rMedian[mid];
                float g = gMedian[mid];
                float b = bMedian[mid];
                float a = aMedian[mid];

                if (r < 0)
                    r = 0;
                if (g < 0)
                    g = 0;
                if (b < 0)
                    b = 0;
                if (r > 255)
                    r = 255;
                if (g > 255)
                    g = 255;
                if (b > 255)
                    b = 255;

                result.setRGB(x, y, new Color(Math.round(r), Math.round(g), Math.round(b), Math.round(a)).getRGB());
            }
        }

        return result;
    }
}
