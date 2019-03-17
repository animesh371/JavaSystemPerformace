package in.controller;

import com.codahale.metrics.Timer;
import in.dao.StudentsDAO;
import in.model.Student;
import in.request.UpdateHostel;
import in.techopedia.Application;
import in.techopedia.Performance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.codahale.metrics.MetricRegistry.name;

@RestController
public class StudentController {
    @Autowired
    StudentsDAO studentsDAO;

    @PostMapping("/addStudent")
    public void insertStudent(@RequestBody Student student) {
        final Timer streamCall = Application.metrics.timer(name(Performance.class, "insertStudent"));
        final Timer.Context context = streamCall.time();
        studentsDAO.insertNewStudent(student);
        context.stop();
    }

    @RequestMapping("/getStudent")
    public Student getStudent(@RequestParam String id) {
        final Timer streamCall = Application.metrics.timer(name(Performance.class, "getStudent"));
        final Timer.Context context = streamCall.time();
        Student student = studentsDAO.getStudent(id);
        context.stop();
        return student;
    }

    @PostMapping("/updateHostel")
    public void updateStudent(@RequestBody UpdateHostel updateHostel) {
        final Timer streamCall = Application.metrics.timer(name(Performance.class, "updateHostel"));
        final Timer.Context context = streamCall.time();
        studentsDAO.updateHostel(updateHostel);
        context.stop();
    }


}
