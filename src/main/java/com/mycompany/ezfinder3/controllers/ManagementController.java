package com.mycompany.ezfinder3.controllers;

import com.mycompany.ezfinder3.Home;
import com.mycompany.ezfinder3.Management;
import com.mycompany.ezfinder3.MainApp;
import com.mycompany.ezfinder3.MyJDBC;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

public class ManagementController implements Initializable {

    private MyJDBC db;
    private ResultSet resultSet;
    private ObservableList<Management> data;

    @FXML
    private TableView<Management> content;
    @FXML
    private TableColumn<Management, String> kolomGebruikersnaam;
    @FXML
    private TableColumn<Management, String> kolomTaal;
    @FXML
    private TableColumn<Management, String> kolomType;
    @FXML
    private TableColumn<Management, String> kolomActief;
    @FXML
    Button Delete;
    @FXML
    TextField Gebruikerszoeken;
    @FXML
    Button zoekbutton;
    @FXML
    private void goGebruiker(ActionEvent event) {

        MainApp.switchScherm("fxml/gebruiker.fxml");
    }

    private void loadDefaultTable() {

        try {
            resultSet = db.executeResultSetQuery("SELECT m.gebruikersnaam, "
                    + "t.naam as taal, r.naam as rol, (CASE WHEN m.actief <> 0 "
                    + "THEN 'ja' ELSE 'nee' END) as actief FROM medewerker m "
                    + "INNER JOIN taal t ON m.taal = t.id INNER JOIN rol r ON "
                    + "m.rol_id = r.id");
            while (resultSet.next()) {

                data.add(
                        new Management(
                                resultSet.getString("gebruikersnaam"),
                                resultSet.getString("taal"),
                                resultSet.getString("rol"),
                                resultSet.getString("actief")
                        )
                );
            }

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

        kolomGebruikersnaam.setCellValueFactory(new PropertyValueFactory<Management, String>("gebruikersnaam"));
        kolomTaal.setCellValueFactory(new PropertyValueFactory<Management, String>("taal"));
        kolomType.setCellValueFactory(new PropertyValueFactory<Management, String>("type"));
        kolomActief.setCellValueFactory(new PropertyValueFactory<Management, String>("archief"));

        content.setItems(data);
        
        
        
       //Waarschuwing
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle(" ");
        alert.setHeaderText("Managment Scherm");
        alert.setContentText("Weet wel dat alleen Admins hier op mogen komen!!");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
        // ... user chose OK
}       else {
        // ... user chose CANCEL or closed the dialog
}
    }

