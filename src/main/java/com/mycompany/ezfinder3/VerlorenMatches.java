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
public class VerlorenMatches {
    
    private IntegerProperty nummer;
    private IntegerProperty kleur;
    private StringProperty bijzonder;

    public VerlorenMatches(int nummer, int kleur, String bijzonder) {
        this.nummer = new SimpleIntegerProperty(nummer);
        this.kleur = new SimpleIntegerProperty(kleur);
        this.bijzonder = new SimpleStringProperty(bijzonder);
    }

    public int getNummer() {
        return nummer.get();
    }

    public void setNummer(int value) {
        nummer.set(value);
    }

    public int getKleur() {
        return kleur.get();
    }


    public void setKleur(int value) {
        kleur.set(value);
    }

    
    public String getBijzonder() {
        return bijzonder.get();
    }

    public void setBijzonder(String value) {
        bijzonder.set(value);
    }
    
}
