package in.hsp.babu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.hsp.babu.entity.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor,Integer>{
	//2.
	@Query("SELECT id,name FROM Doctor")
	public List<Object[]> getDocorNameByIdAndName();
	
	/**.show the all the doctor for appointment booking by join Query.
	 * 1.write one method at Doctor Repository.
	 * 2.over ride the method at Doctor Repository service.
	 * 3.over ride the method at doctor service impl.
	 * 4.over rider the all methods based on requierment 
	 * */
	@Query("SELECT doc FROM Doctor doc INNER JOIN doc.specilazation as spec WHERE spec.id=:id")
	public List<Doctor> getAllById(Integer id);

}
