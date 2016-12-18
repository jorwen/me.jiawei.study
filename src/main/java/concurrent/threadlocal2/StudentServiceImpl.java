package concurrent.threadlocal2;

public class StudentServiceImpl implements StudentService
{

    public void addStudent(Student std) throws Exception
    {
        StudentDAO studentDAO = new StudentDAOImpl();
        ClassRoomDAO classRoomDAO = new ClassRoomDAOImpl();
        try
        {
            ConnectionManager.BeginTrans(true);
            studentDAO.addStudent(std);
            classRoomDAO.addStudentClassRoom(std.getClassRoomId(), std.getsNo());
            ConnectionManager.commit();
        }
        catch (Exception e)
        {
            try
            {
                ConnectionManager.rollback();
            }
            catch (Exception de)
            {
            }
            throw new Exception(e);
        }
        finally
        {
            try
            {
                ConnectionManager.close();
            }
            catch (Exception e)
            {
            }
        }
    }
}