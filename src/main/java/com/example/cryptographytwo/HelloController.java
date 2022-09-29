package com.example.cryptographytwo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.File;
import java.io.IOException;

public class HelloController {
    @FXML
    private ImageView algorithm;

    @FXML
    private ImageView coffee;

    @FXML
    private TextField lineProbability;

    @FXML
    private Pane main;

    @FXML
    private Button algorithmButton;

    @FXML
    private TextField lineSymbols;

    @FXML
    private ImageView nameTeam;

    private File fileProbability;
    private File fileSymbols;

    @FXML
    void searchSymbols(ActionEvent event) {
        SearchFile.search(fileSymbols, lineSymbols);
    }


    @FXML
    void searchProbabilities(ActionEvent event) {
        SearchFile.search(fileProbability, lineProbability);
    }


    @FXML
    void algorithm(ActionEvent event) throws IOException {
        Stage ss = (Stage) main.getScene().getWindow();
        ss.close();

        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("workingAlgorithm.fxml"));

        Stage stage = new Stage();
        Scene scene = new Scene(loader.load());
        stage.setTitle("Cryptography");
        stage.getIcons().add(new Image(HelloApplication.class.getResourceAsStream("bit.png")));
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

    }

    @FXML
    void nameTeam(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initOwner((Stage) main.getScene().getWindow().getScene().getWindow());
        alert.setTitle("Создатели");

        // Header Text: null
        alert.setHeaderText(null);
        alert.setContentText("Группа ПМИ-01\nБриганда 8\nЗубань Ярослав\nПавлович Владислав");

        alert.showAndWait();


    }


    private void error() {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initOwner((Stage) main.getScene().getWindow().getScene().getWindow());
        alert.setTitle("Ошибка");

        // Header Text: null
        alert.setHeaderText(null);
        alert.setContentText("Не эффективное сжатие!");

        alert.showAndWait();


    }
}