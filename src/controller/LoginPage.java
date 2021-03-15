package controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginPage implements Initializable {

    @FXML
    private JFXButton loginButton;



    @Override
    public void initialize(URL location, ResourceBundle resources) {



        loginButton.setOnAction(actionEvent->{

            FXMLLoader fxmlLoader =  new FXMLLoader();

            fxmlLoader.setLocation(getClass().getResource("../FXML/NavigationPage.fxml"));

            try {

                Parent root = fxmlLoader.load();
                NavigationPage navigationPage = fxmlLoader.getController();

                Stage stage = new Stage();
                stage.setScene(new Scene(root,1280,720));
                stage.show();

            }
            catch (IOException e) {
                e.printStackTrace();
            }


        });

    }
}
