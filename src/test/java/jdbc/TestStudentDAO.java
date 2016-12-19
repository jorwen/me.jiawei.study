package jdbc;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContextManager;

import javax.annotation.Resource;

//启动Spring容器
@ContextConfiguration(locations = { "classpath:spring-jdbc-test.xml"})
public class TestStudentDAO {
    @Resource
    private StudentDAO studentDAO;


    @Before
    public void setUpContext() throws Exception {
        TestContextManager testContextManager;
        testContextManager = new TestContextManager(getClass());
        testContextManager.prepareTestInstance(this);
    }

    @Test
    public void countStudentTest(){
        Integer count = studentDAO.countStudent();

        Assert.assertEquals(count, new Integer(7));
    }
}
