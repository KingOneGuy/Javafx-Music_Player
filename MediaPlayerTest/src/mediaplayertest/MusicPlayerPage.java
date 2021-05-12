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
 *  Handles the JavaFX GUI.
 */
public class MusicPlayerPage {
    private final int WINDOW_WIDTH = 400; // Width of window
    private final int WINDOW_HEIGHT = 400; // Height of window
    
    private Text nowPlaying; // Displays "Now Playing:"
    private Text songName; // The name of current song
    private Button nextButton; // Button to play next song
    private Button previousButton; // Button to play previous song
    
    private VBox textVBox;
    private HBox buttonHBox;

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
            // Play next song
            music.playNext();
            updateSongName(music.getSongName());
            
            // Button visibility
            nextButton.setVisible(!music.playlistAtEnd());
            previousButton.setVisible(true);
        });
        
        // Previous Button
        previousButton = new Button("Previous");
        previousButton.setVisible(false);
        
        previousButton.setOnAction(value -> {
            // Play previous song
            music.playPrevious();
            updateSongName(music.getSongName());
            
            // Button visibility
            nextButton.setVisible(true);
            previousButton.setVisible(!music.playlistAtBeginning());
        });
        
        // Button HBox
        buttonHBox = new HBox(previousButton, nextButton);
        buttonHBox.setLayoutY(50);
        buttonHBox.setMargin(previousButton, new Insets(0, 50, 0, 0));
        buttonHBox.setAlignment(Pos.BASELINE_CENTER);
        
        // Text VBox
        textVBox = new VBox(nowPlaying, songName);
        textVBox.setAlignment(Pos.BASELINE_CENTER);
        textVBox.setSpacing(10);
        
        // Root
        root = new Pane();
        root.getChildren().add(textVBox);
        root.getChildren().add(buttonHBox);
    }
    
    // Updates songName text to display specified song name
    // @param String - song name
    public void updateSongName(String name)
    {
        songName.setText(name);
    }
    
    // @return - the page to display with dimensions defined in WINDOW_WIDTH and WINDOW_HEIGHT
    public Scene getPage()
    {
        return new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
    }
}
