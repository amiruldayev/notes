package com.example.xx;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.io.*;
import java.util.Scanner;

public class HelloController {

    @FXML
    private Button deleteBut;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button newNoteButton;

    @FXML
    private VBox notes;

    @FXML
    private TextField text;

    public TextField getText() {
        return text;
    }

    @FXML
    void initialize() {
        try {
            File notesfile = new File("notesfile.txt");
            if (!notesfile.exists()){
                notesfile.createNewFile();

            } else {
                // Если файл существует, читаем его содержимое и добавляем в "notes"
                Scanner scanner = new Scanner(notesfile);
                while (scanner.hasNextLine()) {
                    String noteText = scanner.nextLine();
                    Label label = new Label(noteText);
                    notes.getChildren().add(label);
                }
                scanner.close();
            }

            newNoteButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    try {
                        String noteText = getText().getText().trim(); // Получаем текст из объекта TextField и удаляем лишние пробелы по краям
                        if (!noteText.isEmpty()) { // Проверяем, что строка не пустая
                            PrintWriter pw = new PrintWriter(new FileWriter(notesfile, true)); // Используем FileWriter с параметром true для дозаписи
                            pw.println(noteText); // Записываем текст в файл на следующую строку
                            pw.close();

                            Label label = new Label(noteText);
                            notes.getChildren().add(label);
                            text.clear(); // Очищаем TextField после добавления текста
                        }
                    }catch (IOException e){
                        System.out.println("Error: " + e);
                    }

                }
            });

            deleteBut.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    try {
                        // Удаление содержимого файла "notesfile.txt"
                        PrintWriter pw = new PrintWriter(notesfile);
                        pw.close();

                        // Очищаем содержимое "notes" в интерфейсе JavaFX
                        notes.getChildren().clear();

                    } catch (IOException e){
                        System.out.println("Error: " + e);
                    }
                }
            });
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }



    }
}