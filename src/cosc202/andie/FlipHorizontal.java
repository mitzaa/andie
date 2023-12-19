package cosc202.andie;

import java.awt.image.*;
import java.awt.geom.*;

public class FlipHorizontal implements ImageOperation {

    /**
     * Default constructor.
     */
    public FlipHorizontal() {
    }

    /**
     * Flip an image horizontally.
     * 
     * Uses AffineTransforms' translate method to flip and image horizontally.
     * 
     * @param input The image to apply the flip to.
     * @return The resulting flipped image.
     */
    public BufferedImage apply(BufferedImage input) {
        BufferedImage output = new BufferedImage(input.getWidth(), input.getHeight(), input.getType());

        AffineTransform tx = AffineTransform.getScaleInstance(1, -1);
        tx.translate(0, -input.getHeight(null));
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        op.filter(input, output);

        return output;
    }
}