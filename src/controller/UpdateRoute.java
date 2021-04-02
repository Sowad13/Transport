package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import sample.ConnectMSSQL;
import sample.Route;
import sample.transportEdit;

public class UpdateRoute implements Initializable {


    @FXML
    private AnchorPane show_info;

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
    private JFXTextField routeField;

    @FXML
    private JFXTextField tfField;

    @FXML
    private JFXTextField spField;

    @FXML
    private JFXTextField desField;

    @FXML
    private JFXButton updateButton;

    @FXML
    private JFXButton delButton;

    @FXML
    private JFXButton cancelButton;

    @FXML
    private JFXTextField tsField;

    @FXML
    private JFXTextField ipField;

    private ObservableList<Route> RouteObservableList;

    String query,updateQuery;
    int index = -1;

    public int getRouteField() {
        return Integer.parseInt(routeField.getText());
    }

    public int getTfField() {
        return Integer.parseInt(tfField.getText());
    }

    public String getSpField() {
        return spField.getText();
    }

    public String  getDesField() {
        return desField.getText();
    }

    public int getTsField() {
        return Integer.parseInt(tsField.getText());
    }

    public String getIpField() {
        return ipField.getText();
    }

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


        updateButton.setOnAction(event -> {


            try {
                Route staff = (Route) route_tableView.getSelectionModel().getSelectedItem();

                updateQuery = "UPDATE Travel_Route SET Route_id='"+getRouteField()+"',Toll_fee='"+getTfField()+"', Starting_point='"+getSpField()+"',Destination='"+getDesField()+"',Travel_time='"+getTsField()+"',Interval_point='"+getIpField()+"' where Route_id = ? ";
                ConnectMSSQL Database = new ConnectMSSQL();
                PreparedStatement preparedstatement = Database.connectDB().prepareStatement(updateQuery);
                preparedstatement.setInt(1,staff.getRouteid());

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

                Route staff = (Route) route_tableView.getSelectionModel().getSelectedItem();

                updateQuery = "DELETE FROM Travel_Route where Route_id = ?";
                ConnectMSSQL Database = new ConnectMSSQL();
                PreparedStatement preparedStatement = Database.connectDB().prepareStatement(updateQuery);
                preparedStatement.setInt(1, staff.getRouteid());

                preparedStatement.executeQuery();
            }
            catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        });

        cancelButton.setOnAction(event -> {


            try {
                Parent fxml = FXMLLoader.load(getClass().getResource("../FXML/Show_Route.fxml"));

                show_info.getChildren().removeAll();
                show_info.getChildren().setAll(fxml);

            } catch (IOException e) {
                e.printStackTrace();
            }

        });



    }

    private void initTable() {

        route_Id.setCellValueFactory(new PropertyValueFactory<>("routeid"));
        toll_fee.setCellValueFactory(new PropertyValueFactory<>("tollfee"));
        starting_point.setCellValueFactory(new PropertyValueFactory<>("startingpoint"));
        destination.setCellValueFactory(new PropertyValueFactory<>("destination"));
        travel_time.setCellValueFactory(new PropertyValueFactory<>("traveltime"));
        interval_point.setCellValueFactory(new PropertyValueFactory<>("intervalpoint"));

        route_tableView.setOnMouseClicked(event -> {

            try {
                Route staff = (Route) route_tableView.getSelectionModel().getSelectedItem();

                String update = "SELECT * FROM Travel_Route where Route_id = ?";
                ConnectMSSQL Database = new ConnectMSSQL();
                PreparedStatement preparedstatement = Database.connectDB().prepareStatement(update);
                preparedstatement.setInt(1,staff.getRouteid());
                ResultSet up = preparedstatement.executeQuery();

                while (up.next()){

                    routeField.setText(up.getString("Route_id"));;
                    tfField.setText(up.getString("Toll_fee"));;
                    spField.setText(up.getString("Starting_point"));;
                    desField.setText(up.getString("Destination"));;
                    tsField.setText(up.getString("Travel_time"));;
                    ipField.setText(up.getString("Interval_point"));;

                }


            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });


    }

    private void loadFromDatabase() throws SQLException, ClassNotFoundException {

        ConnectMSSQL Database = new ConnectMSSQL();
        Statement statement = Database.connectDB().createStatement();
        query = "Select *from Travel_Route";
        ResultSet rs = statement.executeQuery(query);

        while (rs.next()) {
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
