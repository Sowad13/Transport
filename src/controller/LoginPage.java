package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.ConnectMSSQL;
import sample.UserInfo;

public class LoginPage implements Initializable {

    @FXML
    private JFXButton loginButton;

    @FXML
    private JFXTextField userName;

    @FXML
    private JFXPasswordField userPass;

    private String query,name,pass,logname,logpass;

    private ObservableList<UserInfo> userObservableList;
    int index = -1;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

     /*   Exit.setOnMouseClicked(event -> {
            System.exit(0);
        });*/


        userObservableList = FXCollections.observableArrayList();
        //initTable();
        try {
            loadFromDatabase();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }


            loginButton.setOnAction(actionEvent -> {


                logname = userName.getText();
                logpass = userPass.getText();

                if(logname.equals(name) && logpass.equals(pass)) {
                FXMLLoader fxmlLoader = new FXMLLoader();

                fxmlLoader.setLocation(getClass().getResource("../FXML/NavigationPage.fxml"));

                try {

                    Parent root = fxmlLoader.load();
                    NavigationPage navigationPage = fxmlLoader.getController();

                    Stage stage = new Stage();
                    stage.setScene(new Scene(root, 1280, 720));
                    stage.show();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                }
                else{
                    System.out.println("Wrong user name password");
                }
            });



    }

    private void loadFromDatabase() throws SQLException, ClassNotFoundException {
        ConnectMSSQL Database = new ConnectMSSQL();
        Statement statement = Database.connectDB().createStatement();
        query = "SELECT * FROM LoginInfo";
        ResultSet rs = statement.executeQuery(query);


        while(rs.next()) {
            name = rs.getString("UserName");
            pass = rs.getString("User_Password");



            userObservableList.add(new UserInfo(rs.getString("UserName"), rs.getString("User_Password")){
            });

            System.out.println(name);
            System.out.println(pass);
        }
    }
}
