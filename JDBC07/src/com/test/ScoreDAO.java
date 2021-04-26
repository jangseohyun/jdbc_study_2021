/*==================
   ScoreDAO.java   
==================*/
// JDBC07


package com.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.util.DBConn;


public class ScoreDAO
{
	private Connection conn;
	
	// 데이터 연결
	public Connection connection() throws ClassNotFoundException, SQLException
	{
		conn = DBConn.getConnection();
		
		return conn;
	}
	
	// 데이터 입력
	public int add(ScoreDTO dto) throws SQLException
	{
		int result = 0;
		
		String sql = "INSERT INTO TBL_SCORE(SID, NAME, KOR, ENG, MAT) VALUES(SCORESEQ.NEXTVAL,?,?,?,?)";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, dto.getName());
		pstmt.setInt(2, dto.getKor());
		pstmt.setInt(3, dto.getEng());
		pstmt.setInt(4, dto.getMat());
		
		result = pstmt.executeUpdate();
		
		pstmt.close();
		
		return result;
	}
	
	// 전체 리스트 출력
	public ArrayList<ScoreDTO> lists() throws SQLException
	{
		ArrayList<ScoreDTO> result = new ArrayList<ScoreDTO>();
		
		String sql = "SELECT SID, NAME, KOR, ENG, MAT, (KOR+ENG+MAT) AS TOT, (KOR+ENG+MAT)/3.0 AS AVG\r\n" + 
				     ", RANK() OVER(ORDER BY (KOR+ENG+MAT) DESC) AS RANK\r\n" + 
				     "FROM TBL_SCORE ORDER BY SID ASC";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		ResultSet rs = pstmt.executeQuery();
		
		while (rs.next())
		{
			ScoreDTO dto = new ScoreDTO();
			
			dto.setSid(rs.getString("SID"));
			dto.setName(rs.getString("NAME"));
			dto.setKor(rs.getInt("KOR"));
			dto.setEng(rs.getInt("ENG"));
			dto.setMat(rs.getInt("MAT"));
			dto.setTot(rs.getInt("TOT"));
			dto.setAvg(rs.getDouble("AVG"));
			dto.setRank(rs.getInt("RANK"));
			
			result.add(dto);
		}
		
		pstmt.close();
		rs.close();
		
		return result;
	}
	
	// 검색
	public ArrayList<ScoreDTO> lists(String key, String value) throws SQLException
	{
		ArrayList<ScoreDTO> result = new ArrayList<ScoreDTO>();
		
		String sql = "";

		if (key.equals("SID"))
			sql = "SELECT * FROM (SELECT SID,NAME,KOR,ENG,MAT,KOR+ENG+MAT AS TOT,(KOR+ENG+MAT)/3.0 AS AVG, RANK() OVER(ORDER BY (KOR+ENG+MAT) DESC) AS RANK FROM TBL_SCORE) WHERE SID = ? ORDER BY SID ASC";
		else if (key.equals("NAME"))
			sql = "SELECT * FROM (SELECT SID,NAME,KOR,ENG,MAT,KOR+ENG+MAT AS TOT,(KOR+ENG+MAT)/3.0 AS AVG, RANK() OVER(ORDER BY (KOR+ENG+MAT) DESC) AS RANK FROM TBL_SCORE) WHERE NAME = ? ORDER BY SID ASC";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1,value);
		
		ResultSet rs = pstmt.executeQuery();
		
		while (rs.next())
		{
			ScoreDTO dto = new ScoreDTO();
			
			dto.setSid(rs.getString("SID"));
			dto.setName(rs.getString("NAME"));
			dto.setKor(rs.getInt("KOR"));
			dto.setEng(rs.getInt("ENG"));
			dto.setMat(rs.getInt("MAT"));
			dto.setTot(rs.getInt("TOT"));
			dto.setAvg(rs.getDouble("AVG"));
			dto.setRank(rs.getInt("RANK"));
			
			result.add(dto);
		}
		
		pstmt.close();
		rs.close();
		
		return result;
	}
	
	// 데이터 수
	public int count() throws SQLException
	{
		int result = 0;
		
		String sql = "SELECT COUNT(*) AS COUNT FROM TBL_SCORE";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		ResultSet rs = pstmt.executeQuery();
		
		while (rs.next())
		{
			result = rs.getInt("COUNT");
		}
		
		rs.close();
		pstmt.close();
		
		return result;
	}
	
	// 특정 데이터 수
	public int count(String key, String value) throws SQLException
	{
		int result = 0;
		String sql = "";
		
		if (key.equals("SID"))
			sql = "SELECT COUNT(*) AS COUNT FROM TBL_SCORE WHERE SID = ?";
		else if (key.equals("NAME"))
			sql = "SELECT COUNT(*) AS COUNT FROM TBL_SCORE WHERE NAME = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1,value);
				
		ResultSet rs = pstmt.executeQuery();
		
		while (rs.next())
		{
			result = rs.getInt("COUNT");
		}
		
		rs.close();
		pstmt.close();
		
		return result;
	}
	
	// 데이터 수정
	public int update(ScoreDTO dto) throws SQLException
	{
		int result = 0;
		
		String sql = "UPDATE TBL_SCORE SET NAME = ?, KOR = ?, ENG = ?, MAT = ? WHERE SID = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, dto.getName());
		pstmt.setInt(2, dto.getKor());
		pstmt.setInt(3, dto.getEng());
		pstmt.setInt(4, dto.getMat());
		pstmt.setString(5, dto.getSid());
		
		result = pstmt.executeUpdate();
		
		return result;
	}
	
	// 데이터 삭제
	public int delete(String sid) throws SQLException
	{
		int result = 0;
		
		String sql = "DELETE FROM TBL_SCORE WHERE SID = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, sid);
		
		result = pstmt.executeUpdate();
		
		return result;
	}
	
	// 데이터 연결 종료
	public void close() throws SQLException
	{
		DBConn.close();
	}
}
