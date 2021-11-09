package in.hsp.babu.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import in.hsp.babu.entity.Doctor;
import in.hsp.babu.exception.DoctorNotFoundException;
import in.hsp.babu.repository.DoctorRepository;
import in.hsp.babu.service.DoctorService;
import in.hsp.babu.util.MyCollectionUtil;

@Service
public class DoctorServiceImpl implements DoctorService{
	
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
		  
	/*	Optional<Doctor> data= repo.findById(id);
		if(data.isPresent())
		{
			Doctor dr=data.get();
			return dr;
		}*/
		//TODO
  return repo.findById(id).orElseThrow(()-> new DoctorNotFoundException(id +", Doctor Not Found"));
	
	}
	
	@Override
	public void updateData(Doctor doctor) {
		
		      if(repo.existsById(doctor.getId()))
		      {
		    	  repo.save(doctor);
		      }
		      else
		     throw new DoctorNotFoundException(doctor.getId()+"Doctor not found");
	}
	
	//
	@Override
	public Map<Integer, String> getDoctorNameByIdName() {

     List<Object[]> list=repo.getDocorNameByIdAndName();
     
     Map<Integer,String> map = MyCollectionUtil.convertToMapIndex(list);
		return map;
	}
	//3.over ride the method for showing.
	@Override
	public List<Doctor> getAllById(Integer id) {
		
		return  repo.getAllById(id);
	}
	//For Pegination 
	
	@Override
	public Page<Doctor> getAllRecords(Pageable pageable) {
		
		return repo.findAll(pageable);
	}
	
	

}
