package controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import javafx.fxml.Initializable;
import sample.ConnectMSSQL;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Observable;
import java.util.ResourceBundle;


public class TransportInput  implements Initializable {

    @FXML
    private TextField Capacity;

    @FXML
    private TextField Servicing_Cost;

    @FXML
    private TextField Transport_plate_No;

    @FXML
    private JFXComboBox<String> classname;

    @FXML
    private JFXComboBox<String> Garage_id;

    @FXML
    private JFXComboBox<String > Insurance;

    @FXML
    private JFXComboBox<String> Servicing_query;

    @FXML
    private JFXComboBox<String> Transport_Condition;

    private String query;


    public String getTransport_Condition() {


        return Transport_Condition.getValue();
    }

    public String getClass_Name() {

        return classname.getValue();
    }

    public int getGarage_Id() {
        int n;

        try{
            n = Integer.parseInt(Garage_id.getValue()) ;


        }
        catch(NumberFormatException ne){

            n=-1;


        }

        return n;


    }

    public int getCapacity() {

        int n;

        try{
            n = Integer.parseInt(Capacity.getText());


        }
        catch(NumberFormatException ne){

            n=-1;


        }

        return n;



    }

    public boolean getServicing_query() {

        if(Servicing_query.getValue()=="yes")
        return true;

        return false;
    }

    public int getServicing_Cost() {

        int n;

        try{
            n = Integer.parseInt( Servicing_Cost.getText());


        }
        catch(NumberFormatException ne){

            n=-1;


        }


        return n;
    }

    public boolean getInsurance() {


        if(Insurance.getValue()=="yes")
            return true;

        return false;

    }

    public String getTransport_plate_No() {
        return Transport_plate_No.getText();
    }

    ObservableList<String>clssname;
    ObservableList<String>garageid;
    ObservableList<String>servicingquery;
    ObservableList<String>insurance;
    ObservableList<String>transport_condition;




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        clssname = FXCollections.observableArrayList("Economic","Business","Sleeper");
        garageid = FXCollections.observableArrayList();
        servicingquery = FXCollections.observableArrayList("yes","no");
        insurance = FXCollections.observableArrayList("yes","no");
        transport_condition = FXCollections.observableArrayList("No Repair Needed","Out Of Order","Needs Repair");


        ConnectMSSQL Database = new ConnectMSSQL();

        String TPNO;
        try {


            Statement statement = Database.connectDB().createStatement();
            query = "select Garage.Garage_id from Garage";
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {


                TPNO = String.valueOf( rs.getInt("Garage_id"));



                garageid .add(TPNO);

            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        classname.setItems(clssname);

        Garage_id.setItems(garageid);

        Insurance.setItems(insurance);

        Servicing_query.setItems(servicingquery);

        Transport_Condition.setItems(transport_condition);











    }
}
