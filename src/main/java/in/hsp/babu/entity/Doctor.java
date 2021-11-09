package in.hsp.babu.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="doctor_tab")
public class Doctor {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="doctor_id")
	private Integer id;
	@Column(name="doctor_name")
	private String name;
	@Column(name="doctor_email")
	private String email;
	@Column(name="doctor_special")
	private String specialization;
	@Column(name="doctor_addredd")
	private String address;
	@Column(name="doctor_mobile")
	private String number;
	@Column(name="doctor_gender")
	private String gender;
	@Column(name="doctor_discription")
	private String note;
	
	/**Module Integration Make Has-A Relation with Child Class*/
	@ManyToOne
	@JoinColumn(name="doc_id_fk_col")
	private Specilazation specilazation;

}
