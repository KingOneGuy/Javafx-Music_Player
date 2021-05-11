/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mediaplayertest;

import javafx.scene.media.MediaPlayer;

/**
 *
 * @author fmalapo6597
 */
public class MusicController {
    private MediaPlayer mediaPlayer; // MediaPlayer supports AAC, MP3, PCM, H.264/AVC, VP6
    private Playlist playlist; // Playlist of songs
    
    public MusicController(Playlist playlist)
    {
        this.playlist = playlist;
        mediaPlayer = new MediaPlayer(playlist.current());
    }
    
    public void play()
    {
        mediaPlayer.play();
    }
    
    public void playNext()
    {
        mediaPlayer.dispose();
        mediaPlayer = new MediaPlayer(playlist.next());
        setOnEnd();
    }
    
    public void playPrevious()
    {
        mediaPlayer.dispose();
        mediaPlayer = new MediaPlayer(playlist.previous());
        setOnEnd();
    }
    
    public String getSongName()
    {
        return playlist.getSongName();
    }
    
    public void randomizePlaylist()
    {
        playlist.randomize();
    }
    
    public boolean playlistAtBeginning()
    {
        return playlist.atBeginning();
    }
    
    public boolean playlistAtEnd()
    {
        return playlist.atEnd();
    }
    
    private void setOnEnd()
    {
        mediaPlayer.setOnEndOfMedia(() -> {
            playNext();
        });
    }
}
