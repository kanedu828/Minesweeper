package minesweeper.game; /***************************************************************************
 *Map.java
 *
 *Represents entire minesweeper map that is used to play the game.
 *
 *Map(int r, int c) - Sets up map.
 *mapToString()- Shows the map visually
 *placeBombs()- Places bombs randomly on map
 *flag(int row, int col)- Allows user to mark bombs
 *revealArea(int row, int col)- Reveals the area that player selects.
 *win()- Checks if bombs are correctly marked.
 ****************************************************************************/
import java.util.Random;

import javafx.scene.input.MouseButton;
import minesweeper.gui.endBox;

public class Map{
    private Random rand = new Random();
    private Square[][] map; //Should always be a square map.
    private final static boolean TESTING_MODE = true;//TESTING MODE, SHOWS BOMB LOCATIONS

    //-------------------------------------------------
    //Map()- Creates empty map
    //-------------------------------------------------
    public Map(){
        map = new Square[0][0];
    }
    //-------------------------------------------------
    //Map(int r, int c)- Creates map of row r and row c
    //-------------------------------------------------
    public Map(int r, int c){
        map = new Square[r][c];
        for(int row = 0; row<map.length; row++){
            for(int col = 0; col<map[0].length; col++){
                map[row][col] = new Square();
                //map[row][col].setVisual(" ");
                int x = row;
                int y = col;
                map[row][col].getSquareButton().setOnMouseClicked(e->{
                    if(e.getButton() == MouseButton.PRIMARY){
                        revealArea(x, y);
                    }
                    if(e.getButton() == MouseButton.SECONDARY){
                        flag(x, y);
                        if(win() == true){
                            System.out.println("WIN");
                            endBox.end("Win", "YOU WIN!!!");
                            System.exit(0);
                        }
                    }
                });


            }

        }
    }

    public Square[][] getMap(){
        Square[][] mapCopy = new Square[map.length][map.length];
        for(int row = 0; row<map.length; row++){
            for(int col = 0; col<map[0].length; col++){
                mapCopy[row][col] = map[row][col];
            }
        }
        return mapCopy;
    }
    //------------------------------------
    //mapToString() - Visually shows map
    //
    //------------------------------------
    public void mapToString() {
        for (Square[] x : map) {
            for (Square y : x) {
                System.out.print(y.getVisual() + " ");
            }
            System.out.println();
        }
    }
    //---------------------------------------------
    //placeBombs()- Randomly places bombs on the map
    //There are two bombs per row.
    //
    //---------------------------------------------
    public void placeBombs(int difficulty){
        int randomRow;
        int randomCol;
        int maxBombs;
        if(difficulty == 1){
            maxBombs = 12;
            for(int bombAmount = 0; bombAmount < maxBombs;){
                randomRow = rand.nextInt(map.length-1);
                randomCol = rand.nextInt(map[0].length-1);
                if(map[randomRow][randomCol].getSafe() == false){
                    //Do nothing. Checks if same row col is not chosen again
                }
                else {
                    map[randomRow][randomCol].setSafe(false);
                    if (TESTING_MODE) {
                        map[randomRow][randomCol].setVisual("b");

                    }
                    bombAmount++;
                }
            }

        }
        if(difficulty == 2){
            maxBombs = 41;
            for(int bombAmount = 0; bombAmount < maxBombs;){
                randomRow = rand.nextInt(map.length-1);
                randomCol = rand.nextInt(map[0].length-1);
                if(map[randomRow][randomCol].getSafe() == false){
                    //Do nothing. Checks if same row col is not chosen again
                }
                else {
                    map[randomRow][randomCol].setSafe(false);
                    if (TESTING_MODE) {
                        map[randomRow][randomCol].setVisual("b");
                    }
                    bombAmount++;
                }
            }
        }
        if(difficulty == 3){
            maxBombs = 102;
            for(int bombAmount = 0; bombAmount < maxBombs;){
                randomRow = rand.nextInt(map.length-1);
                randomCol = rand.nextInt(map[0].length-1);
                if(map[randomRow][randomCol].getSafe() == false){
                    //Do nothing. Checks if same row col is not chosen again
                }
                else {
                    map[randomRow][randomCol].setSafe(false);
                    if (TESTING_MODE) {
                        map[randomRow][randomCol].setVisual("b");
                    }
                    bombAmount++;
                }
            }
        }

    }
    //----------------------------------------
    //flag(int row, int col)- Allows user to
    //visually mark the map.
    //
    //--------------------------------------
    public void flag(int row, int col){
        if(map[row][col].getFlagged()){
            map[row][col].setVisual(" ");
            map[row][col].setFlagged(false);
        }
        else{
            if(!map[row][col].getFlagged() && !map[row][col].getRevealed()){
                map[row][col].setVisual("F");
                map[row][col].setFlagged(true);
            }

        }
    }
    //-----------------------------------------
    //isExplode(int row, int col)- checks if
    //player loses.
    //Helper class for revealArea()
    //-----------------------------------------
    private boolean isExplode(int row, int col){
        if(map[row][col].getSafe() == true){
            return false;
        }
        else{
            return true;
        }
    }
    //-----------------------------------
    //detectBombs(int row, int col)- Helper
    //method for reveal area.
    //
    //-----------------------------------
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
            map[row][col].setVisual(Integer.toString(bombCount));
            map[row][col].setRevealed(true);
        }


        //return bombCount;


    }
    //-------------------------------------------
    //revealArea(int row, int col)- Reveals a square
    //and also reveals squares around squares with 0
    //bombs. Helper class for Map(int r, int c)
    //
    //-------------------------------------------
    public void revealArea(int row, int col) {
        if(this.isExplode(row, col) == true){
            System.out.println("BOOOOM!\nYOU LOSE!");
            endBox.end("Lose", "BOOOOM! YOU LOSE!!!");
            System.exit(0);
        }
        this.detectBombs(row, col);
        for(int i = 0; i<100; i++){//Searches for 0 over and over again to make sure no more 0's are unchecked.
            for (int r = 0; r<map.length; r++) {
                for (int c = 0; c<map[0].length; c++) {
                    if(map[r][c].getVisual().equals("0")){
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

    //-----------------------------------------
    //win()- checks if bombs are correctly marked
    //If so, returns true.
    //
    //----------------------------------------
    public boolean win(){
        for(int row = 0; row<map.length; row++){
            for(int col = 0; col<map[0].length; col++){
                if(map[row][col].getSafe() == false && (map[row][col].getVisual().equals(" ") || map[row][col].getVisual().equals("B"))){
                    return false;
                }
                if(map[row][col].getSafe() == true && map[row][col].getVisual().equals("F")){
                    return false;
                }
            }
        }
        return true;
    }

}