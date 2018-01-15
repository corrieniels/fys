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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class MatchesGevondenController implements Initializable {

    private MyJDBC db;
    private ResultSet resultSet;
    private ObservableList<GevondenMatches> data;
    private static int lastInsertedID;

    @FXML
    private TableView<GevondenMatches> matchesGevonden;
    @FXML
    private TableColumn<GevondenMatches, Integer> Nummer;
    @FXML
    private TableColumn<GevondenMatches, String> Kleur;
    @FXML
    private TableColumn<GevondenMatches, String> Bijzonderheden;
    @FXML
    private TableColumn<GevondenMatches, String> Naam;
    @FXML
    private TableColumn<GevondenMatches, String> Telefoonnummer;
    
    public static void setLastInsertedID(int id){
        lastInsertedID = id;
    }
    
    public static int getLastInsertedID(){
        return lastInsertedID;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            db = new MyJDBC();
            data = FXCollections.observableArrayList();

            resultSet = db.executeResultSetQuery("SELECT \n" +
                "    b.bagagenummer, b.kleur, b.bijzonder, CONCAT(k.voornaam, \" \", k.achternaam) AS naam , k.telefoon \n" +
                "    FROM\n" +
                "    bagage b\n" +
                "    LEFT JOIN \n" +
                "    klant k\n" +
                "    ON\n" +
                "    b.klant_id = k.id\n" +
                "    WHERE b.bagagenummer = "+getLastInsertedID());
            while (resultSet.next()) {

                data.add(new GevondenMatches(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5)));
            }

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

        Nummer.setCellValueFactory(new PropertyValueFactory<GevondenMatches, Integer>("Nummer"));
        Kleur.setCellValueFactory(new PropertyValueFactory<GevondenMatches, String>("Kleur"));
        Bijzonderheden.setCellValueFactory(new PropertyValueFactory<GevondenMatches, String>("Bijzonder"));
        Naam.setCellValueFactory(new PropertyValueFactory<GevondenMatches, String>("Naam"));
        Telefoonnummer.setCellValueFactory(new PropertyValueFactory<GevondenMatches, String>("Telefoon"));
        matchesGevonden.setItems(data);
    }
}
