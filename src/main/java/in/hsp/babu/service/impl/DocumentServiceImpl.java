package in.hsp.babu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.hsp.babu.entity.Document;
import in.hsp.babu.repository.DocumentRepository;
import in.hsp.babu.service.DocumentService;


@Service
public class DocumentServiceImpl implements DocumentService {
	
@Autowired
private DocumentRepository repo;


     public void saveDocument(Document doc) {
	   repo.save(doc);
		
	}

	public Document getDocumentById(Integer id) {
		
		return repo.findById(id).orElseThrow(()->new RuntimeException("Document Not Exit"));
	}

     public void deleteDocumentById(Integer id) {
	
	if(repo.existsById(id))
		repo.deleteById(id);
	else
		throw new RuntimeException("Document Not Exit");
	
	}

	public List<Object[]> getDocumentIdAndName() {
		
		return repo.getDocumentIdAndName();
	}


}
