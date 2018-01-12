/*
 * alle getters en setters en properties
 */
package com.mycompany.ezfinder3;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Julian Davies
 */
public class MatchVerloren {

    private final StringProperty nummer;
    private final StringProperty kleur;
    private final StringProperty bijzonderheden;

    //Default constructor
    public MatchVerloren(String nummer, String kleur, String bijzonderheden) {
        this.nummer = new SimpleStringProperty(nummer);
        this.kleur = new SimpleStringProperty(kleur);
        this.bijzonderheden = new SimpleStringProperty(bijzonderheden);
    }

    //getters
    public String getNummer() {
        return nummer.get();
    }

    public String getKLeur() {
        return kleur.get();
    }

    public String getBijzonderheden() {
        return bijzonderheden.get();
    }

    //setters
    public void setNummer(String value) {
        nummer.set(value);
    }

    public void setKleur(String value) {
        kleur.set(value);
    }

    public void setBijzonderheden(String value) {
        bijzonderheden.set(value);
    }

    //property values
    public StringProperty nummerProperty() {
        return nummer;
    }

    public StringProperty kleurProperty() {
        return kleur;
    }

    public StringProperty bijzonderheden() {
        return bijzonderheden;
    }
    
    //property values
   
    
}
