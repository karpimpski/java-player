package musicplayer;

import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;

public class Song 
{
    final private File songFile;
    final private MediaPlayer mediaPlayer;
    
    public String title;
    
    public Song(File f)
    {
        songFile = f;
        Media hit = new Media(songFile.toURI().toString());
        mediaPlayer = new MediaPlayer(hit);
        gatherMetaData();
    }
    
    private void gatherMetaData()
    {
        try
        {
            AudioFile f = AudioFileIO.read(songFile);
            Tag tag = f.getTag();
            title = tag.getFirst(FieldKey.TITLE);
        }
        catch(Exception ex)
        {
            title = songFile.getName();
            System.out.println("Error: " + ex);
        }
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
