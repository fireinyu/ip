package com.fireinyu.themyth.ui.gui;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 * Represents a dialog box consisting of an ImageView to represent the speaker's face
 * and a text flow containing styled text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private TextFlow dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Private constructor for a DialogBox.
     *
     * @param textNodes The styled text nodes to display in the dialog box.
     * @param img The image to display.
     */
    private DialogBox(List<Text> textNodes, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.getChildren().setAll(textNodes);
        displayPicture.setImage(img);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
        dialog.getStyleClass().add("reply-label");
    }

    /**
     * Creates a dialog box for the user.
     *
     * @param text The text from the user.
     * @param img The user's image.
     * @return A new DialogBox for the user.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(FlamboyantTextFormatter.formatPlain(text), img);
    }

    /**
     * Creates a dialog box for TheMyth with flamboyant rich text styling.
     * This dialog box is flipped.
     *
     * @param text The text from TheMyth.
     * @param img TheMyth's image.
     * @return A new DialogBox for TheMyth.
     */
    public static DialogBox getTheMythDialog(String text, Image img) {
        var db = new DialogBox(FlamboyantTextFormatter.format(text), img);
        db.flip();
        return db;
    }
}
