package in.hsp.babu.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.hsp.babu.entity.Document;

public interface DocumentRepository extends JpaRepository<Document, Integer> {
	

	@Query("SELECT docId,docName FROM Document")
	List<Object[]> getDocumentIdAndName();

}
