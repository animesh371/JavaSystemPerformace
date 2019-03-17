package in.dao;

import in.model.Student;
import in.request.UpdateHostel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class StudentsDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional(readOnly=true)
    public List<Student> findAll() {
        List<Student> result = jdbcTemplate.query("select * from users",
                (resultSet, num) -> new Student(resultSet.getString("id"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("hostel")));
        return result;
    }

    public void insertNewStudent(Student student) {
         jdbcTemplate.update("Insert into students(id, firstName, lastName, hostel) values (?,?,?,?)",
                student.getId(), student.getFirstName(), student.getLastName(), student.getHostel());
    }

    public void updateHostel(UpdateHostel updateHostel) {
        jdbcTemplate.update("Update students SET hostel = ? where id = ?",
                updateHostel.getHostel(), updateHostel.getId());
    }

    public Student getStudent(String id) {
        return jdbcTemplate.queryForObject("select * from students where id = ? ", new Object[]{id},
        (resultSet, rowNum) -> new Student(resultSet.getString("id"),
                resultSet.getString("firstName"),
                resultSet.getString("lastName"),
                resultSet.getString("hostel")));
    }


}
