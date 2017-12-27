package com.mycompany.ezfinder3.controllers;

import com.mycompany.ezfinder3.Home;
import com.mycompany.ezfinder3.MainApp;
import com.mycompany.ezfinder3.MyJDBC;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ManagementController implements Initializable {
    
    private MyJDBC db;
    private ResultSet resultSet;
    private TableView<Home> verlorenBagage;
    private ObservableList<Home> data;
    
    @FXML TableColumn Gebruikersnaam;
    @FXML TableColumn Taal;
    @FXML TableColumn Type;  
    @FXML TableColumn Archief;
    @FXML Button Zoeken;
    @FXML TextField Gebruikerzoeken;
    
    @FXML
    private void goGebruiker(ActionEvent event) throws IOException {
        MainApp.switchScherm("fxml/gebruiker.fxml");
        
        //Medewerker in de zoekbalk kunnen laten typen 
        String zoek = Gebruikerzoeken.getText();
        
        
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }     
    //Database laten zien
    
    
}
