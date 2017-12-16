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
        
        Slider volume = new Slider();
        volume.setMin(0);
        volume.setMax(100);
        volume.setValue(50);
        root.add(volume, 0, i++);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Music Player");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
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
