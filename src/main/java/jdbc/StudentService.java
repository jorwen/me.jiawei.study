package jdbc;

import javax.annotation.Resource;

public class StudentService {
    @Resource
    private StudentDAO studentDAO;

    public Integer countStudent(){
        return studentDAO.countStudent();
    }
}
