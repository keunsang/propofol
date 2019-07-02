package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.MessageBean;

public class MessageDao {
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;

	public MessageDao() {
		con = JdbcUtil.getConnection();
		System.out.println("연결성공");
	}

	public void close() {
		JdbcUtil.close(rs, pstmt, con);
	}

	public List<MessageBean> rmessages(String m_r_id) {
		String SQL = "SELECT * FROM MESSAGE WHERE M_R_ID = ? ORDER BY m_s_time desc";
		List<MessageBean> mlist = new ArrayList<MessageBean>();
		try {
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, m_r_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				MessageBean message = new MessageBean();
				message.setM_num(rs.getString("m_num"));
				message.setM_title(rs.getString("m_title"));
				message.setM_content(rs.getString("m_content"));
				message.setM_s_time(rs.getString("m_s_time"));
				message.setM_s_id(rs.getString("m_s_id"));
				message.setM_r_id(rs.getString("m_r_id"));
				mlist.add(message);
			}
			return mlist;
		} catch (SQLException e) {
			System.out.println("DB 데이터 입력 실패");
			e.printStackTrace();
		}
		return null;
	}

	public MessageBean getsmcontent(String id, String m_title) {
		String SQL = "SELECT * FROM MESSAGE WHERE M_R_ID=? AND M_TITLE=?";
		try {
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, id);
			pstmt.setString(2, m_title);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				MessageBean message = new MessageBean();
				message.setM_content(rs.getString("M_CONTENT"));
				message.setM_r_id((rs.getString("M_R_ID")));
				message.setM_s_id((rs.getString("M_S_ID")));
				message.setM_s_time(rs.getString("M_S_TIME"));
				message.setM_title(rs.getString("M_TITLE"));
				return message;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String insertmessagen(String m_s_id, String m_title, String m_content, String m_r_id) {
		String sql = "INSERT INTO MESSAGE VALUES(MSG_SEQ.nextval,?,?,SYSDATE,?,?)";
		String a="";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, m_title);
			pstmt.setString(2, m_content);
			pstmt.setString(3, m_r_id);
			pstmt.setString(4, m_s_id);
			int rs = pstmt.executeUpdate();
			if (rs != 0) {
				System.out.println("메시지 보내기 성공");
				a="메시지 보내기 성공";
				return a;
			} else {
				System.out.println("메시지 보내기 실패");
				return null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public boolean deletemessage(String m_r_id, String m_title, String m_num) {
		String sql = "DELETE MESSAGE WHERE M_R_ID=? AND M_TITLE=? AND M_NUM=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, m_r_id);
			pstmt.setString(2, m_title);
			pstmt.setString(3, m_num);
			pstmt.executeQuery();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public String getid(String id) {
		String sql = "select N_ID,C_ID from commemberjoin,nomalmemberjoin where N_ID=? OR c_id=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return "메시지 전송 성공";
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
			return "메시지 전송 실패";
		}

		return "메시지 전송 실패";
	}

	public List<MessageBean> sendmessages(String id) {
		String SQL = "SELECT * FROM MESSAGE WHERE M_S_ID = ? ORDER BY m_s_time desc";
		List<MessageBean> mlist = new ArrayList<MessageBean>();
		try {
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				MessageBean message = new MessageBean();
				message.setM_num(rs.getString("m_num"));
				message.setM_title(rs.getString("m_title"));
				message.setM_content(rs.getString("m_content"));
				message.setM_s_time(rs.getString("m_s_time"));
				message.setM_s_id(rs.getString("m_s_id"));
				message.setM_r_id(rs.getString("m_r_id"));
				mlist.add(message);
			}
			return mlist;
		} catch (SQLException e) {
			System.out.println("DB 데이터 입력 실패");
			e.printStackTrace();
		}
		return null;
	}

	public MessageBean getrmcontent(String id, String m_title) {
		String SQL = "SELECT * FROM MESSAGE WHERE M_S_ID=? AND M_TITLE=?";
		try {
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, id);
			pstmt.setString(2, m_title);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				MessageBean message = new MessageBean();
				message.setM_content(rs.getString("M_CONTENT"));
				message.setM_s_id((rs.getString("M_S_ID")));
				message.setM_r_id((rs.getString("M_R_ID")));
				message.setM_s_time(rs.getString("M_S_TIME"));
				message.setM_title(rs.getString("M_TITLE"));
				return message;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}