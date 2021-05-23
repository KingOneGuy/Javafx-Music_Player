package javafxmusicplayer;

import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

/**
 *  Controls the MediaPlayer and keeps track of playlist.
 *  Handles display of music-related values to page.
 */
public class MusicController {
    private MediaPlayer mediaPlayer; // MediaPlayer supports AAC, MP3, PCM, H.264/AVC, VP6
    private Playlist playlist; // Playlist of songs
    private MusicPlayerPage page; // Display page
    
    // Creates new MusicController using given playlist. Randomizes playlist automatically.
    // @param Playlist playlist - playlist to play
    public MusicController(Playlist playlist)
    {
        this.playlist = playlist;
        playlist.randomize();
        mediaPlayer = new MediaPlayer(playlist.current());
        setTimeListener();
    }
    
    // Creates new MusicController using given playlist. Randomizes playlist based on given boolean.
    // @param Playlist playlist - playlist to play
    // @param boolean randomize - whether or not to randomize the playlist
    public MusicController(Playlist playlist, boolean randomize)
    {
        this.playlist = playlist;
        if(randomize) { playlist.randomize(); }
        mediaPlayer = new MediaPlayer(playlist.current());
        setTimeListener();
    }
    
    // Sets the page the controller will display on. Necessary to update NowPlaying text.
    // @param MusicPlayerPage page - page to be set
    public void setPage(MusicPlayerPage page)
    {
        this.page = page;
    }
    
    // Plays the media
    public void play()
    {
        mediaPlayer.play();
    }
    
    // Plays the next song and automatically updates position in playlist
    // Reinitializes setOnEnd()
    // Precondition: Not at the end of playlist
    void playNext()
    {
        mediaPlayer.dispose();
        mediaPlayer = new MediaPlayer(playlist.next());
        initializeFunctions();
        mediaPlayer.play();
    }
    
    // Plays the previous song and automatically updates position in playlist
    // Reinitializes setOnEnd()
    // Precondition: Not at the beginning of playlist
    void playPrevious()
    {
        mediaPlayer.dispose();
        mediaPlayer = new MediaPlayer(playlist.previous());
        initializeFunctions();
        mediaPlayer.play();
    }
    
    // @return String - name of the current song playing
    String getSongName()
    {
        return playlist.getSongName();
    }
    
    // Randomizes playlist
    void randomizePlaylist()
    {
        playlist.randomize();
    }
    
    // @return boolean - whether the playlist is at the beginning
    boolean playlistAtBeginning()
    {
        return playlist.atBeginning();
    }
    
    // @return boolean - whether the playlist is at the end
    boolean playlistAtEnd()
    {
        return playlist.atEnd();
    }
    
    // @return boolean - whether the playlist exists according to criteria listed in Playlist.exists()
    boolean playlistExists()
    {
        return playlist.exists();
    }
    
    String getSongLength()
    {
        return durationToString(mediaPlayer.getTotalDuration());
    }
    
    private String durationToString(Duration duration)
    {
        int totalSeconds = (int) duration.toSeconds();
        return (totalSeconds / 60) + ":" + (totalSeconds % 60);
    }
    
    // Sets the MediaPlayer to automaticaly play the next song at song end
    // MediaPlayer will do nothing if at the end of playlist
    private void setOnEnd()
    {
        mediaPlayer.setOnEndOfMedia(() -> {
            if(!playlistAtEnd())
            {
                playNext();
                page.setSongName(getSongName());
                page.updateSong(getSongName(), getSongLength());
                page.buttonVisibility(this);
            }
        });
    }
    
    private void setTimeListener()
    {
        mediaPlayer.currentTimeProperty().addListener((observable, oldValue, newValue) -> {
            page.updateCurrentTime(durationToString(newValue));
        });
    }
    
    private void initializeFunctions()
    {
        setOnEnd();
        setTimeListener();
    }
}
