package cosc202.andie;

import java.awt.image.*;

/**
 * Two edge detection filters.
 */
public class EdgeDetectFilters implements ImageOperation, java.io.Serializable {
    private int filterOption;

    /**
     * Constructor to assign which emboss filter to use.
     */
    EdgeDetectFilters(int filterOption) {
        this.filterOption = filterOption;
    }

    public BufferedImage apply(BufferedImage input) {
        float[] array = new float[9];

        if (filterOption == 1) {
            float[] option1 = { -1 / 2.0f, 0, 1 / 2.0f,
                    -1 / 2.0f, 0, 1 / 2.0f,
                    -1 / 2.0f, 0, 1 / 2.0f };
            array = option1;
        } else if (filterOption == 2) {
            float[] option2 = { -1 / 2.0f, -1, -1 / 2.0f,
                    0, 0, 0,
                    1 / 2.0f, 1, 1 / 2.0f };
            array = option2;
        }

        Kernel kernel = new Kernel(3, 3, array);
        // ConvolveOp convOp = new ConvolveOp(kernel);
        BufferedImage output = new BufferedImage(input.getColorModel(), input.copyData(null),
                input.isAlphaPremultiplied(), null);
        // convOp.filter(input, output);

        output = Convolution.extendedConvolution(kernel, output, true);

        return output;
    }
}
