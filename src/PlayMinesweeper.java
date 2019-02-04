/*------------------------------------------------------------------------------------
PlayMinesweeper.java - Console/text based version of minesweeper.
Probably not functional anymore.
------------------------------------------------------------------------------------ */


/***************************************************************************
*PlayMinesweeper.java
*
*Executes and runs minesweeper
*
*How to play:
*Enter coordinates to reveal a square in the format of "y x". Ex: 3 2
*To flag or mark a square, enter "y x f". Ex: 3 2 f
*
*By: Kane Du
*
*Last Edited: 12/8/2018
*
*Version: 1.0.1
*Change log:
*Revealed squares can not be flagged.
*Checks for valid inputs.
*
*
****************************************************************************/
import minesweeper.game.Map;

import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;
public class PlayMinesweeper{
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(System.in);
        int row = 9;
        int col = 9;
        Map map = new Map(row, col);
        
        map.placeBombs(1);

        map.mapToString();

        while (true) {
            System.out.println("Coords:");
            int userRow;
            int userCol;
            String userFlag;
            try{
                userRow = input.nextInt();
                userCol = input.nextInt();
                
            }
            catch(InputMismatchException ex){
                System.out.println("Invalid input, please try again.");
                input.next();
                input.next();
                input.nextLine();
                continue;
            }
            userFlag = input.nextLine();
           
            if(userRow > row || userRow<1 || userCol<1 || userCol > col){
                System.out.println("Invalid input, please try again.");
                continue;
            }
            
            if(userFlag.equalsIgnoreCase(" flag") || userFlag.equalsIgnoreCase(" f")){
                map.flag(userRow, userCol);
            }
            else{
                map.revealArea(userRow, userCol);
            }
            map.mapToString();
            if(map.win() == true){
                System.out.println("MAP COMPLETED, YOU WIN!");
                System.exit(0);
            }
        }
    }
}