/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mediaplayertest;

import java.io.File;
import java.util.Random;
import javafx.scene.media.Media;

/**
 *
 * @author Frans Malapo
 */
public class Playlist {
    private File songs[]; // List of songs
    private int current; // Array index of song currently being played 
    
    // Initialize Playlist with the path to the directory containing the songs
    // Throws IllegalArgumentException if the path passed isn't a directory
    public Playlist(String path)
    {
        try
        {
            File folder = new File(path);
            if(!folder.isDirectory())
            {
               throw new IllegalArgumentException();
            }
            songs = folder.listFiles();
            current = 0;
        }
        catch(IllegalArgumentException e)
        {
            System.out.println("File is not a directory");
            e.printStackTrace();
        }
    }
    
    // Sets current to the next song in the list and returns the song as Media
    // If already at the end of the list, returns null
    // @return - Next song's URI as String. Null if at end of list.
    Media next()
    {
        if(current+1 < songs.length)
        {
            current++;
            return new Media(songs[current].toURI().toString());
        }
        else
        {
            System.out.println("Already reached end of playlist.");
            return null;
        }
    }
    
    // Sets current to the previous song in the list and returns the song as Media
    // If already at the beginning of the list, returns null
    // @return - Previous song's URI as String. Null if at beginning of list.
    Media previous()
    {
        if(current > 0)
        {
            current--;
            return new Media(songs[current].toURI().toString());
        }
        else
        {
            System.out.println("Already at beginning of playlist.");
            return null;
        }
    }
    
    // @return - the current song as a Media object
    Media current()
    {
        return new Media(songs[current].toURI().toString());
    }
    
    // Randomize the playlist
    void randomize()
    {
        Random rand = new Random();
        for(int i = 0; i < songs.length; i++)
        {
            int tempI = rand.nextInt(songs.length);
            File tempF = songs[tempI];
            
            songs[tempI] = songs[i];
            songs[i] = tempF;
        }
    }
    
    
    // @return - The song name without .mp3 extension as a String
    String getSongName()
    {
        String temp = songs[current].getName();
        return temp.substring(0, temp.lastIndexOf("."));
    }
    
    // Returns a string of the entire list of songs in list format
    // Ex:
    //   1. Song1.mp3
    //   2. Song2.mp3
    //   3. Song3.mp3
    // @return - String value of list of songs in numbered format
    @Override
    public String toString()
    {
        String output = "";
        for(int i = 0; i < songs.length; i++)
        {
            output += (i+1) + ". " + songs[i].getName();
            if(i != songs.length - 1)
            {
                output += "\n";
            }
        }
        return output;
    }
}
