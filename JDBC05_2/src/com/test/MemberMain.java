/*====================
   MemberMain.java   
====================*/
// JDBC05_2


package com.test;

import java.sql.SQLException;
import java.util.Scanner;

public class MemberMain
{

	public static void main(String[] args) throws SQLException
	{
		MemberDAO dao = new MemberDAO();
		
		dao.connection();
		
		Scanner sc = new Scanner(System.in);
		MemberProcess prc = new MemberProcess();
		
		do
		{
			System.out.println("========[직원 관리]========");
			System.out.println("1. 직원 정보 입력");
			System.out.println("2. 직원 전체 출력");
			System.out.println("   - 사번 정렬");
			System.out.println("   - 이름 정렬");
			System.out.println("   - 부서 정렬");
			System.out.println("   - 직위 정렬");
			System.out.println("   - 급여 내림차순 정렬");
			System.out.println("3. 직원 검색 출력");
			System.out.println("   - 사번 검색");
			System.out.println("   - 이름 검색");
			System.out.println("   - 부서 검색");
			System.out.println("   - 직위 검색");
			System.out.println("4. 직원 정보 수정");
			System.out.println("5. 직원 정보 삭제");
			System.out.println("=========================");
			System.out.print(">> 메뉴 선택(1~5, -1종료): ");
			
			int response = sc.nextInt();
			
			try
			{
				switch (response)
				{
					case 1:
						prc.memberInsert();
						break;
					case 2:
						prc.memberLists();
						break;
					case 3:
						prc.memberSearch();
						break;
					case 4:
						prc.memberUpdate();
						break;
					case 5:
						prc.memberDelete();
						break;
					case -1:
						System.out.println("\n>> 프로그램이 종료됩니다.");
						return;
					default:
						System.out.println("\n>> 잘못 입력하셨습니다.\n");
						break;
				}
			} catch (Exception e)
			{
				System.out.println(e.toString());
			}
			
		} while (true);
	}
}
