package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;

import bean.Forward;
import service.ComMemberMM;
import service.NomalmemberMM;
import service.PortfolioMM;

@WebServlet({ "/portDelete","/comBestPort","/noMalBestPort","/logout","/portfolioDetail", "/profilePortfolio", "/newPortFolio", "/thumbnail", "/portfolioSave",
		"/portfolioMade", "/memberjoin", "/menu", "/nomaljoin", "/comjoin", "/emailCertification", "/index" })
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*HttpSession session = null;*/
		request.setCharacterEncoding("utf-8");
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String url = uri.substring(conPath.length());
		System.out.println("url=" + url);

		Forward fw = null;

		NomalmemberMM nm = new NomalmemberMM(request, response);
		ComMemberMM cm = new ComMemberMM(request, response);
		PortfolioMM pm = new PortfolioMM(request, response);

		switch (url) {
		case "/portDelete":
			fw = pm.portDelete();
			break;
		case "/portfolioDetail":
			fw = new Forward();
			fw.setPath("portfolioDetail.jsp");
			fw.setRedirect(false);
			break;
		case "/profilePortfolio":
			fw = pm.profilePortfolio();
			break;
		case "/thumbnail":
			fw = pm.thumbnail();
			break;
		case "/newPortFolio":
			fw = pm.newPortFolio();
			break;
		case "/noMalBestPort":
			fw = pm.noMalBestPort();
			break;
		case "/comBestPort":
			fw = pm.comBestPort();
			break;
		case "/portfolioSave":
			fw = pm.portFolioSave();
			break;
		case "/memberjoin":
			fw = new Forward();
			fw.setPath("memberJoinMain.jsp");
			fw.setRedirect(true);
			break;
		case "/menu":
			fw = new Forward();
			fw.setPath("menu.jsp");
			fw.setRedirect(false);
			break;
		case "/nomaljoin":
			System.out.println("nomaljoin");
			fw = nm.insertNomalJoin();
			fw.setRedirect(false);
			break;
		case "/comjoin":
			System.out.println("comjoin");
			fw = cm.insertComJoin();
			fw.setRedirect(false);
			break;
		case "/portfolioMade":
			fw = new Forward();
			fw.setPath("thumbnail.jsp");
			fw.setRedirect(false);
			break;
		case "/index":
			fw = new Forward();
			/*String id = request.getParameter("id");
			session = request.getSession();
			session.setAttribute("id", id);*/
			fw.setPath("index.jsp");
			fw.setRedirect(false);
			break;
		case "/logout":
			fw = pm.logOut();
			break;
		}

		// �룷�썙�뵫
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