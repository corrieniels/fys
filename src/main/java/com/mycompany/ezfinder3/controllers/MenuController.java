package com.mycompany.ezfinder3.controllers;

import com.mycompany.ezfinder3.MainApp;
import com.mycompany.ezfinder3.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

public class MenuController implements Initializable {
    
    @FXML private AnchorPane APBegin;

    @FXML
    private void goHome(ActionEvent event) throws IOException {
        MainApp.switchScherm("fxml/home.fxml");
        MainApp.setMenu(true);
    }

    @FXML
    private void goLogin(ActionEvent event) throws IOException {
        User.setUserID(0);
        MainApp.switchScherm("fxml/login.fxml");
        MainApp.setMenu(false);
    }
    
    @FXML
    private void goGevonden(ActionEvent event) throws IOException {
        MainApp.switchScherm("fxml/gevonden.fxml");        
        MainApp.setMenu(true);
    }
    
    @FXML
    private void goManagement(ActionEvent event) throws IOException {
        MainApp.switchScherm("fxml/management.fxml");
        MainApp.setMenu(true);
    }
    
    @FXML
    private void goVerloren(ActionEvent event) throws IOException {
        MainApp.switchScherm("fxml/verloren.fxml");
        MainApp.setMenu(true);
    }
    
    @FXML
    private void goRapportage(ActionEvent event) throws IOException {
        MainApp.switchScherm("fxml/rapportages.fxml");
        MainApp.setMenu(true);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
}
