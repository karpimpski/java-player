package musicplayer;

import java.io.File;
import java.nio.file.Files;
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
        File[] musicFiles = getMusicChildren(musicDir);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Music Player");
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
    
    /*
    * Given a directory, finds all music files that belong to it.
    * @param parent the parent directory
    * @return       an array of music files
    */
    private static File[] getMusicChildren(File parent)
    {
        File[] children = parent.listFiles();
        File[] tmpResult = new File[children.length];
        int emptyCount = 0;
        
        //goes through each child and, if audio, adds it to result
        for(int i = 0; i < children.length; i++)
        {
            File child = children[i];
            
            try
            {
                //determine content type
                String contentType = Files.probeContentType(child.toPath());

                //if content type is audio, add the file to result
                if(contentType.split("/")[0].equalsIgnoreCase("audio"))
                {
                    tmpResult[i] = child;
                }
                //if content type isn't audio, add blank file to result
                else
                {
                    tmpResult[i] = new File("");
                    emptyCount++;
                }
            }
            catch(Exception e)
            {
                tmpResult[i] = new File("");
                emptyCount++;
                System.out.println("Error: " + e);
            }
        }
        
        File[] result = stripEmpty(tmpResult, emptyCount);
        
        return result;
    }
    
    /*
    * Given an array of files, creates a new array with only non-empty files.
    * @param arr        array of files
    * @param emptyCount how many empty files are in the array
    * @return           array of only non-empty files
    */
    private static File[] stripEmpty(File[] arr, int emptyCount)
    {
        //initialize result and resultIndex
        File[] result = new File[arr.length - emptyCount];
        int resultIndex = 0;
        
        //add each non-empty file to the result array
        for(int i = 0; i < arr.length; i++)
        {
            File file = arr[i];
            if(!file.getPath().equals(""))
            {
                result[resultIndex++] = file;
            }
        }
        
        return result;
    }
    
}
