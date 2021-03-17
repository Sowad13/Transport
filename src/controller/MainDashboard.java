package controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainDashboard implements Initializable {

    @FXML
    private JFXButton routine_btn;

    @FXML
    private AnchorPane show_info;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        routine_btn.setOnAction(actionEvent ->{


            try {
                Parent fxml = FXMLLoader.load(getClass().getResource("../FXML/TravelRoutine.fxml"));

                show_info.getChildren().removeAll();
                show_info.getChildren().setAll(fxml);

            } catch (IOException e) {
                e.printStackTrace();
            }


        });


    }
}
