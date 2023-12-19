package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import javax.swing.*;

import java.awt.Color;
import java.awt.Cursor;

 /**
 * <p>
 * Actions provided by the Edit menu.
 * </p>
 * 
 * <p>
 * The Edit menu is very common across a wide range of applications.
 * There are a lot of operations that a user might expect to see here.
 * In the sample code there are Undo and Redo actions, but more may need to be added.
 * </p>
 * 
 * <p> 
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Steven Mills
 * @version 1.0
 */
public class DrawActions {
    
    /** A list of actions for the Edit menu. */
    protected ArrayList<Action> actions;

    /**
     * <p>
     * Create a set of Edit menu actions.
     * </p>
     */
    public DrawActions() {
        actions = new ArrayList<Action>();
        actions.add(new RectangleAction("Rectangle", null, "Rectangle", Integer.valueOf(KeyEvent.VK_Z)));
        actions.add(new OvalAction("Oval", null, "Oval", Integer.valueOf(KeyEvent.VK_Z)));
        actions.add(new LineAction("Line", null, "Line", Integer.valueOf(KeyEvent.VK_Z)));
        actions.add(new LeaveAction("Leave draw mode (ESC)", null, "Leave", KeyEvent.VK_Z));
        actions.add(new ChangeColourAction("Change colour", null, "Change colour", KeyEvent.VK_Z));
        
    }

    /**
     * <p>
     * Create a menu contianing the list of Edit actions.
     * </p>
     * 
     * @return The edit menu UI element.
     */
    public JMenu createMenu() {
        JMenu editMenu = new JMenu("Draw");

        for (Action action: actions) {
            editMenu.add(new JMenuItem(action));
        }

        return editMenu;
    }

    /**
     * <p>
     * Action to undo an {@link ImageOperation}.
     * </p>
     * 
     * @see EditableImage#undo()
     */
    public class RectangleAction extends ImageAction {

        /**
         * <p>
         * Create a new undo action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        RectangleAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the undo action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the UndoAction is triggered.
         * It undoes the most recently applied operation.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            java.awt.Color color = JColorChooser.showDialog(null, "Choose a background color", Color.white);
    if (color == null) return;
            ImagePanel.currentColor = color;
            ImagePanel.rectangleDrawMode = true;
            ImagePanel.regionSelectedStatic = false;
            ImagePanel.ovalDrawMode = false;
            ImagePanel.lineDrawMode = false;
            
        }

        
    }
    public class OvalAction extends ImageAction {

        /**
         * <p>
         * Create a new undo action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        OvalAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the undo action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the UndoAction is triggered.
         * It undoes the most recently applied operation.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            java.awt.Color color = JColorChooser.showDialog(null, "Choose a background color", Color.white);
    if (color == null) return;
            ImagePanel.currentColor = color;
            ImagePanel.rectangleDrawMode = false;
            ImagePanel.regionSelectedStatic = false;
            ImagePanel.ovalDrawMode = true;
            ImagePanel.lineDrawMode = false;
            
        }

    

        
}

public class LineAction extends ImageAction {

    /**
     * <p>
     * Create a new undo action.
     * </p>
     * 
     * @param name The name of the action (ignored if null).
     * @param icon An icon to use to represent the action (ignored if null).
     * @param desc A brief description of the action  (ignored if null).
     * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
     */
    LineAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
        super(name, icon, desc, mnemonic);
    }

    /**
     * <p>
     * Callback for when the undo action is triggered.
     * </p>
     * 
     * <p>
     * This method is called whenever the UndoAction is triggered.
     * It undoes the most recently applied operation.
     * </p>
     * 
     * @param e The event triggering this callback.
     */
    public void actionPerformed(ActionEvent e) {
        java.awt.Color color = JColorChooser.showDialog(null, "Choose a background color", Color.white);
    if (color == null) return;
            ImagePanel.currentColor = color;
        ImagePanel.rectangleDrawMode = false;
        ImagePanel.regionSelectedStatic = false;
        ImagePanel.ovalDrawMode = false;
        ImagePanel.lineDrawMode = true;
        
    }

    



}

public class LeaveAction extends ImageAction {

    /**
     * <p>
     * Create a new undo action.
     * </p>
     * 
     * @param name The name of the action (ignored if null).
     * @param icon An icon to use to represent the action (ignored if null).
     * @param desc A brief description of the action  (ignored if null).
     * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
     */
    LeaveAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
        super(name, icon, desc, mnemonic);
    }

    /**
     * <p>
     * Callback for when the undo action is triggered.
     * </p>
     * 
     * <p>
     * This method is called whenever the UndoAction is triggered.
     * It undoes the most recently applied operation.
     * </p>
     * 
     * @param e The event triggering this callback.
     */
    public void actionPerformed(ActionEvent e) {
        
        ImagePanel.rectangleDrawMode = false;
        ImagePanel.regionSelectedStatic = false;
        ImagePanel.ovalDrawMode = false;
        ImagePanel.lineDrawMode = false;
        
    }
}

public class ChangeColourAction extends ImageAction {

    /**
     * <p>
     * Create a new undo action.
     * </p>
     * 
     * @param name The name of the action (ignored if null).
     * @param icon An icon to use to represent the action (ignored if null).
     * @param desc A brief description of the action  (ignored if null).
     * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
     */
    ChangeColourAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
        super(name, icon, desc, mnemonic);
    }

    /**
     * <p>
     * Callback for when the undo action is triggered.
     * </p>
     * 
     * <p>
     * This method is called whenever the UndoAction is triggered.
     * It undoes the most recently applied operation.
     * </p>
     * 
     * @param e The event triggering this callback.
     */
    public void actionPerformed(ActionEvent e) {
        if(ImagePanel.regionSelectedStatic == true){
            JOptionPane.showMessageDialog(null, "Please select a draw mode", "No draw tool selected", 2);
        }else{
            java.awt.Color color = JColorChooser.showDialog(null, "Choose a background color", Color.white);
    if (color == null) return;
            ImagePanel.currentColor = color;
        }
        
        
    }
}
}

