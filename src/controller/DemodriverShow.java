package controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;


public class DemodriverShow implements Initializable {


    @FXML
    private Label name;

    @FXML
    private Label phone_number;

    @FXML
    private Label license;

    @FXML
    private Label nid;

    public void setname(String name) {
        this.name.setText(name);
    }

    public void setPhone_number(String phone_number) {
        this.phone_number.setText( phone_number);
    }

    public void setLicense(String license) {
        this.license.setText( license);
    }

    public void setNid(String nid) {
        this.nid.setText( nid);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
