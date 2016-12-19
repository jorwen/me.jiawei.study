package jdbc;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContextManager;

import javax.annotation.Resource;

import static org.mockito.Mockito.when;

//启动Mock容器
@RunWith(MockitoJUnitRunner.class)
//启动Spring容器
@ContextConfiguration(locations = { "classpath:spring-jdbc-test.xml"})
public class TestStudentService {
    @InjectMocks
    @Resource
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
}
