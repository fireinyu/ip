package com.fireinyu.themyth.ui.gui;

import com.fireinyu.themyth.TheMyth;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Gui extends Application {
    private final TheMyth model = new TheMyth();
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
            fxmlLoader.<MainWindow>getController().setTheMyth(new TheMyth());  // inject the TheMyth instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() throws Exception {
        this.model.stop();
    }

    public Gui() {
        super();
    }
}
