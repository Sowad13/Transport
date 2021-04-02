package controller;

import com.jfoenix.controls.JFXRadioButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
    private JFXRadioButton insurance_true;

    @FXML
    private JFXRadioButton insurance_false;

    private ObservableList<InsuranceStaff> InsuranceList;

    String query;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        InsuranceList = FXCollections.observableArrayList();
        initTable();
        try {
            loadFromDatabase();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        insurance_tableView.setItems(InsuranceList);

    }

    private void initTable() {

        staff_Id.setCellValueFactory(new PropertyValueFactory<>("staffId"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        nid.setCellValueFactory(new PropertyValueFactory<>("nid"));
        garage_port.setCellValueFactory(new PropertyValueFactory<>("garageId"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));

    }

    private void loadFromDatabase() throws SQLException, ClassNotFoundException  {



        insurance_true.setOnAction(actionEvent ->{

            query = "SELECT * FROM Garage_staff WHERE Medical_insurance='true'";
            ConnectMSSQL Database = new ConnectMSSQL();
            PreparedStatement statement = null;
            try {
                statement = Database.connectDB().prepareStatement(query);
                ResultSet rs = statement.executeQuery();

                while (rs.next()) {
                    System.out.println(rs.getString("Transport_plate_no"));
                    System.out.println(rs.getString("Travel_time"));
                    System.out.println(rs.getString("Starting_point"));
                    System.out.println(rs.getString("Destination"));
                    System.out.println(rs.getString("Interval_point"));

                    InsuranceList.add(new InsuranceStaff(rs.getInt("Staff_id"), rs.getString("Name"),
                            rs.getString("NID"), rs.getString("Phone_number")) {
                    });
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }




        });


        insurance_false.setOnAction(actionEvent ->{

            query = "SELECT * FROM Garage_staff WHERE Medical_insurance='false'";
            ConnectMSSQL Database = new ConnectMSSQL();
            PreparedStatement statement = null;
            try {
                statement = Database.connectDB().prepareStatement(query);
                ResultSet rs = statement.executeQuery();

                while (rs.next()) {
                    System.out.println(rs.getString("Transport_plate_no"));
                    System.out.println(rs.getString("Travel_time"));
                    System.out.println(rs.getString("Starting_point"));
                    System.out.println(rs.getString("Destination"));
                    System.out.println(rs.getString("Interval_point"));

                    InsuranceList.add(new InsuranceStaff(rs.getInt("Staff_id"), rs.getString("Name"),
                            rs.getString("NID"), rs.getString("Phone_number")) {
                    });
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }




        });




    }
}
