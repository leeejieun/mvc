package member;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import config.DBCP;

public class memberDAO {

	private Connection conn;
	private PreparedStatement pstmt;
	
	// DB 수정
	public void updateMember(memberDTO dto) {
		try {
			conn = DBCP.dbConn();
			
				String id = dto.getId();
				String pwd = dto.getPwd();
				String name = dto.getName();
				String email = dto.getEmail();
				
				String sql = "update t_member set pwd=?,name=?,email=? where id=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, pwd);
				pstmt.setString(2, name);
				pstmt.setString(3, email);
				pstmt.setString(4, id);
				pstmt.executeUpdate();
				
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null)pstmt.close();
				if(conn!=null)conn.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public memberDTO getMember(String id) {
		ResultSet rs = null;
		memberDTO dto = null;
		
		try {
			conn = DBCP.dbConn();
			String sql = "select * from t_member where id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String _id = rs.getString("id");
				String _pwd = rs.getString("pwd");
				String _name = rs.getString("name");
				String _email = rs.getString("email");
				Date _joinDate = rs.getDate("joinDate");
				
				dto = new memberDTO();
				dto.setId(_id);
				dto.setPwd(_pwd);
				dto.setName(_name);
				dto.setEmail(_email);
				
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null)pstmt.close();
				if(conn!=null)conn.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return dto;
	}
	
	// DB에 자료 추가
	public void addMember(memberDTO dto) {
		try {
			conn = DBCP.dbConn();
			
			String id = dto.getId();
			String pwd = dto.getPwd();
			String name = dto.getName();
			String email = dto.getEmail();
			
			String sql  = "insert into t_member (id,pwd,name,email) values(?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,id);
			pstmt.setString(2, pwd);
			pstmt.setString(3, name);
			pstmt.setString(4, email);
			
			pstmt.executeUpdate(); //삽입, 수정, 삭제 할때는 전부 이거를 씀. 실제 sql문을 실행하는 부분임.
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null)pstmt.close();
				if(conn!=null)conn.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	//DB 자료 삭제
	public void delMember(String id) {
		try {
			conn=DBCP.dbConn();
			String sql = "delete from t_member where id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn !=null) conn.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	//회원목록
	public List listMembers() {
		ResultSet rs = null;
		List list = new ArrayList<memberDTO>();
		
		try {
			conn = DBCP.dbConn();
			String sql  = "select * from t_member";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String id = rs.getString("id");
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Date joinDate = rs.getDate("joinDate");
				
				//DTO개체에 보관(rs 꾸러미 보관)
				memberDTO dto = new memberDTO();
				dto.setId(id);
				dto.setPwd(pwd);
				dto.setName(name);
				dto.setEmail(email);
				dto.setJoinDate(joinDate);
				
				//dto를 list배열 객체에 보관
				list.add(dto);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn!=null) conn.close();
			}catch (Exception e2) {
				
			}
		}
		return list;
	}
}
