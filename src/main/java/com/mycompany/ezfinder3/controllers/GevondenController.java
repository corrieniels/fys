package com.mycompany.ezfinder3.controllers;

import com.mycompany.ezfinder3.MainApp;
import com.mycompany.ezfinder3.MyJDBC;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

public class GevondenController implements Initializable {
    
    @FXML TextField bijzonderheden;
    @FXML TextField nummer;
    @FXML ComboBox kleur;  
    @FXML ComboBox vliegveld;
    
    private MyJDBC db;
    private ResultSet resultSet;
    
    @FXML 
    private void verder(ActionEvent event) throws IOException {
//        MainApp.switchScherm("fxml/matches_gevonden.fxml");
        String bijzonder = bijzonderheden.getText();
        System.out.println(bijzonder);
        
        
        String num = nummer.getText();
        System.out.println(num);
        
        String color = kleur.getSelectionModel().getSelectedItem().toString();
        System.out.println(color);
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        db = new MyJDBC();
        
        try {
            
            resultSet = db.executeResultSetQuery("SELECT * FROM vliegveld");
            
            while ( resultSet.next() ){
                vliegveld.getItems().addAll(
                      resultSet.getString("naam")
                );
            }
            
            resultSet = db.executeResultSetQuery("SELECT * FROM vertaling WHERE categorie = 'kleur' AND taal_id = 1");
            
            while ( resultSet.next() ){
                kleur.getItems().addAll(
                      resultSet.getString("tekst")
                );
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(GevondenController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
