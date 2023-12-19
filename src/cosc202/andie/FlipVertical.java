package cosc202.andie;

import java.awt.image.*;
import java.awt.geom.*;

public class FlipVertical implements ImageOperation {

    /**
     * Default constructor.
     */
    public FlipVertical() {
    }

    /**
     * Flip an image vertically.
     * 
     * Uses AffineTransforms' translate method to flip and image vertically.
     * 
     * @param input The image to apply the flip to.
     * @return The resulting flipped image.
     */
    public BufferedImage apply(BufferedImage input) {
        BufferedImage output = new BufferedImage(input.getWidth(), input.getHeight(), input.getType());

        AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
        tx.translate(-input.getWidth(null), 0);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        op.filter(input, output);

        return output;
    }
}