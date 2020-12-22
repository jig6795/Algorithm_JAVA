package day1222;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_4386_별자리만들기_201222 {
	
	static int n; // 별의 개수
	static class star {
		double x, y;

		public star(double x, double y) {
			this.x = x;
			this.y = y;
		}
	}
	static star[] stars; // 별 위치 배열
	static int[] parents;
	static class distance implements Comparable<distance>{
		int starA, starB;
		double distance;
		
		public distance(int starA, int starB, double distance) {
			this.starA = starA;
			this.starB = starB;
			this.distance = distance;
		}

		@Override
		public int compareTo(distance o) {
			return (int) (this.distance - o.distance);
		}
	}
	static PriorityQueue<distance> pq = new PriorityQueue<distance>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		n = Integer.parseInt(br.readLine());
		stars = new star[n+1];
		parents = new int[n+1];
		
		// 별 좌표 입력
		for (int i = 1; i <= n; i++) {
			parents[i] = i;
			st = new StringTokenizer(br.readLine(), " ");
			stars[i] = new star(Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()));
		}

		for (int i = 1; i < n; i++) {
			for (int j = i+1; j <= n; j++) {
				// 각 별들 사이의 거리 계산
				double dist = (Math.sqrt(Math.pow(stars[i].x - stars[j].x, 2) + Math.pow(stars[i].y - stars[j].y, 2)));
				
				// 우선 순위 큐에 넣어 오름차순으로 정렬
				pq.offer(new distance(i, j, dist));
			}
		}
		
		// 가장 거리가 짧은 간선부터 연결한다. => Kruskal Algorithm
		// 단, cycle이 생성되지 않도록 주의
		double result = 0.0; // 최소 거리
		int numOfEdge = 0; // 이어진 간선의 수
		
		while(!pq.isEmpty()) {
			distance d = pq.poll();
			
			if(union(d.starA, d.starB)) {
				result += d.distance;
				numOfEdge++;
			}
			
			if(numOfEdge == n-1) break;
		}
		
		System.out.printf("%.2f", result);
	}

	public static int find(int a) {
		if(parents[a] == a) return a;
		return parents[a] = find(parents[a]);
	}
	
	public static boolean union(int a, int b) {
		int ga = find(a);
		int gb = find(b);
		
		if(ga == gb) return false;
		
		if(ga > gb) parents[ga] = gb;
		else parents[gb] = ga;
		return true;
	}
}
