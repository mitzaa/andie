package cosc202.andie;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.*;

public class LineAction implements ImageOperation{

    private Region region;
    private Color color;
    int firstClickX;
    int firstClickY;
    int mostRecentClickX;
    int mostRecentClickY;

    public LineAction(int firstClickX, int firstClickY, int mostRecentClickX, int mostRecentClickY, Color color, Region region){
        this.region = region;
        this.firstClickX = firstClickX;
        this.firstClickY = firstClickY;
        this.mostRecentClickX = mostRecentClickX;
        this.mostRecentClickY = mostRecentClickY;
        this.color = color;
    }

    public LineAction( Region region, Color color){
        this.region = region;
        
        this.color = color;
    }
    @Override
    public BufferedImage apply(BufferedImage input) {
        BufferedImage bimage = input;
        

        Graphics2D g2d = bimage.createGraphics();

        g2d.setColor(color);
        g2d.drawLine(firstClickX, firstClickY, mostRecentClickX, mostRecentClickY);
        g2d.dispose();
        
        return bimage;
    }
    
}