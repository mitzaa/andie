package cosc202.andie;
import java.awt.image.*;


public class BrightnessContrast implements ImageOperation, java.io.Serializable {
    private int br;
    private int co;

    /**                 
 * <p>
 * Constructor to apply a brightness contrast filter.
 *
 * @param br gives the value of brightness      (3)
 * @param co gives the value of contrast
 */

    public BrightnessContrast(int br, int co){
        if (br > 100 || br < -100){
            this.br = 0;
        }else{
            this.br = br;
        }

        if (co > 100 || co < -100){
            this.co = 0;
        }else{
            this.co = br;
        }
        
        this.co = co;
        
    }

                      
 

    public int getBrightness(){
        return br;
    }

    public int getContrast(){
        return br;
    }
    public static void main(String[] args) {
       System.out.println(returnNewValue(212, 25, 25));
    }



      /**                 
 * <p>
 * Method to apply the brightness contrast filter to image input. Adjusts each individual pixel to formula used in returnNewValue();
 *
 * @param input takes a buffered image      (3)
 * @return input returns the altered image
 */
    public BufferedImage apply(BufferedImage input){
        for (int y = 0; y < input.getHeight(); y++) {
            for (int x = 0; x < input.getWidth(); x++) {
                int p = input.getRGB(x,y);
                
                //get alpha
                int a = (p>>24) & 0xff;
            
                //get red
                int r = (p>>16) & 0xff;
            
                //get green
                int g = (p>>8) & 0xff;
            
                //get blue
                int b = p & 0xff;
                //System.out.println("Before: " + r  + " " + g  + " " + b);
                a = 255;
                
                int rr = returnNewValue(r, br, co);
                if (rr > 255){
                    rr = 255;
                }
                if(rr < 0){
                    rr = 0;
                }

                int gg = returnNewValue(g, br, co);
                if (gg > 255){
                    gg = 255;
                }
                if(gg < 0){
                    gg = 0;
                }

                int bb = returnNewValue(b, br, co);
                if (bb > 255){
                    bb = 255;
                }
                if(bb < 0){
                    bb = 0;
                }

                //System.out.println("After" + rr  + " " + gg  + " " + bb);
                
                
            
                //set the pixel value
                p = (a<<24) | (rr<<16) | (gg<<8) | bb;
                input.setRGB(x, y, p);
            
            }
        }
        
        return input;


    }


      /**                 
 * <p>
 * Creates a value based off a rgb value and a brightness and contrast value to apply the filter to said pixel
 *
 * @param x is starting pixel      (3)
 * @param b brightness value
 * @param c contrast value
 * @return result of the equation
 */

    public static int returnNewValue(int x, double b, double c){ 
        int result = (int) Math.round((1 + c/100) * (x-127.5) + 127.5 * (1 + b/100));
        return result;

    }

    

   
}
