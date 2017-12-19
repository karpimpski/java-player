package musicplayer;

import java.util.Arrays;

public class Album 
{
    public String title;
    public Song[] songs;
    
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
        Song[] tmpSongs = Arrays.copyOf(songs, songs.length + 1);
        tmpSongs[tmpSongs.length - 1] = song;
        songs = tmpSongs;
    }
}
