package controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainDashboard implements Initializable {

    @FXML
    private JFXButton garage_state;

    @FXML
    private AnchorPane show_info;


    @FXML
    private JFXButton routine_btn;

    @FXML
    private JFXButton transport_state;

    @FXML
    private JFXButton staff_insurance;



    @FXML
    private JFXButton available_transport;

    @FXML
    private JFXButton reserve;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        garage_state.setOnAction(actionEvent ->{


            try {
                Parent fxml = FXMLLoader.load(getClass().getResource("../FXML/garageState.fxml"));

                show_info.getChildren().removeAll();
                show_info.getChildren().setAll(fxml);

            } catch (IOException e) {
                e.printStackTrace();
            }


        });

        routine_btn.setOnAction(actionEvent ->{


            try {
                Parent fxml = FXMLLoader.load(getClass().getResource("../FXML/TravelRoutine.fxml"));

                show_info.getChildren().removeAll();
                show_info.getChildren().setAll(fxml);

            } catch (IOException e) {
                e.printStackTrace();
            }


        });



        available_transport.setOnAction(actionEvent ->{

            try {
                Parent fxml = FXMLLoader.load(getClass().getResource("../FXML/Transport_available.fxml"));

                show_info.getChildren().removeAll();
               show_info.getChildren().setAll(fxml);
            }
            catch (IOException e) {
                e.printStackTrace();
            }


        });



        reserve.setOnAction(actionEvent ->{


            try {
                Parent fxml = FXMLLoader.load(getClass().getResource("../FXML/ReserveTransport.fxml"));

                show_info.getChildren().removeAll();
                show_info.getChildren().setAll(fxml);

            } catch (IOException e) {
                e.printStackTrace();
            }


        });





        transport_state.setOnAction(actionEvent ->{


            try {
                Parent fxml = FXMLLoader.load(getClass().getResource("../FXML/State_Transport.fxml"));

                show_info.getChildren().removeAll();
                show_info.getChildren().setAll(fxml);

            } catch (IOException e) {
                e.printStackTrace();
            }


        });


        staff_insurance.setOnAction(actionEvent ->{


            try {
                Parent fxml = FXMLLoader.load(getClass().getResource("../FXML/Staff_Insurance.fxml"));

                show_info.getChildren().removeAll();
                show_info.getChildren().setAll(fxml);

            } catch (IOException e) {
                e.printStackTrace();
            }


        });

    }
}
