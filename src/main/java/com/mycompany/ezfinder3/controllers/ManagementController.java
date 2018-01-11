package com.mycompany.ezfinder3.controllers;

import com.mycompany.ezfinder3.Home;
import com.mycompany.ezfinder3.Management;
import com.mycompany.ezfinder3.MainApp;
import com.mycompany.ezfinder3.MyJDBC;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

public class ManagementController implements Initializable {
    
    private MyJDBC db;
    private ResultSet resultSet;
    private ObservableList<Management> data;
    
    @FXML
    private TableView<Management> Gebruikertoevoegen;
    @FXML
    private TableColumn<Management, String> kolomGebruikersnaam;
    @FXML
    private TableColumn<Management, String> kolomTaal;
    @FXML
    private TableColumn<Management, String> kolomType;
    @FXML
    private TableColumn<Management, String> kolomArchief;
    
    @FXML
    private void goGebruiker(ActionEvent event) throws IOException {
        MainApp.switchScherm("fxml/gebruiker.fxml");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        db = new MyJDBC();

        data = FXCollections.observableArrayList();
        
        try {
            resultSet = db.executeResultSetQuery("SELECT * FROM medewerker WHERE gebruikersnaam");
            while (resultSet.next()) {
                data.add(new Management(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4)));
                //resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4)

            }

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

        kolomGebruikersnaam.setCellValueFactory(new PropertyValueFactory<Management, String>("gebruikersnaam"));
        kolomTaal.setCellValueFactory(new PropertyValueFactory<Management, String>("taal"));
        kolomType.setCellValueFactory(new PropertyValueFactory<Management, String>("type"));
        kolomArchief.setCellValueFactory(new PropertyValueFactory<Management, String>("archief"));
        
//        kolomGebruikersnaam.setCellValueFactory(new PropertyValueFactory<Management, String>("gebruikersnaam"));
//        kolomTaal.setCellValueFactory(new PropertyValueFactory<Management, String>("Taal"));
//        kolomType.setCellValueFactory(new PropertyValueFactory<Management, String>("Type"));
//        kolomArchief.setCellValueFactory(new PropertyValueFactory<Management, String>("Archief"));
        
        Gebruikertoevoegen.setItems(null);
        Gebruikertoevoegen.setItems(data);
        
        
        
    }
}
