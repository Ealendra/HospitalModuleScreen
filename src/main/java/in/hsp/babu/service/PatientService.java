package in.hsp.babu.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import in.hsp.babu.entity.Patient;

public interface PatientService {
	
	             /*CURD operation methods*/
	             public Integer saveData(Patient patient);
	              public List<Patient> getAllPatients();
                 public  void deleteData( Integer id);
                 public  Patient editData(Integer id);
                  public void updateData(Patient patient);
                  //For PageNation Concept.
                  Page<Patient> getAllPatientRecords(Pageable pageable);
}
