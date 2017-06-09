package com.mp.mail;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.app.conn.conexionOracle;
import com.app.modelo.Contacto;

public class SpringEmailTemplateExample {
	@Autowired
	//private static conexionOracle conn;
	// @Autowired
	// private static Contacto contacto;

	public static void main(String[] args) {
		// conn = new conexionOracle();
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-beans.xml");
		Mailer mailer = (Mailer) context.getBean("mailer");

		// conn = new conexionOracle();
		// conn.getConnection();
		Contacto contacto = new Contacto();
		ArrayList<Contacto> listContacto = new ArrayList<Contacto>();

		contacto.setPrimer_nombre("William");
		contacto.setPrimer_apellido("Huera");
		contacto.setEmail_primario("whuera@gmail.com");

		listContacto.add(contacto);

		Contacto contacto2 = new Contacto();
		contacto2.setPrimer_nombre("Eduardo");
		contacto2.setPrimer_apellido("Huera");
		contacto2.setEmail_primario("eduardohuera@gmail.com");

		listContacto.add(contacto2);
		
		

		Mail mail = new Mail();
		/*
		mail.setMailFrom("publicidadmobilpymes@gmail.com");
		mail.setMailBcc("whuera@gmail.com");
		mail.setMailTo("freddy.lojan@etafashion.com");
		mail.setMailSubject("Promociones Mobilpymes.com");
		mail.setTemplateName("emailtemplate.vm");
		*/

		for (Contacto contactoFinal : listContacto) {
			mail.setMailFrom("publicidadmobilpymes@gmail.com");
			mail.setMailTo(contactoFinal.getEmail_primario());
			mail.setMailSubject("Promociones Mobilpymes.com");
			mail.setTemplateName("emailtemplate.vm");
			try {
				mailer.sendMail(mail, contactoFinal.getPrimer_nombre(), contactoFinal.getPrimer_apellido());
			} catch (ResourceNotFoundException e) {

				e.printStackTrace();
			} catch (ParseErrorException e) {

				e.printStackTrace();
			} catch (MethodInvocationException e) {

				e.printStackTrace();
			} catch (IOException e) {

				e.printStackTrace();
			}
			contactoFinal = null;
		}

	}

}
