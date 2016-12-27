package serializalbe;

import demooject.Track;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class ReadObject {
    public static void main(String[] args) throws Exception {
        ObjectInputStream is = new ObjectInputStream(new FileInputStream("/work/out/wxp.txt"));
        Track track = (Track) is.readObject();
        System.out.println(track.getName());
        is.close();
    }
}