/*=========================
   Test001.java
   - 쿼리문 전송 실습 1
=========================*/


package com.test;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;

import com.util.DBConn;


public class Test001
{
	public static void main(String[] args)
	{
		try
		{
			Connection conn = DBConn.getConnection();
			
			if (conn != null)
			{
				System.out.println("데이터베이스 연결 성공");
				
				try
				{
					/*
					// 1
					Statement stmt = conn.createStatement();
					
					String sql = "INSERT INTO TBL_MEMBER(SID, NAME, TEL)\r\n" + 
							"VALUES(MEMBERSEQ.NEXTVAL, '전혜림', '010-2222-2222')";
							
					int result = stmt.executeUpdate(sql);
					
					if (result > 0)
						System.out.println("데이터 입력 성공");
					
					stmt.close();
					DBConn.close();
					*/
					
					
					/*
					// 2
		            String sql = "INSERT INTO TBL_MEMBER(SID, NAME, TEL)"
		                     + " VALUES(MEMBERSEQ.NEXTVAL, '장서현', '010-3333-3333')";
		               
		            Statement stmt = conn.createStatement();
		               
		            int result = stmt.executeUpdate(sql);
		                
		            if (result > 0)
		            	System.out.println("데이터 입력 성공~!!!");
		            
		            stmt.close();
		            DBConn.close(); 
		            */
		            
					// 3
					// 쿼리문 준비 → 매개변수 위치 지정 → 『?』 활용
		            String sql = "INSERT INTO TBL_MEMBER(SID, NAME, TEL)"
		                     + " VALUES(MEMBERSEQ.NEXTVAL, ?, ?)";
		              
		            // 작업 객체 생성 → 쿼리문 전달
		            PreparedStatement pstmt = conn.prepareStatement(sql);
		            
		            // IN 매개변수 넘겨주기
		            pstmt.setString(1, "이새롬");
		            pstmt.setString(2, "010-4444-4444");
		            
		            // 작업객체 실행
		            int result = pstmt.executeUpdate();		       
		   
		            if (result > 0)
		            	System.out.println("데이터 입력 성공~!!!");
		            
		            pstmt.close();
		            DBConn.close(); 		            
					
				} catch (Exception e)
				{
					System.out.println(e.toString());
				}
			}
			
		} catch (Exception e2)
		{
			System.out.println(e2.toString());
		}
	}
}

/*
[실행 결과]

데이터베이스 연결 성공
데이터 입력 성공~!!!
*/