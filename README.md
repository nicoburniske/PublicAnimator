# EnhancedAnimator

1. Pause, Play, Rewind, Restart, Speed Up, Slow Down.

These alter the tick of the animation.

2. Delete Shape

By selecting a Shape ID on the drop-down menu (right below the slow down button) the user is able to then click the 'delete shape' button in order to effectively delete the shape from the animation.

3. Add Shape

If the user were to type at the bottom of the sidebar panel (where 'new id' is listed) an id for a new shape to be added, they would then be able to select what shape they'd want to draw (either ellipse or rectangle) from the dropdown menu right above the add shape button. After doing so the user would be able to add a shape of the selected type with the given ID to the animation by clicking on the 'add shape' button.

4. Add KeyFrame

To add a keyframe the user must fill in each of the specified text boxes with the properties of the shape that are desired at the specified tick. Based on the selected shape from the dropdown menu, pressing the ‘Add Keyframe’ button will add the new keyframe to the shape.

5. Delete KeyFrame

Right below the 'Delete Key Frame' button there is a drop-down menu with the all of the existing keyframes of the SELECTED SHAPE. They can select the keyframe of their liking and remove it from the animation by clicking in the 'Delete Key Frame' button. This alteration is represented by (a) the visual animation, (b) the textual output, as well as (c) the svg output. The latter two will be explained below.
In order to delete a keyframe from another shape, the user can go to the uppermost drop-down menu (same one used for Delete Shape) and select another shape ID. 

6. Read new file (Extra Credit Functionality)

Underneath the 'Read New File' Button there exists a text field where the user can enter a new file path that will update the given animation to be the new animation passed in as a file. 

7. Save File (Extra Credit Functionality)

Under the 'Save File' button the user can select from a drop-down menu the type of file they would like to save (either SVG or TXT). The user must also input a file path where the file should be saved. It is important to note that the last part of the filepath must mention a file of the appropriate selection. After doing so the user can click on the Save File button and the file will be saved in the specified location.

Example: C:/Users/me/desktop/animation.txt  OR  C:/Users/me/desktop/animation.SVG

8. Slider

Use the bottom slider to traverse through the animation.

#Screenshots and examples

![alt text](https://github.com/nicoburniske/PublicAnimator/blob/master/examples/EditorView.PNG)
![alt text](https://github.com/nicoburniske/PublicAnimator/blob/master/examples/SVG%20Output/hanoi.svg)

RUNNING THE PROGRAM

You should use one of the supplied text files in the examples directory. You should then choose the view of your choice (edit, visual, svg, text, provider). Specify an output directory if you choose either text or svg. 

The user input as program arguments should follow the following template 

-view (your selected view)
-in (your selected file path) 
-out (your selected file path)

Sample Main Program Arguments

-view edit -speed 200 -in "...\PublicAnimator\examples\buildings.txt"

-view svg -speed 200 -in "...\PublicAnimator\examples\hanoi.txt" -out "hanoi.svg"


	
