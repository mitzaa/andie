package cosc202.andie;

import java.awt.image.*;
import java.awt.Image;

public class ResizeMinus implements ImageOperation {
    private double scale;

    /**
     * Default constructor assigns scale as 0.5 to decrease the size of the image by
     * 50%.
     */
    public ResizeMinus() {
        this.scale = 0.5;
    }

    /**
     * Returns the scale.
     * 
     * @return The current scale value.
     */
    public double getScale() {
        return scale;
    }

    /**
     * Resize the image by 50%.
     * 
     * Uses Graphics2D to draw a scaled image of the current buffered image input
     * using the {@link scale} value.
     * 
     * @param input The image to resize.
     * @return The resulting resized image.
     */
    public BufferedImage apply(BufferedImage input) {
        int scaledWidth = (int) (input.getWidth() * scale);
        int scaledHeight = (int) (input.getHeight() * scale);

        Image image = input.getScaledInstance(scaledWidth, scaledHeight, input.getType());

        BufferedImage output = new BufferedImage(scaledWidth, scaledHeight, input.getType());
        output.getGraphics().drawImage(image, 0, 0, null);

        return output;
    }
}
