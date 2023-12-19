package cosc202.andie;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Toolbar extends JPanel {

    protected JToolBar toolBar = new JToolBar();
    protected JTextArea textArea;
    protected String newline = "\n";
    protected Action hFlip, vFlip, rLeft, rRight, zIn, zOut, cropSelection;

    public Toolbar() {

        // Sets size of bar
        // delete this line
        setPreferredSize(new Dimension(600, 50));

        // Create the actions shared by the toolbar and menu.
        hFlip = new hFlip("Vertical Flip", createNavigationIcon("flipV"), "Flips image along x axis.",
                Integer.valueOf(KeyEvent.VK_L));
        vFlip = new vFlip("Horizontal Flip", createNavigationIcon("flipH"), "Flips image along y axis.",
                Integer.valueOf(KeyEvent.VK_M));
        rLeft = new rLeft("rotate left 90°", createNavigationIcon("rotateLeft"), "Rotates image left 90°.",
                Integer.valueOf(KeyEvent.VK_R));
        rRight = new rRight("rotate right 90°", createNavigationIcon("rotateRight"), "Rotates image right 90°.",
                Integer.valueOf(KeyEvent.VK_R));
        zIn = new zIn("Zoom in", createNavigationIcon("zoomIn"), "Zooms in.",
                Integer.valueOf(KeyEvent.VK_R));
        zOut = new zOut("Zoom out", createNavigationIcon("zoomOut"), "Zooms out.",
                Integer.valueOf(KeyEvent.VK_R));
        cropSelection = new cropSelection("Crop Selection", createNavigationIcon("cropSelection"), "Crops selection.",
                Integer.valueOf(KeyEvent.VK_R));
    }

    /** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createNavigationIcon(String imageName) {
        String imgLocation = "./src/toolbarButtonGraphics/" + imageName + ".png";

        return new ImageIcon(imgLocation);
    }

    public void createToolBar() {
        JButton button = null;

        // Create the toolbar.
        add(toolBar, BorderLayout.NORTH);

        // first button
        button = new JButton(hFlip);
        if (button.getIcon() != null) {
            button.setText(""); // an icon-only button
        }
        toolBar.add(button);

        // second button
        button = new JButton(vFlip);
        if (button.getIcon() != null) {
            button.setText(""); // an icon-only button
        }
        toolBar.add(button);

        // third button
        button = new JButton(rLeft);
        if (button.getIcon() != null) {
            button.setText(""); // an icon-only button
        }
        toolBar.add(button);

        // fourth button
        button = new JButton(rRight);
        if (button.getIcon() != null) {
            button.setText(""); // an icon-only button
        }
        toolBar.add(button);

        // fifth button
        button = new JButton(zIn);
        if (button.getIcon() != null) {
            button.setText(""); // an icon-only button
        }
        toolBar.add(button);

        // sixth button
        button = new JButton(zOut);
        if (button.getIcon() != null) {
            button.setText(""); // an icon-only button
        }
        toolBar.add(button);

        button = new JButton(cropSelection);
        if (button.getIcon() != null) {
            button.setText(""); // an icon-only button
        }
        toolBar.add(button);
    }

    public JToolBar getToolbar() {
        return toolBar;
    }

    /*
     * public void displayResult(String actionDescription, ActionEvent e) {
     * String s = ("Action event detected: " + actionDescription + newline +
     * "    Event source: " + e.getSource()
     * + newline);
     * textArea.append(s);
     * }
     */

    public class hFlip extends ImageAction {
        hFlip(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {
            target.getImage().apply(new FlipHorizontal());
            target.repaint();
            target.getParent().revalidate();
        }
    }

    public class vFlip extends ImageAction {
        vFlip(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {
            target.getImage().apply(new FlipVertical());
            target.repaint();
            target.getParent().revalidate();
        }
    }

    public class rLeft extends ImageAction {
        rLeft(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {
            target.getImage().apply(new RotateLeft());
            target.repaint();
            target.getParent().revalidate();
        }
    }

    public class rRight extends ImageAction {
        rRight(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {
            target.getImage().apply(new RotateRight());
            target.repaint();
            target.getParent().revalidate();
        }
    }

    public class zIn extends ImageAction {
        public zIn(String text, ImageIcon icon,
                String desc, Integer mnemonic) {
            super(text, icon, desc, mnemonic);
            putValue(SHORT_DESCRIPTION, desc);
            putValue(MNEMONIC_KEY, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {
            target.setZoom(target.getZoom() + 10);
            target.repaint();
            target.getParent().revalidate();
        }
    }

    public class zOut extends ImageAction {
        public zOut(String text, ImageIcon icon,
                String desc, Integer mnemonic) {
            super(text, icon, desc, mnemonic);
            putValue(SHORT_DESCRIPTION, desc);
            putValue(MNEMONIC_KEY, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {
            target.setZoom(target.getZoom() - 10);
            target.repaint();
            target.getParent().revalidate();
        }
    }

    public class cropSelection extends ImageAction {
        public cropSelection(String text, ImageIcon icon,
                String desc, Integer mnemonic) {
            super(text, icon, desc, mnemonic);
            putValue(SHORT_DESCRIPTION, desc);
            putValue(MNEMONIC_KEY, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {
            if(ImagePanel.regionSelectedStatic == true){
                target.getImage().apply(new Crop(ImagePanel.getCurrentSelection()));
                ImagePanel.regionSelectedStatic = false;
                target.repaint();
                target.getParent().revalidate();
            
            }
            
            
        }
    }


    /**
     * <p>
     * (Re)draw the component in the GUI.
     * </p>
     * 
     * @param g The Graphics component to draw the image on.
     */
    /*
     * @Override
     * public void paintComponent(Graphics g) {
     * super.paintComponent(g);
     * }
     */

}