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
    private TextArea encryptedWord;//Поле вывода слова
    @FXML
    private TextArea encryptionOutput;//поле вывода кодировки
    @FXML
    private TextField lineCode;
    @FXML
    private TextField lineCodeTwo;
    @FXML
    private Pane work;//главная манель
    private File cipher;//создание файла
    private File decryption;//создание файла
    private WorkingFiles workingFiles = new WorkingFiles();//создание обьекта работы с файлом

    @FXML
    void encryption(ActionEvent event) {//при нажатии на кнопку зашифровать, автоматически вызовиться метод encryption
        SearchFile.search(decryption, lineCodeTwo);//поиск заданого файла в окне

        if (!lineCodeTwo.getText().equals("")) {//если файл не нашли и строка пустая ничего не сделается
            String line = workingFiles.words(lineCodeTwo.getText());//выдает строку символов которую нужно закодировать
            encryptedWord.setText(line);//вставляется в поле вывода, чтобы человек видел
            String temp = WorkCipher.coding(line);//кодируем строку

            if (temp == null) {//если строка равно нулю то выведиться сообщение
                Error.error("Проверти файл на правильность символов!", work);
            } else {
                encryptionOutput.setText(temp);//вставиться текст шифровки
                workingFiles.writingSymbolCipher(temp);//и запишиться в файл на рабочем столе
            }
        }
    }
    @FXML
    void isHome(ActionEvent event) throws IOException {//если нажать на иконку дом
        Stage ss = (Stage) work.getScene().getWindow();
        ss.close();

        FXMLLoader loader = new FXMLLoader(WorkingAlgorithm.class.getResource("hello-view.fxml"));//считывание дизайн самого интерфейса

        Stage stage = new Stage();//создается stage для запуска нового окна
        Scene scene = new Scene(loader.load());//загружается дизайн с fxml
        stage.setTitle("Cryptography");//название нового окнание нового окна
        stage.getIcons().add(new Image(WorkingAlgorithm.class.getResourceAsStream("bit.png")));//значок нового окна
        stage.setScene(scene);//установка Scene для Stage
        stage.setResizable(false);//запрещает пользователю изменять размер окна
        stage.show();//Попытки показать это окно, установив для видимости значение true

        HilbertMoore.encoded = new ArrayList<>();//обнуляем список, чтобы не выдало ошибок, если решим заполнить новыми данными
        HilbertMoore.symbol = new ArrayList<>();//обнуляем список, чтобы не выдало ошибок, если решим заполнить новыми данными
        HilbertMoore.sizeCode = new ArrayList<>();//обнуляем список, чтобы не выдало ошибок, если решим заполнить новыми данными
        HilbertMoore.probabilities = new ArrayList<>();//обнуляем список, чтобы не выдало ошибок, если решим заполнить новыми данными
    }
    @FXML
    void searchCode(ActionEvent event) {//метод создан чтобы декодировать
        SearchFile.search(cipher, lineCode);//поиск заданого файла в окне

        if (!lineCode.getText().equals("")) {//если файл не нашли и строка пустая ничего не сделается
            String line = workingFiles.cipher(lineCode.getText());//выдает строку символов которую нужно закодировать

            encryptionOutput.setText(line);//вставляет в окно, шифр

            String temp=WorkCipher.decoding(line);//получаем декодированную строку

            if (temp==null){//если она равно нулю, то выводить ошибку
                Error.error("Проверти файл на корректноть символов!", work);
            }else {
                encryptedWord.setText(temp);//вставляем декодированный код в окно
                workingFiles.writingСipherSymbol(temp);//записывает переведенный код в файл
            }
        }
    }
    @FXML
    void info(ActionEvent event) {//методи выдает информацию
        Alert alert = new Alert(Alert.AlertType.INFORMATION);//значек информации
        alert.initOwner((Stage) work.getScene().getWindow().getScene().getWindow());// создается окно, на основы панели
        alert.setTitle("Информация о шифровки");// названия окна
        // Header Text: null
        alert.setHeaderText(null);//текст заголовка, в данном случаи он нам не нужен
        String inf = "";

        for (int i = 0; i < HilbertMoore.encoded.size(); i++) {
            inf += HilbertMoore.symbol.get(i) + " - " + HilbertMoore.encoded.get(i) + "\n";//записывавет в строку информацию какому символу соответсвует код
        }

        inf += "Избыточность: " + HilbertMoore.redundancy() + "\n";
        inf += "Неравенстов Крафта: " + HilbertMoore.inequalityKrafts() + "\n";
        inf += "Среднее кодовое слово: " + HilbertMoore.middleWord() + "\n";

        alert.setContentText(inf);//выводиться в окно
        alert.showAndWait();//Показывает диалоговое окно и ожидает ответа пользователя
    }
}
