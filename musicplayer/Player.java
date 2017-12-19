package musicplayer;

import helpers.FileHelper;
import java.io.File;
import java.util.ArrayList;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.stage.Stage;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;

public class Player 
{
    public File musicDir;
    public Song[] songs;
    public Song currentSong;
    public boolean playing = false;
    public Slider volumeSlider;
    public Button playBtn;
    
    public Player(Stage primaryStage)
    {
        //get music files from user
        musicDir = FileHelper.selectDirectory(primaryStage);
        songs = FileHelper.getMusicChildren(musicDir, new ArrayList());
        System.out.println(getAlbums().length);
    }
    
    /*
    * Returns an array of all albums belonging to the music folder.
    */
    public Album[] getAlbums()
    {
        ArrayList<String> albumStrings = new ArrayList();
        ArrayList<Album> albums = new ArrayList();
        
        for(Song song : songs)
        {
            String albumString = getAlbumString(song);
            boolean knownAlbum = albumStrings.contains(albumString);
            if(!knownAlbum)
            {
                if(albumString.equals("")) albumString = "Unknown";
                Album album = new Album(albumString);
                albumStrings.add(albumString);
                albums.add(album);
                
            }
            else
            {
                Album album = new Album(albumString);
                album.addSong(song);
                song.album = album;
            }
        }
        
        return albums.toArray(new Album[albums.size()]);
    }
    
    /*
    * Given a song, returns the string of the album it belongs to.
    *
    * @param song the chosen Song to analyze
    */
    private String getAlbumString(Song song)
    {
        try
        {
            AudioFile f = AudioFileIO.read(song.songFile);
            Tag tag = f.getTag();
            return tag.getFirst(FieldKey.ALBUM);
        }
        catch(Exception ex)
        {
            System.out.println("Error: " + ex);
        }
        
        return "";
    }
    
    public void play()
    {
        if(currentSong != null)
        {
            playBtn.setText("Pause");
            setVolume();
            currentSong.play();
            playing = true;
        }
    }
    
    public void pause()
    {
        playBtn.setText("Play");
        if(currentSong != null)
        {
            playing = false;
            currentSong.pause();
        }
    }
    
    public void switchSong(Song song)
    {
        if(playing) currentSong.stop();
        currentSong = song;
        play();
    }
    
    /*
    * Assigns volume to currentSong.
    * 
    * Note: This value is always tied to the volume slider. You can not enter a
    * custom value.
    */
    public void setVolume()
    {
        double volume = volumeSlider.valueProperty().doubleValue() * .01;
        currentSong.setVolume(volume);
    }
    
    public void assignVolumeSlider(Slider v)
    {
        volumeSlider = v;
    }
}
