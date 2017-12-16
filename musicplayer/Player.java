package musicplayer;

import helpers.FileHelper;
import java.io.File;

/**
 *
 * @author michael
 */
public class Player 
{
    public File musicDir;
    public Song[] songs;
    public Song currentSong;
    
    public Player()
    {
        //get music files from user
        musicDir = FileHelper.selectDirectory();
        songs = FileHelper.getMusicChildren(musicDir);
    }
    
    public void play()
    {
        currentSong.play();
    }
    
    public void switchSong(Song song)
    {
        currentSong = song;
        play();
    }
}
