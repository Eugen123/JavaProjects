package sample;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Client class:
 *  - connects to a given host and port
 *  - supports multithreading
 * Created by eugen.dragomir on 1/7/2017.
 */
public class Client implements Runnable{
    private static Socket socket;

    @Override
    public void run() {
        try {
            String host = getPropertyValue("HOST");
            String port = getPropertyValue("PORT");
            InetAddress address = InetAddress.getByName(host);
            socket = new Socket("localhost", Integer.parseInt(port));

            while (true){
                OutputStream os = socket.getOutputStream();
                OutputStreamWriter osw = new OutputStreamWriter(os);
                BufferedWriter br = new BufferedWriter(osw);
                BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
                String s = bufferRead.readLine();
            }
        } catch (IOException e){
            Logger.getAnonymousLogger().log(Level.CONFIG, "Client was closed!");
        }
    }

    private String getPropertyValue(String property)
    {
        try {

            FileReader settings = new FileReader("D:\\repositories\\MyProjects\\JavaProjects\\PatientRegistrationSystem\\PRSClient\\src\\settings");
            BufferedReader bufferedReader = new BufferedReader(settings);
            String line;
            String[] array = null;
            while ((line = bufferedReader.readLine()) != null){
                array = line.split(property + "=");
            }
            settings.close();
            bufferedReader.close();

            if (!(array[1] == null))
                return array[1];
        }catch (Exception e){

            Logger.getAnonymousLogger().log(Level.CONFIG,"Could not open settings file");
            Logger.getAnonymousLogger().log(Level.CONFIG, e.getMessage());
        }

        return null;

    }
}
