package com.fireinyu.themyth.ui.gui;

import java.io.IOException;

import com.fireinyu.themyth.TheMyth;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The main class for the GUI of TheMyth application.
 * It extends the JavaFX Application class to set up the main window.
 */
public class Gui extends Application {
    private final TheMyth model = new TheMyth();
    /**
     * The main entry point for all JavaFX applications.
     *
     * @param stage The primary stage for this application, onto which the application scene can be set.
     * @throws Exception if an error occurs during startup.
     */
    @Override
    public void start(Stage stage) throws Exception {
        this.model.start();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Gui.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            fxmlLoader.<MainWindow>getController().setTheMyth(new TheMyth()); // inject the TheMyth instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is called when the application should stop.
     * It ensures that the model's resources are released.
     *
     * @throws Exception if an error occurs during shutdown.
     */
    @Override
    public void stop() throws Exception {
        this.model.stop();
    }
}
