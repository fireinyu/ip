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

    private TheMyth theMyth;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image theMythDefaultImage = new Image(this.getClass().getResourceAsStream("/images/DaMyth.png"));
    private final Image theMythHappyImage = new Image(this.getClass().getResourceAsStream("/images/DaMythHappy.png"));
    private final Image theMythAngryImage = new Image(this.getClass().getResourceAsStream("/images/DaMythAngry.png"));
    private Image theMythImage = theMythDefaultImage;

    /**
     * Constructs a new {@code MainWindow}.
     * This constructor is called by the FXML loader to instantiate the controller.
     */
    public MainWindow() {
    }
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    public void initialize() {
        // These assertions ensure that the FXML loader has injected the required UI components.
        assert scrollPane != null : "fx:id=\"scrollPane\" was not injected: check your FXML file.";
        assert dialogContainer != null : "fx:id=\"dialogContainer\" was not injected: check your FXML file.";
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets the TheMyth instance for the main window.
     *
     * @param d The TheMyth instance.
     */
    public void setTheMyth(TheMyth d) {
        // This assertion documents the assumption that the main window should never be given a null
        // logic component. A null value here would be a programming error in the application's setup.
        assert d != null;
        theMyth = d;
    }

    /**
     * Returns the current profile image used for TheMyth.
     *
     * @return the current {@link Image}
     */
    Image getTheMythImage() {
        return theMythImage;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing TheMyth's reply
     * and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();

        // This assertion documents the assumption that the TheMyth logic instance
        // has been injected before any user input is handled. If this fails,
        // it points to a programming error in the application's initialization sequence.
        assert theMyth != null;
        Response response = theMyth.handleInput(input);

        // This assertion documents the post-condition that handleInput should always
        // return a non-null Response object. A failure here would indicate a bug
        // within the TheMyth.handleInput() implementation.
        assert response != null;
        theMythImage = switch (response.getMood()) {
            case HAPPY -> theMythHappyImage;
            case ANGRY -> theMythAngryImage;
            default -> theMythDefaultImage;
        };
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getTheMythDialog(response.getBody(), theMythImage)
        );
        userInput.clear();
        if (response.doExit()) {
            Platform.exit();
        }
    }
}
