package thewizard.demo.student;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name= "course")
@Table(name="course")
//Step 24 create an Entity called course Entity (Many to many)
//step 24 map the entity on the database using tables and columns
public class Course {
    @Id
    @SequenceGenerator(
            name = "course_sequence",
            sequenceName = "course_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "course_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long Id;
    @Column(
            name = "name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String name;
    @Column(
            name = "department",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String department;

    // public List<Student> getStudents () {//step 27
    //        return students;
    //  }
    //        public void setStudents (List<Student> students) {//step 27
    //        this.students = students;
    @ManyToMany (//step 27 connecting/mapping to enrollment---->@ManyToMany mappedBy
    mappedBy = "courses")
    private List<Student>students=new ArrayList<>();  //step 27: connecting course to enrolment
    @OneToMany
            (
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            mappedBy = "course"//step 33 this course is the one found in Enrolment
    )
    private List<Enrolment> enrolments = new ArrayList<>();//step 33 create a list of enrolment to add enrolment to course

    public Course(String name, String department) {
        this.name = name;
        this.department = department;
    }

    public Course() {
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    //step 34 create add and remove enrollment to the course class
    public void addEnrolment(Enrolment enrolment){
        if (!enrolments.contains(enrolment)) {
            enrolments.add(enrolment);
        }
    }
    public void removeEnrolment(Enrolment enrolment){
        if (enrolments.contains(enrolment)){
            enrolments.remove(enrolment);
        }
    }

    @Override
    public String toString() {
        return "Course{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                '}';
    }
}


