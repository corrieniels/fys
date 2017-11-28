/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ezfinder2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Justin
 */
public class EzFinder2 extends Application {
    
    public static Stage parentWindow;
    @Override
    public void start(Stage stage) throws Exception {
        parentWindow = stage;

        Parent login = FXMLLoader.load(getClass().getResource("login.fxml"));

        Scene scene1 = new Scene(login);

        stage.setScene(scene1);
        stage.show();

	// test
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
