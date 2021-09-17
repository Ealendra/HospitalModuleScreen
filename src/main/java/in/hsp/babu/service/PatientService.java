package in.hsp.babu.service;

import java.util.List;

import in.hsp.babu.entity.Patient;

public interface PatientService {
	
	Integer saveData(Patient patient);
	            List<Patient> getAllPatients();
                    void deleteData( Integer id);
                       Patient editData(Integer id);
                       
                       void updateData(Patient patient);
}
