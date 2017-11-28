package src;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

public class FXMLDocumentController implements Initializable {

    // Vraag content aan die binnen je AnchorPane, VBox/HBox kan zitten.
    // De variabele naam is je fx:id="";
    @FXML
    private AnchorPane APBegin;

    // Maak een nieuw event om naar het andere scherm te gaan geef de button eerst een OnAction event
    @FXML
    private void goHome(ActionEvent event) throws IOException {
        //Voorbeeld om naar Borderpane scherm te gaan
        AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("ezfinder2/home.fxml"));
        APBegin.getChildren().setAll(pane);
    }
    @FXML
    private void goZoeken(ActionEvent event) throws IOException {
        //Voorbeeld om naar Borderpane scherm te gaan
        AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("ezfinder2/home.fxml"));
        APBegin.getChildren().setAll(pane);
    }
    
    @FXML
    private void goUitloggen(ActionEvent event) throws IOException {
        //Voorbeeld om naar Borderpane scherm te gaan
        AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("ezfinder2/login.fxml"));
        APBegin.getChildren().setAll(pane);
    }
    
    @FXML
    private void goGevonden(ActionEvent event) throws IOException {
        //Voorbeeld om naar Borderpane scherm te gaan
        AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("ezfinder2/gevonden.fxml"));
        APBegin.getChildren().setAll(pane);
    }
    
    @FXML
    private void goManagement(ActionEvent event) throws IOException {
        //Voorbeeld om naar Borderpane scherm te gaan
        AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("ezfinder2/management.fxml"));
        APBegin.getChildren().setAll(pane);
    }
    
    @FXML
    private void goVerloren(ActionEvent event) throws IOException {
        //Voorbeeld om naar Borderpane scherm te gaan
	//test
        AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("ezfinder2/verloren.fxml"));
        APBegin.getChildren().setAll(pane);
    }
    
    @FXML
    private void goRapportage(ActionEvent event) throws IOException {
        //Voorbeeld om naar Borderpane scherm te gaan
        AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("ezfinder2/rapportages.fxml"));
        APBegin.getChildren().setAll(pane);
    }
    
    @FXML
    private void goVerder(ActionEvent event) throws IOException {
        //Voorbeeld om naar Borderpane scherm te gaan
        AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("ezfinder2/gevonden.fxml"));
        APBegin.getChildren().setAll(pane);
    }
    
     @FXML
    private void goVerzenden(ActionEvent event) throws IOException {
        //Voorbeeld om naar Borderpane scherm te gaan
        AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("ezfinder2/matches_gevonden.fxml"));
        APBegin.getChildren().setAll(pane);
    }
    @FXML
    private void goVerzendenVerloren(ActionEvent event) throws IOException {
        //Voorbeeld om naar Borderpane scherm te gaan
        AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("ezfinder2/matches_verloren.fxml"));
        APBegin.getChildren().setAll(pane);
    }
    @FXML
    private void goVerderVerloren(ActionEvent event) throws IOException {
        //Voorbeeld om naar Borderpane scherm te gaan
        AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource("ezfinder2/home.fxml"));
        APBegin.getChildren().setAll(pane);
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
	// test
    }
}
