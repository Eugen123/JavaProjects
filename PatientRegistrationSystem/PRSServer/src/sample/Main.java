package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Main extends Application {


    private static final int TCP_SERVER_PORT =8890 ;
    private Button start_button;
    @Override
    public void start(Stage primaryStage) throws Exception{

        primaryStage.setTitle("Server");

        start_button = new Button();
        start_button.setText("START");
        start_button.setVisible(true);
        start_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Server server = null;
                if (!hostAvailabilityCheck()){
                    start_button.setText("STOP");
                    server= new Server();
                    server.run();
                } else {
                    try {
                        server.connection.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        });

        StackPane layout = new StackPane();
        layout.getChildren().add(start_button);

        primaryStage.setScene(new Scene(layout, 1000, 1000));
        primaryStage.show();

    }

    private static boolean hostAvailabilityCheck() {
        InetAddress host = null;
        try {
            host = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        try{
            Socket s = new Socket(host, TCP_SERVER_PORT);
            return true;
        } catch (IOException ex) {
        /* ignore */
        }
        return false;
    }
    public static void main(String[] args) {
        launch(args);
    }
}
