package day1231;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_11650_좌표정렬하기_201231 {

	static int N; // 점의 개수
	static class point implements Comparable<point>{
		int x, y;

		public point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(point o) {
			if(this.x > o.x) return 1;
			else if(this.x < o.x) return -1;
			else return this.y - o.y;
		}
	}
	static PriorityQueue<point> pq = new PriorityQueue<point>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			pq.offer(new point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		
		for (int i = 0; i < N; i++) {
			point p = pq.poll();
			System.out.println(p.x + " " + p.y);
		}
	}
}
