package in.hsp.babu.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="appointment_tab")
public class Appointment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="appoint_id")
	private Integer id;
	
	@DateTimeFormat(iso = ISO.DATE)
	@Temporal(TemporalType.DATE)
	@Column(name="appoint_date")
	private Date date;
	
	@Column(name="appointment_noofslots")
	private Integer noOfSlots;
	
	@Column(name="appointment_consultfees")
	private Double consultFee;
		
	@Column(name="appointment_discription")
	private String note;

	//1.Module Integration 
	@ManyToOne
	@JoinColumn(name="appoint_fk_id")
	private Doctor doctor; 
}
