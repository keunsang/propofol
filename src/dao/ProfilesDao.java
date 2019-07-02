package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfilesDao {
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;

	public ProfilesDao() {
		con = JdbcUtil.getConnection();
		System.out.println("Profiledao connection");

	}

	public void close() {
		JdbcUtil.close(rs, pstmt, con);
	}

	public void insertprofiles(String id, String etc) {
		String sql = "UPDATE PROFILES SET P_PROFILE = ? WHERE N_ID=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, etc);
			pstmt.setString(2, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("프로필 입력 실패");
			e.printStackTrace();
		}

	}

	public String getetc(String id) {
		String sql = "SELECT P_PROFILE FROM PROFILES WHERE N_ID= ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String a = rs.getNString("P_PROFILE");
				return a;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
}
