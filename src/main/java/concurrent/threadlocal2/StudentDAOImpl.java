package concurrent.threadlocal2;

import java.sql.*;

public class StudentDAOImpl implements StudentDAO
{

    public void addStudent(Student std) throws Exception
    {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try
        {
            conn = ConnectionManager.getConnection();
            pstmt = conn.prepareStatement("xxxxx");
            pstmt.setString(1, std.getsNo());
            pstmt.setString(2, std.getsName());
            pstmt.setString(3, std.getsAge());
            pstmt.setString(4, std.getGender());

            pstmt.executeUpdate();
        }
        catch (Exception e)
        {
            throw new Exception("addStudent:" + e.getMessage(), e);
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

    public void delStudent(String sNo) throws Exception
    {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try
        {
            conn = ConnectionManager.getConnection();
            pstmt = conn.prepareStatement("yyyyy");
            pstmt.setString(1, sNo);
            pstmt.executeUpdate();
        }
        catch (Exception e)
        {
            throw new Exception("delStudent:" + e.getMessage(), e);
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
