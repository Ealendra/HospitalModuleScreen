package in.hsp.babu.service;

import java.util.List;

import in.hsp.babu.entity.Specilazation;

public interface SpeclizationService {
	
	
	          public Integer saveSpecialization(Specilazation specilazation);
                 
	            public List<Specilazation> getAllData();
	                public  void deleteData(Integer id );
	                 
	                public  Specilazation editData( Integer id);
	               public  void  updateData(Specilazation specilazation);
	          public boolean isCodeExit(Integer code);
	          
	          public boolean isNameExit(String name);
}