    //zoek functie
    @FXML
    private void zoekButton(ActionEvent event) throws IOException, SQLException {

        String searchQuery = "%" + Gebruikerszoeken.getText() + "%";
        content.getItems().clear();

        if (searchQuery.length() > 0) {

            try {

                resultSet = db.executeResultSetQuery(""
                        + "SELECT m.gebruikersnaam, t.naam as taal, r.naam as rol, (CASE WHEN m.actief <> 0 \n"
                        + "THEN 'ja' ELSE 'nee' END) as actief FROM medewerker m \n"
                        + "INNER JOIN taal t ON m.taal = t.id INNER JOIN rol r ON m.rol_id = r.id\n"
                        + "WHERE lower(m.gebruikersnaam) LIKE '"+searchQuery+"'\n"
                        + "OR  lower(t.naam) like '"+searchQuery+"'\n"
                        + "OR lower(r.naam) like '"+searchQuery+"'\n"
                        + "OR lower(actief) like '"+searchQuery+"'");
                while (resultSet.next()) {

                    data.add(
                            new Management(
                                    resultSet.getString("gebruikersnaam"),
                                    resultSet.getString("taal"),
                                    resultSet.getString("rol"),
                                    resultSet.getString("actief")
                            )
                    );
                }

            } catch (SQLException ex) {
                System.err.println("Error" + ex);
            }

            kolomGebruikersnaam.setCellValueFactory(new PropertyValueFactory<Management, String>("gebruikersnaam"));
            kolomTaal.setCellValueFactory(new PropertyValueFactory<Management, String>("taal"));
            kolomType.setCellValueFactory(new PropertyValueFactory<Management, String>("type"));
            kolomActief.setCellValueFactory(new PropertyValueFactory<Management, String>("archief"));

            content.setItems(data);

        } else {
            loadDefaultTable();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        db = new MyJDBC();
        data = FXCollections.observableArrayList();

        loadDefaultTable();

    }
    
    
    
    
    

//    private void zoekenButton(ActionEvent event) throws IOException, SQLException {
//
//        String searchQuery = "%"+zoekenBalk.getText()+"%";
//        verlorenBagage.getItems().clear();
//
//        if ( searchQuery.length() > 0 ) {
//
//            try {
//                // resultSet = db.executeResultSetQuery("SELECT ba.bagagenummer, ba.kleur, ba.bijzonder, concat(kl.voornaam, ' ', kl.achternaam) as naam, kl.email, kl.telefoon, bs.status FROM bagage as ba LEFT JOIN klant as kl on ba.klant_id = kl.id LEFT JOIN bagagestatus as bs ON ba.bagagenummer = bs.bagagenummer WHERE status is not NULL ORDER BY bs.datum DESC");
//                resultSet = db.executeResultSetQuery(
//                        "SELECT \n"
//                        + "     ba.bagagenummer, \n"
//                        + "    (SELECT tekst FROM vertaling WHERE code = ba.kleur AND taal_id = 1 ) as kleur,\n"
//                        + "    ba.bijzonder, \n"
//                        + "    concat(kl.voornaam, ' ', kl.achternaam) as naam, \n"
//                        + "    kl.email, \n"
//                        + "    kl.telefoon,\n"
//                        + "    (SELECT CASE WHEN status <> 0 THEN "gevonden" ELSE "verloren" END FROM bagagestatus WHERE bagagenummer =
//ba.bagagenummer ORDER BY id DESC LIMIT 1) as status,  \n"
//                        + "    (SELECT datum FROM bagagestatus WHERE bagagenummer = ba.bagagenummer ORDER BY id DESC LIMIT 1) \n"
//                        + "    FROM bagage as ba \n"
//                        + "    LEFT JOIN klant as kl \n"
//                        + "    ON ba.klant_id = kl.id \n"
//                        + "    WHERE lower(ba.bagagenummer) LIKE '"+searchQuery+"' \n"
//                        + "    OR lower(ba.bijzonder) LIKE '"+searchQuery+"'\n"
//                        + "    OR lower(kl.voornaam) LIKE '"+searchQuery+"' \n"
//                        + "    OR lower(kl.achternaam) LIKE '"+searchQuery+"' \n"
//                        + "    OR lower(kl.email) LIKE '"+searchQuery+"' \n"
//                        + "    OR lower(kl.telefoon) LIKE '"+searchQuery+"' \n"
//                        + "    OR lower( (SELECT tekst FROM vertaling WHERE code = ba.kleur AND taal_id = 1 ) ) LIKE '"+searchQuery+"' \n"
//                        + "    OR (SELECT CASE WHEN status <> 0 THEN "gevonden" ELSE "verloren" END FROM bagagestatus WHERE bagagenummer = ba.bagagenummer ORDER BY id DESC LIMIT 1) LIKE '"+searchQuery+"' \n"
//                        + "    ORDER BY
//ba.bagagenummer"
//                );
//                while (resultSet.next()) {
//                    data.add(new Home(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7)));
//                }
//
//            } catch (SQLException ex) {
//                System.err.println("Error" + ex);
//            }
//
//            kolomNummer.setCellValueFactory(new PropertyValueFactory<Home, Integer>("nummer"));
//            kolomKleur.setCellValueFactory(new PropertyValueFactory<Home, String>("kleur"));
//            kolomBijzonder.setCellValueFactory(new PropertyValueFactory<Home, String>("bijzonder"));
//            kolomNaam.setCellValueFactory(new PropertyValueFactory<Home, String>("naam"));
//            kolomEmail.setCellValueFactory(new PropertyValueFactory<Home, String>("email"));
//            kolomTelefoonnummer.setCellValueFactory(new PropertyValueFactory<Home, String>("telefoon"));
//            kolomStatus.setCellValueFactory(new PropertyValueFactory<Home, String>("status"));
//
//            //verlorenBagage.setItems(null);
//            verlorenBagage.setItems(data);
//
//        }else{
//            loadDefaultTable();
//        }
}
