package in.hsp.babu.service;

import java.util.List;

import in.hsp.babu.entity.Document;

public interface DocumentService {

	void saveDocument(Document doc);
	List<Object[]> getDocumentIdAndName();
	void deleteDocumentById(Integer id);
	Document getDocumentById(Integer id);	      
}
