package musicplayer;

import helpers.FileHelper;
import java.io.File;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.stage.Stage;

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
        songs = FileHelper.getMusicChildren(musicDir);
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
        if(currentSong != null) currentSong.stop();
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
