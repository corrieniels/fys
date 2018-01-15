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
    private void verder(ActionEvent event) throws IOException, SQLException {
        
        // vraag gegevens op uit formulier
        String bijzonder = bijzonderheden.getText();
        String num = nummer.getText();
        int intNum = Integer.parseInt(num);
        String color = kleur.getSelectionModel().getSelectedItem().toString();
        String vlieg = vliegveld.getSelectionModel().getSelectedItem().toString();

        // vraag vliegveld_id op adhv vliegveldnaam
        resultSet = db.executeResultSetQuery("SELECT id FROM vliegveld WHERE naam = '"+vlieg+"'");

        int vliegId = 0;
        while ( resultSet.next() ){
            vliegId = resultSet.getInt("id");
        }
        
        // vraag kleur_id op adhv kleurnaam
        resultSet = db.executeResultSetQuery("SELECT code FROM `vertaling` WHERE categorie = 'kleur' AND tekst = '"+color+"'");

        int colorId = 0;
        while ( resultSet.next() ){
            colorId = resultSet.getInt("code");
        }
        System.out.println(colorId);
                
        // voer bagage in in database
       // db.executeUpdateQuery("INSERT INTO `bagage` (`bagagenummer`, `vliegveld_id`, `kleur`, `foto`, `kosten`, `bijzonder`, `klant_id`) VALUES ('"+num+"', '"+vliegId+"', '"+colorId+"', '', '0', '"+bijzonder+"', '0')");
        
        // geef gegevens door naar matchescontroller
        MatchesGevondenController.setID(intNum);
        MatchesGevondenController.setVlieg(vliegId);
        MatchesGevondenController.setBijzonder(bijzonder);
        MatchesGevondenController.setKleur(colorId);

        // ga naar matches scherm
        MainApp.switchScherm("fxml/matches_gevonden.fxml");
        
    }
    
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        db = new MyJDBC();
        
        try {
            
            // vul vliegveld combobox
            resultSet = db.executeResultSetQuery("SELECT * FROM vliegveld");
            
            while ( resultSet.next() ){
                vliegveld.getItems().addAll(
                      resultSet.getString("naam")
                );
            }
            
            // vul kleur combobox
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

