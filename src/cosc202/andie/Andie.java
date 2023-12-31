package cosc202.andie;

import java.io.*;
import java.awt.*;
import javax.swing.*;
import javax.imageio.*;


/**
 * <p>
 * Main class for A Non-Destructive Image Editor (ANDIE).
 * </p>
 * 
 * <p>
 * This class is the entry point for the program.
 * It creates a Graphical User Interface (GUI) that provides access to various
 * image editing and processing operations.
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA
 * 4.0</a>
 * </p>
 * 
 * @author Steven Mills
 * @version 1.0
 *          Modified by the Otago Otters group
 */
public class Andie {

    /**
     * <p>
     * Launches the main GUI for the ANDIE program.
     * </p>
     * 
     * <p>
     * This method sets up an interface consisting of an active image (an
     * {@code ImagePanel})
     * and various menus which can be used to trigger operations to load, save,
     * edit, etc.
     * These operations are implemented {@link ImageOperation}s and triggerd via
     * {@code ImageAction}s grouped by their general purpose into menus.
     * </p>
     * 
     * @see ImagePanel
     * @see ImageAction
     * @see ImageOperation
     * @see FileActions
     * @see EditActions
     * @see ViewActions
     * @see FilterActions
     * @see ColourActions
     * 
     * @throws Exception if something goes wrong.
     */
    private static void createAndShowGUI() {
        // Set up the main GUI frame
        JFrame frame = new JFrame("ANDIE Image Editor");

        try {
            Image image = ImageIO.read(new File("./src/icon.png"));
            frame.setIconImage(image);
        } catch (IOException e) {
            System.out.println("Icon not found");
        }

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // The Toolbar panel
        Toolbar toolbar = new Toolbar();
        toolbar.createToolBar();

        // The main content area is an ImagePanel
        ImagePanel imagePanel = new ImagePanel();
        ImageAction.setTarget(imagePanel);
        JScrollPane scrollPane = new JScrollPane(imagePanel);
        // frame.add(scrollPane, BorderLayout.CENTER);

        // Populates main frame
        // delete this line
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(toolbar.getToolbar(), BorderLayout.NORTH);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        // Add in menus for various types of action the user may perform.
        JMenuBar menuBar = new JMenuBar();

        // File menus are pretty standard, so things that usually go in File menus go
        // here.
        FileActions fileActions = new FileActions();
        menuBar.add(fileActions.createMenu());

        // Likewise Edit menus are very common, so should be clear what might go here.
        EditActions editActions = new EditActions();
        menuBar.add(editActions.createMenu());

        // View actions control how the image is displayed, but do not alter its actual
        // content
        ViewActions viewActions = new ViewActions();
        menuBar.add(viewActions.createMenu());

        // Filters apply a per-pixel operation to the image, generally based on a local
        // window
        FilterActions filterActions = new FilterActions();
        menuBar.add(filterActions.createMenu());

        // Actions that affect the representation of colour in the image
        ColourActions colourActions = new ColourActions();
        menuBar.add(colourActions.createMenu());

        // Group to house all the image adjustment actions.
        AdjustActions adjustActions = new AdjustActions();
        menuBar.add(adjustActions.createMenu());

        DrawActions drawActions = new DrawActions();
        menuBar.add(drawActions.createMenu());

        frame.setJMenuBar(menuBar);
        frame.pack();
        frame.setVisible(true);

        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (FileActions.unSavedChanges == true) {
                    frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                    if (JOptionPane.showConfirmDialog(frame,
                            "Are you sure you want to close this window? Any unsaved changes will be lost",
                            "Close Window?",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                        System.exit(0);
                    }
                } else {
                    System.exit(1);
                }

            }
        });
    }

    /**
     * <p>
     * Main entry point to the ANDIE program.
     * </p>
     * 
     * <p>
     * Creates and launches the main GUI in a separate thread.
     * As a result, this is essentially a wrapper around {@code createAndShowGUI()}.
     * </p>
     * 
     * @param args Command line arguments, not currently used
     * @see #createAndShowGUI()
     */
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    createAndShowGUI();
                } catch (NullPointerException ex) {
                    JOptionPane.showMessageDialog(null, "Error starting program", "Try again later", 2);

                }
            }
        });
    }
}
