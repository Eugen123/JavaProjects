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

    private static Socket socket;

    @Override
    public void start(Stage primaryStage) throws Exception{
        try {
            String host = getPropertyValue("HOST");
            String port = getPropertyValue("PORT");
            InetAddress address = InetAddress.getByName(host);
            socket = new Socket(address, Integer.parseInt(port));

            while (true){
                OutputStream os = socket.getOutputStream();
                OutputStreamWriter osw = new OutputStreamWriter(os);
                BufferedWriter br = new BufferedWriter(osw);
                BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
                String s = bufferRead.readLine();
            }
        } catch (Exception e){

        }
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Patient Management System");
        primaryStage.setScene(new Scene(root, 1000, 1000));

        addContent(primaryStage);
        primaryStage.show();


    }

    String getPropertyValue(String property)
    {
        try {

            FileReader settings = new FileReader("C:\\Users\\eugen.dragomir\\IdeaProjects\\Test\\src\\sample\\settings");
            BufferedReader bufferedReader = new BufferedReader(settings);
            String line = null;
            String[] array = null;
            while ((line = bufferedReader.readLine()) != null){
                array = line.split(property + "=");
            }
            settings.close();
            bufferedReader.close();

            return array[1];
        }catch (Exception e){

            Logger.getAnonymousLogger().log(Level.CONFIG,"Could not open settings file");
            Logger.getAnonymousLogger().log(Level.CONFIG, e.getMessage());
        }

        return null;

    }
    void startClient(){
        Socket client= null;
        try {
            client.connect(client.getLocalSocketAddress());
        } catch (IOException e) {
            e.printStackTrace();
        } {
        };
    }
    public void addContent(Stage primaryStage){
        Parent grid = (primaryStage.getScene().getRoot());


    }


    public static void main(String[] args) {
        launch(args);
    }
}
