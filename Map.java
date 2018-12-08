/***************************************************************************
*Map.java
*
*Represents entire minesweeper map that is used to play the game.
*
*mapToString()- Shows the map visually
*placeBombs()- Places bombs randomly on map 
*flag(int row, int col)- Allows user to mark bombs
*revealArea(int row, int col)- Reveals the area that player selects.
*win()- Checks if bombs are correctly marked.
****************************************************************************/
import java.util.Arrays;
import java.util.Random;
public class Map{
    private Random rand = new Random();
    private Square[][] map; //Should always be a square map.

    public Map(){
        map = new Square[0][0];
    }

    public Map(int r, int c){
        map = new Square[r+1][c+1];
        int coord = 1; //To setString of coordanites for user accesability.

        map[0][0] = new Square();
        map[0][0].setVisual("-");

        for(int row = 1; row<map.length; row++){
            map[row][0] = new Square();
            map[row][0].setVisual(Integer.toString(coord));
            coord++;
        }
        coord = 1;
        for(int col = 1; col<map[0].length; col++){
            map[0][col] = new Square();
            map[0][col].setVisual(Integer.toString(coord));
            coord++;
        }
        for(int row = 1; row<map.length; row++){
            for(int col = 1; col<map[0].length; col++){
                map[row][col] = new Square();
                map[row][col].setVisual("*");

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
    public void placeBombs() {
        boolean test = false;//TESTING LINE, SET TO TRUE FOR TESTING PURPOSES
        int indexBomb;
        int bombAmount = 2;
        for (int row = 1; row < map.length; row++) {
            for(int bombs = 0; bombs < bombAmount; bombs++){
                indexBomb = rand.nextInt(map[0].length-1) + 1;
                map[row][indexBomb].setSafe(false);
                if(test){
                    map[row][indexBomb].setVisual("B");
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
        if(map[row][col].getVisual().equals("F")){
            map[row][col].setVisual("*");
        }
        else{
            if(map[row][col].getVisual().equals("*")){
                map[row][col].setVisual("F");
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
        if((row-1 > 0) && row<map.length && col<map[0].length && col>0 && map[row-1][col].getSafe() == false){
            bombCount++;
        }
        //Check down
        if((row+1 < map.length) && row>0&& col<map[0].length && col>0 && map[row+1][col].getSafe() == false){
            bombCount++;
        }
        //Check right
        if((col+1 < map[0].length) && col>0 && row<map.length && row>0 && map[row][col+1].getSafe() == false){
            bombCount++;
        }
        //Check left
        if((col-1 > 0) && col<map[0].length && row<map.length && row>0 && map[row][col-1].getSafe() == false){
            bombCount++;
        }
        //Check up right
        if((row-1 > 0) && row<map.length && (col+1 < map[0].length) && col>0 && map[row-1][col+1].getSafe() == false){
            bombCount++;
        }
        //Check up left
        if((row-1 > 0) && row<map.length && (col-1 > 0) && col<map[0].length&& map[row-1][col-1].getSafe() == false){
            bombCount++;
        }
        //Check down left
        if((row+1 < map.length) && row>0 && (col-1 > 0)&& col<map[0].length && map[row+1][col-1].getSafe() == false){
            bombCount++;
        }
        //check down right
        if((row+1 < map.length) && row>0 && (col+1 < map[0].length)&& col>0 && map[row+1][col+1].getSafe() == false){
            bombCount++;
        }
        if(row>0 && col>0 && row<map.length && col<map[0].length){
            map[row][col].setVisual(Integer.toString(bombCount));
        }
        
        
        //return bombCount;


    }
    //-------------------------------------------
    //revealArea(int row, int col)- Reveals a square
    //and also reveals squares around squares with 0
    //bombs.
    //
    //-------------------------------------------
    public void revealArea(int row, int col) {
        if(this.isExplode(row, col) == true){
            System.out.println("BOOOOM!\nYOU LOSE!");
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
                if(map[row][col].getSafe() == false && (map[row][col].getVisual().equals("*") || map[row][col].getVisual().equals("B"))){
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