package in.hsp.babu.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import in.hsp.babu.entity.Appointment;

public interface AppointmentService {
	
	//1.Curd Operations Methods.
	public Integer saveAppointmentData(Appointment appointment);
	
	public List<Appointment> getAllAppointData();
	
	public void deleteAppointmentData(Integer id);
	
	public Appointment editAppointmentData(Integer id);
	
	public void updateAppointmentData(Appointment appointment);
	
	//.
	public List<Object[]>  getApmentByDocId(Integer id);
	//Peginations concept.
	
	public Page<Appointment> getAllAppointRecords(Pageable pageable);
	

}
