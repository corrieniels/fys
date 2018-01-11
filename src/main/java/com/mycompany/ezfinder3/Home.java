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
 * @author Justin
 */
public class Home {

    private final IntegerProperty nummer;
    private final StringProperty kleur;
    private final StringProperty bijzonder;
    private final StringProperty naam;
    private final StringProperty email;
    private final StringProperty telefoon;
    private final StringProperty status;

    public Home(int nummer, String kleur, String bijzonder, String naam, String email, String telefoon, String status) {
        this.nummer = new SimpleIntegerProperty(nummer);
        this.kleur = new SimpleStringProperty(kleur);
        this.bijzonder = new SimpleStringProperty(bijzonder);
        this.naam = new SimpleStringProperty(naam);
        this.email = new SimpleStringProperty(email);
        this.telefoon = new SimpleStringProperty(telefoon);
        this.status = new SimpleStringProperty(status);
    }

    public int getNummer() {
        return nummer.get();
    }

    public String getKleur() {
        return kleur.get();
    }

    public String getBijzonder() {
        return bijzonder.get();
    }

    public String naam() {
        return naam.get();
    }

    public String email() {
        return email.get();
    }

    public String telefoon() {
        return telefoon.get();
    }

    public String status() {
        return status.get();
    }

    public void setNummer(int value) {
        nummer.set(value);
    }

    public void setKleur(String value) {
        kleur.set(value);
    }

    public void setBijzonder(String value) {
        bijzonder.set(value);
    }

    public void setNaam(String value) {
        naam.set(value);
    }

    public void setEmail(String value) {
        email.set(value);
    }

    public void setTelefoon(String value) {
        telefoon.set(value);
    }

    public void setStatus(String value) {
        status.set(value);
    }

    public IntegerProperty nummerProperty() {
        return nummer;
    }

    public StringProperty kleurProperty() {
        return kleur;
    }

    public StringProperty bijzProperty() {
        return bijzonder;
    }

    public StringProperty naamProperty() {
        return naam;
    }

    public StringProperty emailProperty() {
        return email;
    }

    public StringProperty telefoonProperty() {
        return telefoon;
    }

    public StringProperty statusProperty() {
        return status;
    }

}
