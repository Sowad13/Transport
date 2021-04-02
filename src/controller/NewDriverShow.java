package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;


public class NewDriverShow implements Initializable {

    @FXML
    private VBox vboxShow;


    @Override
    public void initialize(URL location, ResourceBundle resources) {


        final Node[] VNodes = new Node[1];

        FXMLLoader fxmlLoader =  new FXMLLoader();

        fxmlLoader.setLocation(getClass().getResource("../FXML/DemodriverShow.fxml"));
        DemodriverShow demodriverShow = fxmlLoader.getController();

        //demodriverShow.setname("KHAN");
        for (int i=0;i<VNodes.length;i++)
        {
            try {


                VNodes[i] =fxmlLoader.load();


                vboxShow.getChildren().add(VNodes[i]);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }



    }
}
