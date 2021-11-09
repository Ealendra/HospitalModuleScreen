package in.hsp.babu.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="doc_tab")
public class Document {
	
	@Id
	@Column(name="doc_id_col")
	private Integer docId;

	@Column(name="doc_name_col")
	private String docName;

	@Lob
	@Column(name="doc_data_col")
	private byte[] docData;

}
