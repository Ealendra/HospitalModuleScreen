package in.hsp.babu.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.hsp.babu.entity.Specilazation;
import in.hsp.babu.repository.SpecilazationRepository;
import in.hsp.babu.service.SpeclizationService;

@Service
public class SpeclizationServiceImpl implements SpeclizationService {
	@Autowired
	private SpecilazationRepository repo;
	
@Override
public Integer saveSpecialization(Specilazation specilazation) {
	
	specilazation =repo.save(specilazation);
	
	return specilazation.getId();
}

       @Override
    public List<Specilazation> getAllData() {
    	
    	   List<Specilazation> list= repo.findAll();
    	   
    	return list;   
    }
       @Override
    public void deleteData(Integer id) {
    	
    	   repo.deleteById(id);
    	
    }	   
    	
    	
       @Override
    public Specilazation editData(Integer id) {
    
    	   Optional<Specilazation> list=repo.findById(id);
    	   if(list.isPresent())
    	   {
    		   Specilazation spl=list.get();
    		   
    	         return spl;
    	   }
    	   // TODO Auto-generated method stub
    	   return null;
    }
       @Override
    public void updateData(Specilazation specilazation) {
    	
    	        repo.save(specilazation);
    	        
    	        
    	
    }
       
     @Override
    public boolean isCodeExit(Integer code) {
    	 
    	     /*  Integer count=repo.getCodeCount(code);
    	       boolean exit= count>0 ? true:false;
    	          return exit;*/
    	 return repo.getCodeCount(code)>0;
    }  
     
     @Override
    public boolean isNameExit(String name) {
    	
    	     Integer count=repo.getNameCount(name);
    	        boolean exit= count>0 ? true : false;
    	        return exit;
    	        
    	       /* return repo.getNameCount(name)>0;*/
    }
       
    }
       
       
       
       


