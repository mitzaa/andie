package cosc202.andie;

import java.awt.image.*;
import java.text.DecimalFormat;

public class GaussianFilter implements ImageOperation, java.io.Serializable {
    private int radius;

    /**
     * Constructor to assign given radius to private datafield.
     * 
     * @param radius
     */
    GaussianFilter(int radius) {
        this.radius = radius;
    }

    /**
     * Defualt constructor assigns radius as 1 if no value is given.
     */
    public GaussianFilter() {
        this(1);
    }

    /**
     * Returns the radius value.
     * 
     * @return radius or filter.
     */
    public int getRadius() {
        return radius;
    }

    /**
     * Apply a Gaussian filter to an image.
     * 
     * The Gaussain filter is implemented via convolution.
     * The size of the convolution kernel is specified by the {@link radius}.
     * Larger radii lead to stronger blurring.
     * 
     * @param input The image to apply the Gaussian filter to.
     * @return The resulting (blurred)) image.
     */
    public BufferedImage apply(BufferedImage input) {

        int size = (2 * radius + 1) * (2 * radius + 1);
        float[] array = new float[size];

        // Format decimals for rounding.
        DecimalFormat df = new DecimalFormat("#.###");
        double omega = (double) radius / 3;
        int index = 0;
        double sum = 0;

        // Gets sum of all values to get the integrated kernal in the later for loop.
        for (int y = -radius; y <= radius; y++) {
            for (int x = -radius; x <= radius; x++) {
                double number = (1 / (2 * Math.PI * Math.pow(omega, 2))
                        * Math.pow(Math.E, (-1 * ((Math.pow(x, 2) + Math.pow(y, 2)) / (2 * Math.pow(omega, 2))))));
                number = Double.parseDouble(df.format(number));
                sum += number;
            }
        }

        // Calculate and add number to array to be added to kernal.
        for (int y = -radius; y <= radius; y++) {
            for (int x = -radius; x <= radius; x++) {
                double number = (1 / (2 * Math.PI * Math.pow(omega, 2))
                        * Math.pow(Math.E, (-1 * ((Math.pow(x, 2) + Math.pow(y, 2)) / (2 * Math.pow(omega, 2))))));
                number = number / sum;
                number = Double.parseDouble(df.format(number));
                array[index] = (float) number;
                index++;
            }
        }

        BufferedImage output = new BufferedImage(input.getColorModel(), input.copyData(null),
                input.isAlphaPremultiplied(), null);

        Kernel kernel = new Kernel(2 * radius + 1, 2 * radius + 1, array);

        // ConvolveOp convOp = new ConvolveOp(kernel);
        // convOp.filter(input, output);

        // New manually done convolution using for loops rather than ConvolveOp method.
        output = Convolution.extendedConvolution(kernel, output, false);

        return output;

    }
}