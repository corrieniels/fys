package com.mycompany.ezfinder3.controllers;

import com.mycompany.ezfinder3.MainApp;
import com.mycompany.ezfinder3.VerlorenMatches;
import static com.mycompany.ezfinder3.MainApp.db;
import com.mycompany.ezfinder3.MyJDBC;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class MatchesVerlorenController implements Initializable {

    private MyJDBC db;
    private ResultSet resultSet;
    private ObservableList<VerlorenMatches> data;
    
    private static int id;
    private static String voornaam;
    private static String achternaam;
    private static String email;
    private static String telefoon;
    private static String bijzonder;
    private static int kleur;
    private static int vlieg;

    @FXML private TableView<VerlorenMatches> matchesVerloren;
    @FXML private TableColumn<VerlorenMatches, Integer> Nummer;
    @FXML private TableColumn<VerlorenMatches, Integer> Kleur;
    @FXML private TableColumn<VerlorenMatches, String> Bijzonderheden;
    @FXML private Label label;
    
    public static void setID(int newId){ id = newId; }
    public static int getID(){ return id; }
    public static void setBijzonder(String newBijzonder){ bijzonder = newBijzonder; }
    public static String getBijzonder(){ return bijzonder; }
    public static void setKleur(int newKleur){ kleur = newKleur; }
    public static int getKleur(){ return kleur; }
    public static void setVlieg(int newVlieg){ vlieg = newVlieg; }
    public static int getVlieg(){ return vlieg; }
    public static void setVoornaam(String newVoornaam){ voornaam = newVoornaam; }
    public static String getVoornaam(){ return voornaam; }
    public static void setAchternaam(String newAchternaam){ achternaam = newAchternaam; }
    public static String getAchternaam(){ return achternaam; }
    public static void setEmail(String newEmail){ email = newEmail; }
    public static String getEmail(){ return email; }
    public static void setTelefoon(String newTelefoon){ telefoon = newTelefoon; }
    public static String getTelefoon(){ return telefoon; }    

    @FXML
    private void terug(ActionEvent event) throws IOException, SQLException {
        MainApp.switchScherm("fxml/verloren.fxml");
    }
    
    @FXML
    private void match(ActionEvent event) throws IOException, SQLException {
        db.executeUpdateQuery("INSERT INTO `bagagestatus` (`id`, `bagagenummer`, `status`, `datum`) VALUES (NULL, '"+getID()+"', '2', NOW()) ");
        MainApp.switchScherm("fxml/verloren.fxml");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        db = new MyJDBC();
        data = FXCollections.observableArrayList();
        
        // Voer klantgegevens in 
        int klantId = db.executeUpdateQueryReturnID("INSERT INTO `klant` (`id`, `voornaam`, `achternaam`, `email`, `telefoon`) VALUES (NULL, '"+getVoornaam()+"', '"+getAchternaam()+"', '"+getEmail()+"', '"+getTelefoon()+"')");
        
        // Voer bagagegegevens in 
        int executeUpdateQuery = db.executeUpdateQuery("INSERT INTO `bagage` (`bagagenummer`, `vliegveld_id`, `kleur`, `foto`, `kosten`, `bijzonder`, `klant_id`) VALUES ('"+getID()+"', '"+getVlieg()+"', '"+getKleur()+"', '0', '0', '"+getBijzonder()+"', '"+klantId+"')");
        
        // Wanneer een duplicate primary key wordt gevonden is er een match
        // Geen match
        if (executeUpdateQuery != -1) {            
            db.executeUpdateQuery("INSERT INTO `bagagestatus` (`id`, `bagagenummer`, `status`, `datum`) VALUES (NULL, '"+getID()+"', '0', NOW()) ");
            label.setText("Geen matches gevonden");
        }
        // Mogelijk een match
        else { 
            
            // Selecteer alle gegevens adhv de ID waar de bagage als gevonden staat
            try {
                resultSet = db.executeResultSetQuery(
                    "SELECT b.bagagenummer, "
                    + "b.kleur, "
                    + "b.bijzonder "
                    + "FROM bagage b "
                    + "WHERE b.bagagenummer = " + getID() + " "
                    + "AND (SELECT bs.status FROM bagagestatus bs WHERE bs.bagagenummer = b.bagagenummer ORDER BY bs.datum DESC LIMIT 1) = 1"
                );
                
                // Voeg de resultaten toe aan de dataset
                while (resultSet.next()) {
                    data.add(new VerlorenMatches(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3)));
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(MatchesVerlorenController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }

        Nummer.setCellValueFactory(new PropertyValueFactory<VerlorenMatches, Integer>("Nummer"));
        Kleur.setCellValueFactory(new PropertyValueFactory<VerlorenMatches, Integer>("Kleur"));
        Bijzonderheden.setCellValueFactory(new PropertyValueFactory<VerlorenMatches, String>("Bijzonderheden"));

        // Zet data in tabel
        matchesVerloren.setItems(data);
        
    }
}
