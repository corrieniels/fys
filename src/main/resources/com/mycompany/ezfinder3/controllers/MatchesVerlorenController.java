/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ezfinder3.controllers;

import com.mycompany.ezfinder3.MatchVerloren;
import com.mycompany.ezfinder3.MyJDBC;
import java.net.URL;
import java.sql.Connection;
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

/**
 * FXML Controller class
 *
 * @author Julian Davies
 */
public class MatchesVerlorenController implements Initializable {

    @FXML
    private TableView<MatchVerloren> tableMatches;
    
    @FXML
    private TableColumn<MatchVerloren, String> kolomNummer;
    @FXML
    private TableColumn<MatchVerloren, String> kolomKleur;
    @FXML
    private TableColumn<MatchVerloren, String> kolomBijzonderheden;
    @FXML
    private Button btnLoad;

    //initiakize observable list to hold out database date
    private ObservableList<MatchVerloren> data;
    private MyJDBC dc;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dc = new MyJDBC();
    }

    @FXML
    private void loadDataFromDatabase(ActionEvent event) {
        try {
            Connection conn = dc.Connect();
            data = FXCollections.observableArrayList();
            //Excecute query and store results in a resultset
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM fys.bagage;");
            while (rs.next()) {
                //get string from db, 
                data.add(new MatchVerloren(rs.getString(2), rs.getString(3), rs.getString(4)));
            }

        } catch (SQLException ex) {
            System.err.println("Error"+ex); 
        }
        //Set cell value factory to tableview
        //NB PropertValues factory must be the same with the one set in model class
        kolomNummer.setCellValueFactory(new PropertyValueFactory<MatchVerloren, String>("nummer"));
        kolomKleur.setCellValueFactory(new PropertyValueFactory<MatchVerloren, String>("kleur"));
        kolomBijzonderheden.setCellValueFactory(new PropertyValueFactory<MatchVerloren, String>("bijzonderheden"));
        
        tableMatches.setItems(null);
        tableMatches.setItems(data);
        
    }
}
