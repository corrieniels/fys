package com.mycompany.ezfinder3.controllers;

import com.mycompany.ezfinder3.Language;
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
    
    @FXML
    private void goManagement(ActionEvent event) throws IOException, SQLException {
        
        String user = gebruikersnaam.getText();
        String pass = wachtwoord.getText();
        String role = type.getSelectionModel().getSelectedItem().toString();
        String lang = taal.getSelectionModel().getSelectedItem().toString();
        
        System.out.println(user+" - "+pass+" - "+role+" - "+lang);
        
        MyJDBC db = new MyJDBC();
//        db.executeUpdateQuery("INSERT INTO medewerker (gebruikersnaam, wachtwoord, taal, rol_id, actief) VALUES (`"+user+"`,`"+pass+"`,`"+role+"`,`"+lang+"`,`1`)");
        
        MainApp.switchScherm("fxml/management.fxml");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        db = new MyJDBC();
        try {
            
            ResultSet resultSet = db.executeResultSetQuery("SELECT * FROM taal");
            
            while ( resultSet.next() ){
                taal.getItems().addAll(
                    resultSet.getString("naam")
                );
            }
            
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
