//File streams.
import java.io.FileInputStream;
import java.io.FileOutputStream;

//Object streams.
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

//IO Exceptions
import java.io.FileNotFoundException;
import java.io.IOException;


///------------------
/// Class: Manager
/// Author: Drew Ridley
/// Purpose: To manage and instance LabrynthGames accordingly.
/// Date Modified: 3/25/22.
/// Methods: main(string[] args), load
class Manager
{
    private Game currentGame;


    //The entrypoint of the program.
    public static void main(String[] args) {
        
    }

    //Loads the game from the disk into the attribute currentGame.
    public void loadGame(String filename) {
        try {
            FileInputStream fs = new FileInputStream(filename);
            ObjectInputStream objs = new ObjectInputStream(fs);

            currentGame = (Game) objs.readObject();
        }
        catch (FileNotFoundException fne) {
            System.out.println("Attempted to load from a file that does not exist!");
        }   
        catch (IOException ioe) {
            //TODO: implement partial retry when encountering IOException.
            System.out.println("A generic IO exception has occured.");
        }
        catch (ClassNotFoundException cnf) {
            System.out.println("File loading encountered a fatal mismatch in class types!");
        }
    }

    //Save the game to the disk from currentGame attribute.
    //Note: the filename must include an extension.
    public void saveGame(String filename) {    
        try {
            FileOutputStream fs = new FileOutputStream(filename);
            ObjectOutputStream objs = new ObjectOutputStream(fs);

            //Write the object to the file specified.
            objs.writeObject(this.currentGame);

            //Close the filestream to prevent a memory leak (file streams are not managed by the JVM).
            fs.close();
        }
        catch (IOException ex) {
            //TODO: insert a GUI popup here to display the exception to the user.
            System.out.println("An error occured while attempting to save the game...");
            System.out.println(ex.toString());
        }
    }
}