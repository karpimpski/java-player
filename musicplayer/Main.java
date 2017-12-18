package musicplayer;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application 
{
    private Player player;
    private VBox root;
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        root = new VBox();
        player = new Player(primaryStage);
        
        addSongContainer();
        addVolumeSlider();
        addPlayBtn();
        
        Scene scene = new Scene(root, 300, 250);
        scene.setOnKeyPressed(e -> { keyPressed(e); });
        
        primaryStage.setTitle("Music Player");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private void addSongContainer()
    {
        //add all songs to VBox
        VBox songBox = new VBox();
        for(int i = 0; i < player.songs.length; i++)
        {
            final Song currentSong = player.songs[i];
            Button btn = new Button(currentSong.getName());
            btn.setPrefWidth(Double.MAX_VALUE);
            btn.setOnAction(e -> {
                songClick(currentSong);
            });
            songBox.getChildren().add(btn);
        }
        
        //create scrollpane
        ScrollPane songScroll = new ScrollPane();
        songScroll.setContent(songBox);
        songScroll.setFitToWidth(true);
        
        root.getChildren().add(songScroll);
    }
    
    private void addVolumeSlider()
    {
        Slider volumeSlider = new Slider();
        volumeSlider.setMin(0);
        volumeSlider.setMax(100);
        volumeSlider.setValue(50);
        volumeSlider.valueProperty().addListener((ob, ov, nv) -> {
            player.setVolume();
        });
        root.getChildren().add(volumeSlider);
        player.assignVolumeSlider(volumeSlider);
    }
    
    private void addPlayBtn()
    {
        Button playBtn = new Button("Play");
        player.playBtn = playBtn;
        playBtn.setOnAction(e -> {
            playClick();
        });
        root.getChildren().add(playBtn);
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
