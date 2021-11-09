package in.hsp.babu.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import in.hsp.babu.entity.Doctor;

public interface DoctorService {
	
	Integer saveData(Doctor doctor);
	
	    public   List<Doctor> getAllData();
	         
	    public  void deleteData( Integer id);
	         
	    public Doctor editData(Integer id);
	         
	    public   void updateData(Doctor doctor);
	    //Make Has a Relation
	    public Map<Integer,String> getDoctorNameByIdName();
	         
//2.over ride the method for show doctors list.
	    public List<Doctor> getAllById(Integer id);
	    
	    //For Pagination concept.
	    public Page<Doctor> getAllRecords(Pageable pageable);

}
