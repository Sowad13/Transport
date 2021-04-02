package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXML;
import sample.ConnectMSSQL;
import sample.Driver;
import sample.Staff;
import sample.driverEdit;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class UpdateDriver implements Initializable {

    @FXML
    private AnchorPane show_info;

    @FXML
    private TableView<driverEdit> transport_tableView;

    @FXML
    private TableColumn<?, ?> staff_Id;

    @FXML
    private TableColumn<?, ?> Name;

    @FXML
    private TableColumn<?, ?> driver_license;

    @FXML
    private TableColumn<?, ?> phone_number;

    @FXML
    private TableColumn<?, ?> transport_plate;

    @FXML
    private TableColumn<?, ?> medicalInsurence;

    @FXML
    private JFXButton updateButton;

    @FXML
    private JFXButton delButton;

    @FXML
    private JFXButton cancelButton;

    @FXML
    private JFXTextField DidField;

    @FXML
    private JFXTextField nameFIeld;

    @FXML
    private JFXTextField dlField;

    @FXML
    private JFXTextField phnField;

    @FXML
    private JFXTextField atField;

    @FXML
    private JFXTextField medField;


    public int getDidField() {
        return Integer.parseInt(DidField.getText());
    }

    public String getNameFIeld() {
        return nameFIeld.getText();
    }

    public String getDlField() {
        return dlField.getText();
    }

    public String getPhnField() {
        return phnField.getText();
    }

    public String getAtField() {
        return atField.getText();
    }

    public Boolean getMedField() {
        return Boolean.parseBoolean(medField.getText());
    }



    private ObservableList<driverEdit> TransportObservableList;

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


    }

    private void initTable() {

        staff_Id.setCellValueFactory(new PropertyValueFactory<>("staffid"));
        Name.setCellValueFactory(new PropertyValueFactory<>("name"));
        phone_number.setCellValueFactory(new PropertyValueFactory<>("phone"));
        transport_plate.setCellValueFactory(new PropertyValueFactory<>("transport_plate"));
        driver_license.setCellValueFactory(new PropertyValueFactory<>("Driver_license") );
        medicalInsurence.setCellValueFactory(new PropertyValueFactory<>("mediIns"));

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

            TransportObservableList.add(new driverEdit(rs.getInt("Staff_id"),rs.getBoolean("Medical_Insurance"), rs.getString("Driver_license_number"), rs.getString("Name"),
                    rs.getString("Phone_number"), rs.getString("Transport_plate_no") ) {
            });

        }



    }
}
