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
*Last Edited: 12/7/2018
*
*Version: 1.0.0
*
*
*
****************************************************************************/

import java.util.Scanner;
public class PlayMinesweeper{
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Map map = new Map(9, 9);
        
        map.placeBombs();

        map.mapToString();

        while (true) {
            System.out.println("Coords:");
            int userRow = input.nextInt();
            int userCol = input.nextInt();
            String userFlag = input.nextLine();

            if(userFlag.equalsIgnoreCase(" flag") || userFlag.equalsIgnoreCase(" f")){
                map.flag(userRow, userCol);
            }
            else{
                map.revealArea(userRow, userCol);
            }
            map.mapToString();
            if(map.win() == true){
                System.out.println("MAP COMPLETED, YOU WIN!");
            }
        }
    }
}