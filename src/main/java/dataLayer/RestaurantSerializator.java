package dataLayer;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class RestaurantSerializator {
    public static final String fileName="menu_items.dat";
    public static Set<? extends Object> deserialize() {
        Set<Object> set=new HashSet<Object>();
        FileInputStream file= null;
        ObjectInputStream objectReader=null;
        try {
            file = new FileInputStream(fileName);
            objectReader = new ObjectInputStream(file);
            set=(Set<Object>)objectReader.readObject();
        } catch (FileNotFoundException e) {
            try {
                FileOutputStream tmp=new FileOutputStream(fileName);
                tmp.close();
            }  catch (IOException ex) {
                ex.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            if(file!=null && objectReader!=null) {
                try {
                    file.close();
                    objectReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return set;
    }
    public static void serialize(Set<? extends Object> set){
        FileWriter.writeSet(set);
    }
}
