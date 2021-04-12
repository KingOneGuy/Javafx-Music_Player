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
import javafx.scene.layout.HBox;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

/**
 *
 * @author Frans Malapo
 */
public class MediaPlayerTest extends Application {
    
    // Holds references to mediaPlayer
    // Garbage collector will clean up otherwise
    private static MediaPlayer mediaPlayer; // MediaPlayer supports AAC, MP3, PCM, H.264/AVC, VP6
    
    @Override
    public void start(Stage primaryStage) {
        // TODO Auto-generated method stub  
        //Initialising path of the media file, replace this with your file path   
        String path = "C:/Users/fmalapo6597/Desktop/2hu/Satori Maiden _ 3rd Eye_ Active NEETS.mp3";
        
        //Instantiating Media class  
        Media media = new Media(new File(path).toURI().toString());  
          
        //Instantiating MediaPlayer class   
        mediaPlayer = new MediaPlayer(media);  
          
        //by setting this property to true, the audio will be played   
        mediaPlayer.play();
        
        Playlist playlist = new Playlist("C:/Users/fmalapo6597/Desktop/2hu");
        playlist.randomize();
        
        // Button declarations
        Button nextButton = new Button("Next");
        
        Button previousButton = new Button("Previous");
        previousButton.setVisible(false);
        
        // Button actions
        nextButton.setOnAction(value -> {
            changeSong(playlist.next());
            
            // Button visibility
            nextButton.setVisible(!playlist.atEnd());
            previousButton.setVisible(true);
            
            System.out.println("Now playing: " + playlist.getIndex() + ". " + playlist.getSongName());
        });
        
        previousButton.setOnAction(value -> {
            changeSong(playlist.previous());
            
            // Button visibility
            previousButton.setVisible(!playlist.atBeginning());
            nextButton.setVisible(true);
            
            System.out.println("Now playing: " + playlist.getIndex() + ". " + playlist.getSongName());
        });
        
        // HBox
        HBox hbox = new HBox(previousButton, nextButton);
        hbox.setMargin(previousButton, new Insets(0, 50, 0, 0));
        
        // Scene setting
        Scene scene = new Scene(hbox, 200, 100);
        
        primaryStage.setScene(scene);
        primaryStage.setTitle("Playing Audio");
        primaryStage.show();
    }
    
    // Changes and plays MediaPlayer's song
    // @param - Media song to change to and play
    private static void changeSong(Media newSong)
    {
        mediaPlayer.dispose();
        mediaPlayer = new MediaPlayer(newSong);
        mediaPlayer.play();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
