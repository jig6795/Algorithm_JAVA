package day0311;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2583_영역구하기_210311 {
	
	// delta
	static int[] dirR = {-1,1,0,0};
	static int[] dirC = {0,0,-1,1};
	static int M, N, K; // 세로(C), 가로(R), 직사각형 개수
	static boolean[][] paper; // 모눈종이
	static boolean[][] isVisited; // 방문여부
	static class point { 
		int r, c;

		public point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	static Queue<point> q = new LinkedList<point>();
	static ArrayList<Integer> result = new ArrayList<Integer>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		paper = new boolean[N][M];
		isVisited = new boolean[N][M];
		
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			int startR = Integer.parseInt(st.nextToken());
			int startC = Integer.parseInt(st.nextToken());
			int endR = Integer.parseInt(st.nextToken());
			int endC = Integer.parseInt(st.nextToken());
			
			for (int j = startR; j < endR; j++) {
				for (int k = startC; k < endC; k++) {
					paper[j][k] = true;
				}
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(!isVisited[i][j] && !paper[i][j]) {
					result.add(BFS(i,j));
				}
			}
		}
		
		Collections.sort(result);
		
		System.out.println(result.size());
		for (int i = 0; i < result.size(); i++) {
			System.out.println(result.get(i));
		}
	}

	private static int BFS(int x, int y) {
		q.offer(new point(x, y));
		isVisited[x][y] = true;
		
		int count = 1;
		
		while(!q.isEmpty()) {
			point p = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int nr = p.r + dirR[i];
				int nc = p.c + dirC[i];
				
				if(nr<0 || nc<0 || nr>=N || nc>=M 
						|| isVisited[nr][nc] 
						|| paper[nr][nc]) continue;
				
				q.offer(new point(nr, nc));
				isVisited[nr][nc] = true;
				count++;
			}
		}
		
		
		return count;
	}
}
