package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.ComMember;
import bean.NomalMember;

public class ComMemDao {
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;

	public ComMemDao() {
		System.out.println("commemdao con");
		con = JdbcUtil.getConnection();
		System.out.println("connection success");
	}

	public void close() {
		JdbcUtil.close(rs, pstmt, con);
	}

	public String getIdCheck1(String id) {
		String sql = "SELECT COUNT(C_ID) AS CNT FROM COMMEMBERJOIN WHERE C_ID=?";
		String count = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				count = rs.getNString("CNT");
				System.out.println("count : " + count);
			}
			return count;
		} catch (SQLException e) {
			System.out.println("idcheck fail");
			e.printStackTrace();
		}
		return null;
	}

	public boolean insertComJoin1(ComMember nmIn) {
		String sql = "INSERT INTO COMMEMBERJOIN VALUES(?,?,?,?,?,?,?,?,?)";
		try {
			
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, nmIn.getC_Id());
			pstmt.setNString(2, nmIn.getC_Pw());
			pstmt.setNString(3, nmIn.getC_Name());
			pstmt.setNString(4, nmIn.getC_Bnum());
			pstmt.setNString(5, nmIn.getC_Rnum());
			pstmt.setNString(6, nmIn.getC_Cname());
			pstmt.setNString(7, nmIn.getC_Email());
			pstmt.setNString(8, nmIn.getC_Phone());
			pstmt.setNString(9, nmIn.getC_Kind());

			int result = pstmt.executeUpdate();

			if (result != 0) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println("insertComJoin1 fail");
			e.printStackTrace();
		}
		return false;
	}

	public String getEmailCkeck1(String email) {
		String sql = "SELECT COUNT(N_EMAIL) AS CNT FROM NOMALMEMBERJOIN WHERE N_EMAIL=?";
		String count = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, email);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				count = rs.getNString("CNT");
				System.out.println("count : " + count);
			}
			return count;
		} catch (SQLException e) {
			System.out.println("emailcheck fail");
			e.printStackTrace();
		}
		return null;
	}

}
