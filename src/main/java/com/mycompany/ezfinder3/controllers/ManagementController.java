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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
    Button Delete;

    @FXML
    private void goGebruiker(ActionEvent event) {
        MainApp.switchScherm("fxml/gebruiker.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        db = new MyJDBC();
        data = FXCollections.observableArrayList();

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

        
        Delete = new Button ("Delete");
        
        
        
        
        
        
        
        
//        Alert alert = new Alert(AlertType.CONFIRMATION);
//        alert.setTitle("Confirmation Dialog");
//        alert.setHeaderText("Look, a Confirmation Dialog");
//        alert.setContentText("Are you ok with this?");
//
//        Optional<ButtonType> result = alert.showAndWait();
//        if (result.get() == ButtonType.OK){
//        // ... user chose OK
//}       else {
//        // ... user chose CANCEL or closed the dialog
//}
        
        
        
    }
    

}
