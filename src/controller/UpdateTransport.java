package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.sun.org.apache.xerces.internal.impl.xpath.XPath;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import sample.ConnectMSSQL;
import sample.Transport;
import sample.staffEdit;
import sample.transportEdit;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;


public class UpdateTransport implements Initializable {



    @FXML
    private AnchorPane show_info;

    @FXML
    private TableView<transportEdit> transport_tableView;

    @FXML
    private TableColumn<?, ?> table_Transport_Plate_No;

    @FXML
    private TableColumn<?, ?> table_ClassName;

    @FXML
    private TableColumn<?, ?> garage_id;

    @FXML
    private TableColumn<?, ?> table_Capacity;

    @FXML
    private TableColumn<?, ?> table_TransportCondition;

    @FXML
    private TableColumn<?, ?> transportInsTable;

    @FXML
    private TableColumn<?, ?> transportsvTable;

    @FXML
    private JFXTextField plateField;

    @FXML
    private JFXTextField garageField;

    @FXML
    private JFXTextField capacityField;

    @FXML
    private JFXTextField classField;

    @FXML
    private JFXTextField insField;

    @FXML
    private JFXTextField svField;

    @FXML
    private JFXButton updateButton;

    @FXML
    private JFXButton delButton;

    @FXML
    private JFXButton cancelButton;


    @FXML
    private JFXTextField stateField;

    public String getPlateField() {
        return plateField.getText();
    }

    public int getGarageField() {
        return Integer.parseInt(garageField.getText());
    }

    public int getCapacityField() {
        return Integer.parseInt(capacityField.getText());
    }

    public String getClassField() {
        return classField.getText();
    }

    public boolean getInsField() {
        return Boolean.parseBoolean(insField.getText());
    }

    public boolean getSvField() {
        return Boolean.parseBoolean(svField.getText());
    }
    public String getStateField() {
        return stateField.getText();
    }

    private String query,updateQuery;


    private ObservableList<transportEdit> TransportObservableList;
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


        updateButton.setOnAction(event -> {


            try {
                transportEdit staff = (transportEdit) transport_tableView.getSelectionModel().getSelectedItem();

                updateQuery = "UPDATE Transport SET Transport_plate_no='"+getPlateField()+"',class_name='"+getClassField()+"', Insurance='"+getInsField()+"',Garage_id='"+getGarageField()+"',Servicing_query='"+getSvField()+"',Capacity='"+getCapacityField()+"',Transport_condition='"+getStateField()+"' where Transport_plate_no = ? ";
                ConnectMSSQL Database = new ConnectMSSQL();
                PreparedStatement preparedstatement = Database.connectDB().prepareStatement(updateQuery);
                preparedstatement.setString(1,staff.getTransportPlateNo());

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

                transportEdit staff = (transportEdit) transport_tableView.getSelectionModel().getSelectedItem();

                updateQuery = "DELETE FROM Transport where Transport_plate_no = ?";
                ConnectMSSQL Database = new ConnectMSSQL();
                PreparedStatement preparedStatement = Database.connectDB().prepareStatement(updateQuery);
                preparedStatement.setString(1, staff.getTransportPlateNo());

                preparedStatement.executeQuery();
            }
            catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        });

        cancelButton.setOnAction(event -> {


            try {
                Parent fxml = FXMLLoader.load(getClass().getResource("../FXML/Show_transport.fxml"));

                show_info.getChildren().removeAll();
                show_info.getChildren().setAll(fxml);

            } catch (IOException e) {
                e.printStackTrace();
            }

        });




    }

    private void initTable() {
        table_Transport_Plate_No.setCellValueFactory(new PropertyValueFactory<>("transportPlateNo"));
        table_ClassName.setCellValueFactory(new PropertyValueFactory<>("className"));
        garage_id.setCellValueFactory(new PropertyValueFactory<>("garageId"));
        table_Capacity.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        table_TransportCondition.setCellValueFactory(new PropertyValueFactory<>("transportCondition"));
        transportInsTable.setCellValueFactory(new PropertyValueFactory<>("insquery"));
        transportsvTable.setCellValueFactory(new PropertyValueFactory<>("svquery"));

        transport_tableView.setOnMouseClicked(event -> {

            try {
                transportEdit staff = (transportEdit) transport_tableView.getSelectionModel().getSelectedItem();

                String update = "SELECT * FROM Transport where Transport_plate_no = ?";
                ConnectMSSQL Database = new ConnectMSSQL();
                PreparedStatement preparedstatement = Database.connectDB().prepareStatement(update);
                preparedstatement.setString(1,staff.getTransportPlateNo());
                ResultSet up = preparedstatement.executeQuery();

                while (up.next()){

                    plateField.setText(up.getString("Transport_plate_no"));;
                    garageField.setText(up.getString("Garage_id"));;
                    capacityField.setText(up.getString("Capacity"));;
                    classField.setText(up.getString("class_name"));;
                    insField.setText(up.getString("Insurance"));;
                    svField.setText(up.getString("Servicing_query"));;

                }


            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
    }

    private void loadFromDatabase() throws SQLException, ClassNotFoundException {
        ConnectMSSQL Database = new ConnectMSSQL();
        Statement statement = Database.connectDB().createStatement();
        query = "SELECT * FROM Transport";
        ResultSet rs = statement.executeQuery(query);

        while(rs.next()) {
            System.out.println(rs.getString("class_name"));
            System.out.println(rs.getString("Transport_plate_no"));
            System.out.println(rs.getString("Insurance"));
            System.out.println(rs.getString("Garage_id"));
            System.out.println(rs.getString("Servicing_query"));
            System.out.println(rs.getString("Capacity"));
            System.out.println(rs.getString("Transport_condition"));

            TransportObservableList.add(new transportEdit(rs.getString("Transport_plate_no"), rs.getString("class_name"),
                    rs.getInt("Garage_id"), rs.getInt("Capacity"),rs.getString("Transport_condition"),rs.getBoolean("Insurance"),rs.getBoolean("Servicing_query")) {
            });
        }
    }
}
