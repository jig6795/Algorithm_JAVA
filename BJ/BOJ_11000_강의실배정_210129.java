package day0129;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_11000_강의실배정_210129 {
	
	static int N; // 수업의 개수
	static class lecture implements Comparable<lecture>{
		int start, end;

		public lecture(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(lecture o) {
			if(this.start - o.start > 0) return 1;
			else if(this.start - o.start < 0) return -1;
			else return this.end - o.end;
		}
	}
	static lecture[] lectures;
	// 우선 순위 큐에는 종료 시간을 입력, 오름차순으로
	static PriorityQueue<Integer> pq = new PriorityQueue<Integer>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		lectures = new lecture[N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			int startTime = Integer.parseInt(st.nextToken());
			int endTime = Integer.parseInt(st.nextToken());
			
			lectures[i] = new lecture(startTime, endTime);
		}
		
		// 1. 시작 시간 기준 오름 차순 정렬
		// 2. 종료 시간 기준 오름 차순 정렬
		Arrays.sort(lectures);
		pq.offer(lectures[0].end);
		
		for (int i = 1; i < N; i++) {
			// 가장 작은 종료 시간과 현재 시작 시간을 비교
			if(pq.peek() <= lectures[i].start) pq.poll();
			pq.offer(lectures[i].end);
		}

		System.out.println(pq.size());
	}

}
