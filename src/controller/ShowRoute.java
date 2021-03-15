package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.ConnectMSSQL;
import sample.Route;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ShowRoute implements Initializable {

    @FXML
    private TableView<Route> route_tableView;

    @FXML
    private TableColumn<?, ?> route_Id;

    @FXML
    private TableColumn<?, ?> toll_fee;

    @FXML
    private TableColumn<?, ?> starting_point;

    @FXML
    private TableColumn<?, ?> destination;

    @FXML
    private TableColumn<?, ?> travel_time;

    @FXML
    private TableColumn<?, ?> interval_point;


    @FXML
    private Button add_btn;

    @FXML
    private Button edit_btn;

    private ObservableList<Route> RouteObservableList;

    String query;
    int index = -1;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        RouteObservableList = FXCollections.observableArrayList();
        initTable();
        try {
            loadFromDatabase();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        route_tableView.setItems(RouteObservableList);

    }

    private void initTable() {

        route_Id.setCellValueFactory(new PropertyValueFactory<>("routeid"));
        toll_fee.setCellValueFactory(new PropertyValueFactory<>("tollfee"));
        starting_point.setCellValueFactory(new PropertyValueFactory<>("startingpoint"));
        destination.setCellValueFactory(new PropertyValueFactory<>("destination"));
        travel_time.setCellValueFactory(new PropertyValueFactory<>("traveltime"));
        interval_point.setCellValueFactory(new PropertyValueFactory<>("intervalpoint"));


    }

    private void loadFromDatabase() throws SQLException, ClassNotFoundException  {

        ConnectMSSQL Database = new ConnectMSSQL();
        Statement statement = Database.connectDB().createStatement();
        query = "Select *from Travel_Route";
        ResultSet rs = statement.executeQuery(query);

        while (rs.next())
        {
            System.out.println(rs.getString("Route_id"));
            System.out.println(rs.getString("Toll_fee"));
            System.out.println(rs.getString("Starting_point"));
            System.out.println(rs.getString("Destination"));
            System.out.println(rs.getString("Travel_time"));
            System.out.println(rs.getString("Interval_point"));

            RouteObservableList.add(new Route(rs.getInt("Route_id"), rs.getInt("Toll_fee"),
                    rs.getString("Starting_point"), rs.getString("Destination"), rs.getInt("Travel_time"),
                    rs.getString("Interval_point")) {
            });

        }



    }
}
