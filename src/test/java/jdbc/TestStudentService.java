package jdbc;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContextManager;

import static org.mockito.Mockito.when;

//启动Mock容器
@RunWith(MockitoJUnitRunner.class)
//启动Spring容器
@ContextConfiguration(locations = { "classpath:spring-jdbc.xml"})
public class TestStudentService {
    @InjectMocks
    @Autowired
    private StudentService studentService;

    @Mock
    private StudentDAO studentDAO = new StudentDAO();

    @Before
    public void setUpContext() throws Exception {
        TestContextManager testContextManager;
        testContextManager = new TestContextManager(getClass());
        testContextManager.prepareTestInstance(this);

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void countStudentMockTest(){
        when(studentDAO.countStudent()).thenReturn(7);

        Integer count = studentService.countStudent();
        Assert.assertEquals(count, new Integer(7));
    }

    @Test
    public void testTransactional(){
        studentService.update2Student(1,2,20,true);
        try {
            studentService.update2Student(1, 2, 16, false);
        }catch (Exception e){
            System.out.println("error");
        }
    }

    @Test
    public void testNoTransactional(){
        studentService.update2Student(1,2,20,true);
        try {
            studentService.update2StudentNoTrans(1, 2, 16, false);
        }catch (Exception e){
            System.out.println("error");
        }
    }

    @Test
    public void testTransactional2(){
        studentService.update2Student(1,2,20,true);
        try {
            studentService.update2Student2(1, 2, 16, false);
        }catch (Exception e){
            System.out.println("error");
        }
    }
}
