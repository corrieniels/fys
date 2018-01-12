package com.mycompany.ezfinder3.controllers;

import com.mycompany.ezfinder3.MainApp;
import com.mycompany.ezfinder3.MyJDBC;
import com.mycompany.ezfinder3.User;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

public class LoginController implements Initializable {
    
    private MyJDBC db;
    
    @FXML TextField gebruikersnaam;
    @FXML PasswordField wachtwoord;
    @FXML Label message;
    
    @FXML
    private void goHome(ActionEvent event) throws IOException, SQLException {
        
        String user = gebruikersnaam.getText();
        String pass = wachtwoord.getText();
        
        ResultSet resultSet = db.executeResultSetQuery("SELECT * FROM medewerker WHERE gebruikersnaam = '"+user+"' AND wachtwoord = '"+pass+"'");
        
        int id = 0;
       
        int role = 0;
        
        while ( resultSet.next() ){
            id = resultSet.getInt("id");
            role = resultSet.getInt("rol_id");
        }
        
        if ( id != 0 ) {
            User.setUserID(id);
            User.setUserRole(role);
            MainApp.switchScherm("fxml/home.fxml");
            MainApp.setMenu(true);     
        }else{
            message.setText("Gebruikersnaam/wachtwoord combinatie ongeldig");
        }
       
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        db = new MyJDBC();
    }
}
