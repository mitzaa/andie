package cosc202.andie;

import java.awt.image.*;

import javax.swing.JOptionPane;
import javax.xml.namespace.QName;

import java.awt.Image;

public class Crop implements ImageOperation{
    Region r;

    /**
     * Default constructor assigns scale as 0.5 to decrease the size of the image by
     * 50%.
     */
    public Crop(Region r) {
        this.r = r;
    }

    /**
     * Returns the scale.
     * 
     * @return The current scale value.
     */
    
    /**
     * Resize the image by 50%.
     * 
     * 
     * 
     * @param input The image to resize.
     * @return The resulting resized image.
     */
    public BufferedImage apply(BufferedImage input) {
        
        try {
            BufferedImage output = input.getSubimage(r.x, r.y, r.width, r.height);
        output.getGraphics().drawImage(output, 0, 0, null);
            ImagePanel.regionSelectedStatic = false;
        return output;
        } catch (RasterFormatException e) {
            JOptionPane.showMessageDialog(null, "No region selected", "Cannot crop", 2);
            return input;
        }
       
        
        
        
    }

    
}
