package in.hsp.babu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import in.hsp.babu.entity.Specilazation;

@Repository
public interface SpecilazationRepository extends JpaRepository<Specilazation,Integer> {
	
	@Query("SELECT COUNT(code) FROM Specilazation WHERE code=:code")
	  public  Integer getCodeCount(Integer code);
	
	@Query("SELECT COUNT(name) FROM Specilazation WHERE name=:name")
	public Integer getNameCount(String name);
	
	/**Module Integration Method */
	@Query("SELECT id,name FROM Specilazation")
	public List<Object[]> getIdName();

}
