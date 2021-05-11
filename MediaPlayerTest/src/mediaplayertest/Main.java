/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mediaplayertest;

import javafx.application.Application;
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
        setUp();
        
        music.play();
        
        primaryStage.setScene(page.getPage());
        primaryStage.show();
    }
    
    private void setUp()
    {
        music = new MusicController(new Playlist("C:/Users/fmalapo6597/Desktop/2hu"));
        music.randomizePlaylist();
        
        page = new MusicPlayerPage(music);
    }
}
