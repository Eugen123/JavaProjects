package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import jdk.nashorn.internal.objects.annotations.Property;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by eugen.dragomir on 12/26/2016.
 */
public class Controller implements Initializable {

    @FXML
    private TextField regId;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        regId.setText("Registration Id");

    }
}

