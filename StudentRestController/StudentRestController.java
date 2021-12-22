package thewizard.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/student/")
public class StudentRestController {
    private StudentService studentService;

    @Autowired
    public StudentRestController(StudentService studentService) {
        this.studentService = studentService;
    }


    @GetMapping
    public List<Student>getStudents() {
        return studentService.getStudents();
    }


    @PostMapping
    public void newStudent(@RequestBody Student student) {
        studentService.addNewStudent(student);
    }



    }

