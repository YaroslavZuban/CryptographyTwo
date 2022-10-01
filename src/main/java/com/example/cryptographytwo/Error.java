package com.example.cryptographytwo;

import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Error {
    public static void error(String line, Pane main) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initOwner((Stage) main.getScene().getWindow().getScene().getWindow());
        alert.setTitle("Ошибка");

        // Header Text: null
        alert.setHeaderText(null);
        alert.setContentText(line);

        alert.showAndWait();


    }
}
