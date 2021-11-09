package in.hsp.babu.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import in.hsp.babu.entity.Appointment;
import in.hsp.babu.repository.AppointmentRepository;
import in.hsp.babu.service.AppointmentService;

@Service
public class AppointmentServiceImpl implements AppointmentService {
	
	@Autowired
	private AppointmentRepository repo;

	@Override
	public Integer saveAppointmentData(Appointment appointment) {
		appointment =repo.save(appointment);
		return appointment.getId();
	}

	@Override
	public List<Appointment> getAllAppointData() {
		List<Appointment> list=repo.findAll();
		return list;
	}

	@Override
	public void deleteAppointmentData(Integer id) {
	         repo.deleteById(id);
		
	}

	@Override
	public Appointment editAppointmentData(Integer id)
	{
		Optional<Appointment> opt=repo.findById(id);
		
		if(opt.isPresent())
		{
			Appointment appointment = opt.get();
			return appointment;
		}
		// TODO Auto-generated method stub
				return null;
		
	}

	@Override
	public void updateAppointmentData(Appointment appointment) {
		repo.save(appointment);
		
	}
	
	@Override
	public List<Object[]> getApmentByDocId(Integer id) {
	
		return repo.getApmentByDocId(id);
	}
	
	
	//For Pegination .
	
	@Override
	public Page<Appointment> getAllAppointRecords(Pageable pageable) {
		
		return repo.findAll(pageable);
	}
	

}
