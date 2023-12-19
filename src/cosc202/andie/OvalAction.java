package cosc202.andie;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.*;

public class OvalAction implements ImageOperation{

    private Region region;
    private Color color;

    public OvalAction(Region region, Color color){
        this.region = region;
        this.color = color;
    }
    @Override
    public BufferedImage apply(BufferedImage input) {
        BufferedImage bimage = input;
        

        Graphics2D g2d = bimage.createGraphics();

        g2d.setColor(color);
        g2d.fillOval(region.x, region.y, region.width, region.height);
        g2d.dispose();
        
        return bimage;
    }
    
}