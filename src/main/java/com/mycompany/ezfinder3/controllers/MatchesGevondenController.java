package com.mycompany.ezfinder3.controllers;

import com.mycompany.ezfinder3.GevondenMatches;
import static com.mycompany.ezfinder3.MainApp.db;
import com.mycompany.ezfinder3.MyJDBC;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class MatchesGevondenController implements Initializable {

    private MyJDBC db;
    private ResultSet resultSet;
    private ObservableList<GevondenMatches> data;
    
    private static int id;
    private static String bijzonder;
    private static int kleur;
    private static int vlieg;

    @FXML private TableView<GevondenMatches> matchesGevonden;
    @FXML private TableColumn<GevondenMatches, Integer> Nummer;
    @FXML private TableColumn<GevondenMatches, String> Kleur;
    @FXML private TableColumn<GevondenMatches, String> Bijzonderheden;
    @FXML private TableColumn<GevondenMatches, String> Naam;
    @FXML private TableColumn<GevondenMatches, String> Telefoonnummer;
    @FXML private Label label;
    
    public static void setID(int newId){ id = newId; }
    public static int getID(){ return id; }
    public static void setBijzonder(String newBijzonder){ bijzonder = newBijzonder; }
    public static String getBijzonder(){ return bijzonder; }
    public static void setKleur(int newKleur){ kleur = newKleur; }
    public static int getKleur(){ return kleur; }
    public static void setVlieg(int newVlieg){ vlieg = newVlieg; }
    public static int getVlieg(){ return vlieg; }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        db = new MyJDBC();
        data = FXCollections.observableArrayList();
        
        // Voer bagagegegevens in 
        int executeUpdateQuery = db.executeUpdateQuery("INSERT INTO `bagage` (`bagagenummer`, `vliegveld_id`, `kleur`, `foto`, `kosten`, `bijzonder`, `klant_id`) VALUES ('"+getID()+"', '"+getVlieg()+"', '"+getKleur()+"', '0', '0', '"+getBijzonder()+"', '0')");
        
        // Wanneer een duplicate primary key wordt gevonden is er een match
        // Geen match
        if (executeUpdateQuery != -1) {            
            db.executeUpdateQuery("INSERT INTO `bagagestatus` (`id`, `bagagenummer`, `status`, `datum`) VALUES (NULL, '"+getID()+"', '1', NOW()) ");
            label.setText("Geen matches gevonden");
        }
        // Mogelijk een match
        else { 
            
            // Selecteer alle gegevens adhv de ID waar de bagage als vermist staat
            try {
                resultSet = db.executeResultSetQuery(
                    "SELECT b.bagagenummer, "
                    + "b.kleur, "
                    + "b.bijzonder, "
                    + "CONCAT(k.voornaam, \" \", k.achternaam) AS naam, "
                    + "k.telefoon "
                    + "FROM bagage b "
                    + "LEFT JOIN klant k "
                    + "ON b.klant_id = k.id "
                    + "WHERE b.bagagenummer = " + getID() + " "
                    + "AND (SELECT bs.status FROM bagagestatus bs WHERE bs.bagagenummer = b.bagagenummer ORDER BY bs.datum DESC LIMIT 1) = 0"
                );
                
                // Voeg de resultaten toe aan de dataset
                while (resultSet.next()) {
                    data.add(new GevondenMatches(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5)));
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(MatchesGevondenController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }

        Nummer.setCellValueFactory(new PropertyValueFactory<GevondenMatches, Integer>("Nummer"));
        Kleur.setCellValueFactory(new PropertyValueFactory<GevondenMatches, String>("Kleur"));
        Bijzonderheden.setCellValueFactory(new PropertyValueFactory<GevondenMatches, String>("Bijzonder"));
        Naam.setCellValueFactory(new PropertyValueFactory<GevondenMatches, String>("Naam"));
        Telefoonnummer.setCellValueFactory(new PropertyValueFactory<GevondenMatches, String>("Telefoon"));

        // Zet data in tabel
        matchesGevonden.setItems(data);
        
    }
}
