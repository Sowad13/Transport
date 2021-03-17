package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.ConnectMSSQL;
import sample.Staff;
import sample.Transport;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;

public class StaffShow implements Initializable {

    @FXML
    private TableView<Staff> staffTableShow;

    @FXML
    private TableColumn<?, ?> Phone_number;

    @FXML
    private TableColumn<?, ?> garage_id;

    @FXML
    private TableColumn<?, ?> nid_staff;

    @FXML
    private TableColumn<?, ?> medical_insurance;

    @FXML
    private TableColumn<?, ?> name_staff;


    @FXML
    private TableColumn<?, ?> staff_id;

    private String query;

    private ObservableList<Staff> StaffObservableList;
    int index = -1;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        StaffObservableList = FXCollections.observableArrayList();
        initTable();
        try {
            loadFromDatabase();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        staffTableShow.setItems(StaffObservableList);


        /*add_btn.setOnAction(actionEvent->{

            FXMLLoader fxmlLoader =  new FXMLLoader();

            fxmlLoader.setLocation(getClass().getResource("../FXML/TransportInput.fxml"));
            try {
                DialogPane dialogPane = fxmlLoader.load();

                TransportInput transportInput = fxmlLoader.getController();

                Dialog<ButtonType> dialog = new Dialog<>();
                dialog.setDialogPane(dialogPane);

                Optional<ButtonType> clickedButton = dialog.showAndWait();


                if (clickedButton.get()==ButtonType.APPLY){

                    save_Data(transportInput);


                }

            } catch (IOException | SQLException e) {
                e.printStackTrace();
            }


        });*/


    }

    private void initTable() {
        staff_id.setCellValueFactory(new  PropertyValueFactory<>("idStaff"));
        name_staff.setCellValueFactory(new PropertyValueFactory<>("nameStaff"));
        nid_staff.setCellValueFactory(new PropertyValueFactory<>("nidStaff"));
        Phone_number.setCellValueFactory(new PropertyValueFactory<>("phoneStaff"));
        garage_id.setCellValueFactory(new PropertyValueFactory<>("garageNo"));
        medical_insurance.setCellValueFactory(new PropertyValueFactory<>("mediinsueranceStaff"));

    }

    private void loadFromDatabase() throws SQLException, ClassNotFoundException {
        ConnectMSSQL Database = new ConnectMSSQL();
        Statement statement = Database.connectDB().createStatement();
        query = "SELECT * FROM Garage_staff";
        ResultSet rs = statement.executeQuery(query);


        while(rs.next()) {
            System.out.println(rs.getString("Staff_id"));
            System.out.println(rs.getString("Name"));
            System.out.println(rs.getString("NID"));
            System.out.println(rs.getString("Phone_number"));
            System.out.println(rs.getString("Garage_id"));
            System.out.println(rs.getString("Medical_Insurance"));

            StaffObservableList.add(new Staff(rs.getInt("Staff_id"), rs.getString("Name"),
                    rs.getInt("NID"), rs.getString("Phone_number"), rs.getInt("Garage_id"),
                    rs.getBoolean("Medical_Insurance")){
            });
        }
    }


    /*void save_Data(TransportInput transportInput ) throws SQLException {

        ConnectMSSQL Database = new ConnectMSSQL();

        PreparedStatement statement  =  Database.connectDB().prepareStatement("INSERT INTO Transport VALUES ('"+transportInput.getTransport_plate_No()+"','
        "+transportInput.getClass_Name()+"','"+transportInput.getGarage_Id()+"','"+transportInput.getInsurance()+"','"+transportInput.getServicing_Cost()+"
        ','"+transportInput.getServicing_query()+"','"+transportInput.getCapacity()+"','"+transportInput.getTransport_Condition()+"')");
        statement.executeQuery();

    }*/
}
