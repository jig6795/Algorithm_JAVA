package day0420;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17086_아기상어2_210420 {
	
	static int[] dirR = {-1,-1,-1,0,0,1,1,1};
	static int[] dirC = {-1,0,1,-1,1,-1,0,1};
	static int N, M, max;
	static int[][] map;
	static boolean[][] isVisited;
	static class point {
		int r, c, distance;

		public point(int r, int c, int distance) {
			this.r = r;
			this.c = c;
			this.distance = distance;
		}
	}
	static Queue<point> q = new LinkedList<point>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		max = Integer.MIN_VALUE;
		
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] == 0) {
					isVisited = new boolean[N][M];
					max = Math.max(max, BFS(i, j, 0));
				}
			}
		}
		
		System.out.println(max);
	}

	public static int BFS(int row, int col, int dis) {
		int min = Integer.MAX_VALUE;
		
		q.offer(new point(row, col, dis));
		isVisited[row][col] = true;
		
		while(!q.isEmpty()) {
			point p = q.poll();
			
			for (int i = 0; i < 8; i++) {
				int nr = p.r + dirR[i];
				int nc = p.c + dirC[i];
				
				if(nr<0 || nc<0 || nr>=N || nc>=M
						|| isVisited[nr][nc]) continue;
				
				if(map[nr][nc] == 1) {
					min = Math.min(min, p.distance+1);
					continue;
				}else {
					q.offer(new point(nr, nc, p.distance+1));
					isVisited[nr][nc] = true;
				}
			}
		}
		
		return min;
	}
}
