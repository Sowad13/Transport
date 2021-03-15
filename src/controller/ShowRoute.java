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
import sample.Driver;
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
    private TableColumn<?, ?> routeid;

    @FXML
    private TableColumn<?, ?> tollfee;

    @FXML
    private TableColumn<?, ?> startingpoint;

    @FXML
    private TableColumn<?, ?> destination;

    @FXML
    private TableColumn<?, ?> traveltime;

    @FXML
    private TableColumn<?, ?> intervalpoint;


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

        routeid.setCellValueFactory(new PropertyValueFactory<>("routeid"));
        tollfee.setCellValueFactory(new PropertyValueFactory<>("tollfee"));
        startingpoint.setCellValueFactory(new PropertyValueFactory<>("startingpoint"));
        destination.setCellValueFactory(new PropertyValueFactory<>("destination"));
        traveltime.setCellValueFactory(new PropertyValueFactory<>("traveltime"));
        intervalpoint.setCellValueFactory(new PropertyValueFactory<>("intervalpoint"));


    }

    private void loadFromDatabase() throws SQLException, ClassNotFoundException  {

        ConnectMSSQL Database = new ConnectMSSQL();
        Statement statement = Database.connectDB().createStatement();
        query = "Select *from Travel_Route";
        ResultSet rs = statement.executeQuery(query);

        while (rs.next())
        {
            System.out.println(rs.getString("Route Id"));
            System.out.println(rs.getString("Toll Fee"));
            System.out.println(rs.getString("Starting Point"));
            System.out.println(rs.getString("Destination"));
            System.out.println(rs.getString("Travel Time"));
            System.out.println(rs.getString("Interval Point"));

            RouteObservableList.add(new Route(rs.getInt("Route Id"), rs.getInt("Toll Fee"),
                    rs.getString("Starting Point"), rs.getString("Destination"), rs.getInt("Travel Time"),
                    rs.getString("Interval Point")) {
            });

        }



    }
}
