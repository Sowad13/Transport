package controller;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;


public class TransportInput  implements Initializable {

    @FXML
    private TextField Transport_Condition;

    @FXML
    private TextField Class_Name;

    @FXML
    private TextField Garage_Id;

    @FXML
    private TextField Capacity;

    @FXML
    private TextField Servicing_query;

    @FXML
    private TextField Servicing_Cost;

    @FXML
    private TextField Insurance;

    @FXML
    private TextField Transport_plate_No;




    public String getTransport_Condition() {


        return Transport_Condition.getText();
    }

    public String getClass_Name() {

        return Class_Name.getText();
    }

    public int getGarage_Id() {

        return Integer.parseInt(Garage_Id.getText()) ;
    }

    public int getCapacity() {
        return Integer.parseInt(Capacity.getText());
    }

    public String getServicing_query() {
        return Servicing_query.getText();
    }

    public int getServicing_Cost() {
        return Integer.parseInt( Servicing_Cost.getText());
    }

    public int getInsurance() {
        return Integer.parseInt( Insurance.getText());
    }

    public String getTransport_plate_No() {
        return Transport_plate_No.getText();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {




    }
}
