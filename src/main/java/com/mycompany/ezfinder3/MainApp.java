package com.mycompany.ezfinder3;

import com.mycompany.ezfinder3.controllers.MenuController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
//import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class MainApp extends Application {
   
    public static Stage parentWindow;
    private static BorderPane borderPane;
    public static User user;
    
    @Override
    public void start(Stage stage) throws Exception {

        user = new User();
        
        parentWindow = stage;

        borderPane = new BorderPane();
        
        switchScherm("fxml/login.fxml");
        
        Scene scene1 = new Scene(borderPane);

        stage.setScene(scene1);
        stage.show();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public static void setMenu(boolean menu) throws IOException{
        
        if (menu) {
            borderPane.setTop((Node) FXMLLoader.load(MainApp.class.getClassLoader().getResource("fxml/menu.fxml")));
        }else{
            borderPane.setTop( null );
        }
        
    }
    
    public static void switchScherm(String fxml) {
        try {
            Parent scherm = FXMLLoader.load(MainApp.class.getClassLoader().getResource(fxml));
            borderPane.setCenter(scherm);
        } catch (IOException ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
