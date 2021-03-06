package jdbc;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContextManager;

//启动Spring容器
@ContextConfiguration(locations = { "classpath:spring-jdbc.xml"})
public class TestStudentDAO {
    @Autowired
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
