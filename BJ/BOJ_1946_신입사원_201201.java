package day1201;

import java.util.PriorityQueue;
import java.util.Scanner;

public class BOJ_1946_신입사원_201201 {
	
	// 지원자 수, 합격자 수
	static int N, answer;
	// 지원자 점수
	static class Person implements Comparable<Person>{
		int document, interview;

		public Person(int document, int interview) {
			this.document = document;
			this.interview = interview;
		}
		
		// 오름차순으로 정렬
		@Override
		public int compareTo(Person o) {
			return this.document - o.document;
		}
	}
	// 지원자 점수를 담을 배열
	static PriorityQueue<Person> pq = new PriorityQueue<Person>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt(); // 테스트 케이스 수
		
		for (int t = 0; t < T; t++) {
			N = sc.nextInt();
			answer = 0;
			
			for (int i = 0; i < N; i++) {
				pq.offer(new Person(sc.nextInt(), sc.nextInt()));
			}
			
			Person standard = pq.poll();
			while(!pq.isEmpty()) {
				Person p = pq.poll();
				
				// 서류, 면접 결과가 모두 후순위인 경우
				if(standard.interview < p.interview) {
					continue;
				}else {
					answer++;
					standard = p;
				}
			}
			
			// 서류 결과가 가장 높은 사람은 무조건 합격
			System.out.println(answer+1);
		}
		sc.close();
	}

}
