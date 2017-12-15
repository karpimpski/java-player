package musicplayer;

import helpers.FileHelper;
import java.io.File;
import java.net.URLEncoder;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MusicPlayer extends Application {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        GridPane root = new GridPane();
        
        //get music files from user
        File musicDir = FileHelper.selectDirectory();
        File[] musicFiles = FileHelper.getMusicChildren(musicDir);
        
        addMusicButtons(musicFiles, root);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Music Player");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    /*
    * Adds music buttons to screen.
    *
    * @param musicFiles array of audio files
    */
    private static void addMusicButtons(File[] musicFiles, GridPane root)
    {
        for(int i = 0; i < musicFiles.length; i++)
        {
            final File currentFile = musicFiles[i];
            Button btn = new Button(currentFile.getName());
            btn.setOnAction(e -> {
                play(currentFile);
            });
            root.add(btn, 0, i);
        }
    }
    
    public static void play(File file)
    {
        try
        {
            Media hit = new Media(file.toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(hit);
            mediaPlayer.play();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}
