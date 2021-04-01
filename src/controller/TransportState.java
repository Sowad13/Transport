package controller;

import com.jfoenix.controls.JFXButton;
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
import sample.StateTransport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class TransportState implements Initializable {

    @FXML
    private TableView<StateTransport> transportState_tableView;

    @FXML
    private TableColumn<?, ?> plate_number;

    @FXML
    private TableColumn<?, ?> insurance;

    @FXML
    private TableColumn<?, ?> servicing_query;

    @FXML
    private TableColumn<?, ?> servicing_cost;

    @FXML
    private TableColumn<?, ?> condition;

    @FXML
    private JFXButton transport_state;

    private ObservableList<StateTransport> TransportStateObservableList;

    String query;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        TransportStateObservableList = FXCollections.observableArrayList();
        initTable();
        try {
            loadFromDatabase();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        transportState_tableView.setItems(TransportStateObservableList);

    }
    private void initTable() {

        plate_number.setCellValueFactory(new PropertyValueFactory<>("plateNumber"));
        insurance.setCellValueFactory(new PropertyValueFactory<>("insurance"));
        servicing_query.setCellValueFactory(new PropertyValueFactory<>("servicingQuery"));
        servicing_cost.setCellValueFactory(new PropertyValueFactory<>("servicingCost"));
        condition.setCellValueFactory(new PropertyValueFactory<>("condition"));

    }

    private void loadFromDatabase() throws SQLException, ClassNotFoundException  {

        ConnectMSSQL Database = new ConnectMSSQL();
        Statement statement = Database.connectDB().createStatement();
        query = "SELECT * FROM Transport";
        ResultSet rs = statement.executeQuery(query);

        while (rs.next())
        {
            System.out.println(rs.getString("Transport_plate_no"));
            System.out.println(rs.getString("Insurance"));
            System.out.println(rs.getString("Servicing_query"));
            System.out.println(rs.getString("Servicing_cost"));
            System.out.println(rs.getString("Transport_condition"));

            TransportStateObservableList.add(new StateTransport (rs.getString("Transport_plate_no"), rs.getBoolean("Insurance"),
                    rs.getBoolean("Servicing_query"), rs.getInt("Servicing_cost"), rs.getString("Transport_condition")) {
            });

        }



    }
}
