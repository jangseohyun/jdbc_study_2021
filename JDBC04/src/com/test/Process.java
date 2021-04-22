/*==================
   Process.java   
==================*/
// JDBC04

package com.test;

import java.util.ArrayList;
import java.util.Scanner;


public class Process
{
	// 주요 속성 구성
	private ScoreDAO dao;

	// 생성자 정의
	public Process()
	{
		dao = new ScoreDAO();
	}

	// 성적 입력 기능
	public void sungjukInsert()
	{
		try
		{
			do
			{
				// 데이터베이스 연결
				dao.connection();

				// 레코드 수 확인
				int count = dao.count();

				Scanner sc = new Scanner(System.in);

				System.out.println();
				System.out.printf("%d번 학생 성적 입력(이름 국어 영어 수학): ", ++count);
				String name = sc.next();

				if (name.contentEquals("."))
				{
					System.out.println();
					break;
				}
				
				int kor = sc.nextInt();
				int eng = sc.nextInt();
				int mat = sc.nextInt();

				// ScoreDTO 객체 구성
				ScoreDTO dto = new ScoreDTO();
				
				dto.setName(name);
				dto.setKor(kor);
				dto.setEng(eng);
				dto.setMat(mat);

				// dao의 add() 메소드 호출
				int result = dao.add(dto);

				if (result > 0)
					System.out.println(">> 성적 입력이 완료되었습니다.");
				
			} while (true);

			// 데이터베이스 연결 종료
			dao.close();

		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
	}

	// 성적 전체 출력
	public void sungjukSelectAll()
	{
		// 사전 작성
		try
		{
			// dao의 connection 메소드 호출 → 데이터베이스 연결
			dao.connection();

			// dao의 count() 메소드 호출 → 인원 수 확인
			System.out.printf("\n전체 인원: %d\n", dao.count());
			
			if (dao.count() > 0)
			{
				System.out.println("번호   이름    국어  영어  수학   총점  평균  석차");
	
				for (ScoreDTO dto : dao.list())
				{
					System.out.printf("%3s %5s %5d %5d %5d %6d %6.1f %4d\n", dto.getSid(), dto.getName(), dto.getKor(),
							dto.getEng(), dto.getMat(), dto.getTot(), dto.getAvg(), dto.getRank());
				}
			}
			else if (dao.count() == 0)
			{
				System.out.println(">> 조회할 성적이 없습니다.");
			}
			
			System.out.println();
			
			// dao의 close() 메소드 호출 → 데이터베이스 연결 종료
			dao.close();

		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
	}
		

	// 이름 검색 출력
	public void sungjukSearchName()
	{
		try
		{
			dao.connection();
			
			Scanner sc = new Scanner(System.in);
			
			System.out.print("\n검색할 이름을 입력하세요: ");
			String name = sc.next();

			int result = dao.count();
			
			if (result > 0)
			{
				System.out.printf("\n>> %s 학생의 성적 조회 결과\n",name);
				System.out.println("번호   이름    국어  영어  수학   총점  평균  석차");
				
				for (ScoreDTO dto : dao.list(name))
				{
					System.out.printf("%3s %5s %5d %5d %5d %6d %6.1f %4d\n", dto.getSid(), dto.getName(), dto.getKor(),
							dto.getEng(), dto.getMat(), dto.getTot(), dto.getAvg(), dto.getRank());
				}
			}
			else if (result == 0)
			{
				System.out.println(">> 존재하지 않는 이름입니다.");
			}
			
			System.out.println();
			
			dao.close();
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
	}

	// 이름 검색 출력 [강사님 풀이]
	public void sungjukSearchName2()
	{
		try
		{
			// 검색할 이름 입력받기
			Scanner sc = new Scanner(System.in);
			System.out.print("검색할 이름 입력: ");
			String name = sc.next();
			
			//--필요할 경우 이 과정에서 프로그래밍적으로 검증(검사) 수행
		
			// 데이터베이스 연결
			dao.connection();
			
			// dao의 list() 메소드 호출 → 매개변수로 검색할 이름 넘겨주기
			ArrayList<ScoreDTO> arrayList = dao.list(name);
			
			if (arrayList.size() > 0)
			{
				// 수신된 내용 출력
				System.out.println("번호   이름    국어  영어  수학   총점  평균  석차");
				
				for (ScoreDTO dto : arrayList)
				{
					System.out.printf("%3s %5s %5d %5d %5d %6d %6.1f %4d\n", dto.getSid(), dto.getName(), dto.getKor(),
							dto.getEng(), dto.getMat(), dto.getTot(), dto.getAvg(), dto.getRank());
				}
			}
			else
			{
				System.out.println(">> 검색 결과가 존재하지 않습니다.");
			}
			
			// 데이터베이스 연결 종료
			dao.close();
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
	}

	// 성적 수정
	public void sungjukUpdate()
	{
		try
		{
			dao.connection();
			
			Scanner sc = new Scanner(System.in);
			
			System.out.print("\n변경할 성적 번호를 입력하세요: ");
			int sid = sc.nextInt();
			
			int result = dao.count();
			
			if (result > 0)
			{
				System.out.printf("\n>> %d번 성적 조회 결과\n",sid);
				System.out.println("번호   이름    국어  영어  수학   총점  평균  석차");
				
				for (ScoreDTO dto : dao.list(sid))
				{
					System.out.printf("%3s %5s %5d %5d %5d %6d %6.1f %4d\n", dto.getSid(), dto.getName(), dto.getKor(),
							dto.getEng(), dto.getMat(), dto.getTot(), dto.getAvg(), dto.getRank());
				}
			
				System.out.print("\n변경사항을 입력하세요(이름 국어 영어 수학): ");
				
				String name = sc.next();
				int kor = sc.nextInt();
				int eng = sc.nextInt();
				int mat = sc.nextInt();
				
				ScoreDTO dto = new ScoreDTO();
				
				dto.setSid(Integer.toString(sid));
				dto.setName(name);
				dto.setKor(kor);
				dto.setEng(eng);
				dto.setMat(mat);
				
				result = dao.modify(dto);
				
				System.out.println(">> 변경사항이 적용되었습니다.\n");
			}
			else if (result == 0)
			{
				System.out.println(">> 존재하지 않는 번호입니다.\n");
			}
			
			dao.close();
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}

	}
	
	// 성적 수정 [강사님 풀이]
	public void sungjukUpdate2()
	{
		try
		{
			// 수정할 번호 입력받기
			Scanner sc = new Scanner(System.in);
			System.out.print("수정할 번호 입력: ");
			int sid = sc.nextInt();
			
			//--입력받은 번호로 체크해야 할 로직 적용 삽입 가능
			
			// 데이터베이스 연결
			dao.connection();
			
			// 데이터 수정을 위해 대상 검색 → 수정할 대상 수신
			ArrayList<ScoreDTO> arrayList = dao.list(sid);
			
			if (arrayList.size() > 0)
			{
				// 수신된 내용 출력
				System.out.println("번호   이름    국어  영어  수학   총점  평균  석차");
				
				for (ScoreDTO dto : arrayList)
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
				
				// dto 구성
				ScoreDTO dto = new ScoreDTO();
				dto.setSid(String.valueOf(sid));
				dto.setName(name);
				dto.setKor(kor);
				dto.setEng(eng);
				dto.setMat(mat);
				
				int result = dao.modify(dto);
				
				if (result > 0)
					System.out.println(">> 수정이 완료되었습니다.");
				
			} else
			{
				System.out.println(">> 수정 대상이 존재하지 않습니다.");
			}
			
			// 데이터베이스 연결 종료
			dao.close();
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
	}

	// 성적 삭제
	public void sungjukDelete()
	{
		try
		{
			dao.connection();
			
			Scanner sc = new Scanner(System.in);
			
			System.out.print("\n삭제할 성적 번호를 입력하세요: ");
			int sid = sc.nextInt();
			
			int count = dao.count();
			
			if (count > 0)
			{
				System.out.printf("\n>> %d번의 성적 조회 결과\n",sid);
				System.out.println("번호   이름    국어  영어  수학   총점  평균  석차");
				
				for (ScoreDTO dto : dao.list(sid))
				{
					System.out.printf("%3s %5s %5d %5d %5d %6d %6.1f %4d\n", dto.getSid(), dto.getName(), dto.getKor(),
							dto.getEng(), dto.getMat(), dto.getTot(), dto.getAvg(), dto.getRank());
				}
				
				System.out.print("\n삭제하시겠습니까?(1: 예, 2: 아니오): ");
				int remove = sc.nextInt();
				
				int result = 0;
				
				if (remove == 1)
					result = dao.remove(sid);
				
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
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
	}
	
	// 성적 삭제 [강사님 풀이]
	public void sungjukDelete2()
	{
		try
		{
			// 삭제할 번호 입력받기
			Scanner sc = new Scanner(System.in);
			
			System.out.print("삭제할 번호 입력: ");
			int sid = sc.nextInt();
			
			// 데이터베이스 연결
			dao.connection();
			
			// 데이터 삭제를 위해 대상 검색 → dao의 list() 메소드 호출 → 삭제 대상 수신
			ArrayList<ScoreDTO> arrayList = dao.list(sid);
			
			if (arrayList.size() > 0)
			{
				// 수신된 내용 처리
				System.out.println("번호   이름    국어  영어  수학   총점  평균  석차");
				
				for (ScoreDTO dto : arrayList)
				{
					System.out.printf("%3s %5s %5d %5d %5d %6d %6.1f %4d\n", dto.getSid(), dto.getName(), dto.getKor(),
							dto.getEng(), dto.getMat(), dto.getTot(), dto.getAvg(), dto.getRank());
				}
				
				System.out.println();
				System.out.print(">> 정말 삭제하시겠습니까? (Y/N): ");
				String response = sc.next();
				
				if (response.equals("Y") || response.equals("y"))
				{
					int result = dao.remove(sid);
					
					if (result > 0)
						System.out.println(">> 삭제가 완료되었습니다.");
				}
				else
				{
					System.out.println(">> 취소되었습니다.");
				}
				
			} else
			{
				System.out.println(">> 수정 대상이 존재하지 않습니다.");
			}
			
			// 데이터베이스 연결 종료
			dao.close();
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
	}
}
