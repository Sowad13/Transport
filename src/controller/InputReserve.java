package controller;


import com.jfoenix.controls.JFXDatePicker;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;

public class InputReserve implements Initializable {

    @FXML
    private TextField reserve_id;

    @FXML
    private TextField renter_number;

    @FXML
    private TextField renter_duration;


    @FXML
    private TextField renter_name;

    @FXML
    private JFXDatePicker reserve_date;

    @FXML
    public JFXButton apply_btn;

    @FXML
    public JFXButton cancle_btn;

    public String getRenter_number() {
        return renter_number.getText();
    }

    public int getReserve_id() {
        int n;

        try{
            n = Integer.parseInt(reserve_id.getText());



        }
        catch(NumberFormatException ne){

            n=-1;


        }

        return n;
    }



    public int getRenter_duration() {

        int n;

        try{
             n = Integer.parseInt(renter_duration.getText());



        }
        catch(NumberFormatException ne){

            n=-1;


        }

        return n;

    }


    public String getRenter_name() {
        return renter_name.getText();
    }

    public String getReserve_date() {
        return reserve_date.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
