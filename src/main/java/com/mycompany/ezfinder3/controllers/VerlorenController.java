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
import javafx.scene.control.TextField;

public class VerlorenController implements Initializable {
    
    @FXML TextField voornaam;
    @FXML TextField tussenvoegsel;
    @FXML TextField achternaam;
    @FXML TextField email;
    @FXML TextField telefoon;
    @FXML TextField bagagenummer;
    @FXML ComboBox kleur;
    @FXML TextField bijzonder;
    @FXML ComboBox vliegveld;
    
    private MyJDBC db;
    private ResultSet resultSet;
    
    @FXML
    private void verder(ActionEvent event) throws IOException, SQLException {
            
        String strVoornaam = voornaam.getText();
        String strAchternaam = achternaam.getText();
        String strEmail = email.getText();
        String strTelefoon = telefoon.getText();
        String strBagagenummer = bagagenummer.getText();
        int intBagagenummer = Integer.parseInt(strBagagenummer);
        String strKleur = kleur.getSelectionModel().getSelectedItem().toString();
        String strBijzonderheden = bijzonder.getText();
        String strVliegveld = vliegveld.getSelectionModel().getSelectedItem().toString();
        
        // vraag vliegveld_id op adhv vliegveldnaam
        resultSet = db.executeResultSetQuery("SELECT id FROM vliegveld WHERE naam = '"+strVliegveld+"'");

        int vliegId = 0;
        while ( resultSet.next() ){
            vliegId = resultSet.getInt("id");
        }
        
        // vraag kleur_id op adhv kleurnaam
        resultSet = db.executeResultSetQuery("SELECT code FROM `vertaling` WHERE categorie = 'kleur' AND tekst = '"+strKleur+"'");

        int colorId = 0;
        while ( resultSet.next() ){
            colorId = resultSet.getInt("code");
        }
        
        // geef gegevens door naar matchescontroller
        MatchesVerlorenController.setID(intBagagenummer);
        MatchesVerlorenController.setVoornaam(strVoornaam);
        MatchesVerlorenController.setAchternaam(strAchternaam);
        MatchesVerlorenController.setEmail(strEmail);
        MatchesVerlorenController.setTelefoon(strTelefoon);
        MatchesVerlorenController.setVlieg(vliegId);
        MatchesVerlorenController.setBijzonder(strBijzonderheden);
        MatchesVerlorenController.setKleur(colorId);
        
        // ga naar matches scherm
        MainApp.switchScherm("fxml/matches_verloren.fxml");
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