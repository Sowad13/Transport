package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import javafx.fxml.FXML;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.ConnectMSSQL;
import sample.Routine;
import sample.TransportReserve;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ReserveTransportInfo implements Initializable {

    @FXML
    private TableView<TransportReserve> reserveinfo_tableView;

    @FXML
    private TableColumn<?, ?> reserve_Id;

    @FXML
    private TableColumn<?, ?> rent_name;

    @FXML
    private TableColumn<?, ?> transport_num;

    @FXML
    private TableColumn<?, ?> drive_name;

    private ObservableList<TransportReserve> ReserveInfoList;

    String query;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ReserveInfoList = FXCollections.observableArrayList();
        initTable();
        try {
            loadFromDatabase();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        reserveinfo_tableView.setItems(ReserveInfoList);
    }

    private void initTable() {

        reserve_Id.setCellValueFactory(new PropertyValueFactory<>("reserveId"));
        rent_name.setCellValueFactory(new PropertyValueFactory<>("rentName"));
        transport_num.setCellValueFactory(new PropertyValueFactory<>("transportNum"));
        drive_name.setCellValueFactory(new PropertyValueFactory<>("driverName"));


    }

    private void loadFromDatabase() throws SQLException, ClassNotFoundException  {

        ConnectMSSQL Database = new ConnectMSSQL();
        Statement statement = Database.connectDB().createStatement();
        query = "Select Reserve.Reserve_id,Reserve.Renter_Name,Transport.Transport_plate_no,Transport_staff.Name FROM Reserve JOIN Gets ON Reserve.Reserve_id = Gets.Reserve_id JOIN Transport ON Transport.Transport_plate_no = Gets.Transport_plate_no JOIN Transport_staff ON Transport.Transport_plate_no = Transport_staff.Transport_plate_no;";
        ResultSet rs = statement.executeQuery(query);

        while (rs.next())
        {
            /*System.out.println(rs.getString("Transport_plate_no"));
            System.out.println(rs.getString("Travel_time"));
            System.out.println(rs.getString("Starting_point"));
            System.out.println(rs.getString("Destination"));
            System.out.println(rs.getString("Interval_point"));*/

            ReserveInfoList.add(new TransportReserve(rs.getInt("Reserve_id"), rs.getString("Renter_name"),
                    rs.getString("Transport_plate_no"), rs.getString("Name")) {
            });

        }





    }
}
