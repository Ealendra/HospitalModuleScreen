package in.hsp.babu.service;

import java.util.List;

import in.hsp.babu.entity.Doctor;

public interface DectorService {
	
	Integer saveData(Doctor doctor);
	
	         List<Doctor> getAllData();
	         
	         void deleteData( Integer id);
	         
	         Doctor editData(Integer id);
	         
	          void updateData(Doctor doctor);
	         
	         

}
