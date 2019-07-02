package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Portfolio;

public class PortfolioDao {
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;

	public PortfolioDao() {
		con = JdbcUtil.getConnection();
		System.out.println("연결성공");
	}

	public void close() {
		JdbcUtil.close(rs, pstmt, con);
	}

	public boolean portFolioSave(Portfolio pf) {
		String sql = "INSERT INTO PORTFOLIO VALUES(PORT_SEQ.NEXTVAL,?,?,SYSDATE,?,?)";
		int result = 0;

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, pf.getP_Id());
			pstmt.setNString(2, pf.getP_Content());
			pstmt.setNString(3, pf.getP_Title());
			pstmt.setNString(4, pf.getP_Thumbnail());			
			result = pstmt.executeUpdate();

			if (result != 0)
				return true;
		} catch (SQLException e) {
			System.out.println("포트제작실패");
			e.printStackTrace();
		} // 파씽1번
		return false;
	}

	public List<Portfolio> newPortFolio() {
		String sql = "SELECT * FROM PORTFOLIO ORDER BY P_NUMBER DESC";

		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			List<Portfolio> pList = new ArrayList<>();
			while (rs.next()) {
				Portfolio p = new Portfolio();
				p.setP_Number(rs.getNString("P_NUMBER"));
				p.setP_Id(rs.getNString("P_ID"));
				p.setP_Content(rs.getNString("P_TEXT"));
				p.setP_Date(rs.getNString("P_DATE"));
				p.setP_Title(rs.getNString("P_TITLE"));
				p.setP_Thumbnail(rs.getNString("P_THUMBNAIL"));
				pList.add(p);
			}
			return pList;

		} catch (SQLException e) {
			System.out.println("포트폴리오가져오기실패");
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Portfolio> noMalBestPort() {
		String sql = "SELECT P.P_NUMBER,P.P_ID,P.P_TEXT,P.P_DATE,P.P_TITLE,P.P_THUMBNAIL,NVL(N.COUNT,0) AS C FROM PORTFOLIO P LEFT OUTER JOIN (SELECT L_N_NUM,COUNT(*) AS COUNT FROM N_LIKE GROUP BY L_N_NUM) N ON P.P_NUMBER=N.L_N_NUM ORDER BY C DESC";

		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			List<Portfolio> pList = new ArrayList<>();
			while (rs.next()) {
				Portfolio p = new Portfolio();
				p.setP_Number(rs.getNString("P_NUMBER"));
				p.setP_Id(rs.getNString("P_ID"));
				p.setP_Content(rs.getNString("P_TEXT"));
				p.setP_Date(rs.getNString("P_DATE"));
				p.setP_Title(rs.getNString("P_TITLE"));
				p.setP_Thumbnail(rs.getNString("P_THUMBNAIL"));
				pList.add(p);
			}
			return pList;

		} catch (SQLException e) {
			System.out.println("포트폴리오가져오기실패");
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Portfolio> comBestPort() {
		String sql ="SELECT P.P_NUMBER,P.P_ID,P.P_TEXT,P.P_DATE,P.P_TITLE,P.P_THUMBNAIL,NVL(N.COUNT,0) AS C  \r\n" + 
				"FROM PORTFOLIO P LEFT OUTER JOIN (SELECT L_C_NUM,COUNT(*) AS COUNT FROM C_LIKE GROUP BY L_C_NUM) N\r\n" + 
				"ON P.P_NUMBER=N.L_C_NUM ORDER BY C DESC";
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			List<Portfolio> pList = new ArrayList<>();
			while (rs.next()) {
				Portfolio p = new Portfolio();
				p.setP_Number(rs.getNString("P_NUMBER"));
				p.setP_Id(rs.getNString("P_ID"));
				p.setP_Content(rs.getNString("P_TEXT"));
				p.setP_Date(rs.getNString("P_DATE"));
				p.setP_Title(rs.getNString("P_TITLE"));
				p.setP_Thumbnail(rs.getNString("P_THUMBNAIL"));
				pList.add(p);
			}
			return pList;

		} catch (SQLException e) {
			System.out.println("포트폴리오가져오기실패");
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Portfolio> profilePortfolio(String id) {
		String sql = "SELECT * FROM PORTFOLIO WHERE P_ID=?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1,id);
			rs = pstmt.executeQuery();
			List<Portfolio> pList = new ArrayList<>();
			while (rs.next()) {
				Portfolio p = new Portfolio();
				p.setP_Number(rs.getNString("P_NUMBER"));
				p.setP_Id(rs.getNString("P_ID"));
				p.setP_Content(rs.getNString("P_TEXT"));
				p.setP_Date(rs.getNString("P_DATE"));
				p.setP_Title(rs.getNString("P_TITLE"));
				p.setP_Thumbnail(rs.getNString("P_THUMBNAIL"));
				pList.add(p);
			}
			return pList;

		} catch (SQLException e) {
			System.out.println("포트폴리오가져오기실패");
			e.printStackTrace();
		}
		return null;
	}

	public Portfolio getajaxDetail(String p_Thumbnail) {
		String sql = "SELECT * FROM PORTFOLIO WHERE P_THUMBNAIL=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1,p_Thumbnail);
			rs = pstmt.executeQuery();
			Portfolio p=null;
			if(rs.next()) {
				p = new Portfolio();
				p.setP_Number(rs.getNString("P_NUMBER"));
				p.setP_Id(rs.getNString("P_ID"));
				p.setP_Title(rs.getString("P_TITLE"));
				p.setP_Content(rs.getString("P_TEXT"));
				p.setP_Date(rs.getString("P_DATE"));
				p.setP_Thumbnail(rs.getString("P_THUMBNAIL"));
			}
			return p;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean nomLike(String p_Number, String p_Id) {
		String sql = "INSERT INTO N_LIKE VALUES(?,?)";
		int result = 0;

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, p_Number);
			pstmt.setNString(2, p_Id);
			result = pstmt.executeUpdate();

			if (result != 0)
				return true;
		} catch (SQLException e) {
			System.out.println("좋아요 인설트 실패");
			e.printStackTrace();
		} // 파씽1번
		return false;
	}

	public String nomLikeCount(String p_Number) {
		String sql= "SELECT COUNT(*) FROM N_LIKE WHERE L_N_NUM=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, p_Number);
			rs = pstmt.executeQuery();
			String nCount=null;
			if(rs.next()) {
				nCount=String.valueOf(rs.getInt("COUNT(*)"));
			}
			return nCount;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getajaxDetailCount(String p_Number) {
		String sql= "SELECT COUNT(*) FROM N_LIKE WHERE L_N_NUM=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, p_Number);
			rs = pstmt.executeQuery();
			String nCount=null;
			if(rs.next()) {
				nCount=String.valueOf(rs.getInt("COUNT(*)"));
			}
			return nCount;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean comLike(String p_Number, String p_Id) {
		String sql = "INSERT INTO C_LIKE VALUES(?,?)";
		int result = 0;

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, p_Number);
			pstmt.setNString(2, p_Id);
			result = pstmt.executeUpdate();

			if (result != 0)
				return true;
		} catch (SQLException e) {
			System.out.println("좋아요 인설트 실패");
			e.printStackTrace();
		} // 파씽1번
		return false;
	}

	public String comLikeCount(String p_Number) {
		String sql= "SELECT COUNT(*) FROM C_LIKE WHERE L_C_NUM=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, p_Number);
			rs = pstmt.executeQuery();
			String nCount=null;
			if(rs.next()) {
				nCount=String.valueOf(rs.getInt("COUNT(*)"));
			}
			return nCount;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getajaxDetailComCount(String p_Number) {
		String sql= "SELECT COUNT(*) FROM C_LIKE WHERE L_C_NUM=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, p_Number);
			rs = pstmt.executeQuery();
			String nCount=null;
			if(rs.next()) {
				nCount=String.valueOf(rs.getInt("COUNT(*)"));
			}
			return nCount;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean portDelete(String thumbnail) {
		String sql = "DELETE FROM PORTFOLIO WHERE P_THUMBNAIL=?";
		int result = 0;

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, thumbnail);
			result = pstmt.executeUpdate();

			if (result != 0)
				return true;
		} catch (SQLException e) {
			System.out.println("삭제 실패");
			e.printStackTrace();
		} // 파씽1번
		return false;

	}

	public boolean cLikeDelete(String number) {
		String sql = "DELETE FROM C_LIKE WHERE L_C_NUM=?";
		int result = 0;

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, number);
			result = pstmt.executeUpdate();

			if (result != 0)
				return true;
		} catch (SQLException e) {
			System.out.println("삭제 실패");
			e.printStackTrace();
		} // 파씽1번
		return false;

	}

	public boolean nLikeDelete(String number) {
		String sql = "DELETE FROM N_LIKE WHERE L_N_NUM=?";
		int result = 0;

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, number);
			result = pstmt.executeUpdate();

			if (result != 0)
				return true;
		} catch (SQLException e) {
			System.out.println("삭제 실패");
			e.printStackTrace();
		} // 파씽1번
		return false;

	}
}
