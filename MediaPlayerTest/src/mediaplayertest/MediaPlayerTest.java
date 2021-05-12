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
import javafx.stage.Screen;

import java.io.File;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author Frans Malapo
 */
public class MediaPlayerTest /*extends Application*/ {
    /*
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
        Text nowPlaying = new Text("Now playing:");
        Text songName = new Text(playlist.getSongName());
        nowPlaying.setTextAlignment(TextAlignment.CENTER);
        nowPlaying.setTextOrigin(VPos.CENTER);
        songName.setTextAlignment(TextAlignment.CENTER);
        nowPlaying.setTextOrigin(VPos.CENTER);
        
        // Button declarations
        Button nextButton = new Button("Next");
        
        Button previousButton = new Button("Previous");
        previousButton.setVisible(false);
        
        // Button actions
        nextButton.setOnAction(value -> {
            changeSong(playlist.next());
            
            songName.setText(playlist.getSongName());
            
            // Button visibility
            nextButton.setVisible(!playlist.atEnd());
            previousButton.setVisible(true);
        });
        
        previousButton.setOnAction(value -> {
            changeSong(playlist.previous());
            
            songName.setText(playlist.getSongName());
            
            // Button visibility
            previousButton.setVisible(!playlist.atBeginning());
            nextButton.setVisible(true);
        });
        
        
        
        // HBox
        HBox hbox = new HBox(previousButton, nextButton);
        hbox.setLayoutY(50);
        hbox.setMargin(previousButton, new Insets(0, 50, 0, 0));
        hbox.setAlignment(Pos.BASELINE_CENTER);
        
        // Text VBox
        VBox vbox = new VBox(nowPlaying, songName);
        vbox.setAlignment(Pos.BASELINE_CENTER);
        vbox.setSpacing(10);
        
        // ButtonHBox + TextVBox
        VBox vbox2 = new VBox(vbox, hbox);
        vbox2.setAlignment(Pos.BASELINE_CENTER);
        vbox2.setMargin(hbox, new Insets(50, 0, 0, 0));
        vbox2.setLayoutX(30);
        
        // Root pane
        Pane root = new Pane();
        root.getChildren().add(vbox2);
        //root.getChildren().add(hbox);
        
        // Scene setting
        Scene scene = new Scene(root, 1000, 1000);
        
        
        
        primaryStage.setScene(scene);
        primaryStage.setTitle("Playing Audio");
        
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX((primScreenBounds.getWidth() - primaryStage.getWidth()) / 2);
        primaryStage.setY((primScreenBounds.getHeight() - primaryStage.getHeight()) / 2);
        primaryStage.show();
    }
    
    // Changes and plays MediaPlayer's song
    // @param - Media song to change to and play. If null, will assume that playlist has reached the end.
    private static void changeSong(Media newSong)
    {
        if(mediaPlayer != null)
        {
            mediaPlayer.dispose();
        }
        
        if(newSong != null)
        {
            mediaPlayer = new MediaPlayer(newSong);
            mediaPlayer.play();
            
            System.out.println("Now playing: " + playlist.getIndex() + ". " + playlist.getSongName());
            
            // Automatically play next song
            mediaPlayer.setOnEndOfMedia(() -> {
                changeSong(playlist.next());
                songName.setText(playlist.getSongName());

                // Button visibility
                nextButton.setVisible(!playlist.atEnd());
                previousButton.setVisible(true);
            });
        }
        else
        {
            System.out.println("Reached end of playlist.");
        }
    }

    /**
     * @param args the command line arguments
     *
    public static void main(String[] args) {
        launch(args);
    }
    */
}
