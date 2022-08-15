package config;

import java.io.*;
import java.util.Scanner;

public class Config<T> {
    public T read(String path) {
        T data = null;
        try (
                FileInputStream fis = new FileInputStream(path);
                ObjectInputStream ois = new ObjectInputStream(fis);
        ) {
            data = (T) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public void write(T data, String path) {
        try (
                FileOutputStream fos = new FileOutputStream(path);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
        ) {
            oos.writeObject(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
