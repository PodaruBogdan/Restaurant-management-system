package dataLayer;

import java.io.*;
import java.util.Set;


public class FileWriter {
    public static void writeSet(Set<? extends Object> set){
       writeObject(set,RestaurantSerializator.fileName,false);
    }
    public static void writeObject(Object obj,String fileName,boolean append){
        try {
            FileOutputStream file=new FileOutputStream(fileName,append);
            ObjectOutputStream objectWriter=new ObjectOutputStream(file);
            objectWriter.writeObject(obj);
            file.close();
            objectWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void writeText(String fileName,String text){
        BufferedWriter writer = null;
        try {
            File file = new File(fileName);
            file.createNewFile();
            writer = new BufferedWriter(new java.io.FileWriter(file,false));
            writer.write(text);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (Exception e) {
            }
        }
    }
}
