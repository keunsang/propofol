package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Forward;
import service.MessageMM;
import service.NomalmemberMM;
import service.ProfilesMM;

/**
 * Servlet implementation class HomeController
 */
@WebServlet({ "/Receivedmessage", "/InsertMessage", "/delete", "/profile", "/profileetc" })
public class MessageController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String url = uri.substring(conPath.length());
		System.out.println("url=" + url);
		MessageMM mmm = new MessageMM(request, response);
		NomalmemberMM nm = new NomalmemberMM(request, response);
		ProfilesMM pmm = new ProfilesMM(request, response);
		Forward fw = new Forward();
		String jsonObj = null;
		HttpSession session;
		switch (url) {
		/*case "/sendsmessage":
			//fw = mmm.loadsmlist();
			break;*/
		case "/Receivedmessage":
			fw = mmm.loadmlist();
			break;
		case "/InsertMessage":
			fw = mmm.insertmessage();
			break;
		case "/delete":
			fw = mmm.deletemessage();
			break;
		case "/profile":
			jsonObj = nm.getprofile();
			fw.setPath("profile.jsp");
			fw.setRedirect(false);
			break;
		case "/profileetc":
			fw = pmm.insertProfiles();
			break;

		}
		
		// 포워딩
		if (jsonObj != null) {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter(); 
			out.write(jsonObj);

		}
		
		if (fw != null) {
			if (fw.isRedirect()) {
				response.sendRedirect(fw.getPath());
			} else {
				RequestDispatcher dis = request.getRequestDispatcher(fw.getPath());
				dis.forward(request, response);
			}
		}

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