package in.hsp.babu.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.hsp.babu.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient,Integer>{

}
