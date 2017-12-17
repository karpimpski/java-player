package helpers;

import java.io.File;
import java.util.ArrayList;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import musicplayer.Song;

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
    * Given a directory, finds all music files that belong to it 
    * and its children.
    * 
    * @param parent the parent directory
    * @param songs  current list of songs
    * @return       an array of music files
    */
    public static Song[] getMusicChildren(File parent, ArrayList<Song> songs) 
    {
        File[] children = parent.listFiles();
        for (File file : children) 
        {
            if (file.isFile()) 
            {
                //if content type is audio, add the file to result
                if(file.getName().substring(file.getName().lastIndexOf(".")+1).equalsIgnoreCase("mp3"))
                {
                    songs.add(new Song(file));
                }
            }
            else if (file.isDirectory()) 
            {
                getMusicChildren(file, songs);
            }
        }
        
        return songs.toArray(new Song[songs.size()]);
    }
}
