package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class DriverInput {

    @FXML
    private TextField stafff_id;

    @FXML
    private TextField nid;

    @FXML
    private TextField phone;

    @FXML
    private TextField name;

    @FXML
    private TextField medical_Insurance;

    @FXML
    private TextField Transport_plate_No;

    @FXML
    private TextField driver_license;

    public String getDriver_license() {
        return driver_license.getText();
    }

    public int getStafff_id() {
        return Integer.parseInt(stafff_id.getText());
    }

    public int getNid() {
        return Integer.parseInt(nid.getText());
    }

    public String getPhone() {
        return phone.getText();
    }

    public String get_Name() {
        return name.getText();
    }

    public int getMedical_Insurance() {
        return Integer.parseInt(medical_Insurance.getText());
    }

    public String getTransport_plate_No() {
        return Transport_plate_No.getText();
    }
}
