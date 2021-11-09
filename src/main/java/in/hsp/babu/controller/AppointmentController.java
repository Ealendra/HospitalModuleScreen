package in.hsp.babu.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import in.hsp.babu.entity.Appointment;
import in.hsp.babu.entity.Doctor;
import in.hsp.babu.service.AppointmentService;
import in.hsp.babu.service.DoctorService;
import in.hsp.babu.service.SpeclizationService;

@Controller
@RequestMapping("/appointment")
public class AppointmentController {
	@Autowired
	private AppointmentService service;
	/** 1.Make has a relation with child Service class.
	 * 2.Create one  Method
	 * 3.over ride the Method where we want.
	 * like at Register Page & Edit Page.  
	 * */
	//1. Has A Relation With Child service class.
	@Autowired
	private DoctorService doctorService;
	//2.Create a Method 
	public void dynamicDropdown(Model model)
	{
		model.addAttribute("doctores", doctorService.getDoctorNameByIdName());
	}
	/*
	 * create method for doctors list.
	 * 
	 * */
	@Autowired
	private SpeclizationService specialService;
	
	@GetMapping("/view")
	public String showDocDropdown(@RequestParam(required=false,defaultValue="0") Integer id ,Model model)
	{
     Map<Integer,String> specMap = specialService.getIdAndName();
		model.addAttribute("specMap",specMap);
		
		       List<Doctor> docList=null;
		       String message=null;
		      
		if(id<=0)
		{
			docList=doctorService.getAllData();
			message="Result : All Doctors";
		}
		else
		{
			docList=doctorService.getAllById(id);
			message="Result : "+specialService.editData(id).getName()+ " Doctors";
		}
		model.addAttribute("docList",docList);
		model.addAttribute("message",message);		
		return"Appointmentshow";
	}
	
	//2nd View the Appointment Slots.
	
	@GetMapping("/viewSlots")
	public String viewSlots(@RequestParam Integer id,Model model)
	{
		  List<Object[]> list= service.getApmentByDocId(id);
		  model.addAttribute("list", list);
		  Doctor doctor= doctorService.editData(id);
		  model.addAttribute("message","Result is howing For :"+doctor.getName());
		return"AppointSlots";
	}
	@GetMapping("/show")
	public String showAppoint(Model model)
	{
		//3.Over Ride The Dynamic DropDown at Resister Level.
		dynamicDropdown(model);
		return"AppointmentRegister";
	}
	@PostMapping("/save")
	public String saveAppointment(@ModelAttribute Appointment appointment,Model model)
	{
		Integer id=service.saveAppointmentData(appointment);
		String message=" '"+id+"' Appointment is Created Successfully";
		model.addAttribute("message",message);
		return"AppointmentRegister";
	}
	//@GetMapping("/data")
	public String getAllAppointmentData(@ModelAttribute Appointment appointment,Model model,@RequestParam(value="message",required=false)String message)
	{
		List<Appointment> list=service.getAllAppointData();
		model.addAttribute("message",message);
		model.addAttribute("list", list);
		return"AppointmentData";
	}
	
	
	//Pegination Method.
	
	@GetMapping("/data")
	public String getAllPeginations(@PageableDefault(page=0,size=5)Pageable pageable,Model model,
			              
			@RequestParam(value="message",required=false)String message)
	{
	Page<Appointment> page=service.getAllAppointRecords(pageable);
	model.addAttribute("list", page.getContent());
	model.addAttribute("page", page);
	model.addAttribute("message",message);
		return"AppointmentData";
	}
	
	@GetMapping("/delete")
	public String deleteAppointmentData(@RequestParam Integer id,Model model)
	{
		service.deleteAppointmentData(id);
		String message="("+id+") Doctor Record is Deleted Successfully...!!!";
		model.addAttribute("message", message);
		List<Appointment> list=service.getAllAppointData();
		model.addAttribute("list", list);
		return"AppointmentData";	
	}
	@GetMapping("/edit")
	public String editAppointmentData(@RequestParam Integer id,Model model)
	{
		Appointment appointment=service.editAppointmentData(id);
		model.addAttribute("appointment",appointment);
		//4.Over Ride The Method at Edit levelR
		dynamicDropdown(model);
		return"AppointmentEdit";
	}
@PostMapping("/update")
	public String updateAppointmentData(@ModelAttribute Appointment appointment,RedirectAttributes attributes)
	{
	  service.updateAppointmentData(appointment);
	  String message="'"+appointment.getId()+"' is updated Successfully...!!!";
	  attributes.addAttribute("message", message);
		return"redirect:data";
	}

}
