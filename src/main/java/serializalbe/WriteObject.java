package serializalbe;

import demooject.Track;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class WriteObject {
    public static void main(String[] args) {
        Track track = new Track();
        track.setName("我是序列化");

        ObjectOutputStream os = null;
        try {
            os = new ObjectOutputStream(new FileOutputStream("/work/out/wxp.txt"));
            os.writeObject(track);
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                assert os != null;
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
