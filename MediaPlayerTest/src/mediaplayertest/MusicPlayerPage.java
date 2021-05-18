package mediaplayertest;

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
    
    private VBox textVBox;
    private HBox buttonHBox;
    private VBox buttonAndText;

    private StackPane root;
    
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
        
        // Button + Text
        buttonAndText = new VBox(textVBox, buttonHBox);
        buttonAndText.setMargin(textVBox, new Insets(0, 0, 30, 0));
        
        // Root
        root = new StackPane();
        root.getChildren().addAll(buttonAndText);
        StackPane.setAlignment(buttonAndText, Pos.CENTER);
        //root.getChildren().addAll(textVBox, buttonHBox);
        //StackPane.setAlignment(textVBox, Pos.CENTER);
        //StackPane.setAlignment(buttonHBox, Pos.CENTER);
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
