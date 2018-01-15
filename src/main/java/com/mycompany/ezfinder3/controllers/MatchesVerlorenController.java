package com.mycompany.ezfinder3.controllers;

import com.mycompany.ezfinder3.MatchVerloren;
import com.mycompany.ezfinder3.MyJDBC;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    

    //initiakize observable list to hold out database date
    private ObservableList<MatchVerloren> data;
    private MyJDBC dc;
    private ResultSet resultSet;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dc = new MyJDBC();

        data = FXCollections.observableArrayList();
    
        try {
            //Excecute query and store results in a resultset
            resultSet = dc.executeResultSetQuery("SELECT bijzonder, bagagenummer, kleur FROM fys.bagage;" );
            while (resultSet.next()) {
                //get string from db, 
                data.add(new MatchVerloren(resultSet.getString(2), resultSet.getString(3), resultSet.getString(1)));
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

