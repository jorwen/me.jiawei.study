package jdbc;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class StudentCache implements InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
