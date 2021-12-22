package thewizard.demo.student;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

// step 30: Create a new class called enrolment id in oder to join student id and course id
// use the embeddable annotation so we can be able to embed into another entity
// when using embeddable we need to implement Serializable
// studentId and courseId are composite key
@Embeddable
public class EnrolmentId implements Serializable {
    @Column(name = "student id")
    private Long studentId;
    @Column(name = "course id")
    private Long courseId;

    public EnrolmentId(Long studentId, Long courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
    }

    public EnrolmentId() {

    }


    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }



//step 31 in oder for this class to be a composite key we need to apply equals and hash code


    @Override
    public boolean equals(Object o) {
       if (this == o ) return true;
       if (o == null || getClass() != o.getClass()) return false;
        EnrolmentId that = (EnrolmentId) o;
        return Objects.equals(studentId, that.studentId) &&
                Objects.equals(courseId, that.courseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, courseId);
    }
}


