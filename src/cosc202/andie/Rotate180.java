package cosc202.andie;

import java.awt.image.*;
import java.awt.geom.*;

public class Rotate180 implements ImageOperation {
    private double rotation;

    /**
     * Default Constructor sets the amount to rotate by in regards to the PI value
     * due to how affineTransform operates.
     */
    public Rotate180() {
        this.rotation = Math.PI;
    }

    /**
     * Rotate an image 180 degrees.
     * 
     * Uses AffineTransforms' rotate method to rotate an image by the amount
     * specified as {@link rotation}.
     * 
     * @param input The image to apply the rotation to.
     * @return The resulting rotated image.
     */
    public BufferedImage apply(BufferedImage input) {
        BufferedImage output = new BufferedImage(input.getWidth(), input.getHeight(), input.getType());

        AffineTransform affineTransform = new AffineTransform();
        affineTransform.rotate(rotation, input.getWidth() / 2, input.getHeight() / 2);

        AffineTransformOp affineTransformOp = new AffineTransformOp(affineTransform, AffineTransformOp.TYPE_BILINEAR);
        affineTransformOp.filter(input, output);

        return output;
    }
}