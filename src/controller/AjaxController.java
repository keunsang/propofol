package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.EmailRandNumSend;
import service.MessageMM;
import service.NomalmemberMM;
import service.ProfilesMM;

@WebServlet({ "/comLike", "/nomLike", "/idcheck", "/mtitle", "/emailcheck", "/emailrndsend", "/login", "/finid",
		"/finpw", "/profiletc", "/ajaxDetail","/getid" })
public class AjaxController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String url = uri.substring(conPath.length());
		System.out.println("url=" + url);

		NomalmemberMM mm = new NomalmemberMM(request, response);
		MessageMM mmm = new MessageMM(request, response);
		EmailRandNumSend erns = new EmailRandNumSend(request, response);
		String jsonObj = null;
		ProfilesMM pmm = new ProfilesMM(request, response);
		switch (url) {	
		case "/getid":
			jsonObj = mmm.getid();
			break;
		case "/comLike":
			jsonObj = mm.comLike();
			break;
		case "/nomLike":
			jsonObj = mm.nomLike();
			break;
		case "/ajaxDetail":
			jsonObj = mm.getajaxDetail();
			break;
		case "/idcheck":
			jsonObj = mm.getIdCheck();
			break;
		case "/emailcheck":
			jsonObj = mm.getEmailCkeck();
			break;
		case "/mtitle":
			jsonObj = mmm.getmcontent();
			break;
		case "/emailrndsend":
			jsonObj = erns.setEmailRandNumSend();
			break;
		case "/login":
			jsonObj = mm.getIdPwCheck();
			break;
		case "/finid":
			jsonObj = mm.getIdFind();
			System.out.println("jsonObj = " + jsonObj);
			break;
		case "/finpw":
			jsonObj = mm.getPwFind();
			System.out.println("jsonObj = " + jsonObj);
			break;
		case "/profiletc":
			jsonObj = pmm.getProfileetc();
			System.out.println("jsonObj=" + jsonObj);
			break;
		}

		if (jsonObj != null) {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter(); // out占쏙옙 占쏙옙占썰객체占쏙옙 占쏙옙占쏙옙
			out.write(jsonObj);

		}

		/*
		 * if (jsonObj != null) { response.setContentType("text/html;charset=utf-8");
		 * PrintWriter out = response.getWriter(); // out占쏙옙 占쏙옙占썰객체占쏙옙 占쏙옙占쏙옙
		 * out.write(jsonObj); }
		 */
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

}
