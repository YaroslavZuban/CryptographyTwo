package com.example.cryptographytwo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
/** Главный класс который запускает первоначальный графический интерфейс**/
public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {//метод который запускается автоматически
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));//считывание дизайн самого интерфейса
        Scene scene = new Scene(fxmlLoader.load());//запуск дизайн
        stage.setTitle("Cryptography");//название окна
        stage.getIcons().add(new Image(HelloApplication.class.getResourceAsStream("bit.png")));//установление иконки
        stage.setScene(scene);//установка Scene для Stage
        stage.setResizable(false);//запрещает пользователю изменять размер окна
        stage.show();//Попытки показать это окно, установив для видимости значение true
    }
    public static void main(String[] args) {
        launch();
    }
}