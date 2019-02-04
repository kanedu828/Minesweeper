package minesweeper.game; /***************************************************************************
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
    private boolean flagged;
    private boolean revealed;
    private String visual;
    private Button squareButton;

    public Square(){
        safe = true;
        visual = " ";
        flagged = false;
        revealed = false;
        squareButton = new Button();
        squareButton.setMaxSize(50, 50);
        squareButton.setMinSize(50, 50);
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
    public boolean getFlagged(){
        return flagged;
    }
    public void setFlagged(boolean flagged){
        this.flagged = flagged;
    }


    public boolean getRevealed() {
        return revealed;
    }

    public void setRevealed(boolean revealed) {
        this.revealed = revealed;
    }


}
