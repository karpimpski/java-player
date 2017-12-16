package musicplayer;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application 
{
    private Player player;
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        GridPane root = new GridPane();
        
        player = new Player(primaryStage);
        
        //create song buttons
        int i;
        for(i = 0; i < player.songs.length; i++)
        {
            final Song currentSong = player.songs[i];
            Button btn = new Button(currentSong.getName());
            btn.setFocusTraversable(false);
            btn.setOnAction(e -> {
                songClick(currentSong);
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
        
        //create play button
        Button playBtn = new Button("Play");
        player.playBtn = playBtn;
        playBtn.setOnAction(e -> {
            playClick();
        });
        root.add(playBtn, 0, i++);
        
        //pass volumeSlider variable to player
        player.assignVolumeSlider(volumeSlider);
        
        Scene scene = new Scene(root, 300, 250);
        scene.setOnKeyPressed(e -> { keyPressed(e); });
        
        primaryStage.setTitle("Music Player");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    /*
    * Given a player and a song, determine which action to perform (swtich,
    * play, or reset.
    *
    * @param player      Player instance to control the music
    * @param currentSong Song object to be played or paused
    */
    private void songClick(Song currentSong)
    {
        player.switchSong(currentSong);
    }
    
    /*
    * Given a player and a song, determine whether to pause or play.
    *
    * @param player      Player instance to control the music
    * @param currentSong Song object to be played or paused
    */
    private void playClick()
    {
        if(player.currentSong != null)
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
    
    /*
    * Add keyboard listeners (using methods from other click listeners)
    */
    private void keyPressed(KeyEvent e)
    {
        switch(e.getCode().toString().toLowerCase())
        {
            case("space"):
                playClick();
                break;
            default:
                break;
        }
    }
}
