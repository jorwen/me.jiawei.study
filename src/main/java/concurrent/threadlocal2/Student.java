package concurrent.threadlocal2;

import java.io.*;

public class Student implements Serializable
{

    public String getsNo()
    {
        return sNo;
    }

    public void setsNo(String sNo)
    {
        this.sNo = sNo;
    }

    public String getsName()
    {
        return sName;
    }

    public void setsName(String sName)
    {
        this.sName = sName;
    }

    public String getsAge()
    {
        return sAge;
    }

    public void setsAge(String sAge)
    {
        this.sAge = sAge;
    }

    public String getGender()
    {
        return gender;
    }

    public void setGender(String gender)
    {
        this.gender = gender;
    }

    private String sNo = "";
    private String sName = "";
    private String sAge = "";
    private String gender = "";
    private String sbirth = "";
    private String classRoomId = "";
    private String classRoomName = "";

    public String getClassRoomId()
    {
        return classRoomId;
    }

    public void setClassRoomId(String classRoomId)
    {
        this.classRoomId = classRoomId;
    }

    public String getClassRoomName()
    {
        return classRoomName;
    }

    public void setClassRoomName(String classRoomName)
    {
        this.classRoomName = classRoomName;
    }

    public String getSbirth()
    {
        return sbirth;
    }

    public void setSbirth(String sbirth)
    {
        this.sbirth = sbirth;
    }

}