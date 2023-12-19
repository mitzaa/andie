package cosc202.andie;

import java.awt.image.*;



/**
 * A filter which inverts the RGB channels of an image.
 */
public class InvertFilter implements ImageOperation, java.io.Serializable {
	
    
    InvertFilter() {

	}



    public BufferedImage apply(BufferedImage input) {

        for (int y = 0; y < input.getHeight(); ++y) {
            for (int x = 0; x < input.getWidth(); ++x) {
                int argb = input.getRGB(x, y);
                int a = (argb & 0xFF000000) >> 24;
                int r = (argb & 0x00FF0000) >> 16;
                int g = (argb & 0x0000FF00) >> 8;
                int b = (argb & 0x000000FF);

                int invertR = 255 - r;
                int invertG = 255 - g;
                int invertB = 255 - b;
        

                argb = (a << 24) | (invertR << 16) | (invertG << 8) | invertB;
              
                
                input.setRGB(x, y, argb);
            }
        }
        
        return input;
        }
    
    }


