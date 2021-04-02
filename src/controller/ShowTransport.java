package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.AvailableTransport;
import sample.ConnectMSSQL;
import sample.Reserve;
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
    private ImageView refresh;

    @FXML
    private ImageView searchButton;

    @FXML
    private TextField search;


    @FXML
    private JFXButton editButton;

    @FXML
    private JFXButton add_btn;

    @FXML
    private AnchorPane show_info;


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

        refresh.setOnMouseClicked(c->{

            TransportObservableList.clear();

            try {
                loadFromDatabase();
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }

        });

        searchButton.setOnMouseClicked(click->{

            String s = search.getText();

            try {
                loadSearchData(s);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }


        });




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

        editButton.setOnAction(actionEvent ->{


            try {
                Parent fxml = FXMLLoader.load(getClass().getResource("../FXML/UpdateTransport.fxml"));

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


        if(transportInput.getGarage_Id()==-1||transportInput.getServicing_Cost()==-1|| transportInput.getCapacity()==-1){

            showAlert();

        }
        else{

            String tplate = transportInput.getTransport_plate_No();
            String classname = transportInput.getClass_Name();
            int gid = transportInput.getGarage_Id();
            boolean insurance = transportInput.getInsurance();
            int tcost = transportInput.getServicing_Cost();
            boolean tquery = transportInput.getServicing_query();
            int tcapa = transportInput.getCapacity();
            String tcondition = transportInput.getTransport_Condition();

            ConnectMSSQL Database = new ConnectMSSQL();

            PreparedStatement statement  =  Database.connectDB().prepareStatement("INSERT INTO Transport VALUES ('"+tplate+"','"+classname+"','"+gid+"','"+insurance+"','"+tcost+"','"+tquery+"','"+tcapa+"','"+tcondition+"')");
            statement.executeQuery();


        }





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


    private void loadSearchData(String search) throws SQLException, ClassNotFoundException {
        // transport_tableView.setItems(null);
        TransportObservableList.clear();
        ConnectMSSQL Database = new ConnectMSSQL();
        Statement statement = Database.connectDB().createStatement();
        query = "select * FROM Transport where Transport.class_name like '%"+search+"%' or Transport.Transport_condition like '%"+search+"%' or Transport.Transport_plate_no like '%"+search+"%'";
        ResultSet rs = statement.executeQuery(query);

        while (rs.next())
        {

            TransportObservableList.add(new Transport(rs.getString("Transport_plate_no"), rs.getString("class_name"),
                    rs.getInt("Garage_id"), rs.getInt("Capacity"),rs.getString("Transport_condition")) {
            });
        }



    }

    private  void  showAlert(){
        Stage stage = (Stage) add_btn.getScene().getWindow();

        Alert.AlertType type = Alert.AlertType.ERROR;
        Alert alert = new Alert(type,"Please enter valid number");
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initOwner(stage);
        alert.showAndWait();

    }

}
