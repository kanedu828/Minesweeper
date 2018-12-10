/***************************************************************************
*Square.java
*
*Represents each square of a minesweeper map.
*
*
*
*
*
****************************************************************************/




import java.util.Random;
import javafx.scene.control.Button;
public class Square{
    Random rand = new Random();
    private boolean safe;
    private String visual;
    private Button squareButton;

    public Square(){
        safe = true;
        visual = " ";
        squareButton = new Button();
        squareButton.setText(visual);
    }
    public void setVisual(String visual){
        this.visual = visual;
        squareButton.setText(visual);
    }

    public Button getSquareButton(){
        return squareButton;
    }

    public String getVisual(){
        return visual;
    }

    public void setSafe(boolean safe){
        this.safe = safe;
    }

    public boolean getSafe(){
        return safe;
    }



    
        
}






