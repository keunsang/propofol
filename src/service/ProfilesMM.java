package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import bean.Forward;
import bean.NomalMember;
import dao.ProfilesDao;

public class ProfilesMM {
	private ProfilesDao pDao;
	HttpServletRequest request;
	HttpServletResponse response;
	private HttpSession session;

	public ProfilesMM(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}

	public Forward insertProfiles() {
		Forward fw = new Forward();
		session = request.getSession();
		String id = (String) session.getAttribute("id");
		String etc = request.getParameter("etc");
		pDao = new ProfilesDao();
		pDao.insertprofiles(id, etc);
		pDao.close();
		fw.setPath("profile");
		fw.setRedirect(true);
		return fw;

	}

	public String getProfileetc() {
		String id = request.getParameter("profileid");
		System.out.println("profile id = "+id);
		pDao = new ProfilesDao();
		String ge = pDao.getetc(id);

		pDao.close();
		String jsonObj = null;

		if (ge != null) {
			jsonObj = new Gson().toJson(ge);
		}
		request.setAttribute("etc", jsonObj);
		System.out.println("etc" + jsonObj);
		return jsonObj;

	}
}
