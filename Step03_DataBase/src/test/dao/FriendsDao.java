package test.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import test.dto.FriendsDto;
import test.dto.MemberDto;
import test.util.DBConnect;

public class FriendsDao {
	private static FriendsDao dao;
	private FriendsDao() {}
	public static FriendsDao getInstance() {
		if(dao==null) {
			dao= new FriendsDao();
		}
		return dao;
	}
	
	// 친구 정보를 수정 반영하는 메소드
	public boolean update(FriendsDto dto) {
		Connection conn= null;
		PreparedStatement pstmt= null;
		boolean isSuccess= false;
		try {
			conn= new DBConnect().getConn();
			String sql="UPDATE friends SET name=?,phone=? WHERE num=?";
			pstmt= conn.prepareStatement(sql);
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getPhone());
			pstmt.setInt(3, dto.getNum());
			
			int flag= pstmt.executeUpdate();
			if(flag>0) {
				isSuccess= true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(conn!=null)conn.close();
				if(pstmt!=null)pstmt.close();
			}catch(Exception e) {}	
		}
		return isSuccess;
	}
	
	// 친구 한 명의 정보를 (셀렉트해서) 리턴해주는 메소드
	public FriendsDto getData(int num) {
		Connection conn= null;
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		FriendsDto dto= null;
		try {
			conn= new DBConnect().getConn();
			String sql= "SELECT name,phone,regdate FROM friends WHERE num=?";
			pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs= pstmt.executeQuery();
			if(rs.next()) {
				String name= rs.getString("name");
				String phone= rs.getString("phone");
				String regdate= rs.getString("regdate");
				dto= new FriendsDto(num,name,phone,regdate);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(conn!=null)conn.close();
				if(pstmt!=null)pstmt.close();
				if(rs!=null)rs.close();
			}catch(Exception e) {}	
		}
		return dto;
	}
	
	// 친구 추가 메소드
	public boolean insert(FriendsDto dto) {
		Connection conn= null;
		PreparedStatement pstmt= null;
		boolean isSuccess= false;
		try {
			conn= new DBConnect().getConn();
			String sql= "INSERT INTO friends (num,name,phone,regdate) VALUES(friends_seq.NEXTVAL,?,?,SYSDATE)";
			pstmt= conn.prepareStatement(sql);
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getPhone());
			
			int flag= pstmt.executeUpdate();
			if(flag>0) {
				isSuccess= true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(conn!=null)conn.close();
				if(pstmt!=null)pstmt.close();
			}catch(Exception e) {}
		}
		return isSuccess;
	}
	
	// 친구 삭제 메소드
	public boolean delete(int num) {
		Connection conn= null;
		PreparedStatement pstmt= null;
		boolean isSuccess= false;
		try {
			conn= new DBConnect().getConn();
			String sql= "DELETE FROM friends WHERE num=?";
			pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			int flag= pstmt.executeUpdate();
			if(flag>0) {
				isSuccess= true;
			}	
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(conn!=null)conn.close();
				if(pstmt!=null)pstmt.close();
			}catch(Exception e) {}
		}
		return isSuccess;
	}
	
	public List<FriendsDto> getList(){
		Connection conn= null;
		PreparedStatement pstmt= null;
		ResultSet rs= null;
		List<FriendsDto> list= new ArrayList<>();
		try {
			conn= new DBConnect().getConn();
			String sql= "SELECT num,name,phone,regdate FROM friends ORDER BY num DESC";
			pstmt= conn.prepareStatement(sql);
			rs= pstmt.executeQuery();
			while(rs.next()) {
				int num= rs.getInt("num");
				String name= rs.getString("name");
				String phone= rs.getString("phone");
				String regdate= rs.getString("regdate");
				
				FriendsDto dto= new FriendsDto(num,name,phone,regdate);
				list.add(dto);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(conn!=null)conn.close();
				if(pstmt!=null)pstmt.close();
				if(rs!=null)rs.close();
			}catch(Exception e) {}
		}
		return list;
	}
}
