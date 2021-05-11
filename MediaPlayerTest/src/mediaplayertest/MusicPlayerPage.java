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
 * @author fmalapo6597
 */
public class MusicPlayerPage {
    private Text nowPlaying;
    private Text songName;
    private Button nextButton;
    private Button previousButton;

    private Pane root;
    
    public MusicPlayerPage(MusicController music)
    {
        // ====TEXT====
        nowPlaying = new Text("Now Playing:");
        songName = new Text(music.getSongName());
        
        // ====BUTTONS====
        // Next Button
        nextButton = new Button("Next");
        nextButton.setOnAction(value -> {
            music.playNext();
            updateSongName(music);
            
            // Button visibility
            nextButton.setVisible(!music.playlistAtEnd());
            previousButton.setVisible(true);
        });
        
        // Previous Button
        previousButton = new Button("Previous");
        previousButton.setVisible(false);
        
        previousButton.setOnAction(value -> {
            music.playPrevious();
            updateSongName(music);
            
            // Button visibility
            nextButton.setVisible(true);
            previousButton.setVisible(!music.playlistAtBeginning());
        });
    }
    
    public void updateSongName(MusicController music)
    {
        songName.setText(music.getSongName());
    }
    
    public Scene getPage()
    {
        return new Scene(root);
    }
}
