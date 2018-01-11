package com.mycompany.ezfinder3.controllers;

import com.mycompany.ezfinder3.MyJDBC;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;

public class RapportagesController implements Initializable {

    @FXML
    ComboBox vliegveld;
    @FXML
    AnchorPane gevonden_koffers;
    @FXML
    AnchorPane gematchte_koffers;
    @FXML
    AnchorPane kosten;
    @FXML
    ComboBox categorie;
    @FXML
    ComboBox jaar;

    private MyJDBC db;
    private ResultSet resultSet;

    private void loadData(AnchorPane ap, String titel) {

        ap.getChildren().clear();
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Maanden");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Aantal");
        BarChart bc = new BarChart(xAxis, yAxis);
        bc.setTitle(titel);
        XYChart.Series series = new XYChart.Series();
        series.setName("2003");
        series.getData().add(new XYChart.Data("bc", 25601.34));
        series.getData().add(new XYChart.Data("bc2", 201.34));
        bc.getData().add(series);
        ap.getChildren().add(bc);

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        loadData(gevonden_koffers, "Gevonden koffers");
        loadData(gematchte_koffers, "Gematchte koffers");
        loadData(kosten, "Kosten");

        db = new MyJDBC();
        try {

            // vul naam combobox
            resultSet = db.executeResultSetQuery("SELECT * FROM vliegveld");

            while (resultSet.next()) {
                vliegveld.getItems().addAll(
                        resultSet.getString("naam")
                );
            }

            // vul categorie combobox
            resultSet = db.executeResultSetQuery("SELECT * FROM vliegveld");

            while (resultSet.next()) {
                categorie.getItems().addAll(
                        resultSet.getString("naam")
                );
            }

            //vul jaar combobox
            resultSet = db.executeResultSetQuery("SELECT * FROM vliegveld");

            while (resultSet.next()) {
                jaar.getItems().addAll(
                        resultSet.getString("naam")
                );
            }

        } catch (SQLException ex) {
            Logger.getLogger(GebruikerController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
