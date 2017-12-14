package musicplayer;

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
        
        File musicDir = selectDirectory();
        System.out.println(musicDir);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    /*
    * Opens a popup for the user to select a directory.
    * @return the selected directory
    */
    private static File selectDirectory()
    {
        //open chooser to the user's home directory
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        chooser.setDialogTitle("Select music folder");
        
        //set selection to directories only
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);
        
        //open file chooser with "Select" button
        chooser.showDialog(chooser, "Select");
        
        return chooser.getSelectedFile();
    }
    
}
