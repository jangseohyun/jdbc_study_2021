/*======================================================
   Test002.java
   - CallableStatement를 활용한 SQL 구문 전송 실습 2
======================================================*/
// JDBC08


package com.test;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

import com.util.DBConn;

import oracle.jdbc.OracleTypes;

public class Test002
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
					String sql = "{call PRC_MEMBERSELECT(?)}";
					CallableStatement cstmt = conn.prepareCall(sql);
					
					// 프로시저 내부에서 SYS_REFCURSOR를 사용하고 있기 때문에
					// OracleTypes.CURSOR를 사용하기 위한 등록 과정이 필요한 상황
					// 1. Project Explorer 상에서 해당 프로젝트 마우스 우클릭
					//    > Build Path > Configure Build Path > Java Build Path
					// 2. Java Build Path 대화창에서 Libraries 탭 선택
					// 3. Add External JARs... 버튼 클릭
					//    > 『ojdbc6.jar』 또는 『ojdbc14.jar』 파일 추가 등록
					//    (외부 jar 파일 연결)
					// 4. 『import oracle.jdbc.OracleTypes;』 구문 추가
					
					// 오라클에서 사용하는 CURSOR를 자바가 인지할 수 있도록 넘겨줌
					cstmt.registerOutParameter(1, OracleTypes.CURSOR);
					cstmt.execute();
					ResultSet rs = (ResultSet)cstmt.getObject(1);
					
					while (rs.next())
					{
						String sid = rs.getString("SID");
						String name = rs.getString("NAME");
						String tel = rs.getString("TEL");
						
						String str = String.format("%3s %7s %10s", sid, name, tel);
						
						System.out.println(str);
					}
					rs.close();
					cstmt.close();
					
				} catch (Exception e)
				{
					System.out.println(e.toString());
				}
			}// end if
			
			DBConn.close();
			
			System.out.println("\n데이터베이스 연결 닫힘");
			System.out.println("프로그램 종료");
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
	}
}

/*
[실행 결과]
데이터베이스 연결 성공
 1     한혜림 010-1111-1111
 2     이새롬 010-4444-4444
 3     전혜림 010-2222-2222
 4     장서현 010-3333-3333
 5     정주희 010-5555-5555
 6     이희주 010-6666-6666
 7     심혜진 010-7777-7777
 8     이상화 010-1212-3434
 9     안정미 010-9797-6565
10     이하림 010-4545-6767

데이터베이스 연결 닫힘
프로그램 종료
*/