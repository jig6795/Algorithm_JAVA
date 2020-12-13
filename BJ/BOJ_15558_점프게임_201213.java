package day1213;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_15558_점프게임_201213 {
	
	static int N, K; // 지도 크기, K칸 이동
	static int[][] map; // 지도
	static boolean[][] isVisited;
	static class pos{
		int n, line; // 위치, 왼(0)/오(1)

		public pos(int n, int line) {
			this.n = n;
			this.line = line;
		}
	}
	static Queue<pos> q = new LinkedList<pos>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		sc.nextLine();
		
		map = new int[N][2];
		isVisited = new boolean[N][2];

		for (int i = 0; i < 2; i++) {
			String s = sc.nextLine();
			for (int j = 0; j < N; j++) {
				map[j][i] = s.charAt(j) - '0';
			}
		}
		
		sc.close();
		
		System.out.println(bfs(0,0));
	}
	
	public static int bfs(int start, int line) {
		int T = 0;
		int length = 0;
		
		q.offer(new pos(start, line));
		isVisited[start][line] = true;
		
		while(T < N) {
			if(q.isEmpty()) break;
			
			length = q.size();
			
			for (int i = 0; i < length; i++) {
				pos p = q.poll();
				
				// 1. 한 칸 앞
				// N을 넘어선 경우, 클리어
				if(p.n+1 >= N) return 1;
				
				// 유저가 위치한 장소가 이미 사라진 곳인 경우
				// 지도상에서 0인 경우
				// 이미 방문한 곳인 경우
				if(p.n+1 > T && map[p.n+1][p.line] != 0
						&& !isVisited[p.n+1][p.line]) {
					q.offer(new pos(p.n+1, p.line));
					isVisited[p.n+1][p.line] = true;
				}
				
				// 2. 한 칸 뒤
				// N을 넘어선 경우, 클리어
				if(p.n-1 >= N) return 1;
				
				if(p.n-1 > T && map[p.n-1][p.line] != 0
						&& !isVisited[p.n-1][p.line]) {
					q.offer(new pos(p.n-1, p.line));
					isVisited[p.n-1][p.line] = true;
				}
				
				// 3. 반대편 줄로 점프
				// N을 넘어선 경우, 클리어
				if(p.n+K >= N) return 1;
				
				int changeLine = 0;
				
				if(p.line == 0) changeLine = 1;
				
				if(p.n+K > T && map[p.n+K][changeLine] != 0
						&& !isVisited[p.n+K][changeLine]) {
					q.offer(new pos(p.n+K, changeLine));
					isVisited[p.n+K][changeLine] = true;
				}
			}
			T++;
		}
		return 0;
	}

}
