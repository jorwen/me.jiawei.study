package jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    private StudentDAO studentDAO;

    public Integer countStudent(){
        return studentDAO.countStudent();
    }
}
