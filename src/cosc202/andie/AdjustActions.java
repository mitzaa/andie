package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * <p>
 * Actions provided by the adjust image menu.
 * </p>
 * 
 * <p>
 * The adjust image menu contains actions that affect how the image is displayed
 * in the
 * application.
 * </p>
 */
public class AdjustActions {

    /**
     * Lists containing actions for the adjust image menu.
     */
    protected ArrayList<Action> resizeActions;
    protected ArrayList<Action> rotateActions;
    protected ArrayList<Action> flipActions;

    /**
     * <p>
     * Create a set of adjust image menu actions.
     * </p>
     */
    public AdjustActions() {
        resizeActions = new ArrayList<Action>();
        rotateActions = new ArrayList<Action>();
        flipActions = new ArrayList<Action>();

        resizeActions.add(new ResizeMinusAction("Resize 50%", null, "Resize 50%", Integer.valueOf(KeyEvent.VK_PLUS)));
        resizeActions.add(new ResizePlusAction("Resize 150%", null, "Resize 150%", Integer.valueOf(KeyEvent.VK_MINUS)));

        rotateActions.add(
                new RotateLeftAction("Rotate left 90°", null, "Rotate left 90°", Integer.valueOf(KeyEvent.VK_PLUS)));
        rotateActions.add(new RotateRightAction("Rotate right 90°", null, "Rotate right 90°",
                Integer.valueOf(KeyEvent.VK_MINUS)));
        rotateActions.add(new Rotate180Action("Rotate 180°", null, "Rotate 180°", Integer.valueOf(KeyEvent.VK_PLUS)));

        flipActions.add(
                new FlipVerticalAction("Flip Vertically", null, "Flip Vertically", Integer.valueOf(KeyEvent.VK_PLUS)));
        flipActions.add(
                new FlipHorizontalAction("Flip Horizontally", null, "Flip Horizontally",
                        Integer.valueOf(KeyEvent.VK_PLUS)));
    }

    /**
     * <p>
     * Create a menu containing the list of image adjustment actions.
     * </p>
     * 
     * @return The adjust image menu UI element.
     */
    public JMenu createMenu() {
        JMenu adjustMenu = new JMenu("Adjust Image");

        for (Action action : resizeActions) {
            adjustMenu.add(new JMenuItem(action));

        }
        adjustMenu.addSeparator();
        for (Action action : rotateActions) {
            adjustMenu.add(new JMenuItem(action));

        }
        adjustMenu.addSeparator();
        for (Action action : flipActions) {
            adjustMenu.add(new JMenuItem(action));

        }

        return adjustMenu;
    }

    /**
     * <p>
     * Action to resize an image making it smaller.
     * </p>
     * 
     * <p>
     * This changes the images contents by altering the image pixels.
     * </p>
     */
    public class ResizeMinusAction extends ImageAction {

        /**
         * <p>
         * Create a new ResizeMinus action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        ResizeMinusAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the ResizeMinus action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the ResizeMinusAction is triggered.
         * It decreases the size of the image by 50%.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            target.getImage().apply(new ResizeMinus());
            target.repaint();
            target.getParent().revalidate();
        }

    }

    /**
     * <p>
     * Action to resize an image making it larger.
     * </p>
     * 
     * <p>
     * This changes the images contents by altering the image pixels.
     * </p>
     */
    public class ResizePlusAction extends ImageAction {

        /**
         * <p>
         * Create a new ResizePlus action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        ResizePlusAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the ResizePlus action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the ResizePlusAction is triggered.
         * It Increases the size of the image by 50%.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            target.getImage().apply(new ResizePlus());
            target.repaint();
            target.getParent().revalidate();
        }

    }

    /**
     * <p>
     * Action to rotate an image to the left by 90 degrees.
     * </p>
     * 
     * <p>
     * This changes the images contents by altering the image pixels.
     * </p>
     */
    public class RotateLeftAction extends ImageAction {

        /**
         * <p>
         * Create a new RotateLeft action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        RotateLeftAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the RotateLeft action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the RotateLeftAction is triggered.
         * It rotates an image to the left by 90 degrees.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            target.getImage().apply(new RotateLeft());
            target.repaint();
            target.getParent().revalidate();
        }

    }

    /**
     * <p>
     * Action to rotate an image to the right by 90 degrees.
     * </p>
     * 
     * <p>
     * This changes the images contents by altering the image pixels.
     * </p>
     */
    public class RotateRightAction extends ImageAction {

        /**
         * <p>
         * Create a new RotateRight action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        RotateRightAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the RotateRight action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the RotateRightAction is triggered.
         * It rotates an image to the right by 90 degrees.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            target.getImage().apply(new RotateRight());
            target.repaint();
            target.getParent().revalidate();
        }

    }

    /**
     * <p>
     * Action to rotate an image by 180 degrees.
     * </p>
     * 
     * <p>
     * This changes the images contents by altering the image pixels.
     * </p>
     */
    public class Rotate180Action extends ImageAction {

        /**
         * <p>
         * Create a new Rotate180Action action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        Rotate180Action(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the Rotate180 action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the Rotate180Action is triggered.
         * It rotates an image by 180 degrees.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            target.getImage().apply(new Rotate180());
            target.repaint();
            target.getParent().revalidate();
        }

    }

    /**
     * <p>
     * Action to flip the image vertically.
     * </p>
     * 
     * <p>
     * This changes the images contents by altering the image pixels.
     * </p>
     */
    public class FlipVerticalAction extends ImageAction {

        /**
         * <p>
         * Create a new Flip Vertical action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        FlipVerticalAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the FlipVertical action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the FlipVerticalAction is triggered.
         * It flips the image vertically.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            target.getImage().apply(new FlipVertical());
            target.repaint();
            target.getParent().revalidate();
        }

    }

    /**
     * <p>
     * Action to flip the image horizontally.
     * </p>
     * 
     * <p>
     * This changes the images contents by altering the image pixels.
     * </p>
     */
    public class FlipHorizontalAction extends ImageAction {

        /**
         * <p>
         * Create a new Flip Horizontal action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        FlipHorizontalAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the FlipHorizontal action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the FlipHorizontalAction is triggered.
         * It flips the image horizontally.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            target.getImage().apply(new FlipHorizontal());
            target.repaint();
            target.getParent().revalidate();
        }

    }
}