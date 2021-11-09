package in.hsp.babu.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import in.hsp.babu.entity.Document;
import in.hsp.babu.service.DocumentService;

@Controller
@RequestMapping("/doc")
public class DocumentController {
	
	private static final Logger LOG = LoggerFactory.getLogger(DocumentController.class);
	
	@Autowired
	private DocumentService service;
	
	//1.Show Document Page..
	@GetMapping("/all")
	public String showDocs(Model model)
	{
		model.addAttribute("idVal", System.currentTimeMillis());
		     List<Object[]>   list=service.getDocumentIdAndName();
		     model.addAttribute("list", list);
		return"Documents";
	}
	//2.upload Document.
	@PostMapping("/upload")
	public String uploadDoc(@RequestParam Integer docId,@RequestParam MultipartFile docOb)
	{
		try {
			Document doc=new Document();
			doc.setDocId(docId);
			doc.setDocName(docOb.getOriginalFilename());
			doc.setDocData(docOb.getBytes());
			service.saveDocument(doc);
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return"redirect:all";
	}
	
	//3.Download By Id.
	@GetMapping("/download")
	public void download(@RequestParam Integer id,HttpServletResponse response)
	{
		
		try
		{
			
			//1.fetch Doc Object.
			          Document doc= service.getDocumentById(id);
			          //2.provide File Using Header.    
	 response.setHeader("Content-Disposition","attachment;filename="+doc.getDocName());
	 
	 //3.copy data from doc to response Object..
	 FileCopyUtils.copy(doc.getDocData(),response.getOutputStream());
	 
		}catch(Exception e)
		{
		   e.printStackTrace();	
		}
		
	}
	
	//4.Delete by Id.
	@GetMapping("/delete")
	public String deleteDoc(@RequestParam Integer id)
	{
		try
		{
			service.deleteDocumentById(id);
		}catch(Exception e)
		{
			LOG.error(e.getMessage());
			e.printStackTrace();
		}
		return"redirect:all";
	}

}
