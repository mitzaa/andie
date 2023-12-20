**Andie Image Editor**



Andie Image Editor is a lightweight, non-descructable code Editor. It is focused on Image manipulation through Filters, Colourscales and changing orientation/size.

---

*Gaussian Filter* 
- Accessed via: Filter menu (Gaussian blur Filter option)
- Tested on large and small images. Formal test done to ensure default radius is set to 1. Other testing was done on different radius sizes by printing the kernals array and cross checking it with examples in the lab-book to ensure values were correctly calculated (Ouput image was also cross-checked with images provided in lab book).
- No know issues.

*Brightness Filter* 
- Accessed via: Colour menu (Brighness option)
- Tested with ranges of brightness percentage from -100 - 100. Satisfies the edge cases of working at these inclusive values (100, -100). But doesn't apply at over 101, -101. Formula was thoroughly tested throughout development to ensure that it returned correct values. Formal testing is done on this with BCUpperbounds and BClowerbounds testing using the Junit framework to ensure that the value becomes zero past the edge cases. Also tested functionality against sample image in COSC202 lab book with the same image at the same values.
- No known  issues

*Contrast Filter* 
- Accessed via: Colour menu (Contrast option)
- Tested with ranges of contrast percentage from -100 - 100. Satisfies the edge cases of working at these inclusive values (100, -100). But doesn't apply at over 101, -101. Formal testing is done on this with BCUpperbounds and BClowerbounds testing using the Junit framework to ensure that the value becomes zero past the edge cases. Also tested functionality against sample image in COSC202 lab book with the same image at the same values. Formula was tested in unison with brightness filter as it shares the same formula.
- No known  issues

*Sharpen Filter* 
- Accessed via: Filter menu (Sharpen option)
- No formal testing was needed here as there is no input from the user to test different cases of. Testing was done using array based implementations of colonels to make sure that the formula correctly worked. Eye tested against sample image in 202 lab book to ensure that it is doing the correct kernel operation.

*Median Filter* 
- Accessed via: Filter menu (Median Option)
- Oliver and Andrew are writing this on James behalf so am unsure what tests where done along the way. But can assume the arrays for the RGB values would have been printed to check the output is correct as well as cross checking the output with a known working median filter to ensure correctness.
- No known issues.

---

*Resize Functions* (Andrew Booth)
- Accessed via: Adjust Image menu (Resize 50% or Resize 150% option)
- Tested on already resized images to see if output images compare. Formal tests were done to ensure the correct height and width were calculated for a test image using the correct scale for increasing or decreasing the size of the image.
- No known issues.

*Flip Functions* 
- Accessed via: Adjust Image menu (Flip Vertically or Flip Horizontally)
- Tested using tall and wide images of varying sizes to ensure the size of the image is not altered. No formal tests done. Images were flipped using other software so that I could compare results to ensure correctness.
- No known issues.

*Rotate Functions* 
- Accessed via: Adjust Image menu (Rotate Left 90° or Rotate Right 90° or Rotate 180°)
- Tested using both very large and very small images. No formal tests done. When rotate left or right was used on a non-square image code was in place to ensure the image does not get moved in the editor so testing of different methods to implement this was used as the image could get moved away from the edge.
- No known issues.

*Export Functions* 
- Accessed via: File menu (Export option)
- Tested on png and jpg images with and without changes made. No formal tests done but many image conversions were tested on both MacOS and WindowsOS.
- Issues with consistency of converting file types when changes were made on differing operating systems. If no file format was used in the file name e.g. jpg or png then image would not export.

---

*Error handling* 
- NullPointerException: Opening a non-image file. This error is caught and user is prompted to choose an image file.
- NullPointerException: Trying to save/save as/export/apply an operation on a file when nothing is opened. This error is caught and user is told to select an image
- EmptyStackException: Trying to undo/redo when there is nothing to undo/redo. User is prompted that there is nothing to undo/redo.

*Other avoidance* 
- Exiting without saving image: When image isn't saved and exit is clicked, user is prompted to save image or continue on without saving. Hopefully will prevent loss of work in the event of a missclick or forgetting to save.
- Opening another image without saving current image: Similar to above, simply prevents loss of work by prompting the user to save or continue without saving.

*Toolbar* 
- Accessed underneath the menu bar and can be dragged around the screen to suit the users preference.
- The buttons to be added to the toolbar where discussed within the members of the group and came to an understanding of some useful image operations that we would want quick access too such as zoom in and out and rotate etc. With James' going through some difficult circumstances he did the majority of the code but could not quite figure out how to implement an action when a button is pressed. Oliver and I have merged what he had and managed to get this feature working as well as adding Images to the buttons.


*Mouse based region selection* 
- Automatically able to select a portion of an image upon opening an image, simply drag-click-release to create a rectangle outline. To deselect press escape key. I figured this was rather intuitive for the vast majority of users so didn't feel need to disclose. 
- Tested in the case where it shouldn't be allowed, eg when no image is open. Simply does nothing as it should.
- Issues come with when the image is zoomed in it makes the crop appear off target, still works somewhat but looks strange and is not ideal.

*Cropping tool* 
- When a region is selected, click crop icon on toolbar to crop image.
- Tested in the case where no region is selected, Catches RasterFormatException from where it's trying to crop a region that doesn't exist. Undo and redo methods work.
- No known issues


*Draw Mode* (Oliver Bisley)
- Access the draw mode through the draw menu in the menu bar. User selects shape they want to draw between elipse, Square, and line. User will be prompted with a popup menu to select colour. To leave draw mode press escape or leave through dropdown menu under draw line option. To change colour use option in dropdown menu.
- Tested in same cases as mouse based region selection.
- Minor issue in drawing animation with drawing elipse, top left corner looks strange until mouse is released. Does not effect  usability


*Extened Filters* 
- A convolution file was created to manually apply the convolution for each filter (replaces convolveOp methods etc.). Many tests were done to ensure right pixels were being gathered as detecting out of bounds pixels had many iterations of 'if statements' to come to the current implemented one. The median filter was also completely overhauled to share the same layout as the Convolution.java file as they had to work similarly but did not require a kernal as the convolution file required. The work can be seen when a filter is called upon an image (in the Filters tab of the drop down menu) and no black border is present (should be noted that with larger radii applied to filters may take a long time to display the change).

*Filters With Negative Results* 
- In the convolution file a boolean value is passed into the extendedConvolution method to know if the filter needs an offset applied to it. When this is applied the value 127 is added to each raw rgb value as a result of the filter. The edge detection and emboss filters use this to show the result of applying certian filters correctly by giving the negative values meaning rather than just defaulting them back to 0 or 255 if the are greater than such.

*Emboss Filters* 
- Accessed via:  Filter menu (Emboss filters option)
- The user can select from 8 preset kernels to apply to an image. Tests were done to ensure the expected kernel is applied when selected from the flter selection menu. The offset as described above is aplied for these filter by adding 127 to the raw results of the rgb value for each pixel when a filter is applied.

*Edge Detection Filters* 
- Accessed via: Filter menu (Edge detection filters option)
- The user can also select from a filter selection menu, two preset options are given. One option is a horizontal edge dectection and the other is vertical edge detection. Works in a similar way as to the emboss filters as an offset is applied to account for negative rgb values.

*Keyboard Shortcuts* 
- Added to most commonly used features such as open file and undo/redo

*Posterize filter* 
- Accessed via: Filter menu
- Tested with different sized images and different colours
- No known issues

*Invert filter*
- Accessed via: Filter menu
- Tested with different sized images and different colours
- No known issues


