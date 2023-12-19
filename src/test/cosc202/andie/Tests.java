package test.cosc202.andie;

import org.junit.jupiter.api.Test;

import cosc202.andie.BrightnessContrast;
import cosc202.andie.GaussianFilter;
import cosc202.andie.ResizeMinus;
import cosc202.andie.ResizePlus;

import org.junit.jupiter.api.*;

public class Tests {
    @Test
    public void BCLowerBoundsTest() {
        BrightnessContrast br = new BrightnessContrast(-101, -101);
        Assertions.assertEquals(0, br.getContrast());
        Assertions.assertEquals(0, br.getBrightness());
        

    }

    @Test
    public void BCUpperBoundsTest(){
        BrightnessContrast br = new BrightnessContrast(101, 101);
        Assertions.assertEquals(0, br.getContrast());
        Assertions.assertEquals(0, br.getBrightness());
    }

   
    @Test
    public void GFDefaultRadiusTest() {
        GaussianFilter gf = new GaussianFilter();
        Assertions.assertEquals(1, gf.getRadius());
    }

    @Test
    public void RMinusWidthHeightTest() {
        ResizeMinus rm = new ResizeMinus();
        int testWidth = 100;
        int testHeight = 100;
        Assertions.assertTrue(rm.getScale() * testWidth == 50);
        Assertions.assertTrue(rm.getScale() * testHeight == 50);
    }

    @Test
    public void RPlusWidthHeightTest() {
        ResizePlus rm = new ResizePlus();
        int testWidth = 100;
        int testHeight = 100;
        Assertions.assertTrue(rm.getScale() * testWidth == 150);
        Assertions.assertTrue(rm.getScale() * testHeight == 150);
    }
}
