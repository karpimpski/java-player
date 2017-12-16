package musicplayer;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application 
{
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        GridPane root = new GridPane();
        
        Player player = new Player(primaryStage);
        
        //create song buttons
        int i;
        for(i = 0; i < player.songs.length; i++)
        {
            final Song currentSong = player.songs[i];
            Button btn = new Button(currentSong.getName());
            btn.setOnAction(e -> {
                songClick(player, currentSong);
            });
            root.add(btn, 0, i);
        }
        
        //create volume slider
        Slider volumeSlider = new Slider();
        volumeSlider.setMin(0);
        volumeSlider.setMax(100);
        volumeSlider.setValue(50);
        volumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            player.setVolume();
        });
        root.add(volumeSlider, 0, i++);
        
        //pass volumeSlider variable to player
        player.assignVolumeSlider(volumeSlider);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Music Player");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    /*
    * Given a player and a song, determine which action to perform (pause,
    * switch, or play).
    *
    * @param player      Player instance to control the music
    * @param currentSong Song object to be played or paused
    */
    private static void songClick(Player player, Song currentSong)
    {
        if(player.currentSong != currentSong)
        {
            player.switchSong(currentSong);
        }
        else
        {
            if(player.playing) 
            {
                player.pause();
            }
            else
            {
                player.play();
            }
        }
    }
    
}
