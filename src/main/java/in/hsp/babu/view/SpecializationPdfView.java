package in.hsp.babu.view;

import java.awt.Color;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import in.hsp.babu.entity.Specilazation;

public class SpecializationPdfView extends AbstractPdfView {
	
	
	@Override
	protected void buildPdfMetadata(Map<String, Object> model,
			Document document,
			HttpServletRequest request) {
		
		HeaderFooter header = new HeaderFooter(new Phrase("SPECIALIZATION PDF VIEW"), false);
		header.setAlignment(Element.ALIGN_CENTER);
		document.setHeader(header);

		HeaderFooter footer = new HeaderFooter(new Phrase(new Date()+" (C) Ealendra, Page # "), true);
		footer.setAlignment(Element.ALIGN_RIGHT);
		document.setFooter(footer);
		
		super.buildPdfMetadata(model, document, request);
	}

	@Override
	protected void buildPdfDocument(Map<String, Object> model,
			Document document, PdfWriter writer,
			HttpServletRequest request,
			HttpServletResponse response) 
			 throws Exception {
		
		
		
		
		//download pdf with Given File Name;
		response.addHeader("Content-Disposition", "attachment;filename=SPEC.pdf");
		
		//Read Data From Specialization Controller
		     @SuppressWarnings("unchecked")
			 List<Specilazation> list=(List<Specilazation>)model.get("list");
		       
		//create element.

		Paragraph titel=new Paragraph();
		//Font(FAMILY,SIZE,STYLE,COLOR)
		Font titleFont = new Font(Font.TIMES_ROMAN, 30, Font.BOLD, Color.RED);
		Paragraph title = new Paragraph("SPECIALIZATION DATA",titleFont);
		title.setAlignment(Element.ALIGN_CENTER);
		title.setSpacingBefore(20.0f);
		title.setSpacingAfter(25.0f);
		//add document..
		document.add(titel);
		
		Font tableHead = new Font(Font.TIMES_ROMAN, 12, Font.BOLD, Color.ORANGE);
		PdfPTable table = new PdfPTable(4);// no.of columns
		table.addCell(new Phrase("ID",tableHead));
		table.addCell(new Phrase("CODE",tableHead));
		table.addCell(new Phrase("NAME",tableHead));
		table.addCell(new Phrase("NOTE",tableHead));

		for(Specilazation spec : list ) {
			table.addCell(spec.getId().toString());
			table.addCell(spec.getCode().toString());
			table.addCell(spec.getName());
			table.addCell(spec.getNote());
		}
		//add to document
		document.add(table);
		
		
		
	}

}
