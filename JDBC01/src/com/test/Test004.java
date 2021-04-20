/*====================================
   Test004.java   
   - 테이블 내의 데이터 읽어오기
====================================*/


package com.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.util.DBConn;


public class Test004
{
	public static void main(String[] args)
	{
		// 작업 객체를 매달 줄 생성
		Connection conn = DBConn.getConnection();
		
		if (conn != null)
		{
			System.out.println("데이터베이스 연결 성공");
			
			try
			{
				// 작업 객체 생성
				Statement stmt =  conn.createStatement();
				
				// 쿼리문 준비
				// String sql = "SELECT SID,NAME,TEL FROM TBL_MEMBER ORDER BY 1";
				String sql = "SELECT SID,NAME,TEL"
						   + " FROM TBL_MEMBER"	//--※ 쿼리문 구성 간 공백 처리에 유의
						   + " ORDER BY 1";
				
				// executeQuery() 메소드를 사용하면
				// 질의 결과를 ResultSet 객체로 가져올 수 있다.
				// 하지만, ResultSet 객체가 질의에 대한 결과물 모두를
				// 한꺼번에 갖고 있는 구조는 아니다.
				// 단지 데이터베이스로부터 획득한 질의 결과물에 대한
				// 관리가 가능한 상태가 되는 것이다.
				// 이때문에 데이터베이스와의 연결을 끊게 되면
				// ResultSet 객체는 더이상 질의 결과를 관리할 수 없게 된다.
				ResultSet rs =  stmt.executeQuery(sql);
				
				// ResultSet에 대한 처리 → 반복문 구성
				// 가져올 값이 있는지 확인하는 메소드 『next()』
				while (rs.next())
				{
					// 레코드에서 결과값을 가져오는 메소드 『getter』
					String sid = rs.getString("SID");
					String name = rs.getString("NAME");
					String tel = rs.getString("TEL");
					
					String str = String.format("%3s %8s %12s", sid, name,tel);
					System.out.println(str);
				}
				rs.close();
				stmt.close();
				
			} catch (Exception e)
			{
				System.out.println(e.toString());
			}
		} //--end if
		
		DBConn.close();
		
		System.out.println("데이터베이스 연결 닫힘");
		System.out.println("프로그램 종료");
	}

}

/*
데이터베이스 연결 성공
  1      김가영 010-1111-1111
  2      김서현 010-2222-2222
  3      박정준 010-3333-3333
  4      이유림 010-4444-4444
데이터베이스 연결 닫힘
프로그램 종료
*/