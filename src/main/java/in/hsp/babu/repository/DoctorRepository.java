package in.hsp.babu.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.hsp.babu.entity.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor,Integer>{

}
