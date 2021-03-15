package controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import javafx.fxml.FXML;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NavigationPage implements Initializable {


    /*@FXML
    private AnchorPane slider;

    @FXML
    private Label menu;

    @FXML
    private Label menuback;*/
    @FXML
    private JFXButton driver_id;

    @FXML
    private AnchorPane paneOne;

    @FXML
    private AnchorPane iconbar;

    @FXML
    private JFXButton staffID;

    @FXML
    private AnchorPane show_info;

    @FXML
    private JFXButton transport_id;

    @FXML
    private JFXButton route_id;

    @FXML
    private AnchorPane paneTwo;

    @FXML
    private Label menu;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        paneOne.setVisible(false);
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5),paneOne);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.play();

        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.5),paneTwo);
        translateTransition.setByX(-600);
        translateTransition.play();

        menu.setOnMouseClicked(event -> {
            paneOne.setVisible(true);

            FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(0.5),paneOne);
            fadeTransition1.setFromValue(0);
            fadeTransition1.setToValue(0.15);
            fadeTransition1.play();

            TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(0.5),paneTwo);
            translateTransition1.setByX(+600);
            translateTransition1.play();

        });

        paneOne.setOnMouseClicked(event -> {

            paneOne.setVisible(true);

            FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(0.5),paneOne);
            fadeTransition1.setFromValue(0.15);
            fadeTransition1.setToValue(0);
            fadeTransition1.play();

            fadeTransition1.setOnFinished(event1 -> {
                paneOne.setVisible(false);
            });

            TranslateTransition translateTransition1 = new TranslateTransition(Duration.seconds(0.5),paneTwo);
            translateTransition1.setByX(-600);
            translateTransition1.play();

        });

       /* slider.setTranslateX(-176);
        menu.setOnMouseClicked(event -> {

            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);

            slide.setToX(0);
            slide.play();

            slider.setTranslateX(-176);

            slide.setOnFinished((ActionEvent e)->{

                menu.setVisible(false);
                menuback.setVisible(true);
            });
        });

        menuback.setOnMouseClicked(event -> {

            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);

            slide.setToX(-176);
            slide.play();

            slider.setTranslateX(0);

            slide.setOnFinished((ActionEvent e)-> {
                menu.setVisible(true);
                menuback.setVisible(false);
            });
        });*/


        transport_id.setOnAction(actionEvent ->{


            try {
                Parent fxml = FXMLLoader.load(getClass().getResource("../FXML/Show_transport.fxml"));

                show_info.getChildren().removeAll();
                show_info.getChildren().setAll(fxml);

            } catch (IOException e) {
                e.printStackTrace();
            }


        });

        driver_id.setOnAction(actionEvent ->{


            try {
                Parent fxml = FXMLLoader.load(getClass().getResource("../FXML/Show_Driver.fxml"));

                show_info.getChildren().removeAll();
                show_info.getChildren().setAll(fxml);

            } catch (IOException e) {
                e.printStackTrace();
            }


        });

        staffID.setOnAction(actionEvent ->{


            try {
                Parent fxml = FXMLLoader.load(getClass().getResource("../FXML/StaffShow.fxml"));

                show_info.getChildren().removeAll();
                show_info.getChildren().setAll(fxml);

            } catch (IOException e) {
                e.printStackTrace();
            }


        });


        route_id.setOnAction(actionEvent ->{


            try {
                Parent fxml = FXMLLoader.load(getClass().getResource("../FXML/Show_Route.fxml"));

                show_info.getChildren().removeAll();
                show_info.getChildren().setAll(fxml);

            } catch (IOException e) {
                e.printStackTrace();
            }


        });




    }
}
