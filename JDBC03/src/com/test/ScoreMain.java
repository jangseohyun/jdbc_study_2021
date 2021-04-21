/*===================
   ScoreMain.java   
===================*/

/*
○ 성적 처리 프로그램 구현 → 데이터베이스 연동 → ScoreDAO, ScoreDTO 클래스 활용

   여러 명의 이름, 국어점수, 영어점수, 수학점수를 입력받아
   총점, 평균을 연산하여 출락혀나느 프로그램을 구현한다.
  
  
○ 실행 예시

1번 학생 성적 입력(이름 국어 영어 수학): 선혜연 90 80 70
2번 학생 성적 입력(이름 국어 영어 수학): 이상화 100 100 100
3번 학생 성적 입력(이름 국어 영어 수학): 이유림 80 85 80
4번 학생 성적 입력(이름 국어 영어 수학): .

---------------------------------------------
번호 이름     국어  영어  수학   총점   평균
---------------------------------------------
1    선혜연    90    80    70	 xxx    xx.x
2    이상화   100    90    80    xxx    xx.x
3    이유림    80    85    80    xxx    xx.x
---------------------------------------------

*/


package com.test;

import java.sql.SQLException;
import java.util.Scanner;

public class ScoreMain
{

	public static void main(String[] args) throws SQLException
	{
		ScoreDAO dao = new ScoreDAO();
		Scanner sc = new Scanner(System.in);
		
		int count = dao.count();
		
		do
		{
			System.out.printf("%d번 학생 성적 입력(이름 국어 영어 수학): ", ++count);
			String name = sc.next();
			
			if (name.equals("."))
				break;
			
			int kor = sc.nextInt();
			int eng = sc.nextInt();
			int mat = sc.nextInt();
			
			ScoreDTO dto = new ScoreDTO();
			
			dto.setName(name);
			dto.setKor(kor);
			dto.setEng(eng);
			dto.setMat(mat);
			
			int result = dao.add(dto);
			
		} while (true);
		
		System.out.println("---------------------------------------------");
		System.out.println("번호  이름     국어  영어  수학   총점  평균");
		System.out.println("---------------------------------------------");
		
		for (ScoreDTO dto : dao.list())
		{
			int tot = dto.getKor() + dto.getEng() + dto.getMat();
			double avg = (tot / 3.0);
			
			System.out.printf("%3s %5s %5d %5d %5d %6d %6.1f\n",dto.getSid(),dto.getName(),dto.getKor(),dto.getEng(),dto.getMat(),tot,avg);
		}
		
		System.out.println("---------------------------------------------");
	}

}


/*
[실행 예시]

1번 학생 성적 입력(이름 국어 영어 수학): 선혜연 90 80 70
2번 학생 성적 입력(이름 국어 영어 수학): 이상화 100 100 100
3번 학생 성적 입력(이름 국어 영어 수학): 이유림 80 85 80
4번 학생 성적 입력(이름 국어 영어 수학): .
---------------------------------------------
번호  이름     국어  영어  수학   총점  평균
---------------------------------------------
  1   선혜연    90    80    70    240   80.0
  2   이상화   100    90    80    270   90.0
  3   이유림    80    85    80    245   81.7
---------------------------------------------
*/