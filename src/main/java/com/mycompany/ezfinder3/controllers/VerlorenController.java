package com.mycompany.ezfinder3.controllers;

import com.mycompany.ezfinder3.MainApp;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class VerlorenController implements Initializable {
    
    @FXML
    private void verder(ActionEvent event) throws IOException {
        MainApp.switchScherm("fxml/matches_verloren.fxml");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
