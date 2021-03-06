/*===================
   ScoreMain.java   
===================*/
// JDBC04

/*
○ 성적 처리 → 데이터베이스 연동(데이터베이스 연결 및 액션 처리)
             → Score DTO 클래스 활용(속성만 존재하는 클래스, getter/setter 구성)
             → ScoreDAO 클래스 활용(데이터베이스 액션 처리)
             → Process 클래스 활용(단위 기능 구성)
             
여러 명의 이름, 국어점수, 영어점수, 수학점수를 입력받아
총점, 평균을 연산하여 출력하는 프로그램을 구현한다.
※ 서브 메뉴 구성 → Process 클래스 활용


○ 실행 예시

=====[성적 처리]======
1. 성적 입력
2. 성적 전체 출력
3. 이름 검색 출력
4. 성적 수정
5. 성적 삭제
======================
>> 선택(1~5, -1 종료): 1

7번 학생 성적 입력(이름 국어 영어 수학): 안정미 50 60 70
>> 성적 입력이 완료되었습니다.
8번 학생 성적 입력(이름 국어 영어 수학): 이새롬 80 70 60
>> 성적 입력이 완료되었습니다.
9번 학생 성적 입력(이름 국어 영어 수학): .

=====[성적 처리]======
1. 성적 입력
2. 성적 전체 출력
3. 이름 검색 출력
4. 성적 수정
5. 성적 삭제
======================
>> 선택(1~5, -1 종료): 2

전체 인원: 8명
번호 이름     국어  영어  수학   총점   평균   석차
  1   선혜연    90    80    70    240   80.0    x
 ...

=====[성적 처리]======
1. 성적 입력
2. 성적 전체 출력
3. 이름 검색 출력
4. 성적 수정
5. 성적 삭제
======================
>> 선택(1~5, -1 종료): 3

검색할 이름을 입력하세요:
*/

package com.test;

import java.util.Scanner;


public class ScoreMain
{
	public static void main(String[] args)
	{
		try
		{
			do
			{
				Scanner sc = new Scanner(System.in);
				Process pr = new Process();
				
				System.out.println("=====[성적 처리]======");
				System.out.println("1. 성적 입력");
				System.out.println("2. 성적 전체 출력");
				System.out.println("3. 이름 검색 출력");
				System.out.println("4. 성적 수정");
				System.out.println("5. 성적 삭제");
				System.out.println("====================");
				System.out.print(">> 선택(1~5, -1 종료): ");
				
				int i = sc.nextInt();
				
				if (i == 1)
				{
					pr.sungjukInsert();
					continue;
				}
				else if (i == 2)
				{
					pr.sungjukSelectAll();
					continue;
				}
				else if (i == 3)
				{
					pr.sungjukSearchName();
					continue;
				}
				else if (i == 4)
				{
					pr.sungjukUpdate();
					continue;
				}
				else if (i == 5)
				{
					pr.sungjukDelete();
					continue;
				}
				else if (i == -1)
				{
					System.out.println(">> 프로그램이 종료됩니다.");
					break;
				}
				
			} while (true);
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
	}
	
	/*
	// main 메소드 [강사님 풀이]
	public static void main(String[] args)
	{
		Process prc = new Process();
		Scanner sc = new Scanner(System.in);
		
		do
		{
			System.out.println("=====[성적 처리]======");
			System.out.println("1. 성적 입력");
			System.out.println("2. 성적 전체 출력");
			System.out.println("3. 이름 검색 출력");
			System.out.println("4. 성적 수정");
			System.out.println("5. 성적 삭제");
			System.out.println("====================");
			System.out.print(">> 선택(1~5, -1 종료): ");
			String menus = sc.next();
			
			try
			{
				int menu = Integer.parseInt(menus);
				
				if (menu == -1)
				{
					System.out.println();
					System.out.println("프로그램이 종료되었습니다.");
					return;
				}
				
				switch (menu)
				{
					case 1:
						// 성적 입력 기능 수행
						prc.sungjukInsert();
						break;
					case 2:
						// 성적 전체 출력 기능 수행
						prc.sungjukSelectAll();
						break;
					case 3:
						// 이름 검색 출력 기능 수행
						prc.sungjukSearchName2();
						break;
					case 4:
						// 성적 수정 기능 수행
						prc.sungjukUpdate2();
						break;
					case 5:
						// 성적 삭제 기능 수행
						prc.sungjukDelete2();
						break;
				}
				
			} catch (Exception e)
			{
				System.out.println(e.toString());
			}
			
		} while (true);
	}
	*/
	
}


/*
[실행 예시]

=====[성적 처리]======
1. 성적 입력
2. 성적 전체 출력
3. 이름 검색 출력
4. 성적 수정
5. 성적 삭제
====================
>> 선택(1~5, -1 종료): 1

1번 학생 성적 입력(이름 국어 영어 수학): 장서현 90 80 70
>> 성적 입력이 완료되었습니다.

2번 학생 성적 입력(이름 국어 영어 수학): .

=====[성적 처리]======
1. 성적 입력
2. 성적 전체 출력
3. 이름 검색 출력
4. 성적 수정
5. 성적 삭제
====================
>> 선택(1~5, -1 종료): 2

전체 인원: 1
번호   이름    국어  영어  수학   총점  평균  석차
  1   장서현    90    80    70    240   80.0    1

=====[성적 처리]======
1. 성적 입력
2. 성적 전체 출력
3. 이름 검색 출력
4. 성적 수정
5. 성적 삭제
====================
>> 선택(1~5, -1 종료): 3

검색할 이름을 입력하세요: 장서현

>> 장서현 학생의 성적 조회 결과
번호   이름    국어  영어  수학   총점  평균  석차
  1   장서현    90    80    70    240   80.0    1

=====[성적 처리]======
1. 성적 입력
2. 성적 전체 출력
3. 이름 검색 출력
4. 성적 수정
5. 성적 삭제
====================
>> 선택(1~5, -1 종료): 4

변경할 성적 번호를 입력하세요: 1

>> 1번의 성적 조회 결과
번호   이름    국어  영어  수학   총점  평균  석차
  1   장서현    90    80    70    240   80.0    1

변경사항을 입력하세요(이름 국어 영어 수학): 장서현 100 100 100
>> 변경사항이 적용되었습니다.

=====[성적 처리]======
1. 성적 입력
2. 성적 전체 출력
3. 이름 검색 출력
4. 성적 수정
5. 성적 삭제
====================
>> 선택(1~5, -1 종료): 5

삭제할 성적 번호를 입력하세요: 1

>> 1번의 성적 조회 결과
번호   이름    국어  영어  수학   총점  평균  석차
  1   장서현   100   100   100    300  100.0    1

삭제하시겠습니까?(1: 예, 2: 아니오): 2
>> 취소하셨습니다.

=====[성적 처리]======
1. 성적 입력
2. 성적 전체 출력
3. 이름 검색 출력
4. 성적 수정
5. 성적 삭제
====================
>> 선택(1~5, -1 종료): 5

삭제할 성적 번호를 입력하세요: 1

>> 1번의 성적 조회 결과
번호   이름    국어  영어  수학   총점  평균  석차
  1   장서현   100   100   100    300  100.0    1

삭제하시겠습니까?(1: 예, 2: 아니오): 1
>> 삭제되었습니다.

=====[성적 처리]======
1. 성적 입력
2. 성적 전체 출력
3. 이름 검색 출력
4. 성적 수정
5. 성적 삭제
====================
>> 선택(1~5, -1 종료): -1
>> 프로그램이 종료됩니다.

*/