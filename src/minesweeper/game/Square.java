package minesweeper.game;
import java.util.Random;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * This class represents a square of a minesweeper game.
 */
public class Square{
    private Random rand = new Random();
    private boolean safe;
    private boolean flagged;
    private boolean revealed;
    private ImageView visual;
    private Button squareButton;

    /**
     * This constructor initializes a map with no bombs.
     */
    public Square(){
        safe = true;
        visual = new ImageView();
        visual.setFitWidth(75);
        visual.setFitHeight(75);
        flagged = false;
        revealed = false;
        squareButton = new Button();
        squareButton.setMaxSize(75, 75);
        squareButton.setMinSize(75, 75);
    }

    /**
     * Sets the square visual
     * @param visual the square visual
     */
    public void setVisual(Image visual){
        this.visual.setImage(visual);
        squareButton.setGraphic(this.visual);
    }

    /**
     * Returns the squareButton
     * @return the squareButton
     */
    public Button getSquareButton(){
        return squareButton;
    }

    /**
     * Returns the visual
     * @return the visual
     */
    public Image getVisual(){
        return visual.getImage();
    }

    /**
     * Sets whether or not the square is safe.
     * @param safe whether or not the square is safe.
     */
    public void setSafe(boolean safe){
        this.safe = safe;
    }

    /**
     * Returns whether or not the square is safe.
     * @return true if the square is safe, false otherwise.
     */
    public boolean getSafe(){
        return safe;
    }

    /**
     * Returns whether or not the square is flagged.
     * @return True if the square is flagged, false otherwise.
     */
    public boolean getFlagged(){
        return flagged;
    }

    /**
     * Sets whether the square is flagged.
     * @param flagged whether or not the square is flagged.
     */
    public void setFlagged(boolean flagged){
        this.flagged = flagged;
    }

    /**
     * Returns whether or not the square is revealed.
     * @return true if the square is revealed, false otherwise.
     */
    public boolean getRevealed() {
        return revealed;
    }

    /**
     * Sets whether or not if the square is revealed.
     * @param revealed whether or not if the square is revealed.
     */
    public void setRevealed(boolean revealed) {
        this.revealed = revealed;
    }


}
