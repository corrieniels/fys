package com.mycompany.ezfinder3.controllers;

import com.mycompany.ezfinder3.Home;
import com.mycompany.ezfinder3.MyJDBC;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class HomeController implements Initializable {

    private MyJDBC db;
    private ResultSet resultSet;

    @FXML
    private TableView<Home> verlorenBagage;
    @FXML
    private TableColumn<Home, Integer> kolomNummer;
    @FXML
    private TableColumn<Home, String> kolomKleur;
    @FXML
    private TableColumn<Home, String> kolomBijzonder;
    @FXML
    private TableColumn<Home, String> kolomNaam;
    @FXML
    private TableColumn<Home, String> kolomEmail;
    @FXML
    private TableColumn<Home, String> kolomTelefoonnummer;
    @FXML
    private TableColumn<Home, String> kolomStatus;

    private ObservableList<Home> data;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        db = new MyJDBC();

        data = FXCollections.observableArrayList();
        try {
            // resultSet = db.executeResultSetQuery("SELECT ba.bagagenummer, ba.kleur, ba.bijzonder, concat(kl.voornaam, ' ', kl.achternaam) as naam, kl.email, kl.telefoon, bs.status FROM bagage as ba LEFT JOIN klant as kl on ba.klant_id = kl.id LEFT JOIN bagagestatus as bs ON ba.bagagenummer = bs.bagagenummer WHERE status is not NULL ORDER BY bs.datum DESC");
            resultSet = db.executeResultSetQuery(
                    "SELECT ba.bagagenummer, ba.kleur, ba.bijzonder, concat(kl.voornaam, ' ', kl.achternaam) as naam, kl.email, kl.telefoon, \n"
                    + "(SELECT status FROM bagagestatus WHERE bagagenummer = ba.bagagenummer ORDER BY id DESC LIMIT 1), \n"
                    + "(SELECT datum FROM bagagestatus WHERE bagagenummer = ba.bagagenummer ORDER BY id DESC LIMIT 1) "
                    + "FROM bagage as ba LEFT JOIN klant as kl on ba.klant_id = kl.id  ORDER BY ba.bagagenummer"
                    );

            while (resultSet.next()) {
                data.add(new Home(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7)));

            }

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

        kolomNummer.setCellValueFactory(new PropertyValueFactory<Home, Integer>("nummer"));
        kolomKleur.setCellValueFactory(new PropertyValueFactory<Home, String>("kleur"));
        kolomBijzonder.setCellValueFactory(new PropertyValueFactory<Home, String>("bijzonder"));
        kolomNaam.setCellValueFactory(new PropertyValueFactory<Home, String>("naam"));
        kolomEmail.setCellValueFactory(new PropertyValueFactory<Home, String>("email"));
        kolomTelefoonnummer.setCellValueFactory(new PropertyValueFactory<Home, String>("telefoon"));
        kolomStatus.setCellValueFactory(new PropertyValueFactory<Home, String>("status"));

        //verlorenBagage.setItems(null);
        verlorenBagage.setItems(data);
        
    }
}
