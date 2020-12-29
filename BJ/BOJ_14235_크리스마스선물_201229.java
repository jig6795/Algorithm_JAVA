package day1229;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_14235_크리스마스선물_201229 {
	
	static int n; // 방문 횟수
	// 우선 순위 큐를 내림차순으로 변경하는 방법 (Integer일 경우만 가능)
	static PriorityQueue<Integer> pq = new PriorityQueue<Integer>(Collections.reverseOrder());

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		n = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			// 아이들을 만난건지, 거점지인지
			int operand = Integer.parseInt(st.nextToken());
			
			if(operand == 0) { // 아이들을 만난 경우
				if(pq.isEmpty()) System.out.println(-1);
				else System.out.println(pq.poll());
			}else { // 거점지에 도착한 경우
				for (int j = 0; j < operand; j++) {
					pq.offer(Integer.parseInt(st.nextToken()));
				}
			}
		}
	}
}
