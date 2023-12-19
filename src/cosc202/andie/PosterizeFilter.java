package cosc202.andie;

import java.awt.image.*;

/**
 * A filter to posterize an image.
 */
public class PosterizeFilter implements ImageOperation, java.io.Serializable {

    private int radius;
    private int[] levels;


    /**
     * Set the posterization level in the output image.
     * 
     * @param radius the posterization number (higher radius = more colours)
     */
    PosterizeFilter(int radius) {
        this.radius = radius;
    }


    public BufferedImage apply(BufferedImage input) {
		levels = new int[256];
		if (radius != 1){
			for (int i = 0; i < 256; i++){
				levels[i] = 255 * (radius*i / 256) / (radius-1);

            }
        }

            for (int y = 0; y < input.getHeight(); ++y) {
                for (int x = 0; x < input.getWidth(); ++x) {

        int argb = input.getRGB(x,y);
		int a = argb & 0xff000000;
		int r = (argb >> 16) & 0xff;
		int g = (argb >> 8) & 0xff;
		int b = argb & 0xff;
		r = levels[r];
		g = levels[g];
		b = levels[b];
		argb = a | (r << 16) | (g << 8) | b;

        input.setRGB(x,y,argb);

	}


    }

    return input;

}

}