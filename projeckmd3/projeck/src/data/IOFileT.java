package data;

import java.io.*;

public class IOFileT<T> {
    public static final String ADMIN_FILE = "admin.txt";
    public void writeToFile(T t, String fileName){
        File file =null;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            file = new File(fileName);
            if (!file.exists()){
                file.createNewFile();
            }
            fos= new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);
            // ghi file
            oos.writeObject(t);
            oos.flush();
        }catch (IOException e){
            throw  new RuntimeException(e);
        }finally {
            if(fos!=null){
                try {
                    fos.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if(oos!=null){
                try {
                    oos.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    public T readFromFile(String fileName){
        File file = null;
        FileInputStream fis =null;
        ObjectInputStream ois = null;
        T t = null;
        try{
            file = new File(fileName);
            fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);
            t = (T) ois.readObject();

        }catch (ClassNotFoundException c){
            c.printStackTrace();
        }catch (IOException e){
//            e.printStackTrace();
        }finally {
            if(fis!=null){
                try {
                    fis.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if(ois!=null){
                try {
                    ois.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return t;
    }
}
