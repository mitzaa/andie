package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * <p>
 * Actions provided by the Filter menu.
 * </p>
 * 
 * <p>
 * The Filter menu contains actions that update each pixel in an image based on
 * some small local neighbourhood.
 * This includes a mean filter (a simple blur) in the sample code, but more
 * operations will need to be added.
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA
 * 4.0</a>
 * </p>
 * 
 * @author Steven Mills
 * @version 1.0
 */
public class FilterActions {

    /** A list of actions for the Filter menu. */
    protected ArrayList<Action> actions;

    /**
     * <p>
     * Create a set of Filter menu actions.
     * </p>
     */
    public FilterActions() {
        actions = new ArrayList<Action>();
        actions.add(new MeanFilterAction("Mean filter", null, "Apply a mean filter", Integer.valueOf(KeyEvent.VK_M)));
        actions.add(new SharpenFilterAction("Sharpen Filter", null, "Apply a sharpen filter",
                Integer.valueOf(KeyEvent.VK_B)));
        actions.add(new GaussianFilterAction("Gaussian blur filter", null, "Apply a gaussian blur filter",
                Integer.valueOf(KeyEvent.VK_M)));
        actions.add(new MedianFilterAction("Median filter", null, "Apply a median filter",
                Integer.valueOf(KeyEvent.VK_F)));
        actions.add(new EmbossFiltersAction("Emboss filters", null, "Apply an emboss filter",
                Integer.valueOf(KeyEvent.VK_F)));
        actions.add(new EdgeDetectAction("Edge Detection filters", null, "Apply an edge detection filter",
                Integer.valueOf(KeyEvent.VK_F)));
        actions.add(new PosterizeFilterAction("Posterize filter", null, "Apply a posterization filter",
                Integer.valueOf(KeyEvent.VK_P)));
        actions.add(new InvertFilterAction("Invert filter", null, "Apply an invert filter", Integer.valueOf(KeyEvent.VK_I)));
    }

    

    /**
     * <p>
     * Create a menu contianing the list of Filter actions.
     * </p>
     * 
     * @return The filter menu UI element.
     */
    public JMenu createMenu() {
        JMenu fileMenu = new JMenu("Filter");

        for (Action action : actions) {
            fileMenu.add(new JMenuItem(action));
        }

        return fileMenu;
    }

    /**
     * <p>
     * Action to blur an image with a mean filter.
     * </p>
     * 
     * @see MeanFilter
     */
    public class MeanFilterAction extends ImageAction {

        /**
         * <p>
         * Create a new mean-filter action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        MeanFilterAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the convert-to-grey action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the MeanFilterAction is triggered.
         * It prompts the user for a filter radius, then applys an appropriately sized
         * {@link MeanFilter}.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {

            // Determine the radius - ask the user.
            int radius = 1;

            // Pop-up dialog box to ask for the radius value.
            SpinnerNumberModel radiusModel = new SpinnerNumberModel(1, 1, 10, 1);
            JSpinner radiusSpinner = new JSpinner(radiusModel);
            int option = JOptionPane.showOptionDialog(null, radiusSpinner, "Enter filter radius",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            // Check the return value from the dialog box.
            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                radius = radiusModel.getNumber().intValue();
            }

            // Create and apply the filter
            target.getImage().apply(new MeanFilter(radius));
            target.repaint();
            target.getParent().revalidate();
        }

    }

    /**
     * <p>
     * Action to use a sharpen filter on an image
     * </p>
     * 
     * @see SharpenFilter
     */
    public class SharpenFilterAction extends ImageAction {

        /**
         * <p>
         * Create a new Sharpen-filter action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        SharpenFilterAction(String name, ImageIcon icon,
                String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * This method is called whenever the SharpenFilterAction is triggered.
         * 
         * {@link SharpenFilter}.
         * </p>
         * 
         * @param e The event triggering this callback.
         */

        public void actionPerformed(ActionEvent e) {
            // Create and apply the filter
            target.getImage().apply(new SharpenFilter());
            target.repaint();
            target.getParent().revalidate();
        }
    }

    /**
     * <p>
     * Action to use a sharpen filter on an image
     * </p>
     * 
     * @see GaussianFilter
     */
    public class GaussianFilterAction extends ImageAction {

        /**
         * <p>
         * Create a new Gaussian-filter action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */

        GaussianFilterAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * This method is called whenever the GaussianFilterAction is triggered.
         * 
         * {@link GaussianFilter}.
         * </p>
         * 
         * @param e The event triggering this callback.
         */

        public void actionPerformed(ActionEvent e) {
            // Determine the radius - ask the user.
            int radius = 1;

            // Pop-up dialog box to ask for the radius value.
            SpinnerNumberModel radiusModel = new SpinnerNumberModel(1, 1, 10, 1);
            JSpinner radiusSpinner = new JSpinner(radiusModel);
            int option = JOptionPane.showOptionDialog(null, radiusSpinner, "Enter filter radius",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            // Check the return value from the dialog box.
            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                radius = radiusModel.getNumber().intValue();
            }

            // Create and apply the filter
            target.getImage().apply(new GaussianFilter(radius));
            target.repaint();
            target.getParent().revalidate();
        }
    }

    public class MedianFilterAction extends ImageAction {
        MedianFilterAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {
            // Determine the radius - ask the user
            int radius = 1;

            // Pop-up dialog box to ask for the radius value.
            SpinnerNumberModel radiusModel = new SpinnerNumberModel(1, 1, 10, 1);
            JSpinner radiusSpinner = new JSpinner(radiusModel);
            int option = JOptionPane.showOptionDialog(null, radiusSpinner, "Enter filter radius",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            // Check the return value from the dialog box.
            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                radius = radiusModel.getNumber().intValue();
            }

            // Create and apply the filter
            target.getImage().apply(new MedianFilter(radius));
            target.repaint();
            target.getParent().revalidate();
        }
    }

