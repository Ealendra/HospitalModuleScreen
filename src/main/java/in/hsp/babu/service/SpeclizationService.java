package in.hsp.babu.service;


import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import in.hsp.babu.entity.Specilazation;

public interface SpeclizationService {
	
	
	                public Integer saveSpecialization(Specilazation specilazation);
	                public List<Specilazation> getAllData();
	                public  void deleteData(Integer id );
	                public  Specilazation editData( Integer id);
	                public  void  updateData(Specilazation specilazation);
	                public boolean isCodeExit(Integer code);
	          
	          public boolean isNameExit(String name);
	          
	          /**Module Integration Method */
	          public Map<Integer,String> getIdAndName();
	          
	          /*Pegination Concept*/
     public Page<Specilazation> getAllRecords(Pageable pageble);
}
