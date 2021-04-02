package controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import sample.ConnectMSSQL;
import sample.Staff;
import sample.garageJoin;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;

public class GarageState implements Initializable {

    @FXML
    private ImageView searchButton;

    @FXML
    private TableView<garageJoin> garageTableShow;

    @FXML
    private TableColumn<?, ?> garage_id;

    @FXML
    private TableColumn<?, ?> gLocation;

    @FXML
    private TableColumn<?, ?> staff_id;

    @FXML
    private TableColumn<?, ?> name_staff;

    @FXML
    private TableColumn<?, ?> tansportPlate;

    @FXML
    private TableColumn<?, ?> parking;

    @FXML
    private JFXButton editButton;

    @FXML
    private AnchorPane showInfo;

    private String query;

    private ObservableList<garageJoin> garageJoinObservableList;
    int index = -1;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        garageJoinObservableList = FXCollections.observableArrayList();
        initTable();
        try {
            loadFromDatabase();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        garageTableShow.setItems(garageJoinObservableList);





    }

    private void initTable() {
        garage_id.setCellValueFactory(new PropertyValueFactory<>("garageId"));
        gLocation.setCellValueFactory(new PropertyValueFactory<>("garageLocation"));
        staff_id.setCellValueFactory(new PropertyValueFactory<>("garageStaff"));
        name_staff.setCellValueFactory(new PropertyValueFactory<>("staffName"));
        tansportPlate.setCellValueFactory(new PropertyValueFactory<>("transportPlate"));
        parking.setCellValueFactory(new PropertyValueFactory<>("parkingSpace"));



    }

    private void loadFromDatabase() throws SQLException, ClassNotFoundException {
        ConnectMSSQL Database = new ConnectMSSQL();
        Statement statement = Database.connectDB().createStatement();
        query = " SELECT Garage.Garage_id,Garage.Location,Garage_staff.Staff_id,Garage_staff.Name,Transport.Transport_plate_no FROM Garage JOIN Garage_staff ON Garage.Garage_id = Garage_staff.Garage_id JOIN Transport ON Transport.Garage_id = Garage.Garage_id;";
        ResultSet rs = statement.executeQuery(query);

        while(rs.next()) {
            System.out.println(rs.getString("Garage_id"));
            System.out.println(rs.getString("Location"));
            System.out.println(rs.getString("Staff_id"));
            System.out.println(rs.getString("Name"));
            System.out.println(rs.getString("Transport_plate_no"));
            //System.out.println(rs.getString("Medical_Insurance"));

            garageJoinObservableList.add(new garageJoin(rs.getInt("Garage_id"), rs.getString("Location"),
                    rs.getInt("Staff_id"), rs.getString("Name"), rs.getString("Transport_plate_no")){
            });
        }

    }

}
