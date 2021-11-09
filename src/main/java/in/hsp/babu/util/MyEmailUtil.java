package in.hsp.babu.util;



import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class MyEmailUtil {
	
	@Autowired
	private JavaMailSender mailSender;
	
	public boolean send(
			String to[],
			String cc[],
			String bcc[],
			String text,
			String subject,
			Resource files[])
	{
		boolean sent=false;
		try {
			//1.Create one Empty Mime Message.
			MimeMessage message = mailSender.createMimeMessage();
			//2.send the data message and attached File Exit or Not
			MimeMessageHelper helper=new MimeMessageHelper(message,files!=null && files.length>0);
			
			helper.setTo(to);
			if(cc!=null)
			helper.setCc(cc);
			if(bcc!=null)
			helper.setBcc(bcc);
			helper.setSubject(subject);
			helper.setText(text);
			if(files!=null)
			{
				for(Resource rs:files)
				{
					helper.addAttachment(rs.getFilename(),rs);
				}
			}
			
			//3.send Message
			
			mailSender.send(message);

			sent=true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			sent=false;
		}
		
		return sent;
	}
	
	/** over loaded methods*/
	
	public boolean send(String to,String text,String subject,Resource file)
	{
		return send(new String[] {to},null,null,text,subject,file!=null ? new Resource[] {file}:null);
	}
	
	public boolean send(String to,String text,String subject)
	{
		return send(to,text,subject,null);
	}
}
