package controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import sample.ConnectMSSQL;
import sample.Driver;
import sample.Reserve;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;


public class ReserveTransport implements Initializable {

    @FXML
    private ImageView searchButton;

    @FXML
    private ImageView refresh;

    @FXML
    private TextField search_bar;

    @FXML
    private Label number;

    @FXML
    private JFXButton edit_btn;

    @FXML
    private JFXButton add_btn;

    @FXML
    private TableView<Reserve> transport_tableView;

    @FXML
    private TableColumn<?, ?> renter_name;

    @FXML
    private TableColumn<?, ?> renter_phone_number;

    @FXML
    private TableColumn<?, ?> duration;

    @FXML
    private TableColumn<?, ?> reserved_date;

    @FXML
    private TableColumn<?, ?> Transport;



    private ObservableList<Reserve> TransportObservableList;

    String query;
    int reserveid;
    String renternumber;
    int renterduration;
    int noTransport;
    String renterName;
    String rentDate;
    String transportplate;

    int numbTrans;

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




        searchButton.setOnMouseClicked(actionEvent->{

            String search = search_bar.getText();




            try {
                loadSearchData(search);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }


        });

        refresh.setOnMouseClicked(actionEvent->{

            TransportObservableList.clear();



            try {
                loadFromDatabase();
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }

        });



        add_btn.setOnAction(actionEvent->{

            FXMLLoader fxmlLoader = new FXMLLoader();

            fxmlLoader.setLocation(getClass().getResource("../FXML/InputReserve.fxml"));

            try {

                Parent root = fxmlLoader.load();
               InputReserve inputReserve = fxmlLoader.getController();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();

               inputReserve.apply_btn.setOnAction(actEvent->{

                   if(inputReserve.getReserve_id()==-1||inputReserve.getRenter_duration()==-1)
                   {
                       Stage stag = (Stage) inputReserve.apply_btn.getScene().getWindow();

                       showAlert(stag);


                   }
                   else{
                       System.out.println(inputReserve.getReserve_id());
                       System.out.println(inputReserve.getRenter_name());
                       System.out.println(inputReserve.getReserve_date());

                       reserveid = inputReserve.getReserve_id();
                       renternumber = inputReserve.getRenter_number();
                       renterduration = inputReserve.getRenter_duration();

                       renterName = inputReserve.getRenter_name();
                       rentDate = inputReserve.getReserve_date();



                       try {
                           SaveData();
                       } catch (SQLException throwables) {
                           throwables.printStackTrace();
                       }

                       stage.close();
                       showNoTransport();



                   }


               });

               inputReserve.cancle_btn.setOnAction(a->{
                   stage.close();
               });




            } catch (IOException e) {
                e.printStackTrace();
            }


        });




    }


    void showNoTransport(){

        FXMLLoader fxmlloader = new FXMLLoader();

        fxmlloader.setLocation(getClass().getResource("../FXML/NoTransport.fxml"));


        try {
            Parent rot = fxmlloader.load();
            NoTransport noTransport = fxmlloader.getController();
            Stage sage = new Stage();
            sage.setScene(new Scene(rot));
            sage.show();

            noTransport.add.setOnAction(action->{
               numbTrans--;
//                if(numbTrans==0)
//                {
//                    sage.close();
//                }
//                else{

                    System.out.println(noTransport.getTransports());
                    transportplate = noTransport.getTransports();

                    sage.close();
                    sage.show();

                try {
                     SaveGetsData();

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }


//                }


            });

        } catch (IOException e) {
            e.printStackTrace();
        }




    }

    private  void  showAlert(Stage stage){
        Alert.AlertType type = Alert.AlertType.ERROR;
        Alert alert = new Alert(type,"Please enter valid number");
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initOwner(stage);
        alert.showAndWait();

    }


    void SaveData() throws SQLException {
        ConnectMSSQL Database = new ConnectMSSQL();

        PreparedStatement statement  =  Database.connectDB().prepareStatement("INSERT INTO Reserve VALUES ('"+reserveid+"','"+renterName+"','"+renternumber+"','"+renterduration+"','"+rentDate+"')");
        statement.executeQuery();




    }

    void SaveGetsData() throws SQLException {
        ConnectMSSQL Database = new ConnectMSSQL();

        PreparedStatement preparedStatement = Database.connectDB().prepareStatement("INSERT INTO Gets VALUES ('"+reserveid+"','"+transportplate+"')");
        preparedStatement.executeQuery();


    }


    private void loadSearchData(String search) throws SQLException, ClassNotFoundException {
       // transport_tableView.setItems(null);
        TransportObservableList.clear();
        ConnectMSSQL Database = new ConnectMSSQL();
        Statement statement = Database.connectDB().createStatement();
        query = "select Reserve.Renter_Name,Reserve.Renter_Phone_Number,Reserve.Reserve_Duration,Reserve.Reserve_Date,Gets.Transport_plate_no from Reserve inner join Gets on Reserve.Reserve_id  = Gets.Reserve_id\n" +
                "where Reserve.Renter_Name like '%"+search+"%'or Reserve.Renter_Phone_Number like '%"+search+"%'" ;
        ResultSet rs = statement.executeQuery(query);

        while (rs.next())
        {


            TransportObservableList.add(new Reserve(rs.getString("Renter_Name"),rs.getString("Renter_Phone_Number"),rs.getInt("Reserve_Duration"),rs.getDate("Reserve_Date"),rs.getString("Transport_plate_no")) {
            });

        }

        String Countquery = "select  count (Reserve.Reserve_id) from Reserve inner join Gets on Reserve.Reserve_id  = Gets.Reserve_id\n" +
                "where Reserve.Renter_Name like '%"+search+"%'or Reserve.Renter_Phone_Number like '%"+search+"%'" ;
        ResultSet cs = statement.executeQuery(Countquery);
        cs.next();

        number.setText(cs.getString(1));

    }


    private void initTable() {

        renter_name.setCellValueFactory(new PropertyValueFactory<>("renter_name"));
        renter_phone_number.setCellValueFactory(new PropertyValueFactory<>("renter_phone_number"));
        duration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        reserved_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        Transport.setCellValueFactory(new PropertyValueFactory<>("transport_plate"));



    }

    private void loadFromDatabase() throws SQLException, ClassNotFoundException  {

        ConnectMSSQL Database = new ConnectMSSQL();
        Statement statement = Database.connectDB().createStatement();
        query = "select Reserve.Renter_Name,Reserve.Renter_Phone_Number,Reserve.Reserve_Duration,Reserve.Reserve_Date,Gets.Transport_plate_no from Reserve inner join Gets on Reserve.Reserve_id  = Gets.Reserve_id";
        ResultSet rs = statement.executeQuery(query);


        while (rs.next())
        {


            TransportObservableList.add(new Reserve(rs.getString("Renter_Name"),rs.getString("Renter_Phone_Number"),rs.getInt("Reserve_Duration"),rs.getDate("Reserve_Date"),rs.getString("Transport_plate_no")) {
            });

        }

        String Countquery = "select count (Reserve.Reserve_id) from Reserve inner join Gets on Reserve.Reserve_id  = Gets.Reserve_id";
        ResultSet cs = statement.executeQuery(Countquery);
        cs.next();

        number.setText(cs.getString(1));



    }







}
