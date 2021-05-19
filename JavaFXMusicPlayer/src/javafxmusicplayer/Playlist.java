/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxmusicplayer;

import java.io.File;
import java.util.Random;
import javafx.scene.media.Media;

/**
 *
 * @author Frans Malapo
 */
public class Playlist {
    private File songs[]; // List of songs
    private int current = 0; // Array index of song currently being played 
    
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
            songs = getMP3s(folder);
            current = 0;
        }
        catch(IllegalArgumentException e)
        {
            songs = null;
            System.out.println("File is not a directory");
            e.printStackTrace();
        }
    }
    
    // Initialize Playlist with the direcotry containing the songs
    // Throws IllegalArgumentException if the File passed isn't a directory
    public Playlist(File directory)
    {
        try
        {
            if(!directory.isDirectory())
            {
               throw new IllegalArgumentException();
            }
            songs = getMP3s(directory);
            current = 0;
        }
        catch(IllegalArgumentException e)
        {
            songs = null;
            System.out.println("File is not a directory");
            e.printStackTrace();
        }
    }
    
    // @param File folder - directory to get MP3s from
    // @return File[] - the array containing only MP3s
    // Precondition: folder is a directory
    private static File[] getMP3s(File folder)
    {
        File[] output = new File[numMP3s(folder)];
        int count = 0;
        for(File file : folder.listFiles())
        {
            if(file.getName().toLowerCase().endsWith(".mp3"))
            {
                output[count] = file;
                count++;
            }
        }
        return output;
    }
    
    // @param File folder - directory to check for MP3s
    // @return int - the amount of MP3s within given directory
    // Precondition: folder is a directory
    private static int numMP3s(File folder)
    {
        int count = 0;
        for(File file : folder.listFiles())
        {
            if(file.getName().toLowerCase().endsWith(".mp3"))
            {
                count++;
            }
        }
        return count;
    }
    
    // Sets current to the next song in the list and returns the song as Media
    // If already at the end of the list, returns null
    // @return Media - Next song as Media. Null if at end of list.
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
    // @return Media - Previous song as Media. Null if at beginning of list.
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
    
    // @return - return current index of playlist
    int getIndex()
    {
        return current;
    }
    
    // @return - The song name without .mp3 extension as a String
    String getSongName()
    {
        String temp = songs[current].getName();
        return temp.substring(0, temp.lastIndexOf("."));
    }
    
    // @return - Whether the current song the first song in the playlist
    boolean atBeginning()
    {
        return current == 0;
    }
    
    // @return - Whether the current song the last song in the playlist
    boolean atEnd()
    {
        return current == songs.length - 1;
    }
    
    // Meant to check if initial constructor worked properly
    // The Playlist exists if songs is not null and has at least one song
    // @return - whether songs array exists depending on criteria above
    boolean exists()
    {
        return songs != null && songs.length >= 1;
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
