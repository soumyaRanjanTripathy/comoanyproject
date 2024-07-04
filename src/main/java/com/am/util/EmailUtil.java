package com.am.util;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class EmailUtil {
	@Autowired
	private JavaMailSender sender;
	
	public boolean sendEmail(
			String to,
			String subject,
			String text,
			String cc,
			String[] bcc,
			MultipartFile file) 
	{
		boolean flag=false;
		try {
			//1 create empty message(mime message)
			MimeMessage message=sender.createMimeMessage();
			
			//use helper class object
			MimeMessageHelper helper=new MimeMessageHelper(message,file!=null);
			
			// set message details 
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(text);
			
			if(cc!=null)
				helper.setCc(cc);
				
			if(bcc!=null)
				helper.setBcc(bcc);
			
			if(file!=null)
				helper.addAttachment(file.getOriginalFilename(), file);
			
			//3 send email
			sender.send(message);
			
			flag=true;
		}catch(Exception e) {
			flag=false;
			e.printStackTrace();
		}
		
		return flag;
	}
	//overloaded
	public boolean sendEmail(
			String to,
			String subject,
			String text
			) 
	{
		return sendEmail(to,subject,text,null,null,null);
		
	}
}
