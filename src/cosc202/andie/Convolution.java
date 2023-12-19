package cosc202.andie;

import java.awt.image.*;
import java.awt.Color;

/**
 * Class to maunally implement convolution using a kernal and buffered image to
 * alter the pixels.
 */
public class Convolution {

    /**
     * Defualt constructor.
     */
    Convolution() {
    }

    /**
     * 
     * @param k      Kernal to alter pixel values.
     * @param output Image to have filter applied too.
     * @param offset Boolean to check if an offset (to account for negative rgb
     *               values) should be applied.
     * @return BufferedImage with fitlers kernal applied to it.
     */
    public static BufferedImage extendedConvolution(Kernel k, BufferedImage output, boolean offset) {

        BufferedImage result = new BufferedImage(output.getColorModel(), output.copyData(null),
                output.isAlphaPremultiplied(), null);

        float[] data = k.getKernelData(null);
        int radiusX = (k.getWidth() - 1) / 2;
        int radiusY = (k.getHeight() - 1) / 2;
        int length = 0;
        float[][] dataReshaped = new float[k.getWidth()][k.getHeight()];
        for (int row = 0; row < dataReshaped.length; row++) {
            for (int col = 0; col < dataReshaped[row].length; col++) {
                dataReshaped[row][col] = data[length];
                length++;
            }
        }

        for (int y = 0; y < output.getHeight(); ++y) {
            for (int x = 0; x < output.getWidth(); ++x) {
                float r = 0;
                float g = 0;
                float b = 0;
                for (int dy = -radiusY; dy <= radiusY; ++dy) {
                    for (int dx = -radiusX; dx <= radiusX; ++dx) {
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

                        float kernalValue = dataReshaped[radiusX + dx][radiusY + dy];
                        // System.out.println(k);
                        Color col = new Color(output.getRGB(rowX, colY));
                        r += kernalValue * col.getRed();
                        g += kernalValue * col.getGreen();
                        b += kernalValue * col.getBlue();

                        // System.out.println("R = " + r + " G = " + g + " B = " + b);
                    }
                }

                // Apply offset for filters with negative results.
                if (offset) {
                    r += 127;
                    g += 127;
                    b += 127;
                }
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

                result.setRGB(x, y, new Color(Math.round(r), Math.round(g), Math.round(b)).getRGB());
            }
        }

        // convOp.filter(input, output);

        return result;
    }
}
