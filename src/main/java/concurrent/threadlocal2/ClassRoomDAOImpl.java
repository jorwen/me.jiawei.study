package concurrent.threadlocal2;

import java.sql.*;

public class ClassRoomDAOImpl implements ClassRoomDAO
{

    public void addStudentClassRoom(String roomId, String sNo) throws Exception
    {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try
        {
            conn = ConnectionManager.getConnection();
            pstmt = conn.prepareStatement("xxxxxxx");
            pstmt.setString(1, roomId);
            pstmt.setString(2, sNo);
            pstmt.executeUpdate();
        }
        catch (Exception e)
        {
            throw new Exception("addStudentClassRoom:" + e.getMessage(), e);
        }
        finally
        {
            try
            {
                if (pstmt != null)
                {
                    pstmt.close();
                    pstmt = null;
                }
            }
            catch (Exception e)
            {
            }
        }
    }
}
