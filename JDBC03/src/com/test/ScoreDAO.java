/*==================
   ScoreDAO.java   
==================*/


package com.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.util.DBConn;


public class ScoreDAO
{
	private Connection conn;

	
	public ScoreDAO()
	{
		conn = DBConn.getConnection();
	}
	
	public int add(ScoreDTO dto) throws SQLException
	{
		int result = 0;
		
		Statement stmt = conn.createStatement();
		
		String sql = String.format("INSERT INTO TBL_SCORE(SID,NAME,KOR,ENG,MAT) VALUES(SCORESEQ.NEXTVAL,'%s',%d,%d,%d)",dto.getName(),dto.getKor(),dto.getEng(),dto.getMat());
		
		result = stmt.executeUpdate(sql);
		
		stmt.close();
		
		return result;
	}
	
	public int count() throws SQLException
	{
		int result = 0;
		
		Statement stmt = conn.createStatement();
		
		String sql = "SELECT COUNT(*) FROM TBL_SCORE";
		
		ResultSet rs = stmt.executeQuery(sql);
		
		while (rs.next())
		{
			result = rs.getInt("COUNT(*)");
		}
		
		rs.close();
		stmt.close();
		
		return result;
	}
	
	public ArrayList<ScoreDTO> list() throws SQLException
	{
		ArrayList<ScoreDTO> result = new ArrayList<ScoreDTO>();
		
		Statement stmt = conn.createStatement();
		
		String sql = "SELECT SID,NAME,KOR,ENG,MAT FROM TBL_SCORE";
		
		ResultSet rs = stmt.executeQuery(sql);
		
		while (rs.next())
		{
			ScoreDTO dto = new ScoreDTO();
			
			dto.setSid(rs.getString("SID"));
			dto.setName(rs.getString("NAME"));
			dto.setKor(rs.getInt("KOR"));
			dto.setEng(rs.getInt("ENG"));
			dto.setMat(rs.getInt("MAT"));
			
			result.add(dto);
		}
		
		rs.close();
		stmt.close();
		
		return result;
	}
}
