package minesweeper.gui;
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
import minesweeper.game.Map;

import java.lang.Integer;

/**
 * Driver class for a game of minesweeper.
 *
 * Author: Kane Du
 *
 * Last Edited: 3/17/19
 */
public class MinesweeperGUI extends Application{

    Stage window;
    Button squareButton;

    static int width = 10;
    static int length = 10;
    int difficulty = 1;
    //10x10, 16x16, 22x22

    /**
     * Launches javafx application
     * @param args Command line input
     */
    public static void main(String[] args) {
        //System.out.println("Width: ");
        launch(args);
        
    }

    /**
     * Sets uo javafx application.
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("Minesweeper 2.0.0");
        

        GridPane mapGrid = new GridPane(); //Creates map
        Scene game = new Scene(mapGrid);
       
        //minesweeper.game.Map Size
        Map map = new Map(width, length);

        //Set up bombs
        map.placeBombs(1);

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
