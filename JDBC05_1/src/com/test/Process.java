/*==================
   Process.java   
==================*/
// JDBC05_1


package com.test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Process
{
	private MemberDAO dao;
	
	public Process()
	{
		dao = new MemberDAO();
	}
	
	// 직원 정보 입력 메소드
	public void memberInsert() throws SQLException
	{
		Scanner sc = new Scanner(System.in);
		
		do
		{
			dao.connection();
			
			System.out.println("\n직원 정보 입력 -----------------------------------------------------------------");
			System.out.print("이름: ");
			String name = sc.next();
			
			if (name.contentEquals("."))
			{
				System.out.println();
				break;
			}
			
			System.out.print("주민등록번호(yymmdd-nnnnnnn): ");
			String ssn = sc.next();
			
			System.out.print("입사일(yyyy-mm-dd): ");
			String ibsadate = sc.next();
			
			System.out.print("지역(강원/경기/경남/경북/부산/서울/인천/전남/전북/제주/충남/충북/): ");
			String city = sc.next();
			
			System.out.print("전화번호: ");
			String tel = sc.next();
			
			System.out.print("부서(개발부/기획부/영업부/인사부/자재부/총무부/홍보부/): ");
			String buseo = sc.next();
			
			System.out.print("직위(사장/전무/상무/이사/부장/차장/과장/대리/사원/): ");
			String jikwi = sc.next();
			
			System.out.print("기본급(최소 840000원 이상): ");
			int basicpay = sc.nextInt();
			
			System.out.print("수당: ");
			int sudang = sc.nextInt();
			
			MemberDTO dto = new MemberDTO();
			
			dto.setName(name);
			dto.setSsn(ssn);
			dto.setIbsadate(ibsadate);
			dto.setCity(city);
			dto.setTel(tel);
			dto.setBuseo(buseo);
			dto.setJikwi(jikwi);
			dto.setBasicpay(basicpay);
			dto.setSudang(sudang);
			
			int result = dao.input(dto);
			
			if (result > 0)
				System.out.println(">> 직원 입력이 완료되었습니다.");
			
		} while (true);
		
		dao.close();
	}
	
	// 직원 정렬 출력 메소드
	public void memberOrder() throws SQLException 
	{
		dao.connection();
		
		Scanner sc = new Scanner(System.in);
		ArrayList<MemberDTO> result = new ArrayList<MemberDTO>();
		
		System.out.print("\n정렬 방식 선택(1: 사번, 2: 이름, 3: 부서, 4: 직위, 5: 급여 내림차순): ");
		int response = sc.nextInt();
		String order = "";
		
		switch (response)
		{
			case 1:
				order = "ORDER BY EMP_ID";
				result = dao.listOrder(order);
				break;
			case 2:
				order = "ORDER BY EMP_NAME";
				result = dao.listOrder(order);
				break;
			case 3:
				order = "ORDER BY BUSEO_NAME";
				result = dao.listOrder(order);
				break;
			case 4:
				order = "ORDER BY JIKWI_NAME";
				result = dao.listOrder(order);
				break;
			case 5:
				order = "ORDER BY PAY DESC";
				result = dao.listOrder(order);
				break;
			default:
				break;	
		}
		
		if (result.size() > 0)
		{
			System.out.println(" 번호    이름     주민등록번호   생년월일   입사일     지역    부서     직위    기본급       수당      급여");
			
			for (MemberDTO dto : result)
			{
				System.out.printf("%5d %5s %16s %14s %5s %5s %5s %10d %10d %10d\n"
						          ,dto.getId(),dto.getName(),dto.getSsn(),dto.getIbsadate(),dto.getCity(),dto.getBuseo(),dto.getJikwi()
						          ,dto.getBasicpay(),dto.getSudang(),dto.getPay());
			}
		}
		else
		{
			System.out.println(">> 검색 결과가 존재하지 않습니다.");
		}
		
		System.out.println();
		dao.close();
	}
	
	// 직원 정보 수정 메소드
	
	// 직원 정보 삭제 메소드
}
