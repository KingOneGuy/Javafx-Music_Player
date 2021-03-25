/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mediaplayertest;

import java.io.File;
import java.util.Random;

/**
 *
 * @author fmalapo6597
 */
public class Playlist {
    private File songs[];
    
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
        }
        catch(IllegalArgumentException e)
        {
            System.out.println("File is not a directory");
            e.printStackTrace();
        }
    }
    
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
