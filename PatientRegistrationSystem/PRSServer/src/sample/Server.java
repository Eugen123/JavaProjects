package sample;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Server class:
 *  - listens on a given port
 *   - supports multithreading
 * Created by eugen.dragomir on 12/28/2016.
 */
public class Server implements Runnable{
    ServerSocket server = null;
    Socket connection = null;
    ObjectOutputStream out;
    ObjectInputStream in;
    String message;
    String port;
    Server(){
        port = getPropertyValue("PORT");
        Logger.getAnonymousLogger().log(Level.INFO, "Server is starting on port " + port);
    }
    String getPropertyValue(String property)
    {
        try {

            FileReader settings = new FileReader("D:\\repositories\\MyProjects\\JavaProjects\\PatientRegistrationSystem\\PRSServer\\src\\sample\\settings");
            BufferedReader bufferedReader = new BufferedReader(settings);
            String line;
            String[] array = null;
            while ((line = bufferedReader.readLine()) != null){
                array = line.split(property + "=");
            }
            settings.close();
            bufferedReader.close();
            if(!(array[1] == null))
                return array[1];
        }catch (Exception e){

            Logger.getAnonymousLogger().log(Level.CONFIG,"Could not open settings file");
            Logger.getAnonymousLogger().log(Level.CONFIG, e.getMessage());
        }

        return null;

    }
    public void run(){
        try{
            Integer serverPort = new Integer(port);
            server = new ServerSocket(serverPort, 0, InetAddress.getByName("127.0.0.1"));
            connection = server.accept();
            Logger.getAnonymousLogger().log(Level.INFO, "Server started");
            while (true){
                InputStream is = connection.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                String msg = br.readLine();
                Logger.getAnonymousLogger().log(Level.INFO, "Received from client" + msg);
            }
        }catch (IOException e){
            Logger.getAnonymousLogger().log(Level.CONFIG,"Could not listen on port:" + port);
            Logger.getAnonymousLogger().log(Level.CONFIG, e.getMessage());
        }
    }


}
