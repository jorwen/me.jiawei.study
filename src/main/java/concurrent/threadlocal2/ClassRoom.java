package concurrent.threadlocal2;

import java.io.*;

public class ClassRoom implements Serializable
{

    private String roomId = "";
    private String roomName = "";

    public String getRoomId()
    {
        return roomId;
    }

    public void setRoomId(String roomId)
    {
        this.roomId = roomId;
    }

    public String getRoomName()
    {
        return roomName;
    }

    public void setRoomName(String roomName)
    {
        this.roomName = roomName;
    }
}
