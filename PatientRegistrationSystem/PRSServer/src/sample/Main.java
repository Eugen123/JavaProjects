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


    private static final int TCP_SERVER_PORT =8100;
    private Button start_button;
    Thread s;
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
                if (hostAvailabilityCheck()){
                    start_button.setText("STOP");
                    server= new Server();
                  s =  new Thread(server);
                  s.start();
                } else {
                    try {
                        start_button.setText("START");
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

        try(Socket s = new Socket("localhost", TCP_SERVER_PORT)){
            s.close();
            return false;

        } catch (IOException ex) {

            return true;
        }
    }
    public static void main(String[] args) {
        launch(args);
    }


}
