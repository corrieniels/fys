package com.mycompany.ezfinder3.controllers;

import com.mycompany.ezfinder3.MainApp;
import com.mycompany.ezfinder3.MyJDBC;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;


    
    public class VerlorenController implements Initializable {
    
    @FXML 
    TextField voornaam;
    
    @FXML 
    TextField tussenvoegsel;
    
    @FXML 
            TextField achternaam;
    @FXML 
            TextField email;
    @FXML 
            TextField telefoon;
    @FXML 
            TextField bagagenummer;
    @FXML 
            TextField kleur;
    @FXML 
            TextField bijzonder;
    @FXML
    TextField vliegveld_id;
    
    private MyJDBC db;
    
    
    //voer de gegevens in
    
    @FXML
    private void verder(ActionEvent event) throws IOException, SQLException {
            
        String strVoornaam = voornaam.getText();
        String strAchternaam = achternaam.getText();
        String strEmail = email.getText();
        String strTelefoon = telefoon.getText();
        String strBagagenummer = bagagenummer.getText();
        String strKleur = kleur.getText();
        String strBijzonderheden = bijzonder.getText();
        String strVliegveldid = vliegveld_id.getText();
        
        db.executeUpdateQuery("INSERT INTO `klant` (`voornaam`, "
                + "`achternaam`, `email`, `telefoon`) VALUES ('"+strVoornaam+"','"+strAchternaam+"','"+strEmail+"','"+strTelefoon+"')");
        
        db.executeUpdateQuery("INSERT INTO `bagage` (`bagagenummer`, `kleur`, "
                + "`bijzonder`, `vliegveld_id`) VALUES ('"+strBagagenummer+"','"+strKleur+"','"+strBijzonderheden+"','"+strVliegveldid+"')");

        
        MainApp.switchScherm("fxml/matches_verloren.fxml");
    }
    
    

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        db = new MyJDBC();
    }
}
