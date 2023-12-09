package com.example.ap_final;

import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class initial_Controller implements Initializable {

    @FXML
    private Circle circle;

    @FXML
    private ImageView player;

    @FXML
    private Text start_text;
    @FXML
    public Text cherry_text;
    @FXML
    private Pane skins_pane;
    Score score_data  = Score.getScoreInstance();
    Skin_Handler skinHandler = Skin_Handler.get_skin();

    Skin_factory s = new Skin_factory();
    @FXML
    void start_game(MouseEvent event) throws IOException {
        Stage stage = (Stage)  start_text.getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("main_scene.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }


    @FXML
    void Skins(MouseEvent event) throws FileNotFoundException {
        skins_pane.setVisible(true);

    }

    @FXML
    void hollow(MouseEvent event) throws FileNotFoundException {
        if(skinHandler.getHollow() == 0 ){
            if(score_data.getCherries() >= 10){
                skinHandler.setCurrent("hollow");
                score_data.setCherries(score_data.getCherries() - 10);
                cherry_text.setText(String.valueOf(score_data.getCherries()));

                skinHandler.setHollow(1);
            }
        }
        else skinHandler.setCurrent("hollow");
        skins_pane.setVisible(false);
        player.setImage(s.get_skin(skinHandler.getCurrent()));
    }

    @FXML
    void hornet(MouseEvent event) throws FileNotFoundException {
        skins_pane.setVisible(false);
        if(skinHandler.getHornet() == 0 ){
            if(score_data.getCherries() >= 50){
                skinHandler.setCurrent("hornet");
                score_data.setCherries(score_data.getCherries() - 50);
                skinHandler.setCurrent("hornet");
                cherry_text.setText(String.valueOf(score_data.getCherries()));
            }
        }
        else skinHandler.setCurrent("hornet");
        skins_pane.setVisible(false);
        player.setImage(s.get_skin(skinHandler.getCurrent()));
    }

    @FXML
    void sprite(MouseEvent event) throws FileNotFoundException {
        skinHandler.setCurrent("sprite");
        skins_pane.setVisible(false);
        player.setImage(s.get_skin(skinHandler.getCurrent()));
    }

    @FXML
    void shadow(MouseEvent event) throws FileNotFoundException {
        if(skinHandler.getShadow() == 0 ){
            if(score_data.getCherries() >= 30){
                skinHandler.setCurrent("shadow");
                score_data.setCherries(score_data.getCherries() - 30);
                skinHandler.setCurrent("shadow");
                cherry_text.setText(String.valueOf(score_data.getCherries()));
            }
        }
        else skinHandler.setCurrent("shadow");
        skins_pane.setVisible(false);
        player.setImage(s.get_skin(skinHandler.getCurrent()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        skins_pane.setVisible(false);
        cherry_text.setText(String.valueOf(score_data.getCherries()));

        TranslateTransition transition = new TranslateTransition();
        transition.setNode(circle);
        transition.setToY(220-150);
        transition.setDuration(Duration.millis(2000));
        transition.setAutoReverse(true);
        transition.setCycleCount(Timeline.INDEFINITE);
        transition.play();

        TranslateTransition transition1 = new TranslateTransition();
        transition1.setNode(start_text);
        transition1.setToY(220-125);
        transition1.setDuration(Duration.millis(2000));
        transition1.setAutoReverse(true);
        transition1.setCycleCount(Timeline.INDEFINITE);
        transition1.play();


    }
}
