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

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

/**
 *
 * @author fmalapo6597
 */
public class MediaPlayerTest extends Application {
    
    // Holds references to the media and mediaPlayer
    // Garbage collector will clean up otherwise
    private Media media;
    private MediaPlayer mediaPlayer;
    
    @Override
    public void start(Stage primaryStage) {
        // TODO Auto-generated method stub  
        //Initialising path of the media file, replace this with your file path   
        String path = "C:/Users/fmalapo6597/Desktop/2hu/Satori Maiden _ 3rd Eye_ Active NEETS.mp3";
        
        //Instantiating Media class  
        media = new Media(new File(path).toURI().toString());  
          
        //Instantiating MediaPlayer class   
        mediaPlayer = new MediaPlayer(media);  
          
        //by setting this property to true, the audio will be played   
        mediaPlayer.play();  
        primaryStage.setTitle("Playing Audio");  
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
