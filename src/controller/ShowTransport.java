package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.ConnectMSSQL;
import sample.Transport;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.scene.image.ImageView;


public class ShowTransport implements Initializable {

    @FXML
    private TableView<Transport> transport_tableView;

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
    private ImageView searchButton;

    @FXML
    private JFXButton edit_btn;

    @FXML
    private JFXButton add_btn;

    private String query;

    // private DatabaseHandler databaseHandler;
    private ObservableList<Transport> TransportObservableList;
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



        add_btn.setOnAction(actionEvent->{

            FXMLLoader fxmlLoader =  new FXMLLoader();

            fxmlLoader.setLocation(getClass().getResource("../FXML/TransportInput.fxml"));
            try {
                DialogPane dialogPane = fxmlLoader.load();

                TransportInput transportInput = fxmlLoader.getController();

                Dialog<ButtonType> dialog = new Dialog<>();
                dialog.setDialogPane(dialogPane);

                Optional<ButtonType> clickedButton = dialog.showAndWait();


                if (clickedButton.get()==ButtonType.APPLY){

                    save_Data(transportInput);


                }

            } catch (IOException | SQLException e) {
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

            TransportObservableList.add(new Transport(rs.getString("Transport_plate_no"), rs.getString("class_name"),
                     rs.getInt("Garage_id"), rs.getInt("Capacity"),rs.getString("Transport_condition")) {
            });
        }
    }


void save_Data(TransportInput transportInput ) throws SQLException {

    ConnectMSSQL Database = new ConnectMSSQL();

    PreparedStatement statement  =  Database.connectDB().prepareStatement("INSERT INTO Transport VALUES ('"+transportInput.getTransport_plate_No()+"','"+transportInput.getClass_Name()+"','"+transportInput.getGarage_Id()+"','"+transportInput.getInsurance()+"','"+transportInput.getServicing_Cost()+"','"+transportInput.getServicing_query()+"','"+transportInput.getCapacity()+"','"+transportInput.getTransport_Condition()+"')");
    statement.executeQuery();




//    System.out.println(transportInput.getClass_Name());
//    System.out.println(transportInput.getTransport_Condition());
//    System.out.println(transportInput.getServicing_Cost());
//    System.out.println(transportInput.getTransport_plate_No());
//    System.out.println(transportInput.getServicing_Cost());
//    System.out.println(transportInput.getCapacity());
//    System.out.println(transportInput.getInsurance());
//    System.out.println(transportInput.getGarage_Id());
//    System.out.println(transportInput.getServicing_query());

}

}
