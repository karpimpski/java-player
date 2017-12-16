package musicplayer;

import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Song 
{
    private File songFile;
    private MediaPlayer mediaPlayer;
    
    public Song(File f)
    {
        songFile = f;
        Media hit = new Media(songFile.toURI().toString());
        mediaPlayer = new MediaPlayer(hit);
    }
    
    public String getName()
    {
        return songFile.getName();
    }
    
    public void play()
    {
        try
        {
            mediaPlayer.play();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
    public void pause()
    {
        mediaPlayer.pause();
    }
    
    public void stop()
    {
        mediaPlayer.stop();
    }
}
