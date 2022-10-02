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
import java.util.Objects;

public class HelloController {
    @FXML
    private TextField lineProbability;//Текстовое поле
    @FXML
    private Pane main;//панель
    @FXML
    private TextField lineSymbols;//Текстовое поле
    private File fileProbability;//файл
    private File fileSymbols;//файл

    @FXML
    void searchSymbols(ActionEvent event) {//при нажатии на кнопку символы, автоматически выдается окно которая позволяет выбрать файл
        SearchFile.search(fileSymbols, lineSymbols);//статический метод который находит заданный файл и выдает его путь
    }

    @FXML
    void searchProbabilities(ActionEvent event) {//при нажатии на кнопку вероятность, автоматически выдается окно которая позволяет выбрать файл
        SearchFile.search(fileProbability, lineProbability);//статический метод который находит заданный файл и выдает его путь
    }

    @FXML
    void algorithm(ActionEvent event) throws IOException {//когда происходит нажатие на иконку алгоритм автоматически идет вычисления алгоритма

        if(Objects.equals(lineSymbols.getText(), "") || Objects.equals(lineProbability.getText(), "")){//если не все данные ведены, алгоритм не будет вычисляться
            Error.error("Ставьте файл символы и вероятности!",main);//ошибка
        }else {
            Stage ss = (Stage) main.getScene().getWindow();//береться параметры стратого она и закрывается
            ss.close();//закрытия окна

            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("workingAlgorithm.fxml"));//берется fxml ф

            Stage stage = new Stage();//создается stage для запуска нового окна
            Scene scene = new Scene(loader.load());//загружается дизайн с fxml

            stage.setTitle("Cryptography");//название нового окна
            stage.getIcons().add(new Image(HelloApplication.class.getResourceAsStream("bit.png")));//значок нового окна
            stage.setScene(scene);//установка Scene для Stage
            stage.setResizable(false);//запрещает пользователю изменять размер окна
            stage.show();//Попытки показать это окно, установив для видимости значение true

            WorkingFiles workingFiles=new WorkingFiles();//создается класс работа с файлом

            HilbertMoore.algorithm(workingFiles.readingNumber(lineProbability.getText()));//запускается статический метод алгоритм, который получает список вещественных чисел из файла который лежит по ссылки вероятности
            HilbertMoore.symbol=workingFiles.readingCharacter(lineSymbols.getText());//присвоение статическому полю список символов, который получается из считывания с файла по пути полученного из строки

            workingFiles.writingCharactersCipher(HilbertMoore.symbol,HilbertMoore.encoded);//запись в файл символов и какая кодировка соответсвует ему
        }
    }
    @FXML
    void nameTeam(ActionEvent event) {//метод который выводит информацию о бригаде
        Alert alert = new Alert(Alert.AlertType.INFORMATION);//значек информации
        alert.initOwner((Stage) main.getScene().getWindow().getScene().getWindow());// создается окно, на основы панели
        alert.setTitle("Создатели");// названия окна

        // Header Text: null
        alert.setHeaderText(null);//текст заголовка, в данном случаи он нам не нужен
        alert.setContentText("Группа ПМИ-01\nБриганда 8\nЗубань Ярослав\nПавлович Владислав");// текст который будет выводиться в окне

        alert.showAndWait();//Показывает диалоговое окно и ожидает ответа пользователя
    }
}