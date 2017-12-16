/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import java.io.File;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import musicplayer.Song;

/**
 *
 * @author michael
 */
public class FileHelper 
{
    /*
    * Opens a popup for the user to select a directory.
    * 
    * @return the selected directory
    */
    public static File selectDirectory(Stage primaryStage)
    {
        DirectoryChooser chooser = new DirectoryChooser();
        File selectedDirectory = chooser.showDialog(primaryStage);
        
        return selectedDirectory;
    }
    
    /*
    * Given a directory, finds all music files that belong to it.
    * 
    * @param parent the parent directory
    * @return       an array of music files
    */
    public static Song[] getMusicChildren(File parent)
    {
        File[] children = parent.listFiles();
        File[] tmpResult = new File[children.length];
        int emptyCount = 0;
        
        //goes through each child and, if audio, adds it to result
        for(int i = 0; i < children.length; i++)
        {
            File child = children[i];
            
            try
            {
                //if content type is audio, add the file to result
                if(child.getName().substring(child.getName().lastIndexOf(".")+1).equalsIgnoreCase("mp3"))
                {
                    tmpResult[i] = child;
                }
                //if content type isn't audio, add blank file to result
                else
                {
                    tmpResult[i] = new File("");
                    emptyCount++;
                }
            }
            catch(Exception e)
            {
                //add empty song to avoid array errors
                tmpResult[i] = new File("");
                emptyCount++;
                System.out.println("Error: " + e);
            }
        }
        
        Song[] result = stripEmpty(tmpResult, emptyCount);
        
        return result;
    }
    
    /*
    * Given an array of files, creates a new array with only non-empty files.
    * 
    * @param arr        array of files
    * @param emptyCount how many empty files are in the array
    * @return           array of only non-empty files
    */
    private static Song[] stripEmpty(File[] arr, int emptyCount)
    {
        //initialize result and resultIndex
        Song[] result = new Song[arr.length - emptyCount];
        int resultIndex = 0;
        
        //add each non-empty file to the result array
        for(File file : arr)
        {
            if(!file.getPath().equals(""))
            {
                result[resultIndex++] = new Song(file);
            }
        }
        
        return result;
    }
}
