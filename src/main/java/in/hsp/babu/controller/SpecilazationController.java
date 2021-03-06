package in.hsp.babu.controller;


import java.util.List;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import in.hsp.babu.entity.Specilazation;
import in.hsp.babu.excel.SpecializationExcelExport;
import in.hsp.babu.service.SpeclizationService;
import in.hsp.babu.view.SpecializationPdfView;

@Controller
@RequestMapping("/specialization")
public class SpecilazationController {
	@Autowired
	private SpeclizationService service;
	@GetMapping("/show")
	public String userPage()
	{
	
	return"SpecializationUserForm";
	}
   @PostMapping("/save")
    public String getUserData(@ModelAttribute Specilazation specilazation,Model model)
{
		Integer id=service.saveSpecialization(specilazation);
		String message="User id'"+id+"'is Created";
		model.addAttribute("message",message);
	return"SpecializationUserForm";
}
  // @GetMapping("/data")
   public String getData(@ModelAttribute Specilazation specilazation,Model model)
   {
	              List<Specilazation> list= service.getAllData();
	                  model.addAttribute("list",list);
	                 return"SpecializationData";
   }
   //For pagination concept.
   @GetMapping("/data")
   public String viewAllPageble(@PageableDefault(page=0,size=3)Pageable pageable,Model model,@RequestParam(value="message",required=false)String message)
   {
	   Page<Specilazation> page=service.getAllRecords(pageable);
	   model.addAttribute("list",page.getContent());
	   model.addAttribute("page", page);
	   model.addAttribute("message", message);
	   return"SpecializationData";
   }
   @GetMapping("/delete")
   public String deleteData(@RequestParam Integer id,Model model)
   {
	   service.deleteData(id);
	   String message="Speicalization Data '"+id+"'is Deleted";
	   List<Specilazation> list= service.getAllData();
       model.addAttribute("list",list);
	   return"SpecializationData";
	   
   }
   @GetMapping("/edit")
   public String editData(@RequestParam Integer id,Model model)
  
   {
	   Specilazation message=service.editData(id);
	   model.addAttribute("message",message);
	    
	   return"SpecilazationlEditForm";
   }
   @PostMapping("/update")
   public String updateData(@ModelAttribute Specilazation specilazation,Model model)
   {
       service.updateData(specilazation);
       model.addAttribute(specilazation);
   return"redirect:data";
   }
   
   @ResponseBody
   @GetMapping("/checked")
   public String getCountValidate(@RequestParam Integer code)
   {
	   String message="";
	   if(service.isCodeExit(code))
	   {
		     message=code + ", *This code Already Exit Please Enter Another code";
	   }
              
   return message;
   }
   @ResponseBody
   @GetMapping("/namecheck")
   public String getNameValidate(@RequestParam String name)
   {
	   String mess="";
	   if(service.isNameExit(name))
	   {
	   mess = name + ", * This Name is Alredy Exit please Enter Another name";
	   }
	   return mess;
   }
   
   @GetMapping("/excel")
   public ModelAndView exportExcelFile()
   {
	   ModelAndView m=new ModelAndView();
	   m.setView(new SpecializationExcelExport());
	   
	   List<Specilazation> list= service.getAllData();
	   
	   m.addObject("list",list);
	   
	   return m;
   }
   
   @GetMapping("/pdf")
   public ModelAndView exportToPdf()
   {
	   ModelAndView m=new ModelAndView();
	   m.setView(new SpecializationPdfView());
      List<Specilazation> list= service.getAllData();
	   m.addObject("list",list);
	   return m;
   }
   
}
