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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
    Button Actief;
    
   @FXML
    private void goGebruiker(ActionEvent event) {       
        MainApp.switchScherm("fxml/gebruiker.fxml");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        db = new MyJDBC();
        data = FXCollections.observableArrayList();
        
        try {
            resultSet = db.executeResultSetQuery("SELECT gebruikersnaam, taal, rol_id, actief FROM medewerker");
            while (resultSet.next()) {

                data.add(   
                    new Management(
                            resultSet.getString("gebruikersnaam"),
                            resultSet.getString("taal"),
                            resultSet.getString("rol_id"),
                            resultSet.getString("actief")
                    )
                );
                //resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4)
            }

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
        
          kolomGebruikersnaam.setCellValueFactory(new PropertyValueFactory<Management,String>("gebruikersnaam"));
          kolomTaal.setCellValueFactory(new PropertyValueFactory<Management,String>("taal"));
          kolomType.setCellValueFactory(new PropertyValueFactory<Management,String>("type"));
          kolomActief.setCellValueFactory(new PropertyValueFactory<Management,String>("archief"));
        
          content.setItems(data);
          
    }

}