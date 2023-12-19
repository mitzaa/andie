package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * <p>
 * Actions provided by the Colour menu.
 * </p>
 * 
 * <p>
 * The Colour menu contains actions that affect the colour of each pixel directly 
 * without reference to the rest of the image.
 * This includes conversion to greyscale in the sample code, but more operations will need to be added.
 * </p>
 * 
 * <p> 
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Steven Mills
 * @version 1.0
 */
public class ColourActions {
    
    /** A list of actions for the Colour menu. */
    protected ArrayList<Action> actions;

    /**
     * <p>
     * Create a set of Colour menu actions.
     * </p>
     */
    public ColourActions() {
        actions = new ArrayList<Action>();
        actions.add(new ConvertToGreyAction("Greyscale", null, "Convert to greyscale", Integer.valueOf(KeyEvent.VK_G)));
        actions.add(new BrightnessAction("Brightness", null, "Adjust", Integer.valueOf(KeyEvent.VK_G)));
        actions.add(new ContrastAction("Contrast", null, "Adjust", Integer.valueOf(KeyEvent.VK_G)));
    }

    /**
     * <p>
     * Create a menu contianing the list of Colour actions.
     * </p>
     * 
     * @return The colour menu UI element.
     */
    public JMenu createMenu() {
        JMenu fileMenu = new JMenu("Colour");

        for(Action action: actions) {
            fileMenu.add(new JMenuItem(action));
        }

        return fileMenu;
    }

    /**
     * <p>
     * Action to convert an image to greyscale.
     * </p>
     * 
     * @see ConvertToGrey
     */
    public class ConvertToGreyAction extends ImageAction {

        /**
         * <p>
         * Create a new convert-to-grey action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        ConvertToGreyAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the convert-to-grey action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the ConvertToGreyAction is triggered.
         * It changes the image to greyscale.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            target.getImage().apply(new ConvertToGrey());
            target.repaint();
            target.getParent().revalidate();
        }

    }


    /**
     * <p>
     * Action to enhance the brightness on an image.
     * </p>
     * 
     * @see BrightnessContrast
     */
    public class BrightnessAction extends ImageAction {

         /**
         * <p>
         * Create a new brightness action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        BrightnessAction(String name, ImageIcon icon,
                String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }


        /**
         * 
         * 
         * <p>
         * This method is called whenever the Brightness action is triggered, It enhances the brighness
         * </p>
         * 
         * @param e The event triggering this callback.
         */

        public void actionPerformed(ActionEvent e) {
            // Create and apply the filter
            // Determine the radius - ask the user.
            int br = 1;
            int co = 0;

            // Pop-up dialog box to ask for the radius value.
            SpinnerNumberModel inputModel = new SpinnerNumberModel(1, -100, 100, 1);
            JSpinner inputSpinner = new JSpinner(inputModel);
        
            int option = JOptionPane.showOptionDialog(null, inputSpinner, "Enter value in range: (-100: 100)",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            // Check the return value from the dialog box.
            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                br = inputModel.getNumber().intValue();
            }
            target.getImage().apply(new BrightnessContrast(br, co));
            target.repaint();
            target.getParent().revalidate();
        }
    }



    /**
     * <p>
     * Action to enhance contrast of image
     * </p>
     * 
     * @see BrightnessContrast
     */
    public class ContrastAction extends ImageAction {



         /**
         * <p>
         * Create a new Contrast action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        ContrastAction(String name, ImageIcon icon,
                String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }


         /**
         * 
         * 
         * <p>
         * This method is called whenever the Contrast action is triggered, It enhances the brighness
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            // Create and apply the filter
            // Determine the radius - ask the user.
            int br = 0;
            int co = 1;

            // Pop-up dialog box to ask for the radius value.
            SpinnerNumberModel inputModel = new SpinnerNumberModel(1, -100, 100, 1);
            JSpinner inputSpinner = new JSpinner(inputModel);
            int option = JOptionPane.showOptionDialog(null, inputSpinner, "Enter value in range: (-100: 100)",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            // Check the return value from the dialog box.
            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                co = inputModel.getNumber().intValue();
            }
            target.getImage().apply(new BrightnessContrast(br, co));
            target.repaint();
            target.getParent().revalidate();
        }
    }

}
