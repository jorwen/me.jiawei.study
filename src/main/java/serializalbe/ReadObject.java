package serializalbe;

import demooject.Track;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ReadObject {
    public static void main(String[] args) {
        ObjectInputStream is = null;
        try {
            is = new ObjectInputStream(new FileInputStream("/work/out/wxp.txt"));
            Track track = (Track) is.readObject();
            System.out.println(track.getName());
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                assert is != null;
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}