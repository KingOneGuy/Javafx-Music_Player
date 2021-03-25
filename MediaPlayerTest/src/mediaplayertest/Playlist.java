/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mediaplayertest;

import java.io.File;
import java.net.URI;
import java.util.Random;

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
    
    // Sets current to the next song in the list and returns the song's URI
    // If already at the end of the list, returns null
    URI next()
    {
        if(current+1 != songs.length)
        {
            current++;
            return songs[current].toURI();
        }
        else
        {
            System.out.println("Already reached end of playlist.");
            return null;
        }
    }
    
    // Sets current to the previous song in the list and returns the song's URI
    // If already at the beginning of the list, returns null
    URI previous()
    {
        if(current > 0)
        {
            current--;
            return songs[current].toURI();
        }
        else
        {
            System.out.println("Already at beginning of playlist.");
            return null;
        }
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
