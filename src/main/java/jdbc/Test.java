package jdbc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        ApplicationContext factory=new ClassPathXmlApplicationContext("classpath:spring-jdbc.xml");

        StudentDAO studentDAO = factory.getBean(StudentDAO.class);
        System.out.println(studentDAO.countStudent());

        List<StudentDO> list = studentDAO.findByName("张");
        for(StudentDO studentDO : list){
            System.out.println(studentDO);
        }
    }
}
