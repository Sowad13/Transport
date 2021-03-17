package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.ConnectMSSQL;
import sample.Routine;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class TravelRoutine implements Initializable {

    @FXML
    private TableView<Routine> travelroutine_tableView;

    @FXML
    private TableColumn<?, ?> startingpoint;

    @FXML
    private TableColumn<?, ?> traveltime;

    @FXML
    private TableColumn<?, ?> interval;

    @FXML
    private TableColumn<?, ?> plate_number;

    @FXML
    private TableColumn<?, ?> dest;

    private ObservableList<Routine> RoutineObservableList;

    String query;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        RoutineObservableList = FXCollections.observableArrayList();
        initTable();
        try {
            loadFromDatabase();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        travelroutine_tableView.setItems(RoutineObservableList);
    }

    private void initTable() {

        plate_number.setCellValueFactory(new PropertyValueFactory<>("plateNum"));
        traveltime.setCellValueFactory(new PropertyValueFactory<>("travelTime"));
        startingpoint.setCellValueFactory(new PropertyValueFactory<>("start"));
        dest.setCellValueFactory(new PropertyValueFactory<>("dest"));
        interval.setCellValueFactory(new PropertyValueFactory<>("interval"));

    }

    private void loadFromDatabase() throws SQLException, ClassNotFoundException  {

        ConnectMSSQL Database = new ConnectMSSQL();
        Statement statement = Database.connectDB().createStatement();
        query = "SELECT Transport.Transport_plate_no,Travel_Route.Travel_time,Travel_Route.Starting_point,Travel_Route.Destination,Travel_Route.Interval_point FROM Transport JOIN Travels ON Transport.Transport_plate_no = Travels.Transport_plate_no JOIN Travel_Route ON Travel_Route.Route_id = Travels.Route_id;";
        ResultSet rs = statement.executeQuery(query);

        while (rs.next())
        {
            System.out.println(rs.getString("Transport_plate_no"));
            System.out.println(rs.getString("Travel_time"));
            System.out.println(rs.getString("Starting_point"));
            System.out.println(rs.getString("Destination"));
            System.out.println(rs.getString("Interval_point"));

            RoutineObservableList.add(new Routine(rs.getString("Transport_plate_no"), rs.getInt("Travel_time"),
                    rs.getString("Starting_point"), rs.getString("Destination"), rs.getString("Interval_point")) {
            });

        }



    }
}
