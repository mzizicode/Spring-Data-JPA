//step 1 student class
package thewizard.demo.student;
//step 2.create tables in our database (map classes to tables using @Entity)
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name="student") //step 2
@Table(//step 2 Create table with unique constraints and column names (this is used to edit names)
        name="student",
        uniqueConstraints = { @UniqueConstraint(name = "student_email_unique",//edited name
        columnNames ="email" )
}
)
public class Student {
    @Id//step 2
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence")
//step 3. @column annotation for every single class property that we have
    @Column(
            name = "id",
            updatable = false//step 3.means no one can update the id
    )
    private Long id;

    @Column(
            name = "first_name",
            nullable = false //step 3.means the column is not empty (column firstname need to be filled)
    )//step 3. we need to insert text
    private String firstName;
    @Column(//step3
            name = "last_name",
            nullable = false)
    private String lastName;
    @Column(//step3
            name = "email",
            nullable = false)
    private String email;
    @Column(//step 3
            name = "age",
            nullable = false
    )
    private Integer age;
    @OneToOne(
            mappedBy = "student",//step 13 bidirectional relationship connecting to student id card
            cascade = {CascadeType.PERSIST, CascadeType.ALL},
            orphanRemoval = true) //step 14 is used if u want to delete a student or a student id card
    private StudentIdCard studentIdCard;//step 13 connecting to the student class
    @OneToMany(//step 18: join OneToMany -->bi directional
            mappedBy = "student",
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, //step 18 save and remove
            fetch = FetchType.LAZY)//step 22 if you change this to eager we join to the books and fetch them out
    // if you change it to lazy we not joining to books
    // one to many relationship and many to many we use fetch type eager but one to one we use lazy
    // always start with lazy and chnage if the application needs more data
    private List<Book> books = new ArrayList<>();//step 18: properties used to join
    // (student to book) returns an Array
    //step 25-28 is another way of doing step 33
    // step 25 (@ManyToMany and @JoinColumn) --->this is to link student with course for now we mute it
//     @ManyToMany (cascade = {CascadeType. PERSIST, CascadeType.REMOVE},
//    fetch = FetchType. LAZY)
//     @JoinTable(//STEP 26:this anotaion will create a new table called enrollment
//      name="enrolment",
//      joinColumns = @JoinColumn(
//      name = "Student_id",
//              foreignKey = @ForeignKey(name = "enrolment_student_id_fk")
//      ),
//        inverseJoinColumns = @JoinColumn(
//         name = "course_id",
//             foreignKey = @ForeignKey(name = "enrolment_course_id_fk")
//        )
//     )
    private List<Course>courses=new ArrayList<>();//Step 25:Create a list of courses the student will enroll to
//     (@ManyToMany and @JoinColumn)
//    step 33 in oder to join student entity class with enrolment entity we need to anotate with @OneToMany

     @OneToMany(
             cascade = {CascadeType.PERSIST,CascadeType.REMOVE},
             mappedBy = "student" //step 33 :this name student is the one found in enrolment
     )
     private List<Enrolment>enrolments=new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "student_courses",
            joinColumns = @JoinColumn(name = "student_id",
                    referencedColumnName = "courses_id"))
    //private List<Course> courses = new ArrayList<>();

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public Student( String firstName, String lastName, String email, Integer age) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;

    }



    public Student() {//no args contactor
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public StudentIdCard getStudentIdCard() {
        return studentIdCard;
    }

    public void setStudentIdCard(StudentIdCard studentIdCard) {//step 13
        this.studentIdCard = studentIdCard;
    }
    //step 19 add method to addbook
    public void addBook(Book book){
        if(!this.books.contains(book)){//step 19 logics to add book
            this.books.add(book);
            book.setStudent(this);
        }
    }
    //step 20 add a method to addbook
    public  void removeBook(Book book){
        if (this.books.contains(book)){//step 20 logic to remove book
            this.books.remove(book);
            book.setStudent(null);
        }
    }
    public List<Book>getBooks(){//step 20 getters and setters for books(array list)
        return books;
    }
    public void setBooks(List<Book>books){//step 20 getters and setters for books(array list)
        this.books=books;
    }
    //step 33 add a method to enrol and unEnrol students for courses
    public void enrolToCourse(Course course){
        courses.add(course);
        course.getStudents().add(this);
    }
    public void unEnrolCourse(Course course){
        courses.remove(course);
        course.getStudents().remove(this);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +

                '}';
    }
}


