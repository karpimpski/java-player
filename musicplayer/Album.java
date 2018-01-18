package musicplayer;

import java.util.Arrays;

public class Album 
{
    public String title;
    public Song[] songs;
    public Artist artist;
    
    public Album (String name)
    {
        title = name;
        songs = new Song[0];
    }
    
    /*
    * Given a song, adds it to the songs array
    */
    public void addSong(Song song)
    {
        songs = append(song);
    }
    
    public Song[] append(Song song)
    {
        Song[] tmpSongs = Arrays.copyOf(songs, songs.length+1);
        tmpSongs[songs.length] = song;
        return tmpSongs;
    }
}
