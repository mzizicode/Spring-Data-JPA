package thewizard.demo.student;
//step 8 create a student id card entity with entity and tables for one to one relationship
import javax.persistence.*;
@Entity(name="studentIdCard")
@Table(name = "studentIdCard",
        uniqueConstraints = {@UniqueConstraint(
        name = "student_card_sequence",
                columnNames = "card_number")
}
)

public class StudentIdCard {
    @Id
    @SequenceGenerator(
            name = "student_id_card_sequence",

            sequenceName ="student_id_card_sequence" ,
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_id_card_sequence")
    @Column(
            name = "id",
            nullable = false)
    private Long id;
    @Column(
            name = "card_number",
            nullable =false,
            updatable = false)
    private String cardNumber; //step 9: This code is used to join student id card and student class (uni directional)
    @OneToOne(cascade = CascadeType.ALL, //step 11. the default for OneToOne is eager.
            fetch = FetchType.EAGER)//STEP 12
    @JoinColumn(//step 9
            name = "student_id",//this name is the one in the student id card
            referencedColumnName = "id",//this id is the one in the student class hence it's a foreign key (FK)
            foreignKey = @ForeignKey(name = "student_id_Fk")//step 15 : this code is used to change the foreign key name
    )
    private Student student;// step 9 @OneToOne and @joinColumn (This is the property used)
    public StudentIdCard(String cardNumber, Student student) {
        this.cardNumber = cardNumber;
        this.student = student;
    }

    public StudentIdCard() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "StudentIdCard{" +
                "id=" + id +
                ", cardNumber='" + cardNumber + '\'' +
                ", student=" + student +
                '}';
    }
}



