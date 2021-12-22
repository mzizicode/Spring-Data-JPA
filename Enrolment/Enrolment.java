package thewizard.demo.student;

import javax.persistence.*;

//EmbeddedId and mapsId
// step 32 create a class and embed/attach enrolment id then
@Entity(name="Enrolment")
@Table(name="enrolment")
public class Enrolment {
    @EmbeddedId//attach enrolment id
    private EnrolmentId id;
    @ManyToOne
    @MapsId("studentId")//maps id shows that the student is part of the id
    @JoinColumn(name = "student_id", foreignKey = @ForeignKey(
            name = "enrolment_student_Fk")
    )
    private Student student;
    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(name = "course_id",
            foreignKey = @ForeignKey(
                    name = "enrolment_course_Fk")
    )
    private Course course;

    public Enrolment(EnrolmentId id, Student student, Course course) {
        this.id = id;
        this.student = student;
        this.course = course;

    }

    public Enrolment() {

    }

    public EnrolmentId getId() {
        return id;
    }

    public void setId(EnrolmentId id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
