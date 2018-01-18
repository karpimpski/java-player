package musicplayer;

import java.util.Arrays;

public class Artist 
{
    public String title;
    public Album[] albums;
    
    public Artist (String name)
    {
        title = name;
        albums = new Album[0];
        System.out.println("ARTIST: " + title);
    }
    
    /*
    * Given a song, adds it to the songs array
    */
    public void addAlbum(Album album)
    {
        albums = append(album);
    }
    
    public Album[] append(Album album)
    {
        Album[] tmpAlbums = Arrays.copyOf(albums, albums.length+1);
        tmpAlbums[albums.length] = album;
        return tmpAlbums;
    }
}
