package musicplayer;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
        
        addMusicButtons(player.songs, root, player);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Music Player");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    /*
    * Adds music buttons to screen.
    *
    * @param songs array of songs to be displayed to user
    */
    private static void addMusicButtons(Song[] songs, GridPane root, Player player)
    {
        for(int i = 0; i < songs.length; i++)
        {
            final Song currentSong = songs[i];
            Button btn = new Button(currentSong.getName());
            btn.setOnAction(e -> {
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
            });
            root.add(btn, 0, i);
        }
    }
    
}
