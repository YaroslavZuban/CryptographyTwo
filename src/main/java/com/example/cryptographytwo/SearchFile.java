package com.example.cryptographytwo;

import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class SearchFile {
    public static void search(File file, TextField textField){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Открыть файл");
        Stage stage = (Stage) textField.getScene().getWindow();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("TXT", "*.txt"));

        file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            textField.setText(String.valueOf(file));
        }
    }
}
