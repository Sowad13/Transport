package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import sample.ConnectMSSQL;
import sample.Staff;
import sample.staffEdit;

public class UpdateStaff implements Initializable
{


    @FXML
    private TableView<staffEdit> staffTableShow;

    @FXML
    private TableColumn<?, ?> staff_id;

    @FXML
    private TableColumn<?, ?> name_staff;


    @FXML
    private TableColumn<?, ?> Phone_number;

    @FXML
    private TableColumn<?, ?> garage_id;

    @FXML
    private TableColumn<?, ?> medical_insurance;

    @FXML
    private JFXTextField nameField;

    @FXML
    private JFXTextField garageField;

    @FXML
    private JFXTextField phnField;

    @FXML
    private JFXTextField insField;

    @FXML
    private JFXButton updateButton;

    @FXML
    private JFXButton delButton;

    @FXML
    private JFXButton cancelButton;

    @FXML
    private AnchorPane show_info;


    public String getNameField() {
        return nameField.getText();
    }

    public int getGarageField() {
        return Integer.parseInt(garageField.getText());
    }

    public String getPhnField() {
        return phnField.getText();
    }

    public int getInsField() {
        return Integer.parseInt(insField.getText());
    }

    int staffId;
    String query,updateQuery;
    private ObservableList<staffEdit> StaffObservableList;
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

        updateButton.setOnAction(event -> {


            try {
                staffEdit staff = (staffEdit) staffTableShow.getSelectionModel().getSelectedItem();

                updateQuery = "UPDATE Garage_staff SET Name='"+getNameField()+"',Phone_number='"+getPhnField()+"', Garage_id='"+getGarageField()+"',Medical_Insurance='"+getInsField()+"' where Staff_id = ? ";
                ConnectMSSQL Database = new ConnectMSSQL();
                PreparedStatement preparedstatement = Database.connectDB().prepareStatement(updateQuery);
                preparedstatement.setInt(1,staff.getIdStaff());

                /*String search = getText("Staff_id");
                preparedstatement.setString(2,nameField.getText());
                preparedstatement.setString(4,phnField.getText());
                preparedstatement.setString(5,garageField.getText());
               preparedstatement.setString(6,insField.getText());*/

                preparedstatement.executeQuery();


            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }


        });


        delButton.setOnMouseClicked(event -> {

            try {

                staffEdit staff = (staffEdit) staffTableShow.getSelectionModel().getSelectedItem();

                updateQuery = "DELETE FROM Garage_staff where Staff_id = ?";
                ConnectMSSQL Database = new ConnectMSSQL();
                PreparedStatement preparedStatement = Database.connectDB().prepareStatement(updateQuery);
                preparedStatement.setInt(1, staff.getIdStaff());

                 preparedStatement.executeQuery();
            }
            catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        });

        cancelButton.setOnAction(event -> {


                try {
                    Parent fxml = FXMLLoader.load(getClass().getResource("../FXML/StaffShow.fxml"));

                    show_info.getChildren().removeAll();
                    show_info.getChildren().setAll(fxml);

                } catch (IOException e) {
                    e.printStackTrace();
                }

        });

    }

    private void initTable() {
        staff_id.setCellValueFactory(new PropertyValueFactory<>("idStaff"));
        name_staff.setCellValueFactory(new PropertyValueFactory<>("nameStaff"));
       // nid_staff.setCellValueFactory(new PropertyValueFactory<>("nidStaff"));
        Phone_number.setCellValueFactory(new PropertyValueFactory<>("phoneStaff"));
        garage_id.setCellValueFactory(new PropertyValueFactory<>("garageNo"));
       medical_insurance.setCellValueFactory(new PropertyValueFactory<>("medins"));


        staffTableShow.setOnMouseClicked(event -> {

            try {
                staffEdit staff = (staffEdit) staffTableShow.getSelectionModel().getSelectedItem();

                String update = "SELECT * FROM Garage_staff where Staff_id = ?";
                ConnectMSSQL Database = new ConnectMSSQL();
                PreparedStatement preparedstatement = Database.connectDB().prepareStatement(update);
                preparedstatement.setInt(1,staff.getIdStaff());
                ResultSet up = preparedstatement.executeQuery();

                while (up.next()){
                    //setText(up.getString("Staff_id"));
                    nameField.setText(up.getString("Name"));
                    phnField.setText(up.getString("Phone_number"));
                    garageField.setText(up.getString("Garage_id"));
                    insField.setText(up.getString("Medical_Insurance"));
                }


            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

    }

    private void loadFromDatabase() throws SQLException, ClassNotFoundException {
        ConnectMSSQL Database = new ConnectMSSQL();
        Statement statement = Database.connectDB().createStatement();
        query = "SELECT * FROM Garage_staff ";
        ResultSet rs = statement.executeQuery(query);


        while (rs.next()) {

            StaffObservableList.add(new staffEdit( rs.getInt("Staff_id"),(rs.getString("Name")),(rs.getBoolean("Medical_Insurance")),
                     rs.getString("Phone_number"), rs.getInt("Garage_id"))
                   {
            });




        }

    }
}
