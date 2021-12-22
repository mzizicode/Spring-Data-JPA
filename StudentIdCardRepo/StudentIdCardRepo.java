package thewizard.demo.student;
//STEP 10 part 1:create a student crud repository to save students id card
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentIdCardRepo extends JpaRepository<StudentIdCard, Long> {

}
//next step:go to the application and create student id card and save it
