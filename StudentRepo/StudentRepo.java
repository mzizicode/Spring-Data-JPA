package thewizard.demo.student;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
//Repository is simply a data access layer
// In this section will add student Repository that will helps as to add student, delete student, search students e.t.c
// student Repository must extend either JpaRepository/pagingAndSortingRepository/CrudRepository
//step 4 create student Repository and save students in our data base
@Repository
public interface StudentRepo extends JpaRepository<Student,Long> {
// //step 6 find students using JPQL language(Query)
@Transactional
@Modifying
@Query("delete from student s where s.id=?1")
int deleteStudentById(Long id);
   @Query("select s from student s where s.email=?1")
   Optional<Student>findStudentByEmail(String email);
    @Query("select s from student s where s.age=?1")
    Optional<Student>findStudentByAge (Integer age);
    @Query("select s from student s where s.id=?1")
    Optional<Student>findStudentById(Long Id);
    @Query("SELECT s from student s where s.email=?1 and s.age=?2")
    List<Student>findStudentByEmailEqualsAndAgeEquals (String email, Integer age);
    @Query("SELECT s from student s where s.age>=?1")
    List<Student>findStudentByAgeGreaterThanEqual(Integer age);

    @Query("SELECT s from student s where s. firstName like ?1")
    Optional<Student>findByFirstNameContaining ( String firstName);

}

//step 5,7 and 8 is on the papers

