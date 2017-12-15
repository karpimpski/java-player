package musicplayer;

import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Song 
{
    private File songFile;
    
    public Song(File f)
    {
        songFile = f;
    }
    
    public String getName()
    {
        return songFile.getName();
    }
    
    public void play()
    {
        try
        {
            Media hit = new Media(songFile.toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(hit);
            mediaPlayer.play();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}
