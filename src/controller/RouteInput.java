package controller;

import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import javafx.fxml.FXML;
import java.util.ResourceBundle;

public class RouteInput implements Initializable {

    @FXML
    private TextField Route_ID;

    @FXML
    private TextField Toll_Fee;

    @FXML
    private TextField Interval;

    @FXML
    private TextField Travel_Time;

    @FXML
    private TextField Dest;

    @FXML
    private TextField Start_Point;

    public int getRoute_ID(){
        return Integer.parseInt(Route_ID.getText());
    }

    public int getToll_Fee(){
        return Integer.parseInt(Toll_Fee.getText());
    }

    public String getStart_Point(){
        return Start_Point.getText();
    }

    public String getDest(){
        return Dest.getText();
    }

    public int getTravel_Time(){
        return Integer.parseInt(Travel_Time.getText());
    }

    public String getInterval(){
        return Interval.getText();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
