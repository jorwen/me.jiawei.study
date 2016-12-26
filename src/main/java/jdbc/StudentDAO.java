package jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentDAO{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Integer countStudent(){
        Integer count = jdbcTemplate.queryForObject("select count(*) from students", Integer.class);
        return count;
    }

    public List<StudentDO> findByName(String name){
        List<StudentDO> studentDOList = jdbcTemplate.query("select * from students where sname like ?"
                ,new StudentDO(),new Object[]{"%"+name+"%"});
        return studentDOList;
    }

    public void updateAge(Integer id, Integer age ){
        jdbcTemplate.update("update students set age = ? where id = ?",new Object[]{age,id});
    }
}
