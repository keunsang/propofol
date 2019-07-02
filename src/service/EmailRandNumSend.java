package service;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.activation.DataSource;

public class EmailRandNumSend {
	HttpServletRequest request;
	HttpServletResponse response;
	final String username = "soonchul88"; //구글아이디
	final String password = "Tnscj"; //구글비밀번호

	public EmailRandNumSend(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}

	public EmailRandNumSend() {
	}

	public String setEmailRandNumSend() {
		String userEmail = request.getParameter("userEmail");
		System.out.println("setEmailRandNumSend : " + userEmail);

		

		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "465");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

		Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		int checkNum = 1;
		String checkNumStr = null;

		while (true) {
			checkNum = ((int) (Math.random() * 1000000));
			if (checkNum > 99999) {
				break;
			}
		}

		checkNumStr = Integer.toString(checkNum);

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("soonchul88@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(userEmail));
			message.setSubject("이메일 인증번호");
			message.setText("인증번호: " + checkNum);

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			e.printStackTrace();
		}

		return checkNumStr;
	}
	
	public void setEmailPwSend(String pwStr, String email) {

		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "465");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

		Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});


		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("soonchul88@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
			message.setSubject("비밀번호찾기");
			message.setText("비밀번호 : " + pwStr);

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

}
