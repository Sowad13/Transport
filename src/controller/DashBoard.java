package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.ConnectMSSQL;
import sample.Transport;


import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class DashBoard  implements Initializable {

    @FXML
    private Button driver_id;

    @FXML
    private Button staff_id;

    @FXML
    private Button transport_id;

    @FXML
    private TableView<Transport> transport_tableView;

    @FXML
    private TableColumn<Transport, String> table_Transport_Plate_No;

    @FXML
    private TableColumn<Transport, String> table_ClassName;

    @FXML
    private TableColumn<Transport, String> table_GarageID;

    @FXML
    private TableColumn<Transport, String> table_Insurance;

    @FXML
    private TableColumn<Transport, String> table_Servicing_Cost;

    @FXML
    private TableColumn<Transport, String> table_Servicing_Query;

    @FXML
    private TableColumn<Transport, Integer> table_Capacity;

    @FXML
    private TableColumn<Transport, Integer> table_TransportCondition;


    private String query;

    // private DatabaseHandler databaseHandler;
    private ObservableList<Transport> TransportObservableList;
    int index = -1;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        transport_id.setOnAction(actionEvent ->{


            TransportObservableList = FXCollections.observableArrayList();
            initTable();
            try {
                loadFromDatabase();
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }

            transport_tableView.setItems(TransportObservableList);


        });



    }
    private void initTable() {
        table_Transport_Plate_No.setCellValueFactory(new PropertyValueFactory<>("transportPlateNo"));
        table_ClassName.setCellValueFactory(new PropertyValueFactory<>("className"));
        table_GarageID.setCellValueFactory(new PropertyValueFactory<>("garageId"));
        table_Insurance.setCellValueFactory(new PropertyValueFactory<>("insurance"));
        table_Servicing_Cost.setCellValueFactory(new PropertyValueFactory<>("servicingCost"));
        table_Servicing_Query.setCellValueFactory(new PropertyValueFactory<>("servicingQuery"));
        table_Capacity.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        table_TransportCondition.setCellValueFactory(new PropertyValueFactory<>("transportCondition"));
    }

    private void loadFromDatabase() throws SQLException, ClassNotFoundException {
       ConnectMSSQL Database = new ConnectMSSQL();
        Statement statement = Database.connectDB().createStatement();
        query = "SELECT * FROM Transport";
        ResultSet rs = statement.executeQuery(query);

        while(rs.next()) {
           System.out.println(rs.getString("class_name"));
            System.out.println(rs.getString("Transport_plate_no"));
            System.out.println(rs.getString("Garage_id"));
            System.out.println(rs.getString("Insurance"));
            System.out.println(rs.getString("Servicing_cost"));
            System.out.println(rs.getString("Servicing_query"));
            System.out.println(rs.getString("Capacity"));
            System.out.println(rs.getString("Transport_condition"));
           TransportObservableList.add(new Transport(rs.getString("Transport_plate_no"), rs.getString("class_name"),
                   rs.getInt("Garage_id"), rs.getBoolean("Insurance"), rs.getInt("Servicing_cost"),
                    rs.getBoolean("Servicing_query"), rs.getInt("Capacity"),rs.getString("Transport_condition")) {
           });
        }
    }

}
