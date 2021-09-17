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

import in.hsp.babu.entity.Doctor;
import in.hsp.babu.service.DectorService;

@Controller
@RequestMapping("/doctor")
public class DoctorController {
	
	@Autowired
	private DectorService service;
	
	@GetMapping("/show")
	public String viewPage()
	{
		return"DoctorRegisterForm";
	}
	@PostMapping("/save")
	public String savePage(@ModelAttribute Doctor doctor,Model model)
	{
		Integer id=service.saveData(doctor);
		String message="Doctor Record '"+id+"'is Created";
		model.addAttribute("message",message);
		
		return"DoctorRegisterForm";
	}
	
	@GetMapping("/data")
	public String getData(@ModelAttribute Doctor doctor,Model model)
	{
		List<Doctor> list=service.getAllData();
		model.addAttribute("list",list);
		return"DoctorRegisterData";
	}
	@GetMapping("/delete")
	public String deleteData(@RequestParam Integer id,Model model)
	{
		service.deleteData(id);
		String mess="Doctor'"+id+"' is Deleted";
		model.addAttribute("mess",mess);
		List<Doctor> list=service.getAllData();
		model.addAttribute("list",list);
	
		return"DoctorRegisterData";
	}
	
	@GetMapping("/edit")
	public String editForm(@RequestParam Integer id,Model model)
	{
		Doctor dr=service.editData(id);
		model.addAttribute("dr",dr);
		return"DoctorEditForm";
	}
	   @PostMapping("/update")
       public String updateData(@ModelAttribute Doctor doctor,Model model)
       {
		   service.updateData(doctor);
		   model.addAttribute(doctor);
    	   return"redirect:data";
       }
}
