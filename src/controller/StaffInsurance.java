package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.ConnectMSSQL;
import sample.InsuranceStaff;

import java.net.URL;
import javafx.fxml.FXML;
import sample.Routine;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class StaffInsurance implements Initializable {


    @FXML
    private TableView<InsuranceStaff> insurance_tableView;

    @FXML
    private TableColumn<?, ?> staff_Id;

    @FXML
    private TableColumn<?, ?> name;

    @FXML
    private TableColumn<?, ?> nid;

    @FXML
    private TableColumn<?, ?> garage_port;

    @FXML
    private TableColumn<?, ?> phone;

    @FXML
    private ComboBox<String> insurance_state;

    private ObservableList<InsuranceStaff> InsuranceList;

    private ObservableList<String> InsuranceStateList;

    String query1;

    String query2;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        InsuranceList = FXCollections.observableArrayList();
        InsuranceStateList = FXCollections.observableArrayList("Insurance Done", "Insurance Not Done");

        initTable();
        try {
            loadFromDatabase();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        insurance_tableView.setItems(InsuranceList);
        insurance_state.setItems(InsuranceStateList);


        insurance_state.setOnAction(actionEvent ->{

            doAction(insurance_state.getValue());

        });
    }



    private void initTable() {

        staff_Id.setCellValueFactory(new PropertyValueFactory<>("staffId"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        nid.setCellValueFactory(new PropertyValueFactory<>("nid"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));

    }

    private void loadFromDatabase() throws SQLException, ClassNotFoundException  {



    }

    private void doAction(String listItem) {
        switch (listItem) {


            case "Insurance Done":

                InsuranceList.clear();

                query1 = "SELECT * FROM Garage_staff WHERE Garage_staff.Medical_insurance='true'";
                PreparedStatement statement1 = null;
                try {
                    ConnectMSSQL Database = new ConnectMSSQL();
                    statement1 = Database.connectDB().prepareStatement(query1);
                    ResultSet rs = statement1.executeQuery();

                    while (rs.next()) {
                    /*System.out.println(rs.getString("Transport_plate_no"));
                    System.out.println(rs.getString("Travel_time"));
                    System.out.println(rs.getString("Starting_point"));
                    System.out.println(rs.getString("Destination"));
                    System.out.println(rs.getString("Interval_point"));*/

                        InsuranceList.add(new InsuranceStaff(rs.getInt("Staff_id"), rs.getString("Name"),
                                rs.getString("NID"), rs.getString("Phone_number")) {
                        });
                    }

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                break;


            case "Insurance Not Done":


                InsuranceList.clear();

                query2 = "SELECT * FROM Garage_staff WHERE Garage_staff.Medical_insurance='false'";
                try {
                    ConnectMSSQL Database = new ConnectMSSQL();
                    PreparedStatement statement2 = null;
                    statement2 = Database.connectDB().prepareStatement(query2);
                    ResultSet rs = statement2.executeQuery();

                    while (rs.next()) {
                /*System.out.println(rs.getString("Transport_plate_no"));
                System.out.println(rs.getString("Travel_time"));
                System.out.println(rs.getString("Starting_point"));
                System.out.println(rs.getString("Destination"));
                System.out.println(rs.getString("Interval_point"));*/

                        InsuranceList.add(new InsuranceStaff(rs.getInt("Staff_id"), rs.getString("Name"),
                                rs.getString("NID"), rs.getString("Phone_number")) {
                        });
                    }

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;

            default:
                break;

        }
    };

}
