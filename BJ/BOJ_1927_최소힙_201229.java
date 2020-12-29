package day1229;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BOJ_1927_최소힙_201229 {
	
	static int N; // 연산의 개수
	static PriorityQueue<Integer> pq = new PriorityQueue<Integer>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(br.readLine());
			
			// 0이 들어오는 경우에는 가장 작은 수를 출력해야 된다.
			if(num == 0) {
				// 비어있는 경우에는 0을 출력
				if(pq.isEmpty()) System.out.println(0);
				else System.out.println(pq.poll());
			}else { // 0이 아닌 경우에는 수를 우선 순위 큐에 넣는다.
				pq.offer(num);
			}
		}
	}
}
