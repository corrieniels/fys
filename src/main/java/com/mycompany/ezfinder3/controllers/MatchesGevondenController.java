package com.mycompany.ezfinder3.controllers;

import com.mycompany.ezfinder3.GevondenMatches;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;

public class MatchesGevondenController implements Initializable {

    @FXML
    private TableView matchesGevonden;
               
    private final ObservableList<GevondenMatches> foundLuggageList
            = FXCollections.observableArrayList();
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
   
        foundLuggageList.add(new GevondenMatches(get.nummer,"kut","hallo","maan","piem"));
    }
}
