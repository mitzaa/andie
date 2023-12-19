package cosc202.andie;

import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.synth.SynthSplitPaneUI;

import java.awt.event.*;
import java.awt.event.MouseEvent;


/**
 * <p>
 * UI display element for {@link EditableImage}s.
 * </p>
 * 
 * <p>
 * This class extends {@link JPanel} to allow for rendering of an image, as well as zooming
 * in and out. 
 * </p>
 * 
 * <p> 
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Steven Mills
 * @version 1.0
 */
public class ImagePanel extends JPanel implements MouseMotionListener, MouseListener, KeyListener {
    
    /**
     * The image to display in the ImagePanel.
     */
    private EditableImage image;
    int mostRecentClickX; 
    int mostRecentClickY; 
    int firstClickX;
    int firstClickY;
    static Region currentRegion = new Region(0,0,0,0);
   
    static boolean regionSelectedStatic = true;
    static boolean rectangleDrawMode = false;
    static boolean ovalDrawMode = false;
    static boolean lineDrawMode = false;
    static Color currentColor;
    static int operationsToUndo;
    static int numberOfLines;
    
    
    

    /**
     * <p>
     * The zoom-level of the current view.
     * A scale of 1.0 represents actual size; 0.5 is zoomed out to half size; 1.5 is zoomed in to one-and-a-half size; and so forth.
     * </p>
     * 
     * <p>
     * Note that the scale is internally represented as a multiplier, but externally as a percentage.
     * </p>
     */
    private double scale;

    /**
     * <p>
     * Create a new ImagePanel.
     * </p>
     * 
     * <p>
     * Newly created ImagePanels have a default zoom level of 100%
     * </p>
     */
    public ImagePanel() {
        image = new EditableImage();
        scale = 1.0;
        addMouseListener(this);
        addMouseMotionListener(this);
        addKeyListener(this);
        
    }

    /**
     * <p>
     * Get the currently displayed image
     * </p>
     *
     * @return the image currently displayed.
     */
    public EditableImage getImage() {
        return image;
    }

    /**
     * <p>
     * Get the current zoom level as a percentage.
     * </p>
     * 
     * <p>
     * The percentage zoom is used for the external interface, where 100% is the original size, 50% is half-size, etc. 
     * </p>
     * @return The current zoom level as a percentage.
     */
    public double getZoom() {
        return 100*scale;
    }

    /**
     * <p>
     * Set the current zoom level as a percentage.
     * </p>
     * 
     * <p>
     * The percentage zoom is used for the external interface, where 100% is the original size, 50% is half-size, etc. 
     * The zoom level is restricted to the range [50, 200].
     * </p>
     * @param zoomPercent The new zoom level as a percentage.
     */
    public void setZoom(double zoomPercent) {
        if (zoomPercent < 50) {
            zoomPercent = 50;
        }
        if (zoomPercent > 200) {
            zoomPercent = 200;
        }
        scale = zoomPercent / 100;
    }


    /**
     * <p>
     * Gets the preferred size of this component for UI layout.
     * </p>
     * 
     * <p>
     * The preferred size is the size of the image (scaled by zoom level), or a default size if no image is present.
     * </p>
     * 
     * @return The preferred size of this component.
     */
    @Override
    public Dimension getPreferredSize() {
        if (image.hasImage()) {
            return new Dimension((int) Math.round(image.getCurrentImage().getWidth()*scale), 
                                 (int) Math.round(image.getCurrentImage().getHeight()*scale));
        } else {
            return new Dimension(450, 450);
        }
    }

    public static Region getCurrentSelection(){
        return currentRegion;
    }

    


    public void mouseClicked(MouseEvent e) {
        


    } 
    
    public void mouseMoved(MouseEvent e){

    }

    public void mouseDragged(MouseEvent e){

        mostRecentClickX = e.getX();
        mostRecentClickY = e.getY(); 
        currentRegion = new Region(firstClickX, firstClickY, mostRecentClickX - firstClickX, mostRecentClickY- firstClickY);
        if(rectangleDrawMode == true || ovalDrawMode == true){
            
            drawShape();
            Cursor cursor = new Cursor(Cursor.CROSSHAIR_CURSOR);
            setCursor(cursor);
            operationsToUndo++;
            
            
        }else if(lineDrawMode == true){
            if(numberOfLines > 0){
                image.undo();
            }
            drawShape();
            Cursor cursor = new Cursor(Cursor.CROSSHAIR_CURSOR);
            setCursor(cursor);
            numberOfLines++;
        }
        
        else {
            drawHighlight();
        }
        
        
        

    }
    public void mouseEntered(MouseEvent e) {  
        
    }  
    public void mouseExited(MouseEvent e) {  
        
    }  
    public void mousePressed(MouseEvent e) {  
        firstClickX = e.getX();
        firstClickY = e.getY(); 
        this.grabFocus();
        operationsToUndo = 0;
        numberOfLines = 0;
    }  
    public void mouseReleased(MouseEvent e) {  
        mostRecentClickX = e.getX();
        mostRecentClickY = e.getY(); 
        //if(mostRecentClickX - firstClickX > 15 && mostRecentClickY- firstClickY )
        if(lineDrawMode == true){
            image.undo();
        }
        if(operationsToUndo > 0){
            for(int i = 0; i < operationsToUndo; i++){
                image.undo();
            }
        }
        
        currentRegion = new Region(firstClickX, firstClickY, mostRecentClickX - firstClickX, mostRecentClickY- firstClickY);
        if(regionSelectedStatic == true){
            drawHighlight();
        }

        else {
            drawShape();
        }
        
        
         
    }  

    
    public void drawHighlight(){
        
        regionSelectedStatic = true;
        repaint();
    }

    public void drawShape(){
        if(rectangleDrawMode == true){
            RectangleAction draw = new RectangleAction(currentRegion, currentColor);
            image.apply(draw);
            this.getParent().revalidate();
            repaint();
        }if(ovalDrawMode == true){
            OvalAction draw = new OvalAction(currentRegion, currentColor);
            image.apply(draw);
            this.getParent().revalidate();
            repaint();
        }if(lineDrawMode == true){
            LineAction draw = new LineAction(firstClickX, firstClickY, mostRecentClickX, mostRecentClickY, currentColor, currentRegion);
            image.apply(draw);
            this.getParent().revalidate();
            repaint();
        }
        
        
    }

    


    public void removeHighlight(){
        
        regionSelectedStatic = false;
        repaint();

    }

    
    

   

    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
            removeHighlight();
            rectangleDrawMode = false;
            ovalDrawMode = false;
            lineDrawMode = false;
            
        }
        System.out.println("KEY PRESSED");
        
    }

    public void keyReleased(KeyEvent e){
        
    }

    public void keyTyped(KeyEvent e){
        
    }

    

    
    
    /**
     * <p>
     * (Re)draw the component in the GUI.
     * </p>
     * 
     * @param g The Graphics component to draw the image on.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image.hasImage()) {
            Graphics2D g2  = (Graphics2D) g.create();
            g2.scale(scale, scale);
            g2.drawImage(image.getCurrentImage(), null, 0, 0);
            if(regionSelectedStatic == true){
                Cursor cursor = new Cursor(Cursor.DEFAULT_CURSOR);
                setCursor(cursor);
                 g2.setColor(Color.GRAY);
                g2.drawRect(currentRegion.x, currentRegion.y, currentRegion.width, currentRegion.height);
            }
            
            
        
        }
        

        
        
    }

    
}
