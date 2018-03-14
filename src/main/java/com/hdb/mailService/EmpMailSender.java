package com.hdb.mailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
@Service
public class EmpMailSender {
	@Autowired
	JavaMailSender javaMailSender;

	public boolean sendMailtoEmp(String EmpName, String empId, String Email, String password) {

		SimpleMailMessage smm = new SimpleMailMessage();
		smm.setFrom("hdbkpl@gmail.com");
		smm.setTo(Email);
		smm.setSubject("account created");
		smm.getSentDate();
		smm.setText("Dear " + EmpName + " Congratulations ! your Emp Id :" + empId + " \n username: " + Email
				+ " Password :" + password + " ");
		try {
			javaMailSender.send(smm);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;

	}

}
