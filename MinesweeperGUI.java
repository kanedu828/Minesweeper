/***************************************************************************
*MinesweeperGUI.java
*
*Executes and runs minesweeper.
*
*How to play:
*Left click to reveal, right click to flag.
*
*
*By: Kane Du
*
*Last Edited: 12/9/2018
*
*Version: 2.0.0
*
*2.0.0 Change log:
*Added GUI.
*
*
*1.0.1 Change log:
*Revealed squares can not be flagged.
*Checks for valid inputs.
*
*
****************************************************************************/

import javafx.application.Application;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import java.lang.Integer;

public class MinesweeperGUI extends Application{

    Stage window;
    Button squareButton;

    int width = 10;
    int length = 10;
    int difficulty;
    
    
    public static void main(String[] args) {
        //System.out.println("Width: ");
        launch(args);
        
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("Minesweeper 2.0.0");
        

        GridPane mapGrid = new GridPane(); //Creates map
        Scene game = new Scene(mapGrid);
       
        //Map Size
        Map map = new Map(width, length);

        //Set up bombs
        map.placeBombs();

        //Set square buttons to grid
        for(int row = 0; row< width; row++){
            for(int col = 0; col<length; col++){  
                mapGrid.setConstraints(map.getMap()[row][col].getSquareButton(), col, row);
                mapGrid.getChildren().add((map.getMap()[row][col].getSquareButton()));
            }
        }



        window.setScene(game);
        window.show();
        
        




    

    
    }
}