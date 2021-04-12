/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mediaplayertest;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.File;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author Frans Malapo
 */
public class MediaPlayerTest extends Application {
    
    // Holds references to mediaPlayer
    // Garbage collector will clean up otherwise
    private static MediaPlayer mediaPlayer; // MediaPlayer supports AAC, MP3, PCM, H.264/AVC, VP6
    private static Playlist playlist; // Playlist of songs
    
    @Override
    public void start(Stage primaryStage) {
        playlist = new Playlist("C:/Users/fmalapo6597/Desktop/2hu");
        playlist.randomize();
        
        changeSong(playlist.current());
        
        // Label declaration
        // TEST VBOX VERTICAL ALIGNMENT
        Text nowPlaying = new Text("Now playing:\n\n" + playlist.getSongName());
        nowPlaying.setTextAlignment(TextAlignment.CENTER);
        nowPlaying.setTextOrigin(VPos.CENTER);
        nowPlaying.setLayoutY(20);
        nowPlaying.setLayoutX(20);
        
        // Button declarations
        Button nextButton = new Button("Next");
        
        Button previousButton = new Button("Previous");
        previousButton.setVisible(false);
        
        // Button actions
        nextButton.setOnAction(value -> {
            changeSong(playlist.next());
            
            nowPlaying.setText("Now playing:\n\n" + playlist.getSongName());
            
            // Button visibility
            nextButton.setVisible(!playlist.atEnd());
            previousButton.setVisible(true);
        });
        
        previousButton.setOnAction(value -> {
            changeSong(playlist.previous());
            
            nowPlaying.setText("Now playing:\n\n" + playlist.getSongName());
            
            // Button visibility
            previousButton.setVisible(!playlist.atBeginning());
            nextButton.setVisible(true);
        });
        
        // HBox
        HBox hbox = new HBox(previousButton, nextButton);
        hbox.setLayoutY(40);
        hbox.setMargin(previousButton, new Insets(0, 50, 0, 0));
        
        // Root pane
        Pane root = new Pane();
        root.getChildren().add(nowPlaying);
        root.getChildren().add(hbox);
        
        // Scene setting
        Scene scene = new Scene(root, 200, 100);
        
        primaryStage.setScene(scene);
        primaryStage.setTitle("Playing Audio");
        primaryStage.show();
    }
    
    // Changes and plays MediaPlayer's song
    // @param - Media song to change to and play
    private static void changeSong(Media newSong)
    {
        if(mediaPlayer != null)
        {
            mediaPlayer.dispose();
        }
        mediaPlayer = new MediaPlayer(newSong);
        mediaPlayer.play();
        
        System.out.println("Now playing: " + playlist.getIndex() + ". " + playlist.getSongName());
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
