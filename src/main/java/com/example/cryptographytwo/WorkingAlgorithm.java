package com.example.cryptographytwo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class WorkingAlgorithm {
    @FXML
    private TextArea encryptedWord;

    @FXML
    private TextArea encryptionOutput;

    @FXML
    private TextField lineCode;

    @FXML
    private TextField lineCodeTwo;

    @FXML
    private Pane work;
    private File cipher;
    private File decryption;
    private WorkingFiles workingFiles = new WorkingFiles();

    @FXML
    void encryption(ActionEvent event) {
        SearchFile.search(decryption, lineCodeTwo);

        if (!lineCodeTwo.getText().equals("")) {
            String line = workingFiles.words(lineCodeTwo.getText());
            encryptedWord.setText(line);
            String temp = WorkCipher.coding(line);

            if (temp == null) {
                Error.error("Проверти файл на правильность символов!", work);
            } else {
                encryptionOutput.setText(temp);
                workingFiles.writingSymbolCipher(temp);
            }
        }
    }

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

        HilbertMoore.encoded = new ArrayList<>();
        HilbertMoore.symbol = new ArrayList<>();
        HilbertMoore.sizeCode = new ArrayList<>();
        HilbertMoore.probabilities = new ArrayList<>();
    }

    @FXML
    void searchCode(ActionEvent event) {
        SearchFile.search(cipher, lineCode);

        if (!lineCode.getText().equals("")) {
            String line = workingFiles.cipher(lineCode.getText());

            encryptionOutput.setText(line);

            String temp=WorkCipher.decoding(line);

            if (temp==null){
                Error.error("Проверти файл на корректноть символов!", work);
            }else {
                encryptedWord.setText(temp);
                workingFiles.writingСipherSymbol(temp);
            }


        }
    }

    @FXML
    void info(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initOwner((Stage) work.getScene().getWindow().getScene().getWindow());
        alert.setTitle("Информация о шифровки");

        // Header Text: null

        alert.setHeaderText(null);
        String inf = "";

        for (int i = 0; i < HilbertMoore.encoded.size(); i++) {
            inf += HilbertMoore.symbol.get(i) + " - " + HilbertMoore.encoded.get(i) + "\n";
        }

        inf += "Избыточность: " + HilbertMoore.redundancy() + "\n";
        inf += "Неравенстов Крафта: " + HilbertMoore.inequalityKrafts() + "\n";
        inf += "Среднее кодовое слово: " + HilbertMoore.middleWord() + "\n";

        alert.setContentText(inf);
        alert.showAndWait();

    }

}
