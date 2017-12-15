package musicplayer;

import helpers.FileHelper;
import java.io.File;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javax.swing.JFileChooser;

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
        
        File musicDir = FileHelper.selectDirectory();
        File[] musicFiles = FileHelper.getMusicChildren(musicDir);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Music Player");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
