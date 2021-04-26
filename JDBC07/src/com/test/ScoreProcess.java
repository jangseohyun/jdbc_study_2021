/*======================
   ScoreProcess.java   
======================*/
// JDBC07


package com.test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class ScoreProcess
{
	private ScoreDAO dao;
	
	ScoreProcess()
	{
		dao = new ScoreDAO();
	}
	
	// 1. 성적 입력
	public void scoreInsert() throws ClassNotFoundException, SQLException
	{
		Scanner sc = new Scanner(System.in);
		
		do
		{
			dao.connection();
			
			int count = dao.count();
			
			System.out.printf("\n%d번 학생의 이름, 성적 입력: ",++count);
			String name = sc.next();
			
			if (name.contentEquals("."))
			{
				System.out.println();
				break;
			}
			
			int kor = sc.nextInt();
			int eng = sc.nextInt();
			int mat = sc.nextInt();
			
			ScoreDTO dto = new ScoreDTO();
			
			dto.setName(name);
			dto.setKor(kor);
			dto.setEng(eng);
			dto.setMat(mat);
			
			int result = dao.add(dto);

			if (result > 0)
				System.out.println("\n>> 성적 입력이 완료되었습니다.");
			
		} while (true);
		
		dao.close();
	}
	
	// 2. 성적 전체 출력
	public void scoreSelectAll() throws SQLException, ClassNotFoundException
	{
		dao.connection();

		System.out.printf("\n전체 인원: %d\n", dao.count());
		
		if (dao.count() > 0)
		{
			System.out.println("번호   이름    국어  영어  수학   총점  평균  석차");

			for (ScoreDTO dto : dao.lists())
			{
				System.out.printf("%3s %5s %5d %5d %5d %6d %6.1f %4d\n", dto.getSid(), dto.getName(), dto.getKor(),
						dto.getEng(), dto.getMat(), dto.getTot(), dto.getAvg(), dto.getRank());
			}
		}
		else if (dao.count() == 0)
		{
			System.out.println(">> 조회할 성적이 존재하지 않습니다.");
		}
		
		System.out.println();
		
		dao.close();
	}
	
	// 3. 이름 검색 출력
	public void scoreSelectName() throws ClassNotFoundException, SQLException
	{
		dao.connection();
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n검색할 이름 입력: ");
		String name = sc.next();
		
		if (dao.count("NAME",name) > 0)
		{
			System.out.println("번호   이름    국어  영어  수학   총점  평균  석차");

			for (ScoreDTO dto : dao.lists("NAME",name))
			{
				System.out.printf("%3s %5s %5d %5d %5d %6d %6.1f %4d\n", dto.getSid(), dto.getName(), dto.getKor(),
						dto.getEng(), dto.getMat(), dto.getTot(), dto.getAvg(), dto.getRank());
			}
		}
		else if (dao.count() == 0)
		{
			System.out.println(">> 해당 학생이 존재하지 않습니다.");
		}
		
		System.out.println();
		
		dao.close();
	}
	
	// 4. 성적 수정
	public void scoreUpdate() throws SQLException, ClassNotFoundException
	{
		Scanner sc = new Scanner(System.in);
		System.out.print("\n수정할 번호 입력: ");
		String sid = sc.next();
		
		dao.connection();
		
		if (dao.count("SID",sid) > 0)
		{
			System.out.println("번호   이름    국어  영어  수학   총점  평균  석차");
			
			for (ScoreDTO dto : dao.lists("SID",sid))
			{
				System.out.printf("%3s %5s %5d %5d %5d %6d %6.1f %4d\n", dto.getSid(), dto.getName(), dto.getKor(),
						dto.getEng(), dto.getMat(), dto.getTot(), dto.getAvg(), dto.getRank());
			}
			
			System.out.println();
			System.out.print("수정 데이터 입력(이름 국어 영어 수학): ");
			String name = sc.next();
			int kor = sc.nextInt();
			int eng = sc.nextInt();
			int mat = sc.nextInt();
			
			ScoreDTO dto = new ScoreDTO();
			dto.setSid(String.valueOf(sid));
			dto.setName(name);
			dto.setKor(kor);
			dto.setEng(eng);
			dto.setMat(mat);
			
			int result = dao.update(dto);
			
			if (result > 0)
				System.out.println(">> 수정이 완료되었습니다.");
			
		} else
		{
			System.out.println(">> 수정 대상이 존재하지 않습니다.");
		}
		
		System.out.println();
		dao.close();
	}
	
	// 5. 성적 삭제
	public void scoreDelete() throws ClassNotFoundException, SQLException
	{
		dao.connection();
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n삭제할 성적 번호를 입력하세요: ");
		String sid = sc.next();
		
		int count = dao.count("SID",sid);
		
		if (count > 0)
		{
			System.out.printf("\n>> %s번의 성적 조회 결과\n",sid);
			System.out.println("번호   이름    국어  영어  수학   총점  평균  석차");
			
			for (ScoreDTO dto : dao.lists("SID",sid))
			{
				System.out.printf("%3s %5s %5d %5d %5d %6d %6.1f %4d\n", dto.getSid(), dto.getName(), dto.getKor(),
						dto.getEng(), dto.getMat(), dto.getTot(), dto.getAvg(), dto.getRank());
			}
			
			System.out.print("\n삭제하시겠습니까?(1: 예, 2: 아니오): ");
			int remove = sc.nextInt();
			
			int result = 0;
			
			if (remove == 1)
				result = dao.delete(sid);
			
			if (result > 0)
				System.out.println(">> 삭제되었습니다.\n");
			else if (result == 0)
				System.out.println(">> 취소하셨습니다.\n");
		}
		else if (count == 0)
		{
			System.out.println(">> 존재하지 않는 번호입니다.\n");
		}
		
		dao.close();
	}
}
