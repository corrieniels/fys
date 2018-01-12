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
 * @author bob_b
 */
public class GevondenMatches {
    
    private IntegerProperty nummer;
    private StringProperty kleur;
    private StringProperty bijzonder;
    private StringProperty naam;
    private StringProperty telefoon;

    public GevondenMatches(int nummer, String kleur, String bijzonder, String naam, String telefoon) {
        this.nummer = new SimpleIntegerProperty(nummer);
        this.kleur = new SimpleStringProperty(kleur);
        this.bijzonder = new SimpleStringProperty(bijzonder);
        this.naam = new SimpleStringProperty(naam);
        this.telefoon = new SimpleStringProperty(telefoon);
    }

 
    public int getNummer() {
        return nummer.get();
    }

    public void setNummer(int value) {
        nummer.set(value);
    }

    public String getKleur() {
        return kleur.get();
    }


    public void setKleur(String value) {
        kleur.set(value);
    }

    
    public String getBijzonder() {
        return bijzonder.get();
    }

    public void setBijzonder(String value) {
        bijzonder.set(value);
    }


    public String getNaam() {
       return naam.get();
    }

    
    public void setNaam(String value) {
        naam.set(value);
    }
 
    public String getTelefoon() {
        return telefoon.get();
    }


    public void setTelefoon(String value) {
       telefoon.set(value);
    }
    
}
