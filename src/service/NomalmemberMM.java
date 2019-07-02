package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import bean.Forward;
import bean.NomalMember;
import bean.Portfolio;
import dao.NomalMemDao;
import dao.PortfolioDao;

/*import bean.Forward;
import dao.ProductDao;*/

public class NomalmemberMM {
	HttpServletRequest request;
	HttpServletResponse response;
	NomalMemDao nDao;
	PortfolioDao pDao;
	HttpSession session;
	Forward fw = null;

	public NomalmemberMM(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}

	public String getIdCheck() {
		String id = request.getParameter("userId");
		System.out.println("id : " + id);
		nDao = new NomalMemDao();
		String nm = nDao.getIdCheck1(id);
		nDao.close();
		String jsonObj = null;

		if (nm != null) {
			jsonObj = new Gson().toJson(nm);
		}
		return jsonObj; // jsonObj
	}

	public Forward insertNomalJoin() {
		NomalMember nmIn = new NomalMember();
		String test = request.getParameter("id");
		System.out.println("insert : "+test);
		
		nmIn.setN_Id(request.getParameter("id"));
		nmIn.setN_Pw(request.getParameter("pw"));
		nmIn.setN_Name(request.getParameter("name"));
		nmIn.setN_Email(request.getParameter("email"));
		nmIn.setN_Phone(request.getParameter("tel"));
		nmIn.setN_sex(request.getParameter("sex"));
		nmIn.setN_Kind("N");
		
		
		nDao = new NomalMemDao();
		
		if (nDao.insertNomalJoin1(nmIn)) { 
			
			nDao.close();
			
			fw = new Forward();
			fw.setPath("index.jsp");
			fw.setRedirect(false);
			
			System.out.println("insertNomalJoin success");
			return fw;
		} else {
			System.out.println("insertNomalJoin fail");
			nDao.close();
		}
		
		return null;
	}

	public String getEmailCkeck() {
		String email = request.getParameter("userEmail");
		System.out.println("email : " + email);
		nDao = new NomalMemDao();
		String nm = nDao.getEmailCkeck1(email);
		nDao.close();
		String jsonObj = null;

		if (nm != null) {
			jsonObj = new Gson().toJson(nm);
		}
		return jsonObj; // jsonObj
	}

	public String getprofile() {
		session=request.getSession();
		String id = (String) session.getAttribute("id");
		nDao = new NomalMemDao();
		NomalMember nm= nDao.getprofile(id);
		nDao.close();
		String jsonObj = null;
		
		if(nm!= null) {
			jsonObj = new Gson().toJson(nm);
		}
		request.setAttribute("profile", jsonObj);
		
		System.out.println(jsonObj);
		return jsonObj;
	}

	public String getIdPwCheck() {
		String id = request.getParameter("userId");
		System.out.println("loginId : " + id);
		nDao = new NomalMemDao();
		NomalMember nm = nDao.getIdPwCkeck1(id);
		session = request.getSession();
		session.setAttribute("id", nm.getN_Id());
		System.out.println("sessionid = "+nm.getN_Id());
		session.setAttribute("kind", nm.getN_Kind());
		System.out.println("sessionkind = "+nm.getN_Kind());
		String str = nm.getN_Pw();
		nDao.close();
		String jsonObj = null;

		if (nm != null) {
			jsonObj = new Gson().toJson(str);
		}
		return jsonObj;
	}
		

	public String getIdFind() {
		String email = request.getParameter("finEmail");
		System.out.println("finEamil : " + email);
		nDao = new NomalMemDao();
		NomalMember nm = nDao.getIdFind1(email);
		/*String str = nm.getN_Id();*/
		nDao.close();
		String jsonObj = null;

		if (nm != null) {
			jsonObj = new Gson().toJson(nm);
		}
		return jsonObj; // jsonObj
	}

	public String getPwFind() {
		String id = request.getParameter("userId");
		String email = request.getParameter("userEmail");
		System.out.println("id = "+id+" email = "+email);
		nDao = new NomalMemDao();
		NomalMember nm = nDao.getPwFind1(id,email);
		String pwStr = nm.getN_Pw();
		if(pwStr==null) {
			return null;
		}
		nDao.close();
		String jsonObj = null;

		if (nm != null) {
			jsonObj = new Gson().toJson(nm);
		}
		
		EmailRandNumSend emailPw = new EmailRandNumSend();
		emailPw.setEmailPwSend(pwStr,email);
		
		return jsonObj; // jsonObj
	}

	public String getajaxDetail() {
		String p_Thumbnail=request.getParameter("p_Thumbnail");
		System.out.println("getajaxDetail="+p_Thumbnail);
		pDao= new PortfolioDao();
		Portfolio pf=null;
		pf=pDao.getajaxDetail(p_Thumbnail);
		pDao.close();
		pDao= new PortfolioDao();
		String nCount=pDao.getajaxDetailCount(pf.getP_Number());
		String cCount=pDao.getajaxDetailComCount(pf.getP_Number());
		pf.setP_LikeCount(nCount);
		pf.setP_comLikeCount(cCount);
		pDao.close();
		String jsonObj=null;
		if(pf !=null) {
			jsonObj = new Gson().toJson(pf);
			System.out.println(jsonObj);
		}
		return jsonObj;
	}

	public String nomLike() {
		session=request.getSession();
		String p_Id = (String) session.getAttribute("id");
		String p_Number=request.getParameter("p_Number");
		System.out.println("nomLike number="+p_Number);
		System.out.println("nomLike id="+p_Id);
		pDao= new PortfolioDao();
		
		boolean nL=pDao.nomLike(p_Number,p_Id);
		pDao.close();
		String jsonObj=null;
		if(nL) {
			pDao= new PortfolioDao();
			String nlc=pDao.nomLikeCount(p_Number);
			pDao.close();
			if(nlc!=null)
			{
			jsonObj = new Gson().toJson(nlc);
			System.out.println("nomlike js="+jsonObj);
			}
		}
		return jsonObj;
	}

	public String comLike() {
		session=request.getSession();
		String p_Id = (String) session.getAttribute("id");
		String p_Number=request.getParameter("p_Number");
		System.out.println(p_Number);
		System.out.println(p_Id);
		pDao= new PortfolioDao();
		
		boolean nL=pDao.comLike(p_Number,p_Id);
		pDao.close();
		String jsonObj=null;
		if(nL) {
			pDao= new PortfolioDao();
			String nlc=pDao.comLikeCount(p_Number);
			pDao.close();
			if(nlc!=null)
			{
			jsonObj = new Gson().toJson(nlc);
			System.out.println(jsonObj);
			}
		}
		return jsonObj;
	}
}
