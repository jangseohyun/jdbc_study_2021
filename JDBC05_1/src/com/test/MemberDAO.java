/*===================
   MemberDAO.java   
===================*/
// JDBC05_1


package com.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.util.DBConn;


public class MemberDAO
{
	private static Connection conn;
	
	
	// 데이터베이스 연결 담당 메소드
	public static Connection connection()
	{
		conn = DBConn.getConnection();
		
		return conn;
	}
	
	// 데이터 입력 담당 메소드
	public int input(MemberDTO dto) throws SQLException
	{
		int result = 0;
		
		Statement stmt = conn.createStatement();
		
		String sql = String.format("INSERT INTO TBL_EMP(EMP_ID,EMP_NAME,SSN,IBSADATE,CITY_ID,TEL,BUSEO_ID,JIKWI_ID,BASICPAY,SUDANG) VALUES(EMPSEQ.NEXTVAL,'%s','%s',TO_DATE('%s','YYYY-MM-DD'), (SELECT CITY_ID FROM TBL_CITY WHERE CITY_LOC = '%s'), '%s', (SELECT BUSEO_ID FROM TBL_BUSEO WHERE BUSEO_NAME = '%s'), (SELECT JIKWI_ID FROM TBL_JIKWI WHERE JIKWI_NAME = '%s'), %d, %d)"
                , dto.getName(), dto.getSsn(), dto.getIbsadate(), dto.getCity(), dto.getTel(), dto.getBuseo(), dto.getJikwi(), dto.getBasicpay(), dto.getSudang());
		
		result = stmt.executeUpdate(sql);
		
		stmt.close();
		
		return result;
	}
	
	// 인원 확인 메소드
	public int count() throws SQLException
	{
		int result = 0;
		
		Statement stmt = conn.createStatement();
		
		String sql = "SELECT COUNT(*) AS COUNT FROM TBL_EMP";
		
		ResultSet rs = stmt.executeQuery(sql);
		
		while (rs.next())
		{
			result = rs.getInt("COUNT");
		}
		
		stmt.close();
		rs.close();
			
		return result;
	}
	
	// 특정 조건 인원 확인 메소드
	public int count(String where) throws SQLException
	{
		int result = 0;
		
		Statement stmt = conn.createStatement();
		
		String sql = String.format("SELECT COUNT(*) AS COUNT FROM TBL_EMP %s",where);
		
		ResultSet rs = stmt.executeQuery(sql);
		
		while (rs.next())
		{
			result = rs.getInt("COUNT");
		}
		
		stmt.close();
		rs.close();
			
		return result;
	}
	
	
	// 전체 리스트 출력 (정렬) 담당 메소드
	public ArrayList<MemberDTO> listOrder(String order) throws SQLException
	{
		ArrayList<MemberDTO> result = new ArrayList<MemberDTO>();
		
		Statement stmt = conn.createStatement();
		
		String sql = String.format("SELECT EMP_ID, EMP_NAME, SSN, IBSADATE, CITY_LOC, TEL, BUSEO_NAME, JIKWI_NAME, BASICPAY, SUDANG, PAY FROM EMPVIEW %s", order);
		
		ResultSet rs = stmt.executeQuery(sql);
		
		while (rs.next())
		{
			MemberDTO dto = new MemberDTO();
			
			dto.setId(rs.getInt("EMP_ID"));
			dto.setBasicpay(rs.getInt("BASICPAY"));
			dto.setSudang(rs.getInt("SUDANG"));
			dto.setPay(rs.getInt("PAY"));
			dto.setName(rs.getString("EMP_NAME"));
			dto.setSsn(rs.getString("SSN"));
			dto.setIbsadate(rs.getString("IBSADATE"));
			dto.setTel(rs.getString("TEL"));
			dto.setCity(rs.getString("CITY_LOC"));
			dto.setBuseo(rs.getString("BUSEO_NAME"));
			dto.setJikwi(rs.getString("JIKWI_NAME"));
			
			result.add(dto);
		}
		
		stmt.close();
		rs.close();
			
		return result;
	}
	
	// 사번/이름/부서/직위 검색 담당 메소드
	public ArrayList<MemberDTO> listWhere(String where) throws SQLException
	{
		ArrayList<MemberDTO> result = new ArrayList<MemberDTO>();
		
		Statement stmt = conn.createStatement();
		
		String sql = String.format("SELECT EMP_ID, EMP_NAME, SSN, IBSADATE, CITY_LOC, TEL, BUSEO_NAME, JIKWI_NAME, MIN_BASICPAY, BASICPAY, SUDANG, PAY FROM EMPVIEW %s", where);
		
		ResultSet rs = stmt.executeQuery(sql);
		
		while (rs.next())
		{
			MemberDTO dto = new MemberDTO();
			
			dto.setId(rs.getInt("EMP_ID"));
			dto.setBasicpay(rs.getInt("BASICPAY"));
			dto.setSudang(rs.getInt("SUDANG"));
			dto.setPay(rs.getInt("PAY"));
			dto.setName(rs.getString("EMP_NAME"));
			dto.setSsn(rs.getString("SSN"));
			dto.setIbsadate(rs.getString("IBSADATE"));
			dto.setTel(rs.getString("TEL"));
			dto.setCity(rs.getString("CITY_LOC"));
			dto.setBuseo(rs.getString("BUSEO_NAME"));
			dto.setJikwi(rs.getString("JIKWI_NAME"));
			
			result.add(dto);
		}
		
		stmt.close();
		rs.close();
			
		return result;
	}
	
	// 데이터 수정 담당 메소드
	public int update(MemberDTO dto) throws SQLException
	{
		int result = 0;
		
		Statement stmt = conn.createStatement();
		
		String sql = String.format("UPDATE TBL_EMP SET EMP_NAME = '%s', SSN = '%s', IBSADATE = TO_DATE('%s','YYYY-MM-DD'), CITY_ID = (SELECT CITY_ID FROM TBL_CITY WHERE CITY_LOC = '%s'), TEL = '%s', BUSEO_ID = (SELECT BUSEO_ID FROM TBL_BUSEO WHERE BUSEO_NAME = '%s'), JIKWI_ID = (SELECT JIKWI_ID FROM TBL_JIKWI WHERE JIKWI_NAME = '%s'), BASICPAY = %d, SUDANG = %d WHERE EMP_ID = %d"
				 , dto.getName(), dto.getSsn(), dto.getIbsadate(), dto.getCity(), dto.getTel(), dto.getBuseo(), dto.getJikwi(), dto.getBasicpay(), dto.getSudang(), dto.getId());
		
		result = stmt.executeUpdate(sql);
		
		stmt.close();
		
		return result;
	}
	
	// 데이터베이스 삭제 담당 메소드
	public int delete(int id) throws SQLException
	{
		int result = 0;
		
		Statement stmt = conn.createStatement();
		
		String sql = String.format("DELETE FROM TBL_EMP WHERE EMP_ID = %d",id);
				
		result = stmt.executeUpdate(sql);
		
		stmt.close();
		
		return result;
	}
	
	// 데이터베이스 연결 종료 담당 메소드
	public void close() throws SQLException
	{
		DBConn.close();
	}
}
