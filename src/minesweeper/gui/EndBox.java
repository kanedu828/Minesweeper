package minesweeper.gui;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

/**
 * This class represents the endscreen for when the player wins.
 */
public class EndBox{
    /**
     * Shows the endscreen.
     * @param title title of the screen
     * @param message message of the screen.
     */
    public static void end(String title, String message){
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);

        Label endMessage = new Label();
        endMessage.setText(message);
        Button exitButton = new Button("Exit");
        exitButton.setOnAction(e-> window.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(endMessage, exitButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
}