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

    private void loadData() {

        gevonden_koffers.getChildren().clear();
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Maanden");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Aantal");
        BarChart test = new BarChart(xAxis, yAxis);
        test.setTitle("Gevonden koffers");
        XYChart.Series series = new XYChart.Series();
        series.setName("Aantal");
        test.getData().add(series);
        gevonden_koffers.getChildren().add(test);

    }

    private void LoadData() {

        gematchte_koffers.getChildren().clear();
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Maanden");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Aantal");
        BarChart test2 = new BarChart(xAxis, yAxis);
        test2.setTitle("Gemachte koffers");
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Aantal");
        test2.getData().add(series1);
        gematchte_koffers.getChildren().add(test2);
    }
    
    private void LoadData2() {
        
        kosten.getChildren().clear();
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Maanden");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Aantal");
        BarChart test3 = new BarChart(xAxis, yAxis);
        test3.setTitle("kosten");
        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Aantal");
        test3.getData().add(series2);
        kosten.getChildren().add(test3);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        loadData();
        LoadData();
        LoadData2();

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
                        resultSet.getString("jaar")
                );
            }
            
            //vul jaar combobox
            resultSet = db.executeResultSetQuery("SELECT * FROM vliegveld");

            while (resultSet.next()) {
                jaar.getItems().addAll(
                        resultSet.getString("jaar")
                );
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(GebruikerController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
