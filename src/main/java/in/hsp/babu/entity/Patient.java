package in.hsp.babu.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="patient")
public class Patient {
	
	@GeneratedValue
	@Id
	@Column(name="patient_id")
	private Integer id;
	@Column(name="patient_name")
	private String name;
	@Column(name="patient_gender")
	private String Gender;
	@Column(name="patient_number")
	private String number;
	@Column(name="patient_dob")
	private String dob;
	@Column(name="patient_marriedstatus")
	private String marridStatus;
	@Column(name="patient_address")
	private String presentAddress;
	@Column(name="patient_communication")
	private String communicationAddress;
	@Column(name="patient_pastmedicalhistory")
	private String pastMedicalHistory;
	@Column(name="patient_othersdetails")
	private String othersDetails;

}


