/*=========================
   Test001.java
   - 쿼리문 전송 실습 1
=========================*/


package com.test;

import java.sql.Statement;
import java.sql.Connection;
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
					Statement stmt = conn.createStatement();
					
					String sql = "INSERT INTO TBL_MEMBER(SID, NAME, TEL)\r\n" + 
							"VALUES(MEMBERSEQ.NEXTVAL, '전혜림', '010-2222-2222')";
							
					int result = stmt.executeUpdate(sql);
					
					if (result > 0)
						System.out.println("데이터 입력 성공");
					
					stmt.close();
					DBConn.close();
					*/
					
					// 2
		            String sql = "INSERT INTO TBL_MEMBER(SID, NAME, TEL)"
		                     + " VALUES(MEMBERSEQ.NEXTVAL, '전혜림', '010-2222-2222')";
		               
		            Statement stmt = conn.createStatement();
		               
		            int result = stmt.executeUpdate(sql);
		                
		            if (result > 0)
		            	System.out.println("데이터 입력 성공~!!!");
		            
		            stmt.close();
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
