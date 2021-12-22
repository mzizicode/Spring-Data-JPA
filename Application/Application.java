package thewizard.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.List;
@Configuration
public class Application {
    @Bean
    CommandLineRunner commandLineRunners(StudentRepo studentRepo,StudentIdCardRepo studentIdCardRepo) {
        return args -> {
            //step 6 create a student and save ,find student by email,age,id and name
            //step 6 find students using JPQL language(Query)
            Student maria = new Student(
                    "maria",
                    "sankara",
                    "maria@gmail.com",
                    29);
            //step 21:create new books that the students borrowed
            maria.addBook(new Book(LocalDateTime.now().minusDays(4),"Clean Code"));

            Student mike = new Student(
                    "mike",
                    "kimani",
                    "kimani@gmail.com",
                    24);
            //step 21:create new books that the students borrowed
            mike.addBook(new Book(LocalDateTime.now(),"Java code"));

            Student mike2 = new Student(
                    "mike2",
                    "kim",
                    "kiman6@gmail.com",
                    30);
            //step 21:create new books that the students borrowed
            mike2.addBook(new Book(LocalDateTime.now().minusYears(1),"Basic Code"));

            //step 10 part 2:creating  and saving student id card
            StudentIdCard studentIdCard = new StudentIdCard("123457024",maria);
            StudentIdCard studentIdCard2 = new StudentIdCard("12345700",mike);
            StudentIdCard studentIdCard3 = new StudentIdCard("12345600",mike2);

            //35 Create new courses for students
            maria.enrolToCourse(new Course("Computer Science","'IT"));
            mike.enrolToCourse(new Course("Amigose DataBase","'IT"));
            mike2.enrolToCourse(new Course("Computer Application","'ICT"));

              //step 23 save students instead of id card
             // student.setStudentIdCard(studentIdCard);
            // student.enrolToCourse // 29 test the enrolment
            //(new course("computer science","IT"));//Step 28 test the enrolment
            //student.enrolToCourse(new course("Mzizi course","Music"));//28 test the enrolement



            studentIdCardRepo.saveAll(List.of(studentIdCard,studentIdCard2,studentIdCard3));
            studentRepo.findStudentByEmail(" kimani@gmail.com").ifPresentOrElse(System.out::println, () ->
                    System.out.println(" student with email kimani@gmail.com does not exist"));
            studentRepo.findStudentByAge(29).ifPresentOrElse(System.out::println, () ->
                    System.out.println("Student with age 29 does not exist"));
            studentRepo.findStudentById(2L).ifPresentOrElse(System.out::println, () ->
                    System.out.println("absent"));
            studentRepo.findStudentByEmailEqualsAndAgeEquals("kimani@gmail. com", 24).forEach(System.out::println);
            studentRepo.findStudentByAgeGreaterThanEqual(29)
                    .forEach(System.out::println);
            studentRepo.findByFirstNameContaining("mike").ifPresentOrElse(System.out::println, () ->
                    System.out.println("not found"));
           // step 22 understanding eager fetch type for one to many
            studentRepo.findById(1L).ifPresent(s->{
              System.out.println("Fetch book lazy...");
                List<Book>books=maria.getBooks();
                books.forEach(book -> {
                    System.out.println(s.getFirstName()+"borrowed"+book.getBookName());
                });
            });
        };
    }
}


