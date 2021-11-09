package in.hsp.babu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import in.hsp.babu.entity.Appointment;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Integer> {
	
	@Query("SELECT apmt.date,apmt.noOfSlots,apmt.consultFee FROM Appointment apmt INNER JOIN apmt.doctor as doc WHERE doc.id=:id")
	public List<Object[]>  getApmentByDocId(Integer id);

}
