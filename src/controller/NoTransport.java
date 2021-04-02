package controller;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import sample.AvailableTransport;
import sample.ConnectMSSQL;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;


public class NoTransport  implements Initializable {

    @FXML
    private JFXComboBox<String> transports;

    public String getTransports() {
        return transports.getValue();
    }

    @FXML
    public JFXButton add;

    private ObservableList<String> TransportObservableList;

    String query;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ConnectMSSQL Database = new ConnectMSSQL();
        TransportObservableList = FXCollections.observableArrayList();
       String TPNO;

        try {


        Statement statement = Database.connectDB().createStatement();
            query = "(SELECT Transport.Transport_plate_no  from Transport WHERE Transport.Transport_condition !='Out Of Order') EXCEPT (SELECT Transport.Transport_plate_no  from Transport inner join Gets on Transport.Transport_plate_no = Gets.Transport_plate_no)";
            ResultSet rs = statement.executeQuery(query);

          while (rs.next()) {


              TPNO = rs.getString("Transport_plate_no");



              TransportObservableList.add(TPNO);

          }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        transports.setItems(TransportObservableList);

    }
}
