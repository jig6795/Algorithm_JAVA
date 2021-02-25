package day0225;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_7562_나이트의이동_210225 {
	
	// delta
	static int[] dirR = {-2,-2,-1,1,2,2,1,-1};
	static int[] dirC = {-1,1,2,2,1,-1,-2,-2};
	static int T, N, minMove; // 테스트케이스 수, 체스판 한 변의 길이, 최소 이동거리
	static int[][] isVisited; // 방문 여부 -> 최소 이동거리로 표시
	static class point {
		int r, c, move;

		public point(int r, int c, int move) {
			this.r = r;
			this.c = c;
			this.move = move;
		}
	}
	static point start, end; // 시작점, 끝점
	static Queue<point> q = new LinkedList<point>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());
			isVisited = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					isVisited[i][j] = -1;
				}
			}
			
			// 나이트가 현재 있는 칸
			st = new StringTokenizer(br.readLine(), " ");
			start = new point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0);
			
			// 나이트가 이동하려는 칸
			st = new StringTokenizer(br.readLine(), " ");
			end = new point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0);
			
			minMove = Integer.MAX_VALUE;
			BFS(start.r, start.c);
			
			System.out.println(minMove);
		}

	}

	private static void BFS(int startR, int startC) {
		q.offer(new point(startR, startC, 0));
		isVisited[startR][startC] = 0;
		
		while(!q.isEmpty()) {
			point p = q.poll();
			
			// 목적지에 도달했다면 
			if(p.r == end.r && p.c == end.c) minMove = Math.min(minMove, p.move);
			
			// 이동 시작
			for (int i = 0; i < 8; i++) {
				int nr = p.r + dirR[i];
				int nc = p.c + dirC[i];
				
				if(nr<0 || nc<0 || nr>=N || nc>=N
						|| (isVisited[nr][nc] != -1 && p.move+1 >= isVisited[nr][nc])) continue;
				
				q.offer(new point(nr, nc, p.move+1));
				isVisited[nr][nc] = p.move+1;
			}
		}
		
	}
	
}
