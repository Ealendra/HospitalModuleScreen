package in.hsp.babu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.hsp.babu.entity.Patient;
import in.hsp.babu.service.PatientService;

@Controller
@RequestMapping("/patient")
public class PatientController {
	
	@Autowired
	private PatientService service;
	
	@GetMapping("/show")
	public String viewPage()
	{
		
		return"PatientRegisterForm";
	}
	
	@PostMapping("/save")
	public String savePage(@ModelAttribute Patient patient,Model model) {
		       Integer id=service.saveData(patient);
		       String message="Patient Data '"+id+"' is Saved";
		       model.addAttribute("message",message);
		return"PatientRegisterForm";
		
	}
	@GetMapping("/data")
	public String getAll(@ModelAttribute Patient patient,Model model)
	{
		List<Patient> list=service.getAllPatients();
		        model.addAttribute("list",list);
		return"PatientAllData";
	}
	@GetMapping("/delete")
	public String deleteFormData(@RequestParam Integer id,Model model)
	{
		
		service.deleteData(id);
		String message="Patient'"+id+"'Record is Deleted";
		model.addAttribute("message",message);
		List<Patient> list=service.getAllPatients();
        model.addAttribute("list",list);
        
		return"PatientAllData";
	}
	
	@GetMapping("/edit")
	public String editPage(@RequestParam Integer id,Model model)
	{     
		Patient patient= service.editData(id);
		model.addAttribute("patient",patient);
		return"PatientEditForm";
	}
	@PostMapping("/update")
	public String updateData(@ModelAttribute Patient patient,Model model)
	{
		service.updateData(patient);
		model.addAttribute(patient);
		return"redirect:data";
	}

}
