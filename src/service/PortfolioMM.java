package service;

import java.io.File;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import bean.Forward;
import bean.Portfolio;
import dao.PortfolioDao;


public class PortfolioMM {
	private PortfolioDao pDao;
	HttpServletRequest request;
	HttpServletResponse response;
	private HttpSession session;

	public PortfolioMM(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}

	public Forward portFolioSave() {
		pDao = new PortfolioDao();
		Portfolio pf=new Portfolio();
		session = request.getSession();
		String id =session.getAttribute("id").toString();
		pf.setP_Id(id);
		pf.setP_Title(request.getParameter("title"));
		pf.setP_Content(request.getParameter("content"));
		pf.setP_Thumbnail(request.getParameter("thumbnailName"));
		
		boolean result = pDao.portFolioSave(pf);
		
		pDao.close(); // 오라클에서반납

		Forward fw = new Forward();
		if (result) {
			fw.setPath("index.jsp");
			fw.setRedirect(true); // redirect 포워딩 주소창이 같아야하고들어있는 객체가 없기떄문에
		} else {
			request.setAttribute("msg", "회원가입실패");
			fw.setPath("portfolioMade"); // joinFrm.jsp
			fw.setRedirect(true);// dispatcher 포워딩
		}
		return fw;
	}


	

	public Forward thumbnail() {
		
		Forward fw = new Forward();
		Portfolio pt = new Portfolio();
		int size = 10 * 1024 * 1024; // 10MB까지
		String uploadPath = request.getSession().getServletContext().getRealPath("/upload");
		// clear후 upload폴더가 부재하다면 생성할 것
		File dir = new File(uploadPath);
		if (!dir.isDirectory()) {
			dir.mkdir();// 폴더생성
		}

		// cos 라이브러리

		try {
			// DafaultFileRenamePolicy(덮어쓰기안하고 이름에번호 붙어서 올라감)

			MultipartRequest multi = new MultipartRequest(request, uploadPath, size, "utf-8",
					new DefaultFileRenamePolicy());
			String thumFileName=multi.getFilesystemName("p_file");
			request.setAttribute("thumbnailName", thumFileName);
			System.out.println("썸파일네임:"+thumFileName);

			if (multi.getFilesystemName("p_file")!=null) {
				System.out.println("파일 업로드 성공");
				fw.setPath("portfolioCreation.jsp");
				fw.setRedirect(false);
			} else {
				request.setAttribute("msg", "");
				fw.setPath("proUpFrm");
				fw.setRedirect(true);
			}
		} catch (IOException e) {
			System.out.println("파일 업로드 예외");
			e.printStackTrace();
		}
		return fw;
	}
	
	public Forward newPortFolio() {
		Forward fw = new Forward();
		pDao = new PortfolioDao();
		List<Portfolio> pList = null;
		pList=pDao.newPortFolio();
		pDao.close();

		String jsonObj=null;
		if (pList != null && pList.size()!=0) {
			jsonObj=new Gson().toJson(pList);
			request.setAttribute("newPortFolio", jsonObj);
			System.out.println("뉴포트제이슨:"+jsonObj);
		}
		fw.setPath("main.jsp");
		fw.setRedirect(false);
		return fw;
	}

	public Forward noMalBestPort() {
		Forward fw = new Forward();
		pDao = new PortfolioDao();
		List<Portfolio> pList = null;
		pList=pDao.noMalBestPort();
		pDao.close();

		String jsonObj=null;
		if (pList != null && pList.size()!=0) {
			jsonObj=new Gson().toJson(pList);
			request.setAttribute("newPortFolio", jsonObj);
			System.out.println("노멀베스트포트제이슨:"+jsonObj);
		}
		fw.setPath("main.jsp");
		fw.setRedirect(false);
		return fw;
	}

	public Forward comBestPort() {
		Forward fw = new Forward();
		pDao = new PortfolioDao();
		List<Portfolio> pList = null;
		pList=pDao.comBestPort();
		pDao.close();

		String jsonObj=null;
		if (pList != null && pList.size()!=0) {
			jsonObj=new Gson().toJson(pList);
			request.setAttribute("newPortFolio", jsonObj);
			System.out.println("컴퍼니베스트포트제이슨:"+jsonObj);
		}
		fw.setPath("main.jsp");
		fw.setRedirect(false);
		return fw;
	}

	public Forward profilePortfolio() {
		Forward fw = new Forward();
		pDao = new PortfolioDao();
		List<Portfolio> pList = null;
		session = request.getSession();
		String id = session.getAttribute("id").toString();
		pList=pDao.profilePortfolio(id);
		pDao.close();

		String jsonObj=null;
		if (pList != null && pList.size()!=0) {
			jsonObj=new Gson().toJson(pList);
			request.setAttribute("profilePortfolio", jsonObj);
			System.out.println("프로필포트제이슨:"+jsonObj);
		}
		fw.setPath("profilePortfolio.jsp");
		fw.setRedirect(false);
		return fw;
	}

	public Forward logOut() {
		Forward fw = new Forward();
		session = request.getSession();
		String id = session.getAttribute("id").toString();
		String ip = request.getRemoteAddr();
		String state = "logout";
		String header = request.getHeader("user-agent");
	/*	pDao = new PortfolioDao();
		pDao.history(id, ip, header, state);
		pDao.close();*/
		// session.removeAttribute("id");
		session.invalidate();// 세션무효화
		fw.setPath("./");// web.xml / 
		fw.setRedirect(true);
		return fw;
	}

	public Forward portDelete() {
		Forward fw = new Forward();
		String number = request.getParameter("Number");
		String thumbnail = request.getParameter("Thumbnail");
		System.out.println(thumbnail);
		pDao = new PortfolioDao();
		pDao.cLikeDelete(number);
		pDao.nLikeDelete(number);
		pDao.portDelete(thumbnail);
		pDao.close();
		
		fw.setPath("profile");
		fw.setRedirect(true);
		return fw;
	}
}
