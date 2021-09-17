package in.hsp.babu.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.hsp.babu.entity.Patient;
import in.hsp.babu.repository.PatientRepository;
import in.hsp.babu.service.PatientService;

@Service
public class PatientServiceImpl implements PatientService {
	
	@Autowired
	private PatientRepository repo;
	
	@Override
	public Integer saveData(Patient patient) {
	  
		            patient= repo.save(patient);
		         return patient.getId();
	}
	
	@Override
	public List<Patient> getAllPatients() {
		 
		           List<Patient> list=repo.findAll();
		return list;
	}
	 @Override
	public void deleteData(Integer id) {
	
		    repo.deleteById(id);
		
	}
	
	 @Override
	public Patient editData(Integer id) {
		 
		 Optional<Patient> optional=repo.findById(id);
		 if(optional.isPresent()) {
			 Patient p=optional.get();
			 return p;
		 }
		// TODO Auto-generated method stub
		return null;
	}
	 
	 @Override
	public void updateData(Patient patient) {
		
		 repo.save(patient);
		
	}
	

}

