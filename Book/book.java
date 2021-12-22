package thewizard.demo.student;

import javax.persistence.*;
import java.time.LocalDateTime;

//Step 16:book entity (one to many relationship)
//step 16 mapping this entity on the database using tables and column
@Entity(name="Book")
@Table(name = "book")
public class Book {

    @Id
    @SequenceGenerator(
            name="student_book_id_sequence",
            sequenceName = "student_book_id_sequence",
            allocationSize = 1)

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator= "student_book_id_sequence")

    @Column(
            name="id",
            updatable = false)
    private Long id;
    @Column(
            name="created_at",
            nullable = false,
            columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private LocalDateTime createdAt;
    @Column(
            name="book_name",
            nullable = false)
    private String bookName;
    @ManyToOne//step 17 is used to join columns together (many to one) from book to student---> uni directional
            @JoinColumn(
                    name = "student_id",
                    nullable = false,
                    referencedColumnName = "id",//step 17 id on the student side
                    foreignKey = @ForeignKey(//step 17 change the Fk name to student_book_fk
                            name = "student_book_fk")
            )
    private Student student;//step 17: properties used to join from books to students

    public Book() {
    }

    public Book(LocalDateTime createdAt, String bookName) {
        this.createdAt = createdAt;
        this.bookName = bookName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", bookName='" + bookName + '\'' +
                ", student=" + student +
                '}';
    }
}






