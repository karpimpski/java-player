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
    public Album[] albums;
    public Artist[] artists;
    
    public Player(Stage primaryStage)
    {
        //get music files from user
        musicDir = FileHelper.selectDirectory(primaryStage);
        songs = FileHelper.getMusicChildren(musicDir, new ArrayList());
        albums = getAlbums();
        artists = getArtists();
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
            //create album string and determine if album is already known
            String albumString = getAlbumString(song);
            boolean knownAlbum = albumStrings.contains(albumString);
            
            //if album isn't known, create a new one
            if(!knownAlbum)
            {
                if(albumString.equals("")) albumString = "Unknown";
                Album album = new Album(albumString);
                albumStrings.add(albumString);
                albums.add(album);
                album.addSong(song);
                song.album = album;
            }
            //if album is known, find it and 
            else
            {
                for(Album selectedAlbum : albums)
                {
                    if(selectedAlbum.title.equalsIgnoreCase(albumString))
                    {
                        selectedAlbum.addSong(song);
                        song.album = selectedAlbum;
                    }
                }
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
    
    public Artist[] getArtists()
    {
        ArrayList<String> artistStrings = new ArrayList();
        ArrayList<Artist> artists = new ArrayList();
        
        for(Album album : albums)
        {
            //create album string and determine if album is already known
            String artistString = getArtistString(album);
            boolean knownArtist = artistStrings.contains(artistString);
            
            //if album isn't known, create a new one
            if(!knownArtist)
            {
                if(artistString.equals("")) artistString = "Unknown";
                Artist artist = new Artist(artistString);
                artistStrings.add(artistString);
                artists.add(artist);
                artist.addAlbum(album);
                album.artist = artist;
            }
            //if album is known, find it add to Artist
            else
            {
                for(Artist selectedArtist : artists)
                {
                    if(selectedArtist.title.equalsIgnoreCase(artistString))
                    {
                        selectedArtist.addAlbum(album);
                        album.artist = selectedArtist;
                    }
                }
            }
        }
        
        return artists.toArray(new Artist[artists.size()]);
    }
    
    private String getArtistString(Album album)
    {
        try
        {
            AudioFile f = AudioFileIO.read(album.songs[0].songFile);
            Tag tag = f.getTag();
            return tag.getFirst(FieldKey.ALBUM_ARTIST);
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
