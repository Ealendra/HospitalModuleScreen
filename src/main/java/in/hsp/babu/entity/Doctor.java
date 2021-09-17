package in.hsp.babu.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="doctor_tab")
public class Doctor {
	@Id
	@GeneratedValue
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

}
