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

    private void loadData(AnchorPane ap, String titel) throws SQLException {

        db = new MyJDBC();
        
        ap.getChildren().clear();
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Maanden");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Aantal");
        BarChart bc = new BarChart(xAxis, yAxis);
        bc.setTitle(titel);
        
        resultSet = db.executeResultSetQuery("SELECT MONTH(bs.datum) as maand, bs.status, COUNT(bs.status) as aantal FROM bagagestatus bs WHERE bs.datum = ( SELECT MAX(bs2.datum) FROM bagagestatus bs2 WHERE bs.bagagenummer = bs2.bagagenummer ) AND YEAR(bs.datum) = 2017 GROUP BY MONTH(bs.datum), bs.status ");
        
        while ( resultSet.next() ){
            //resultSet.getInt("id");
            
            int maand = resultSet.getInt("maand");
            int status = resultSet.getInt("status");
            int aantal = resultSet.getInt("aantal");
            
            String strMaand = String.valueOf(maand);

            
            XYChart.Series series = new XYChart.Series();
            series.setName( strMaand );
            series.getData().add( new XYChart.Data(strMaand, aantal) );
            bc.getData().add(series);

            
        }
        
//        XYChart.Series series = new XYChart.Series();
//        series.setName("test");
//        series.getData().add(new XYChart.Data("bc", 25601.34));
//        series.getData().add(new XYChart.Data("bc2", 201.34));
//        
//        XYChart.Series series2 = new XYChart.Series();
//        series2.setName("2005");
//        series2.getData().add(new XYChart.Data("bc", 25601.34));
//        series2.getData().add(new XYChart.Data("bc2", 201.34));
//        
//        bc.getData().addAll(series, series2);

        ap.getChildren().add(bc);

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
  
        db = new MyJDBC();

        try {
            loadData(gevonden_koffers, "Gevonden koffers");
            loadData(gematchte_koffers, "Gematchte koffers");
            loadData(kosten, "Kosten");

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
