package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.awt.*;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends Application {


    private StackPane layout;
    private Button start_button;
    @Override
    public void start(Stage primaryStage) throws Exception {

        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Patient Management System");


        layout = new StackPane();
        primaryStage.setScene(new Scene(layout, 1000, 1000));
        addContent(primaryStage);
        start_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                new Thread(new Client()).start();

            }
        });
        primaryStage.show();

    }



    private void addContent(Stage primaryStage){
        Parent grid = (primaryStage.getScene().getRoot());
        start_button = new Button();
        start_button.setText("CONNECT");
        start_button.setVisible(true);
        layout.getChildren().add(start_button);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
