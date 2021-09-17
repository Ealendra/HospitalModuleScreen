package in.hsp.babu.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.hsp.babu.entity.Doctor;
import in.hsp.babu.repository.DoctorRepository;
import in.hsp.babu.service.DectorService;

@Service
public class DoctorServiceImpl implements DectorService{
	
	@Autowired
	private DoctorRepository repo;
	
	@Override
	public Integer saveData(Doctor doctor) {
		doctor=repo.save(doctor);
		return doctor.getId();
	}
	@Override
	public List<Doctor> getAllData() {
		List<Doctor> list=repo.findAll();
		return list;
	}
	
	@Override
	public void deleteData(Integer id) {
		
		 repo.deleteById(id);
	
	}
	@Override
	public Doctor editData(Integer id) {
		  
		Optional<Doctor> data= repo.findById(id);
		if(data.isPresent())
		{
			Doctor dr=data.get();
			return dr;
		}
		 
		
		return null;
	}
	
	@Override
	public void updateData(Doctor doctor) {
		
		      repo.save(doctor);
	}
	
	

}
