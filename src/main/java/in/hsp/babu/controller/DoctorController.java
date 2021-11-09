package in.hsp.babu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
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

import in.hsp.babu.entity.Doctor;
import in.hsp.babu.exception.DoctorNotFoundException;
import in.hsp.babu.service.DoctorService;
import in.hsp.babu.service.SpeclizationService;
import in.hsp.babu.util.MyEmailUtil;

@Controller
@RequestMapping("/doctor")
public class DoctorController {

	@Autowired
	private DoctorService service;

	/** Module Integration Step 1 (Make Has A Relation With Child Service)*/

	@Autowired
	private SpeclizationService specializationService;
	
	
	@Autowired
	private MyEmailUtil mailUtil;

	@GetMapping("/show")
	public String viewPage(@RequestParam(value="message",required=false)String message,Model model)
	{
		model.addAttribute("message",message);
		/** Step 3-over ride the Dynamic Drop Down Method at Resister Page*/
		 createDropDown(model);
		 return"DoctorRegisterForm";
	}
	@PostMapping("/save")
	public String savePage(@ModelAttribute Doctor doctor,RedirectAttributes attributes)
	{
		Integer id=service.saveData(doctor);
		//String message="Doctor Record '"+id+"'is Created";
		attributes.addAttribute("message","Doctor is("+id+")is Created");
		String message=" Doctor("+id+") is created succesFully";
		attributes.addAttribute("message",message);
		if(id!=null)
		{
			new Thread(new Runnable()

			{ public void run() {
				mailUtil.send(doctor.getEmail(),"SUCCESS", message,
						new ClassPathResource("/static/myres/sample.pdf"));
			}}).start();


		}

		return"redirect:show";
	}

	//@GetMapping("/data")
	public String getData(@ModelAttribute Doctor doctor,Model model,

			@RequestParam(value="message",required=false)String message)
	{
		List<Doctor> list=service.getAllData();
		model.addAttribute("list",list);
		return"DoctorRegisterData";
	}
	
	//for Pegination concept.
	@GetMapping("/data")
	 public String getAllPegination(@PageableDefault(page=0,size=3)Pageable pageable,Model model,@RequestParam(value="message",required=false)String message)
	{
		Page<Doctor> page=service.getAllRecords(pageable);
		model.addAttribute("list", page.getContent());
		model.addAttribute("page", page);
		model.addAttribute("message", message);
		return"DoctorRegisterData";
	}
	
	
	
	
	@GetMapping("/delete")
	public String deleteData(@RequestParam("id") Integer id,RedirectAttributes attributes)
	{
		String message=null;

		try {

			service.deleteData(id);
			message="Doctor ("+id+") is Deleted";


		}
		catch( DoctorNotFoundException e) {

			e.printStackTrace();
			e.getMessage();
		}
		attributes.addAttribute("message","("+id+") is Deleted Successfully");
		return"redirect:data";
	}

	@GetMapping("/edit")
	public String editForm(@RequestParam("id") Integer id,RedirectAttributes attributes,Model model)
	{
		String page=null;
		try {
			Doctor doctor=service.editData(id);
			model.addAttribute("doctor",doctor);
			/**Step 4- Over Ride The Dynamic DRop Down at Edit Page */
			createDropDown(model);
			return"DoctorEditForm";
		}
		catch(DoctorNotFoundException e)
		{
			e.printStackTrace();
			attributes.addAttribute("meassage",e.getMessage());
			page="redirect:data";
		}
		return page;
	}
	@PostMapping("/update")
	public String updateData(@ModelAttribute Doctor doctor,RedirectAttributes attributes,Model model)
	{
		service.updateData(doctor);
		model.addAttribute("message",doctor.getId()+",updated");
		return"redirect:data";
	}

	/**Model Integration Step 2 */

	private void createDropDown(Model model)
	{
		model.addAttribute("Specializations",specializationService.getIdAndName());
	}
}
