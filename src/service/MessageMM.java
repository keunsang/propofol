package service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import bean.Forward;
import bean.MessageBean;
import bean.NomalMember;
import dao.MessageDao;
import dao.NomalMemDao;

public class MessageMM {
	HttpServletRequest request;
	HttpServletResponse response;
	private MessageDao mDao; // 회원 DB 서비스 클래스 Date Access Object
	private HttpSession session;

	public MessageMM(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}

	public Forward loadmlist() {
		Forward fw = new Forward();
		session = request.getSession();
		String id = (String) session.getAttribute("id");
		mDao = new MessageDao();
		List<MessageBean> MessageBean = mDao.rmessages(id);
		List<MessageBean> MessageBeans = mDao.sendmessages(id);
		String jsonobj = null;
		String jsonobjs = null;
		mDao.close();
		if (MessageBean != null) {
			jsonobj = new Gson().toJson(MessageBean);
			jsonobjs = new Gson().toJson(MessageBeans);
			request.setAttribute("mlist", jsonobj);
			request.setAttribute("smlist", jsonobjs);

			fw.setPath("Receivedmessage.jsp");
			fw.setRedirect(false);
		}

		return fw;
	}

	public String getmcontent() {
		session = request.getSession();
		String id = (String) session.getAttribute("id");
		String m_title = request.getParameter("m_title");
		String kind = request.getParameter("kind");
		System.out.println("아이작스 카인드="+kind);
		mDao = new MessageDao();
		MessageBean mb=null;
		if(kind.equals("a")) {
		 mb = (MessageBean) mDao.getsmcontent(id, m_title);
		}
		if(kind.equals("b")) {
		 mb = (MessageBean) mDao.getrmcontent(id, m_title);
		}
		mDao.close();
		String jsonObj = null;
		if (mb != null) {
			jsonObj = new Gson().toJson(mb);
		}
		return jsonObj; // jsonObj
	}

	public Forward insertmessage() {
		Forward fw = new Forward();
		session = request.getSession();
		String m_r_id = (String) session.getAttribute("id");
		String kind = (String) session.getAttribute("kind");
		String m_s_id = request.getParameter("m_s_id");
		String m_title = request.getParameter("m_title");
		String m_content = request.getParameter("m_content");
		mDao = new MessageDao();
		String nm=mDao.getid(m_s_id);
		if(nm=="메시지 전송 성공") {
		String a = mDao.insertmessagen(m_s_id, m_title, m_content, m_r_id);
		}
			mDao.close();
		fw.setPath("Receivedmessage");
		fw.setRedirect(true);

		return fw;
	}

	public Forward deletemessage() {
		Forward fw = new Forward();
		session = request.getSession();
		String m_r_id = (String) session.getAttribute("id");
		String m_title = request.getParameter("title");
		String m_num = request.getParameter("num");
		mDao = new MessageDao();
		mDao.deletemessage(m_r_id, m_title, m_num);
		mDao.close();
		fw.setPath("Receivedmessage");
		fw.setRedirect(true);
		return fw;
	}

	public String getid() {
		String id = request.getParameter("sendid");
		mDao = new MessageDao();
		String a = mDao.getid(id);

		mDao.close();

		return a;
	}

	public Forward loadsmlist() {
		Forward fw = new Forward();
		session = request.getSession();
		String id = (String) session.getAttribute("id");
		mDao = new MessageDao();
		List<MessageBean> MessageBean = mDao.sendmessages(id);
		String jsonobj = null;
		mDao.close();
		if (MessageBean != null) {
			jsonobj = new Gson().toJson(MessageBean);
			request.setAttribute("smlist", jsonobj);

			fw.setPath("Receivedmessage");
			fw.setRedirect(false);
		}

		return fw;
	}
}
