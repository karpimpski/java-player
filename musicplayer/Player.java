package musicplayer;

import helpers.FileHelper;
import java.io.File;
import javafx.stage.Stage;

/**
 *
 * @author michael
 */
public class Player 
{
    public File musicDir;
    public Song[] songs;
    public Song currentSong;
    public boolean playing = false;
    
    public Player(Stage primaryStage)
    {
        //get music files from user
        musicDir = FileHelper.selectDirectory(primaryStage);
        songs = FileHelper.getMusicChildren(musicDir);
    }
    
    public void play()
    {
        playing = true;
        currentSong.play();
    }
    
    public void pause()
    {
        playing = false;
        currentSong.pause();
    }
    
    public void switchSong(Song song)
    {
        if(currentSong != null) currentSong.stop();
        currentSong = song;
        play();
    }
}
