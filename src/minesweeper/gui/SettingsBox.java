package minesweeper.gui;
/*******************************************************************************************
*
*
*
*
*
*
*
*
*
*********************************************************************************************/


import org.w3c.dom.Text;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.lang.Integer;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class SettingsBox{
 

    public static int settings(){
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Settings");
        window.setMinWidth(250);

        Label promptWidth = new Label();
        promptWidth.setText("Width:");
        TextField enterWidth = new TextField();
        

        Label promptLength = new Label();
        promptLength.setText("Length:");
        TextField enterLength = new TextField();
        

        Label promptDifficulty = new Label();
        promptDifficulty.setText("Difficulty (1-5):");
        TextField enterDifficulty = new TextField();

        Button playButton = new Button("Play");
        playButton.setOnAction(e-> {
            window.close();
        });

        VBox layout = new VBox();
        layout.getChildren().addAll(promptWidth, enterWidth, promptLength, enterLength, promptDifficulty, enterDifficulty, playButton);

        Scene scene = new Scene(layout, 500, 500);

        window.setScene(scene);
        window.show();

        return Integer.parseInt(enterWidth.getText());


    }
}