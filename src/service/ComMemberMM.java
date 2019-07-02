package service;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import bean.ComMember;
import bean.Forward;
import dao.ComMemDao;

public class ComMemberMM {
	HttpServletRequest request;
	HttpServletResponse response;
	ComMemDao cDao;
	Forward fw = null;

	public ComMemberMM(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}

	public String getIdCheck() {
		String id = request.getParameter("userId");
		System.out.println("id : " + id);
		cDao = new ComMemDao();
		String nm = cDao.getIdCheck1(id);
		cDao.close();
		String jsonObj = null;

		if (nm != null) {
			jsonObj = new Gson().toJson(nm);
		}
		return jsonObj; // jsonObj
	}

	public Forward insertComJoin() {
		cDao = new ComMemDao();
		ComMember nmIn = new ComMember();
		String test = request.getParameter("id");
		System.out.println("insert : "+test);
		
		nmIn.setC_Id(request.getParameter("id"));
		nmIn.setC_Pw(request.getParameter("pw"));
		nmIn.setC_Name(request.getParameter("cpnName"));
		nmIn.setC_Bnum(request.getParameter("cpnNum"));
		nmIn.setC_Rnum(request.getParameter("cpnNum1"));
		nmIn.setC_Cname(request.getParameter("cName"));
		nmIn.setC_Email(request.getParameter("cEmail"));
		nmIn.setC_Phone(request.getParameter("cTel"));
		nmIn.setC_Kind("C");
		
		
		
		if (cDao.insertComJoin1(nmIn)) { 
			
			cDao.close();
			
			fw = new Forward();
			fw.setPath("index.jsp");
//			fw.setRedirect(false);
			
			System.out.println("insertComJoin success");
			return fw;
		} else {
			System.out.println("insertComJoin fail");
			cDao.close();
		}
		
		return null;
	}

	public String getEmailCkeck() {
		String email = request.getParameter("userEmail");
		System.out.println("email : " + email);
		cDao = new ComMemDao();
		String nm = cDao.getEmailCkeck1(email);
		cDao.close();
		String jsonObj = null;

		if (nm != null) {
			jsonObj = new Gson().toJson(nm);
		}
		return jsonObj; // jsonObj
	}

}
