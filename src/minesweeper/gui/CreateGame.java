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

import java.io.FileNotFoundException;
import java.lang.Integer;

/**
 * I dont know. class aint being used.
 */
public class CreateGame{

    private static Stage window;
    private static Button squareButton;

    /**
     * No clue, class aint being used.
     * @param difficulty difficulty
     * @throws FileNotFoundException if a file cant be found.
     */
    public static void createGame(int difficulty) throws FileNotFoundException {
        GridPane mapGrid = new GridPane(); //Creates map
        Scene game = new Scene(mapGrid);
       Map map;
        //minesweeper.game.Map Size
        if(difficulty == 1){
            map = new Map(10, 10);
        }
        else if(difficulty == 2){
            map = new Map(16, 16);
        }
        else if(difficulty == 3){
            map = new Map(22, 22);
        }
        else{
            map = new Map(10,10);
        }
        

        //Set up bombs
        map.placeBombs(difficulty);

        //Set square buttons to grid
        for(int row = 0; row < map.getMap().length; row++){
            for(int col = 0; col<map.getMap()[0].length; col++){  
                mapGrid.setConstraints(map.getMap()[row][col].getSquareButton(), col, row);
                mapGrid.getChildren().add((map.getMap()[row][col].getSquareButton()));
            }
        }

        window.setScene(game);
    }
}