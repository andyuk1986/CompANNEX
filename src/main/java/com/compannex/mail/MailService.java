package com.compannex.mail;

import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import com.compannex.message.MessageService;
import com.compannex.properties.CompANNEXProperties;

public class MailService {

	private MailSender mailSender;
    private SimpleMailMessage templateMessage;
    private MessageService messageService;
    private CompANNEXProperties compANNEXProperties;
    
    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void setTemplateMessage(SimpleMailMessage templateMessage) {
        this.templateMessage = templateMessage;
    }
    
    public void setMessageService(MessageService messageService) {
    	this.messageService = messageService;
    }

	public void setCompANNEXProperties(CompANNEXProperties compANNEXProperties) {
		this.compANNEXProperties = compANNEXProperties;
	}
	
    public void resetPassword(String email, Integer id, String token) {

        SimpleMailMessage msg = new SimpleMailMessage(this.templateMessage);
        msg.setTo(email);
        msg.setText(messageService.getString("reset.password", compANNEXProperties.getHostName() + "/CompANNEX/resetpasswordnew.do?id=" + id + "&token=" + token + "&email=" + email));
        
        try{
            this.mailSender.send(msg);
        }
        catch(MailException ex) {
            // simply log it and go on...
            System.err.println(ex.getMessage());            
        }
    }
}
