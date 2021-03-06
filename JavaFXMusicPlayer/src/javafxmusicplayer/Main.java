/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxmusicplayer;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

/**
 *
 * @author fmalapo6597
 */
public class Main extends Application {
    private MusicController music;
    private MusicPlayerPage page;
    
    public static void main(String[] args)
    {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) 
    {
        setUp(primaryStage);
        
        if(music != null && music.playlistExists())
        {
            music.play();
            
            primaryStage.setScene(page.getPage());
            primaryStage.setTitle("Music Player");
            primaryStage.show();
        }
        else
        {
            Platform.exit();
        }
        
    }
    
    // Initializes music and page
    private void setUp(Stage primaryStage)
    {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        try
        {
            Playlist playlist = new Playlist(directoryChooser.showDialog(primaryStage));
            if(playlist.exists())
            {
                music = new MusicController(playlist);
                page = new MusicPlayerPage(music);
                music.setPage(page);
            }
            else
            {
                throw new IllegalArgumentException();
            }
        }
        catch(Exception e)
        {
            System.out.println("Playlist doesn't exist.");
        }
    }
}
