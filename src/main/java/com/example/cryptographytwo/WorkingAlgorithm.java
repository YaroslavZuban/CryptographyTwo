package com.example.cryptographytwo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class WorkingAlgorithm {
    @FXML
    private TextField averageLength;

    @FXML
    private TextArea encryptedWord;

    @FXML
    private TextArea encryptionOutput;

    @FXML
    private TextField inequality;

    @FXML
    private TextField redundancy;

    @FXML
    private TextField lineCode;

    @FXML
    private Pane work;

    private File file;

    @FXML
    void isHome(ActionEvent event) throws IOException {
        Stage ss = (Stage) work.getScene().getWindow();
        ss.close();

        FXMLLoader loader = new FXMLLoader(WorkingAlgorithm.class.getResource("hello-view.fxml"));

        Stage stage = new Stage();
        Scene scene = new Scene(loader.load());
        stage.setTitle("Cryptography");
        stage.getIcons().add(new Image(WorkingAlgorithm.class.getResourceAsStream("bit.png")));
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    void searchCode(ActionEvent event) {
        SearchFile.search(file, lineCode);
    }


}
