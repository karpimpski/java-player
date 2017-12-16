package musicplayer;

import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Song 
{
    final private File songFile;
    final private MediaPlayer mediaPlayer;
    
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
    
    public void setVolume(double volume)
    {
        mediaPlayer.setVolume(volume);
    }
}
