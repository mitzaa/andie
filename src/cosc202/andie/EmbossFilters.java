package cosc202.andie;

import java.awt.image.*;

/**
 * Eight edge detection filters chosen by an int passed in via constructor.
 */
public class EmbossFilters implements ImageOperation, java.io.Serializable {
    private int filterOption;

    /**
     * Constructor to assign which emboss filter to use.
     * 
     * @param filterOption The chosen filter from int value 1 to 8.
     */
    EmbossFilters(int filterOption) {
        this.filterOption = filterOption;
    }

    /**
     * Default constructor to assign filterOption 1 if no filterOption is passed
     * through to class.
     */
    EmbossFilters() {
        this.filterOption = 1;
    }

    public BufferedImage apply(BufferedImage input) {
        float[] array = new float[9];

        if (filterOption == 1) {
            float[] option1 = { 0, 0, 0,
                    1f, 0, -1f,
                    0, 0, 0 };
            array = option1;
        } else if (filterOption == 2) {
            float[] option2 = { 1f, 0, 0,
                    0, 0, 0,
                    0, 0, -1f };
            array = option2;
        } else if (filterOption == 3) {
            float[] option3 = { 0, 1f, 0,
                    0, 0, 0,
                    0, -1f, 0 };
            array = option3;
        } else if (filterOption == 4) {
            float[] option4 = { 0, 0, 1f,
                    0, 0, 0,
                    -1f, 0, 0 };
            array = option4;
        } else if (filterOption == 5) {
            float[] option5 = { 0, 0, 0,
                    -1f, 0, 1f,
                    0, 0, 0 };
            array = option5;
        } else if (filterOption == 6) {
            float[] option6 = { -1f, 0, 0,
                    0, 0, 0,
                    0, 0, 1f };
            array = option6;
        } else if (filterOption == 7) {
            float[] option7 = { 0, -1f, 0,
                    0, 0, 0,
                    0, 1f, 0 };
            array = option7;
        } else if (filterOption == 8) {
            float[] option8 = { 0, 0, -1f,
                    0, 0, 0,
                    1f, 0, 0 };
            array = option8;
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
