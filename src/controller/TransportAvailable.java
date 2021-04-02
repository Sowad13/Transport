package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.AvailableTransport;
import sample.ConnectMSSQL;
import sample.Transport;

import javafx.scene.control.Label;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class TransportAvailable implements Initializable  {


    @FXML
    private TableView<AvailableTransport> transport_tableView;

    @FXML
    private TableColumn<?, ?> table_Transport_Plate_No;

    @FXML
    private TableColumn<?, ?> table_ClassName;

    @FXML
    private TableColumn<?, ?> table_GarageID;

    @FXML
    private TableColumn<?, ?> table_Insurance;

    @FXML
    private TableColumn<?, ?> table_Capacity;

    @FXML
    private TableColumn<?, ?> table_TransportCondition;

    @FXML
    private Label count;

    private ObservableList<AvailableTransport> TransportObservableList;

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

    private void loadFromDatabase() throws SQLException, ClassNotFoundException{

        ConnectMSSQL Database = new ConnectMSSQL();
        Statement statement = Database.connectDB().createStatement();
        query = "(SELECT Transport.Transport_plate_no,Transport.class_name,Transport.Garage_id,Transport.Insurance,Transport.Transport_condition ,Transport.Capacity  from Transport WHERE Transport.Transport_condition !='Out Of Order') EXCEPT (SELECT Transport.Transport_plate_no,Transport.class_name,Transport.Garage_id,Transport.Insurance,Transport.Transport_condition, Transport.Capacity   from Transport inner join Gets on Transport.Transport_plate_no = Gets.Transport_plate_no)";
        ResultSet rs = statement.executeQuery(query);

        int cnt=0;


        while(rs.next()) {

            System.out.println(rs.getString("class_name"));
            System.out.println(rs.getString("Transport_plate_no"));
            System.out.println(rs.getString("Garage_id"));
            System.out.println(rs.getString("Insurance"));
            System.out.println(rs.getString("Capacity"));
            System.out.println(rs.getString("Transport_condition"));
            TransportObservableList.add(new AvailableTransport(rs.getString("Transport_plate_no"), rs.getString("class_name"),
                    rs.getInt("Garage_id"), rs.getBoolean("Insurance"), rs.getInt("Capacity"),rs.getString("Transport_condition")) {
            });

            cnt++;
        }



        System.out.println(cnt);

       count.setText(String.valueOf(cnt));



    }

    private void initTable() {
        table_Transport_Plate_No.setCellValueFactory(new PropertyValueFactory<>("transportPlateNo"));
        table_ClassName.setCellValueFactory(new PropertyValueFactory<>("className"));
        table_GarageID.setCellValueFactory(new PropertyValueFactory<>("garageId"));
        table_Insurance.setCellValueFactory(new PropertyValueFactory<>("insurance"));
        table_Capacity.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        table_TransportCondition.setCellValueFactory(new PropertyValueFactory<>("transportCondition"));

    }
}
