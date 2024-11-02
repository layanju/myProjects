package pack305;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.swing.GroupLayout.Group;

public class Main extends Application {
//START SYSTEM

    public static void main(String[] args) {
        launch(args);
        Welcome welcomePage = new Welcome();
        welcomePage.Start();
        
   

    }

    public void start(Stage stage) throws Exception {
        Media video = new Media(getClass().getResource("/image/logo.mp4").toURI().toString());
        MediaPlayer player = new MediaPlayer(video);
        MediaView view = new MediaView(player);

        player.setAutoPlay(true);

        StackPane root = new StackPane(view);

        // Here we created the window content with a Node and specified its size
        Scene scene = new Scene(root, 900, 700);

        // Here we set the title for the window
        stage.setTitle("Logo");

        // Here we set the scene we created as the content of the window (stage)
        stage.setScene(scene);

        // Here we showed the window
        stage.show();
    }

//           PauseTransition pause = new PauseTransition(javafx.util.Duration.seconds(8));
//        pause.setOnFinished(new EventHandler<javafx.event.ActionEvent>() {
//            public void handle(javafx.event.ActionEvent event) {
//           
//
//            }
//
//        });
//        pause.play(); // Start the timer
//    }
}
