package com.fireinyu.themyth.ui.gui;

import com.fireinyu.themyth.TheMyth;
import com.fireinyu.themyth.responses.Response;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private TheMyth TheMyth;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image TheMythImage = new Image(this.getClass().getResourceAsStream("/images/DaMyth.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the TheMyth instance */
    public void setTheMyth(TheMyth d) {
        TheMyth = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing TheMyth's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        Response response = TheMyth.handleInput(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getTheMythDialog(response.getBody(), TheMythImage)
        );
        userInput.clear();
        if (response.doExit()) {
            Platform.exit();
        }
    }
}
