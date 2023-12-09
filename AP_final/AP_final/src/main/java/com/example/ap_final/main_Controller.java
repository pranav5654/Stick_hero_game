package com.example.ap_final;

import javafx.animation.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.*;
//import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class main_Controller implements Initializable {


    @FXML
    private Rectangle stick;

    @FXML
    private Rectangle current;

    @FXML
    private Rectangle next;
    @FXML
    private Rectangle perfect;

    @FXML
    private ImageView player;

    @FXML
    private ImageView cherry;

    @FXML
    private AnchorPane bg;

    @FXML
    private Text score;
    @FXML
    private Text highscore;
    @FXML
    private Text cherry_text;
    @FXML
    private Text last_score;
    @FXML
    private Pane pause_menu;
    @FXML
    private Pane game_over_pane;
    @FXML
    private Pane pause_button_pane;

    Score score_data  = Score.getScoreInstance();
    Skin_Handler skinHandler = Skin_Handler.get_skin();
    Timeline timeline;
    Timeline timeline1;

    double  rect_x = 90;
    double  rect_y = 404;
    double  rect_w = 9;
    double  rect_h = 0;

    int rand =0 ;
    int initial = 0;

    boolean on_pause = false;
    boolean pause_menu_bool = false;

    int moving = 0;
    int initial_perfect =0 ;
    boolean need_to_do = false;

    boolean flipped = false;

    public Thread collisionThread;
    boolean out;
    boolean intersect = false;

    String path = "bg.mp3";

    String path1 = "out.mp3";


    Media media , media1;
    MediaPlayer mediaPlayer , mediaPlayer1;


    TranslateTransition transition1 = new TranslateTransition();
    TranslateTransition transition2 = new TranslateTransition();
    TranslateTransition transition3 = new TranslateTransition();
    TranslateTransition transition = new TranslateTransition();

    Skin_factory skin_factory = new Skin_factory();

    @FXML
    void extend_stick(MouseEvent event) {

        if(moving == 1){
            if(!flipped){
                player.setY(player.getY() + player.getFitHeight());
                player.setRotate(180);
                flipped = true;
            }
            else{
                player.setY(player.getY() - player.getFitHeight());
                player.setRotate(0);
                flipped = false;

            }
        }

        if(!event.isPrimaryButtonDown()) return;
        if(moving == 0  && !on_pause && !pause_menu_bool) {
            timeline.play();
            intersect = false;
            need_to_do = true;
        }




    }
    @FXML
    void stop_extending(MouseEvent event) throws InterruptedException {
        if(!need_to_do){
            return;
        }
        moving =1;
        need_to_do = false;


        timeline.stop();
        stick.setWidth(stick.getHeight());
        stick.setHeight(10);
        stick.setY(416);


        double check;

        if(initial != 0 ) {
            check = 90 + stick.getWidth();
            out = !(36 + rand < check && check < 38 + rand + next.getWidth());
        }
        else{
            check = 100 + stick.getWidth();
            out = !(385 < check && check < 390 + next.getWidth());

            initial = 1;
        }

        double checker = 90 + stick.getWidth();

        if(initial_perfect == 0){
            checker = 100 + stick.getWidth();
            if(380 + rand  + perfect.getX() - next.getX()  < checker && checker < 380 +  rand  + perfect.getX() - next.getX() + perfect.getWidth()){
                score_data.setCherries(score_data.getCherries() - 1);
                cherry_text.setText(String.valueOf(score_data.getCherries()));

                score_data.setScore(score_data.getScore() + 3);
                score.setText(String.valueOf(score_data.getScore()));
                out = ! (score_data.getCherries() >= 0) ;
            }
            initial_perfect = 1;
        }

        else if ( 40 + rand  + perfect.getX() - next.getX()  < checker && checker < 40 +  rand  + perfect.getX() - next.getX() + perfect.getWidth()){
            score_data.setCherries(score_data.getCherries() - 1);
            cherry_text.setText(String.valueOf(score_data.getCherries()));
            score_data.setScore(score_data.getScore() + 3);
            score.setText(String.valueOf(score_data.getScore()));
            out = ! (score_data.getCherries() >= 0) ;
        }



        transition.setNode(player);
        transition.setToX(stick.getWidth());
        transition.setDuration(Duration.millis(1000));
        transition.setCycleCount(1);
        transition.play();





        transition.setOnFinished(e-> {

            if(out)  {
                transition2.setNode(player);
                transition2.setToY(400);
                transition2.setToX(stick.getWidth());
                transition2.setDuration(Duration.millis(60));
                transition2.setCycleCount(1);
                transition2.play();
            }

            else {
                current.setVisible(false);
                stick.setVisible(false);


                score_data.setScore(score_data.getScore() + 1);
                score.setText(String.valueOf(score_data.getScore()));

                transition1.setNode(next);
                transition1.setToX(-(next.getX() - 38));
                transition1.setDuration(Duration.millis(1000));
                transition1.setCycleCount(1);

                transition3.setNode(perfect);
                transition3.setToX(-(next.getX() - 38));
                transition3.setDuration(Duration.millis(1000));
                transition3.setCycleCount(1);


                transition2.setNode(player);
                transition2.setToX(-(player.getX() - 38));
                transition2.setDuration(Duration.millis(1000));
                transition2.setCycleCount(1);

                transition1.play();
                transition2.play();
                transition3.play();
            }
        });


        transition2.setOnFinished(e -> {

            if(out){
                Game_over();
            }
            else {
                set_next();
                restore_stick();
                moving = 0;

            }
        });

    }

    Thread changeSkin = new Thread(() -> {
        Image image;
        try {
            image = skin_factory.get_skin(skinHandler.getCurrent());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        player.setImage(image);
    })  ;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        changeSkin.start();

        media = new Media(new File(path).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();


        media1 = new Media(new File(path1).toURI().toString());
        mediaPlayer1 = new MediaPlayer(media1);
        mediaPlayer1.setCycleCount(1);

        mediaPlayer1.stop();


        timeline = new Timeline(new KeyFrame(Duration.millis(20), e -> {
            stick.setHeight(stick.getHeight() + 10);
            stick.setY(stick.getY() - 10);
        }
        ));

        timeline.setCycleCount(Animation.INDEFINITE);

        score.setText(String.valueOf(score_data.getScore()));
        if(score_data.getCherries() < 0) score_data.setCherries(0);
        cherry_text.setText(String.valueOf(score_data.getCherries()));
        pause_menu.setVisible(false);
        game_over_pane.setVisible(false);




        timeline1 = new Timeline(new KeyFrame(Duration.millis(155), e -> {

            if (player.getBoundsInParent().intersects(cherry.getBoundsInParent())) {
                score_data.setCherries(score_data.getCherries() + 1);
                intersect = true;
                cherry_text.setText(String.valueOf(score_data.getCherries()));
                cherry.setVisible(false);
            }

            if (player.getBoundsInParent().intersects(next.getBoundsInParent())) {
                out = true;
            }

            if (player.getBoundsInParent().intersects(current.getBoundsInParent())) {
                out = true;
            }

        }
        ));

        timeline1.setCycleCount(Animation.INDEFINITE);
        timeline1.play();
    }

    private void Game_over() {
        mediaPlayer1.play();
        mediaPlayer.stop();
        last_score.setText(String.valueOf(score_data.getLast()));
        score_data.setLast(score_data.getScore());
        if(score_data.getScore() > score_data.getHighscore())   score_data.setHighscore(score_data.getScore());
        if(intersect) score_data.setCherries(score_data.getCherries()-1);
        cherry_text.setText(String.valueOf(score_data.getCherries()));
        highscore.setText(String.valueOf(score_data.getHighscore()));
        game_over_pane.setVisible(true);
        pause_button_pane.setVisible(false);
        timeline1.stop();
    }


    public void restore_stick(){
        stick.setX(rect_x);
        stick.setY(rect_y);
        stick.setHeight(rect_h);
        stick.setWidth(rect_w);
        stick.setVisible(true);

    }
    public void set_next(){

        cherry.setVisible(false);

        current.setWidth(next.getWidth());
        current.setVisible(true);
        perfect.setVisible(false);

        RandomNumberGenerator rng = RandomNumberGenerator.getRng();

        rand = rng.get_random(200 , 250);
        int rand1 = rng.get_random(50 , 150);
        int rand2 = rng.get_random(2,6);



        perfect.setX(next.getX() + rand   +  (double) (rand1-15)/2);
        next.setX(next.getX() + rand);



        next.setWidth(rand1);

        perfect.setVisible(true);


        if(rand > 200 + 100){
            cherry.setY(431);
            cherry.setX(    (rand - current.getX() - current.getWidth()) / rand2);
            cherry.setVisible(true);
        }
        else{
            cherry.setY(-100);
        }

    }



    @FXML
    void true_pause(MouseEvent event) {on_pause = true;}
    @FXML
    void false_pause(MouseEvent event) {on_pause = false;}
    @FXML
    void pause(MouseEvent event) {
        if(pause_menu_bool){
            pause_menu.setVisible(false);
            pause_menu_bool = false;
        }
        else {
            pause_menu.setVisible(true);
            pause_menu_bool = true;
        }

    }



    @FXML
    void retry(MouseEvent event) throws IOException {
        score_data.setScore(0);


        Stage stage = (Stage)  player.getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("main_scene.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    @FXML
    void save(MouseEvent event) throws IOException {
        score_data.setLast(score_data.getScore());
        if(score_data.getScore() > score_data.getHighscore())   score_data.setHighscore(score_data.getScore());


    }

    @FXML
    void home(MouseEvent event) throws IOException {
        score_data.setLast(score_data.getScore());
        if(score_data.getScore() > score_data.getHighscore())   score_data.setHighscore(score_data.getScore());
        score_data.setScore(0);


        Stage stage = (Stage)  player.getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("initial.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    @FXML
    void home_over(MouseEvent event) throws IOException {
        score_data.setScore(0);
        Stage stage = (Stage)  player.getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("initial.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    @FXML
    void retry_over(MouseEvent event) throws IOException {
        score_data.setScore(0);
        Stage stage = (Stage)  player.getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("main_scene.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);

    }

    @FXML
    void revive(MouseEvent event) throws IOException {
        if(score_data.getCherries() >= 5 ){
            score_data.setCherries(score_data.getCherries() -5);
            Stage stage = (Stage)  player.getScene().getWindow();
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("main_scene.fxml")));
            Scene scene = new Scene(root);
            stage.setScene(scene);
        }

    }




}
