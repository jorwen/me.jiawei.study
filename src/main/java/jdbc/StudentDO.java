package jdbc;


import org.springframework.jdbc.core.RowMapper;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentDO implements RowMapper<StudentDO>,Serializable{
    private Integer id;

    private String sno;

    private String sname;

    private Integer age;

    private String sex;

    private String bplace;

    @Override
    public String toString() {
        return "StudentDO{" +
                "id=" + id +
                ", sno='" + sno + '\'' +
                ", sname='" + sname + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", bplace='" + bplace + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBplace() {
        return bplace;
    }

    public void setBplace(String bplace) {
        this.bplace = bplace;
    }

    @Override
    public StudentDO mapRow(ResultSet rs, int i) throws SQLException {
        StudentDO studentDO = new StudentDO();
        studentDO.setId(rs.getInt("id"));
        studentDO.setAge(rs.getInt("age"));
        studentDO.setSex(rs.getString("sex"));
        studentDO.setSname(rs.getString("sname"));
        studentDO.setSno(rs.getString("sno"));
        studentDO.setBplace(rs.getString("bplace"));
        return studentDO;
    }
}