    /**
     * <p>
     * Action to use an emboss filter on an image
     * </p>
     * 
     * @see EmbossFilters
     */
    public class EmbossFiltersAction extends ImageAction {

        EmbossFiltersAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {
            // Determine the radius - ask the user
            String filter = "Emboss Filter 1";
            int filterOption = 1;
            ArrayList<String> filters = new ArrayList<String>();
            filters.add("Emboss Filter 1");
            filters.add("Emboss Filter 2");
            filters.add("Emboss Filter 3");
            filters.add("Emboss Filter 4");
            filters.add("Emboss Filter 5");
            filters.add("Emboss Filter 6");
            filters.add("Emboss Filter 7");
            filters.add("Emboss Filter 8");

            // Pop-up dialog box to ask for the filter option.

            SpinnerListModel filterModel = new SpinnerListModel(filters);
            JSpinner filterSpinner = new JSpinner(filterModel);
            // Make JSpinner non-editable.
            JFormattedTextField tf = ((JSpinner.DefaultEditor) filterSpinner.getEditor())
                    .getTextField();
            tf.setEditable(false);
            int option = JOptionPane.showOptionDialog(null, filterSpinner, "Please select a filter option.",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null,
                    null);

            // Check the return value from the dialog box.
            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                filter = filterModel.getValue().toString();
            }

            if (filter.equals("Emboss Filter 1")) {
                filterOption = 1;
            } else if (filter.equals("Emboss Filter 2")) {
                filterOption = 2;
            } else if (filter.equals("Emboss Filter 3")) {
                filterOption = 3;
            } else if (filter.equals("Emboss Filter 4")) {
                filterOption = 4;
            } else if (filter.equals("Emboss Filter 5")) {
                filterOption = 5;
            } else if (filter.equals("Emboss Filter 6")) {
                filterOption = 6;
            } else if (filter.equals("Emboss Filter 7")) {
                filterOption = 7;
            } else if (filter.equals("Emboss Filter 8")) {
                filterOption = 8;
            }

            // Create and apply the filter
            target.getImage().apply(new EmbossFilters(filterOption));
            target.repaint();
            target.getParent().revalidate();
        }
    }

    /**
     * <p>
     * Action to use an edge detection filter on an image
     * </p>
     * 
     * @see EdgeDetectFilters
     */
    public class EdgeDetectAction extends ImageAction {

        EdgeDetectAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {
            // Determine the radius - ask the user
            String filter = "Horizontal Edge Detection";
            int filterOption = 1;
            ArrayList<String> filters = new ArrayList<String>();
            filters.add("Horizontal Edge Detection");
            filters.add("Vertical Edge Detection");

            // Pop-up dialog box to ask for the filter option.

            SpinnerListModel filterModel = new SpinnerListModel(filters);
            JSpinner filterSpinner = new JSpinner(filterModel);
            // Make JSpinner non-editable.
            JFormattedTextField tf = ((JSpinner.DefaultEditor) filterSpinner.getEditor())
                    .getTextField();
            tf.setEditable(false);
            int option = JOptionPane.showOptionDialog(null, filterSpinner, "Please select a filter option.",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null,
                    null);

            // Check the return value from the dialog box.
            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                filter = filterModel.getValue().toString();
            }

            if (filter.equals("Horizontal Edge Detection")) {
                filterOption = 1;
            } else if (filter.equals("Vertical Edge Detection")) {
                filterOption = 2;
            }

            // Create and apply the filter
            target.getImage().apply(new EdgeDetectFilters(filterOption));
            target.repaint();
            target.getParent().revalidate();
        }
    }


    /**
     * <p>
     * Action to use a posterization filter on an image
     * </p>
     * 
     * @see PosterizeFilter
     */
    public class PosterizeFilterAction extends ImageAction {

        /**
         * <p>
         * Create a new Posterize filter action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */

        PosterizeFilterAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * This method is called whenever the PosterizeFilterAction is triggered.
         * 
         * {@link PosterizeFilter}.
         * </p>
         * 
         * @param e The event triggering this callback.
         */

        public void actionPerformed(ActionEvent e) {
            // Determine the radius - ask the user.
            int radius = 1;

            // Pop-up dialog box to ask for the radius value.
            SpinnerNumberModel radiusModel = new SpinnerNumberModel(2, 2, 10, 1);
            JSpinner radiusSpinner = new JSpinner(radiusModel);
            int option = JOptionPane.showOptionDialog(null, radiusSpinner, "Enter posterization level",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            // Check the return value from the dialog box.
            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                radius = radiusModel.getNumber().intValue();
            }

            // Create and apply the filter
            target.getImage().apply(new PosterizeFilter(radius));
            target.repaint();
            target.getParent().revalidate();
        }
    }
/**
     * <p>
     * Action to use a posterization filter on an image
     * </p>
     * 
     * @see PosterizeFilter
     */
    public class InvertFilterAction extends ImageAction {

        /**
         * <p>
         * Create a new Posterize filter action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */

        InvertFilterAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * This method is called whenever the PosterizeFilterAction is triggered.
         * 
         * {@link PosterizeFilter}.
         * </p>
         * 
         * @param e The event triggering this callback.
         */

        public void actionPerformed(ActionEvent e) {
           
            target.getImage().apply(new InvertFilter());
            target.repaint();
            target.getParent().revalidate();
        }
    }

}

