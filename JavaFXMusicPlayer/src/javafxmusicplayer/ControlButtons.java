/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxmusicplayer;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

/**
 *
 * @author frans
 */
class ControlButtons {
    private final MusicPlayerPage page;
    private final MusicController music;
    
    private Button nextButton;
    private Button previousButton;
    private Button pauseButton;
    
    private HBox hbox;
    
    ControlButtons(MusicPlayerPage page, MusicController music)
    {
        this.page = page;
        this.music = music;

        // Next Button
        nextButton = new Button("Next");
        nextButton.setVisible(!music.playlistAtEnd());
        
        nextButton.setOnAction(value -> {
            // Play next song
            music.playNext();
            page.updateSong(music.getSongName(), music.getSongLength());
            
            buttonVisibility();
        });
        
        // Previous Button
        previousButton = new Button("Previous");
        previousButton.setVisible(false);
        
        previousButton.setOnAction(value -> {
            // Play previous song
            music.playPrevious();
            page.updateSong(music.getSongName(), music.getSongLength());
            
            buttonVisibility();
        });
        
        // Pause Button
        pauseButton = new Button("||");
        pauseButton.setOnAction(value -> {
            if(pauseButton.getText() == "||") // Pause
            {
                pauseButton.setText(">");
                music.pause();
            }
            else // Play
            {
                pauseButton.setText("||");
                music.play();
            }
        });
        
        // HBox settings
        hbox = new HBox(previousButton, pauseButton, nextButton);
        hbox.setLayoutY(50);
        hbox.setSpacing(30);
        hbox.setAlignment(Pos.BASELINE_CENTER);
    }
    
    HBox getButtons()
    {
        return hbox;
    }
    
    // Controls button visibility
    // If the playlist is at the beginning, hides the previous button
    // If the playlist is at the end, hides the next button
    // @param MusicController music - MusicController to read playlist info from
    void buttonVisibility()
    {
        nextButton.setVisible(!music.playlistAtEnd());
        previousButton.setVisible(!music.playlistAtBeginning());
    }
}
