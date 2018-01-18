package musicplayer;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
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
        addHeaderText();
        
        //create scrollpane
        ScrollPane songScroll = new ScrollPane();
        songScroll.setFitToWidth(true);
        songScroll.setId("song_scroll");
        root.getChildren().add(songScroll);
        
        addArtistButtons(songScroll);
        addVolumeSlider();
        addPlayBtn();
        
        Scene scene = new Scene(root, 300, 250);
        scene.getStylesheets().add("style.css");
        scene.setOnKeyPressed(e -> { keyPressed(e); });
        
        primaryStage.setTitle("Music Player");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private void addHeaderText()
    {
        Text headerText = new Text("Music Player");
        headerText.setId("header_text");
        root.getChildren().add(headerText);
    }
    
    private void addSongButtons(ScrollPane songScroll, Song[] songs)
    {
        //add all songs to VBox
        VBox songBox = new VBox();
        for(Song currentSong : songs)
        {
            Button btn = new Button(currentSong.title);
            btn.setPrefWidth(Double.MAX_VALUE);
            btn.setOnMouseClicked(e -> {
                songClick(currentSong);
            });
            btn.getStyleClass().add("option_btn");
            songBox.getChildren().add(btn);
        }
        
        songScroll.setContent(songBox);
    }
    
    public void addAlbumButtons(ScrollPane songScroll, Album[] albums)
    {
        VBox albumBox = new VBox();
        for(Album currentAlbum : albums)
        {
            Button btn = new Button(currentAlbum.title);
            btn.setPrefWidth(Double.MAX_VALUE);
            btn.setOnMouseClicked(e -> {
                addSongButtons(songScroll, currentAlbum.songs); 
            });
            btn.getStyleClass().add("option_btn");
            albumBox.getChildren().add(btn);
        }
        
        songScroll.setContent(albumBox);
    }
    
    public void addArtistButtons(ScrollPane songScroll)
    {
        VBox artistBox = new VBox();
        for(Artist currentArtist : player.artists)
        {
            Button btn = new Button(currentArtist.title);
            btn.setPrefWidth(Double.MAX_VALUE);
            btn.setOnMouseClicked(e -> {
                addAlbumButtons(songScroll, currentArtist.albums); 
            });
            btn.getStyleClass().add("option_btn");
            artistBox.getChildren().add(btn);
        }
        
        songScroll.setContent(artistBox);
    }
    
    private void addVolumeSlider()
    {
        Slider volumeSlider = new Slider();
        volumeSlider.setMin(0);
        volumeSlider.setMax(100);
        volumeSlider.setValue(50);
        volumeSlider.valueProperty().addListener((ob, ov, nv) -> {
            if(player.currentSong != null) player.setVolume();
        });
        root.getChildren().add(volumeSlider);
        player.assignVolumeSlider(volumeSlider);
    }
    
    private void addPlayBtn()
    {
        Button playBtn = new Button("Play");
        playBtn.setId("play_btn");
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
    * @param currentSong Song object to be played or paused
    */
    private void songClick(Song currentSong)
    {
        player.switchSong(currentSong);
    }
    
    /*
    * Based on current song status, determine whether to pause or play.
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
