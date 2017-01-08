package serializalbe;

import demooject.Track;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class WriteObject {
    public static void main(String[] args) throws Exception {
        Track track = new Track();
        track.setName("我是序列化");

        //Java文件流
        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("/work/out/wxp.txt"));
        os.writeObject(track);
        os.close();
    }
}
