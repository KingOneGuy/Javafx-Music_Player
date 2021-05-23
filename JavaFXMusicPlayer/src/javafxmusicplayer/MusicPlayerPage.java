package javafxmusicplayer;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javafx.scene.text.Text;

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
    
    private Text currentTime;
    private Text totalTime;
    private Text timeSlash;
    
    private VBox textVBox;
    private HBox buttonHBox;
    private HBox timeHBox;
    
    private VBox playerGUI;

    private StackPane root;
    
    public MusicPlayerPage(MusicController music)
    {
        // ====SONG TEXT====
        nowPlaying = new Text("Now Playing:");
        songName = new Text(music.getSongName());
        
        // ====TIME TEXT====
        currentTime = new Text("0:00");
        totalTime = new Text("0:00");
        timeSlash = new Text("/");
        
        
        // ====BUTTONS====
        // Next Button
        nextButton = new Button("Next");
        nextButton.setOnAction(value -> {
            // Play next song
            music.playNext();
            updateSong(music.getSongName(), music.getSongLength());
            
            buttonVisibility(music);
        });
        
        // Previous Button
        previousButton = new Button("Previous");
        previousButton.setVisible(false);
        
        previousButton.setOnAction(value -> {
            // Play previous song
            music.playPrevious();
            updateSong(music.getSongName(), music.getSongLength());
            
            buttonVisibility(music);
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
        
        // Time HBox
        timeHBox = new HBox(currentTime, timeSlash, totalTime);
        timeHBox.setAlignment(Pos.BASELINE_CENTER);
        
        // Player GUI (Text, Buttons, Time)
        playerGUI = new VBox(textVBox, buttonHBox, timeHBox);
        VBox.setMargin(textVBox, new Insets(0, 0, 30, 0));
        VBox.setMargin(timeHBox, new Insets(30, 0, 0, 0));
        
        
        // Root
        root = new StackPane();
        root.getChildren().addAll(playerGUI);
        StackPane.setAlignment(playerGUI, Pos.CENTER);
        //StackPane.setAlignment(timeHBox, Pos.CENTER);
        //root.getChildren().addAll(textVBox, buttonHBox);
        //StackPane.setAlignment(textVBox, Pos.CENTER);
        //StackPane.setAlignment(buttonHBox, Pos.CENTER);
    }
    
    public void updateSong(String songName, String songLength)
    {
        setSongName(songName);
        setSongLength(songLength);
    }
    
    // Updates songName text to display specified song name
    // @param String - song name
    public void setSongName(String name)
    {
        songName.setText(name);
    }
    
    public void setSongLength(String songLength)
    {
        totalTime.setText(songLength);
    }
    
    // Controls button visibility
    // If the playlist is at the beginning, hides the previous button
    // If the playlist is at the end, hides the next button
    // @param MusicController music - MusicController to read playlist info from
    public void buttonVisibility(MusicController music)
    {
        nextButton.setVisible(!music.playlistAtEnd());
        previousButton.setVisible(!music.playlistAtBeginning());
    }
    
    public void updateCurrentTime(String time)
    {
        currentTime.setText(time);
    }
    
    // @return - the page to display with dimensions defined in WINDOW_WIDTH and WINDOW_HEIGHT
    public Scene getPage()
    {
        return new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
    }
}
