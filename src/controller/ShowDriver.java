package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.ConnectMSSQL;
import sample.Driver;
import sample.Transport;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import javafx.scene.image.ImageView;


public class ShowDriver implements Initializable {



    @FXML
    private TableView<Driver> transport_tableView;

    @FXML
    private TableColumn<?, ?> staff_Id;

    @FXML
    private TableColumn<?, ?> nid;

    @FXML
    private TableColumn<?, ?> Medical_insurance;

    @FXML
    private TableColumn<?, ?> Name;

    @FXML
    private TableColumn<?, ?> driver_license;

    @FXML
    private TableColumn<?, ?> phone_number;

    @FXML
    private TableColumn<?, ?> transport_plate;


    @FXML
    private ImageView searchButton;

    @FXML
    private JFXButton edit_btn;

    @FXML
    private JFXButton add_btn;


    private ObservableList<Driver> TransportObservableList;

    String query;
    int index = -1;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        TransportObservableList = FXCollections.observableArrayList();
        initTable();
        try {
            loadFromDatabase();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        transport_tableView.setItems(TransportObservableList);


        add_btn.setOnAction(actionEvent->{

            FXMLLoader fxmlLoader =  new FXMLLoader();

            fxmlLoader.setLocation(getClass().getResource("../FXML/DriverInput.fxml"));

            try {
                DialogPane dialogPane = fxmlLoader.load();

                DriverInput driverInput = fxmlLoader.getController();

                Dialog<ButtonType> dialog = new Dialog<>();
                dialog.setDialogPane(dialogPane);

                Optional<ButtonType> clickedButton = dialog.showAndWait();


                if (clickedButton.get()==ButtonType.APPLY){

                    saveData(driverInput);


                }

            } catch (IOException | SQLException e) {
                e.printStackTrace();
            }


        });


    }

    private void initTable() {

        staff_Id.setCellValueFactory(new PropertyValueFactory<>("staffid"));
        nid.setCellValueFactory(new PropertyValueFactory<>("nid"));
        Medical_insurance.setCellValueFactory(new PropertyValueFactory<>("insurance"));
        Name.setCellValueFactory(new PropertyValueFactory<>("name"));
        phone_number.setCellValueFactory(new PropertyValueFactory<>("phone"));
        transport_plate.setCellValueFactory(new PropertyValueFactory<>("transport_plate"));
        driver_license.setCellValueFactory(new PropertyValueFactory<>("Driver_license") );


    }

    private void loadFromDatabase() throws SQLException, ClassNotFoundException  {

        ConnectMSSQL Database = new ConnectMSSQL();
        Statement statement = Database.connectDB().createStatement();
        query = "Select *from Transport_staff where Transport_staff.Staff_type='Driver'";
        ResultSet rs = statement.executeQuery(query);

        while (rs.next())
        {
            System.out.println(rs.getString("Staff_id"));
            System.out.println(rs.getString("NID"));
            System.out.println(rs.getString("Medical_Insurance"));
            System.out.println(rs.getString("Name"));
            System.out.println(rs.getString("Phone_number"));
            System.out.println(rs.getString("Transport_plate_no"));

            TransportObservableList.add(new Driver(rs.getInt("Staff_id"), rs.getInt("NID"),
                    rs.getString("Driver_license_number"), rs.getBoolean("Medical_Insurance"), rs.getString("Name"),
                    rs.getString("Phone_number"), rs.getString("Transport_plate_no")) {
            });

        }



    }

    void saveData(DriverInput driverInput) throws SQLException {

        ConnectMSSQL Database = new ConnectMSSQL();

        PreparedStatement statement  =  Database.connectDB().prepareStatement("INSERT INTO Transport_staff VALUES ('"+driverInput.getStafff_id()+"','"+driverInput.getNid()+"','Driver',null,'"+driverInput.getDriver_license()+"','"+driverInput.getMedical_Insurance()+"','"+driverInput.get_Name()+"','"+driverInput.getPhone()+"','"+driverInput.getTransport_plate_No()+"')");
        statement.executeQuery();


    }
}
