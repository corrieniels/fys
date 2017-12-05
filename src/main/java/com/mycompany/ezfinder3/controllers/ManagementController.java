package com.mycompany.ezfinder3.controllers;

import com.mycompany.ezfinder3.MainApp;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class ManagementController implements Initializable {
    
    @FXML
    private void goGebruiker(ActionEvent event) throws IOException {
        MainApp.switchScherm("fxml/gebruiker.fxml");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
