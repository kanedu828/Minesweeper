package minesweeper.game;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import minesweeper.gui.EndBox;

/**
 * This class represents the map for a minesweeper game.
 */
public class Map{
    private Random rand = new Random();
    private Square[][] map; //Should always be a square map.
    private final static boolean TESTING_MODE = false;//TESTING MODE, SHOWS BOMB LOCATIONS
    private Image bomb;
    private Image zero;
    private Image one;
    private Image two;
    private Image three;
    private Image four;
    private Image five;
    private Image six;
    private Image seven;
    private Image eight;
    private Image flag;
    private Image square;

    /**
     * This constructor creates an empty map
     */
    public Map(){
        map = new Square[0][0];
    }

    /**
     * This constructor creates a map of size r*c.
     * @param r Rows of the map
     * @param c Columns of the map
     */
    public Map(int r, int c){
        try {
            bomb = new Image(new FileInputStream("Minesweeper_Images/bomb.png"));
            zero = new Image(new FileInputStream("Minesweeper_Images/zero.png"));
            one = new Image(new FileInputStream("Minesweeper_Images/one.png"));
            two = new Image(new FileInputStream("Minesweeper_Images/two.png"));
            three = new Image(new FileInputStream("Minesweeper_Images/three.png"));
            four = new Image(new FileInputStream("Minesweeper_Images/four.png"));
            five = new Image(new FileInputStream("Minesweeper_Images/five.png"));
            six = new Image(new FileInputStream("Minesweeper_Images/six.png"));
            seven = new Image(new FileInputStream("Minesweeper_Images/seven.png"));
            eight = new Image(new FileInputStream("Minesweeper_Images/eight.png"));
            flag = new Image(new FileInputStream("Minesweeper_Images/flag.png"));
            square = new Image(new FileInputStream("Minesweeper_Images/square.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        map = new Square[r][c];
        for(int row = 0; row<map.length; row++){
            for(int col = 0; col<map[0].length; col++){
                map[row][col] = new Square();
                //map[row][col].setVisual(" ");
                int x = row;
                int y = col;
                map[row][col].setVisual(square);
                map[row][col].getSquareButton().setOnMouseClicked(e->{
                    if(e.getButton() == MouseButton.PRIMARY){
                        revealArea(x, y);
                    }
                    if(e.getButton() == MouseButton.SECONDARY){
                        flag(x, y);
                        if(win() == true){
                            System.out.println("WIN");
                            EndBox.end("Win", "YOU WIN!!!");
                            System.exit(0);
                        }
                    }
                });


            }

        }
    }

    /**
     * Returns the map.
     * @return the map.
     */
    public Square[][] getMap(){
        Square[][] mapCopy = new Square[map.length][map.length];
        for(int row = 0; row<map.length; row++){
            for(int col = 0; col<map[0].length; col++){
                mapCopy[row][col] = map[row][col];
            }
        }
        return mapCopy;
    }

    /**
     * Shows the map in string format. Method no longer functional.
     */
    public void mapToString() {
        for (Square[] x : map) {
            for (Square y : x) {
                System.out.print(y.getVisual() + " ");
            }
            System.out.println();
        }
    }

    /**
     * Places bombs based off of the difficulty.
     * @param difficulty the difficulty of the minesweeper game.
     */
    public void placeBombs(int difficulty){
        int randomRow;
        int randomCol;
        int maxBombs;
        if(difficulty == 1){
            maxBombs = 12;
            bombDifficulty(maxBombs);

        }
        if(difficulty == 2){
            maxBombs = 41;
            bombDifficulty(maxBombs);
        }
        if(difficulty == 3){
            maxBombs = 102;
            bombDifficulty(maxBombs);
        }

    }

    /**
     * Helper method for placeBombs(int difficulty).
     * @param maxBombs Amount of bombs in the map.
     */
    private void bombDifficulty(int maxBombs) {
        int randomRow;
        int randomCol;
        for(int bombAmount = 0; bombAmount < maxBombs;){
            randomRow = rand.nextInt(map.length-1);
            randomCol = rand.nextInt(map[0].length-1);
            if(map[randomRow][randomCol].getSafe() == false){
                //Do nothing. Checks if same row col is not chosen again
            }
            else {
                map[randomRow][randomCol].setSafe(false);
                if (TESTING_MODE) {
                    map[randomRow][randomCol].setVisual(bomb);

                }
                bombAmount++;
            }
        }
    }

    /**
     * Flags the map
     * @param row row to be flagged
     * @param col col to be flagged
     */
    public void flag(int row, int col){
        if(map[row][col].getFlagged()){
            map[row][col].setVisual(square);
            map[row][col].setFlagged(false);
        }
        else{
            if(!map[row][col].getFlagged() && !map[row][col].getRevealed()){
                map[row][col].setVisual(flag);
                map[row][col].setFlagged(true);
            }

        }
    }

    /**
     * Checks if the player lands on a bomb.
     * @param row row to be checked.
     * @param col col to be checked.
     * @return
     */
    private boolean isExplode(int row, int col){
        if(map[row][col].getSafe() == true){
            return false;
        }
        else{
            return true;
        }
    }

    /**
     * Helper method for revealArea(int row, int col).
     * @param row row to check for bombs.
     * @param col col to check for bombs.
     */
    private void detectBombs(int row, int col){
        int bombCount = 0; //Amount of bombs nearby
        //Check up
        if((row-1 >= 0) && row<map.length && col<map[0].length && col>=0 && map[row-1][col].getSafe() == false){
            bombCount++;
        }
        //Check down
        if((row+1 < map.length) && row>=0&& col<map[0].length && col>=0 && map[row+1][col].getSafe() == false){
            bombCount++;
        }
        //Check right
        if((col+1 < map[0].length) && col>=0 && row<map.length && row>=0 && map[row][col+1].getSafe() == false){
            bombCount++;
        }
        //Check left
        if((col-1 >= 0) && col<map[0].length && row<map.length && row>=0 && map[row][col-1].getSafe() == false){
            bombCount++;
        }
        //Check up right
        if((row-1 >= 0) && row<map.length && (col+1 < map[0].length) && col>=0 && map[row-1][col+1].getSafe() == false){
            bombCount++;
        }
        //Check up left
        if((row-1 >= 0) && row<map.length && (col-1 >= 0) && col<map[0].length&& map[row-1][col-1].getSafe() == false){
            bombCount++;
        }
        //Check down left
        if((row+1 < map.length) && row>=0 && (col-1 >= 0)&& col<map[0].length && map[row+1][col-1].getSafe() == false){
            bombCount++;
        }
        //check down right
        if((row+1 < map.length) && row>=0 && (col+1 < map[0].length)&& col>=0 && map[row+1][col+1].getSafe() == false){
            bombCount++;
        }
        if(row>=0 && col>=0 && row<map.length && col<map[0].length && !map[row][col].getVisual().equals("F") && map[row][col].getSafe() == true){
            if(bombCount==0){
                map[row][col].setVisual(zero);
            }
            if(bombCount==1){
                map[row][col].setVisual(one);
            }
            if(bombCount==2){
                map[row][col].setVisual(two);
            }
            if(bombCount==3){
                map[row][col].setVisual(three);
            }
            if(bombCount==4){
                map[row][col].setVisual(four);
            }
            if(bombCount==5){
                map[row][col].setVisual(five);
            }
            if(bombCount==6){
                map[row][col].setVisual(six);
            }
            if(bombCount==7){
                map[row][col].setVisual(seven);
            }
            if(bombCount==8){
                map[row][col].setVisual(eight);
            }
            map[row][col].setRevealed(true);
        }


        //return bombCount;


    }

    /**
     * Reveals a square and also reveals squares around squares with 0 bombs.
     * @param row row to be revealed
     * @param col col to be revealed
     */
    public void revealArea(int row, int col) {
        if(this.isExplode(row, col) == true){
            System.out.println("BOOOOM!\nYOU LOSE!");
            EndBox.end("Lose", "BOOOOM! YOU LOSE!!!");
            System.exit(0);
        }
        this.detectBombs(row, col);
        for(int i = 0; i<100; i++){//Searches for 0 over and over again to make sure no more 0's are unchecked.
            for (int r = 0; r<map.length; r++) {
                for (int c = 0; c<map[0].length; c++) {
                    if(map[r][c].getVisual().equals(zero)){
                        this.detectBombs(r - 1, c);
                        this.detectBombs(r + 1, c);
                        this.detectBombs(r, c + 1);
                        this.detectBombs(r, c - 1);
                        this.detectBombs(r - 1, c + 1);
                        this.detectBombs(r - 1, c - 1);
                        this.detectBombs(r + 1, c - 1);
                        this.detectBombs(r + 1, c + 1);
                    }
                }

            }
        }

    }

    /**
     * Checks if the player wins
     * @return true if the player wins, false otherwise.
     */
    public boolean win(){
        for(int row = 0; row<map.length; row++){
            for(int col = 0; col<map[0].length; col++){
                if(map[row][col].getSafe() == false && (map[row][col].getVisual().equals(square) || map[row][col].getVisual().equals(bomb))){
                    return false;
                }
                if(map[row][col].getSafe() == true && map[row][col].getVisual().equals(flag)){
                    return false;
                }
            }
        }
        return true;
    }

}