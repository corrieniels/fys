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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class GebruikerController implements Initializable {

    @FXML TextField gebruikersnaam;
    @FXML TextField wachtwoord;
    @FXML ComboBox type;
    @FXML ComboBox taal;
    @FXML Label message;
    
    private MyJDBC db;
    private ResultSet resultSet;
    
    @FXML
    private void goManagement(ActionEvent event) throws IOException, SQLException {
        
        // vraag gegevens op uit formulier
        String user = gebruikersnaam.getText();
        String pass = wachtwoord.getText();
        String role = type.getSelectionModel().getSelectedItem().toString();
        String lang = taal.getSelectionModel().getSelectedItem().toString();
        
        // controleer of gebruikersnaam al bestaat 
        resultSet = db.executeResultSetQuery("SELECT * FROM medewerker WHERE gebruikersnaam = '"+user+"'");
        
        int records = 0;
        while ( resultSet.next() ){
            records++;
        }
        
        // als gebruikersnaam nog niet bestaat
        if (records == 0) {
        
            // vraag rol-id op
            resultSet = db.executeResultSetQuery("SELECT id FROM rol WHERE naam = '"+role+"'");

            int roleid = 0;
            while ( resultSet.next() ){
                roleid = resultSet.getInt("id");
            }

            // vraag taal-id op
            resultSet = db.executeResultSetQuery("SELECT id FROM taal WHERE naam = '"+lang+"'");

            int langid = 0;
            while ( resultSet.next() ){
                langid = resultSet.getInt("id");
            }

            // voer gebruiker in in database
            db.executeUpdateQuery("INSERT INTO `medewerker` (`gebruikersnaam`, `wachtwoord`, `taal`, `rol_id`, `actief`) VALUES ('"+user+"','"+pass+"','"+roleid+"','"+langid+"','1')");

            // ga naar managementscherm
            MainApp.switchScherm("fxml/management.fxml");
        
        }else{
            message.setText("Gebruikersnaam bestaat al");
        }
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        db = new MyJDBC();
        try {
            
            // vul taal combobox
            resultSet = db.executeResultSetQuery("SELECT * FROM taal");
            
            while ( resultSet.next() ){
                taal.getItems().addAll(
                    resultSet.getString("naam")
                );
            }
            
            // vul rol combobox
            resultSet = db.executeResultSetQuery("SELECT * FROM rol");
            
            while ( resultSet.next() ){
                type.getItems().addAll(
                    resultSet.getString("naam")
                );
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(GebruikerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    
    
}
