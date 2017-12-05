package com.mycompany.ezfinder3.controllers;

import com.mycompany.ezfinder3.MainApp;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class GevondenController implements Initializable {
    
    @FXML
    private void verder(ActionEvent event) throws IOException {
        MainApp.switchScherm("fxml/matches_gevonden.fxml");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
