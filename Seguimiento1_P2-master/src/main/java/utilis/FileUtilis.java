package utilis;

import model.Toy;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtilis {
    public static void saveToys(File file, List<Toy> list){

        try{
            // Create a FileOutputStream to write data to the specified file
            FileOutputStream exit = new FileOutputStream(file);
            // Create an ObjectOutputStream to write objects to the output stream
            ObjectOutputStream objectExit = new ObjectOutputStream(exit);
            // Write the list of toys to the output stream
            objectExit.writeObject(list);
            // Close the ObjectOutputStream to release associated resources
            objectExit.close();
        } catch (FileNotFoundException e){
            // Handle FileNotFoundException by printing a message indicating that the file doesn't exist
            System.out.println("The file does not exist");
        } catch (Exception e){
            // Handle any other exception by printing the exception message
            System.out.println(e.getMessage());
        }

        }
    public static List<Toy> getToys(File file){
        List<Toy> toys = new ArrayList<>();

        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInput ois = new ObjectInputStream(fis);
            toys = (List<Toy>) ois.readObject();
            ois.close();
        } catch (FileNotFoundException e) {
            System.out.println("File does not exist");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return toys;
    }
}

