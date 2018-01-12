/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ezfinder3;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Yassine
 */
public class Management {

    private final StringProperty gebruikersnaam;
    private final StringProperty taal;
    private final StringProperty type;
    private final StringProperty actief;
    //Constructors
    public Management(String gebruikersnaam, String taal, String type, String actief) {
        this.gebruikersnaam = new SimpleStringProperty(gebruikersnaam);
        this.taal = new SimpleStringProperty(taal);
        this.type = new SimpleStringProperty(type);
        this.actief = new SimpleStringProperty(actief);
    }
    //getters
    public String getGebruikersnaam() {
        return gebruikersnaam.get();
    }

    public String getTaal() {
        return taal.get();
    }

    public String getType() {
        return type.get();
    }

    public String getArchief() {
        return actief.get();
    }

    //setters
    public void setGebruikersnaam(String value) {
        gebruikersnaam.set(value);
    }

    public void setTaal(String value) {
        taal.set(value);
    }

    public void setType(String value) {
        type.set(value);
    }

    public void setArchief(String value) {
        actief.set(value);
    }
    //Property values
    public StringProperty gebruikersnaamProperty() {
        return gebruikersnaam;
    }

    public StringProperty taalProperty() {
        return taal;
    }

    public StringProperty typeProperty() {
        return type;
    }

    public StringProperty archiefProperty() {
        return actief;
    }

}
