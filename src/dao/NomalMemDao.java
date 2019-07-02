package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.NomalMember;

public class NomalMemDao {
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	ResultSet rs1;

	public NomalMemDao() {
		System.out.println("nomalmemdao con");
		con = JdbcUtil.getConnection();
		System.out.println("connection success");
	}

	public void close() {
		JdbcUtil.close(rs, pstmt, con);
	}

	public String getIdCheck1(String id) {
		String sql = "SELECT COUNT(N_ID) AS CNT FROM NOMALMEMBERJOIN WHERE N_ID=?";
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

	public boolean insertNomalJoin1(NomalMember nmIn) {
		String sql = "INSERT INTO NOMALMEMBERJOIN VALUES(?,?,?,?,?,?,?)";
		String sql1 = "INSERT INTO PROFILES VALUES(?,null)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, nmIn.getN_Id());
			pstmt.setNString(2, nmIn.getN_Pw());
			pstmt.setNString(3, nmIn.getN_Name());
			pstmt.setNString(4, nmIn.getN_Phone());
			pstmt.setNString(5, nmIn.getN_Email());
			pstmt.setNString(6, nmIn.getN_sex());
			pstmt.setNString(7, nmIn.getN_Kind());

			int result = pstmt.executeUpdate();

			pstmt = con.prepareStatement(sql1);
			pstmt.setNString(1, nmIn.getN_Id());
			int result1 = pstmt.executeUpdate();

			if (result != 0 && result1 != 0) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println("insertNomalJoin1 fail");
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

	public NomalMember getprofile(String id) {
		String sql = "SELECT * FROM NOMALMEMBERJOIN WHERE N_ID=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				NomalMember nm = new NomalMember();
				nm.setN_Id(rs.getString("N_ID"));
				nm.setN_Pw(rs.getString("N_PW"));
				nm.setN_Name(rs.getString("N_NAME"));
				nm.setN_Phone(rs.getString("N_Phone"));
				nm.setN_Email(rs.getString("N_EMAIL"));
				nm.setN_sex(rs.getString("N_SEX"));
				return nm;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public NomalMember getIdPwCkeck1(String id) {
		String sql = "SELECT * FROM NOMALMEMBERJOIN WHERE N_ID=?";
		String sql1 = "SELECT * FROM COMMEMBERJOIN WHERE C_ID=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			pstmt = con.prepareStatement(sql1);
			pstmt.setString(1, id);
			rs1 = pstmt.executeQuery();
			
			
			if (rs.next()) {
				NomalMember nm = new NomalMember();
				nm.setN_Id(rs.getString("N_ID"));
				nm.setN_Pw(rs.getString("N_PW"));
				nm.setN_Kind(rs.getString("N_KIND"));
				return nm;
			}
			if(rs1.next()){
				NomalMember nm = new NomalMember();
				nm.setN_Id(rs1.getString("C_ID"));
				nm.setN_Pw(rs1.getString("C_PW"));
				nm.setN_Kind(rs1.getString("C_KIND"));
				return nm;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public NomalMember getIdFind1(String email) {
		System.out.println("getIdFind1"+email);
		String sql = "SELECT N_ID FROM NOMALMEMBERJOIN WHERE N_EMAIL=?";
		String sql1 = "SELECT C_ID FROM COMMEMBERJOIN WHERE C_EMAIL=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			
			pstmt = con.prepareStatement(sql1);
			pstmt.setString(1, email);
			rs1 = pstmt.executeQuery();
			
			
			if (rs.next()) {
				NomalMember nm = new NomalMember();
				nm.setN_Id(rs.getString("N_ID"));
				return nm;
			}
			if(rs1.next()){
				NomalMember nm = new NomalMember();
				nm.setN_Id(rs1.getString("C_ID"));
				return nm;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public NomalMember getPwFind1(String id, String email) {
		System.out.println("getIdFind1"+email);
		String sql = "SELECT N_PW FROM NOMALMEMBERJOIN WHERE N_ID=? AND N_EMAIL=?";
		String sql1 = "SELECT C_PW FROM COMMEMBERJOIN WHERE C_ID=? AND C_EMAIL=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, email);
			rs = pstmt.executeQuery();
			
			pstmt = con.prepareStatement(sql1);
			pstmt.setString(1, id);
			pstmt.setString(2, email);
			rs1 = pstmt.executeQuery();
			
			
			if (rs.next()) {
				NomalMember nm = new NomalMember();
				nm.setN_Pw(rs.getString("N_PW"));
				return nm;
			}
			if(rs1.next()){
				NomalMember nm = new NomalMember();
				nm.setN_Pw(rs1.getString("C_PW"));
				return nm;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
