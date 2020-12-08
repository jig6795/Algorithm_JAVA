package day1204;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_10026_적록색약_201204 {
	
	static int N, normalGroupCount, redGreenGroupCount; // 그림의 크기
	static char[][] picture; // 그림
	static boolean[][] isVisited; // 확인 여부
	// delta
	static int[] dirR = {-1,1,0,0};
	static int[] dirC = {0,0,-1,1};
	static class point {
		int row, col;

		public point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	static Queue<point> q = new LinkedList<point>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		sc.nextLine();
		isVisited = new boolean[N][N];
		picture = new char[N][N];
		
		for (int i = 0; i < N; i++) {
			picture[i] = sc.nextLine().toCharArray();
		}
		sc.close();
		
		// 적록색약이 아닌 사람이 본 경우
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(!isVisited[i][j]) {
					bfs(i, j, picture[i][j]);
					normalGroupCount++;
				}
			}
		}
		
		// isVisited배열 초기화
		// 그림에서 R부분을 G으로 바꿈
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				isVisited[i][j] = false;
				if(picture[i][j] == 'R') picture[i][j] = 'G';
			}
		}
		
		// 적록색약인 경우
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(!isVisited[i][j]) {
					bfs(i, j, picture[i][j]);
					redGreenGroupCount++;
				}
			}
		}
		
		System.out.print(normalGroupCount + " " + redGreenGroupCount);
	}

	public static void bfs(int r, int c, char section) {
		q.offer(new point(r, c));
		isVisited[r][c] = true;
		
		while(!q.isEmpty()) {
			point p = q.poll();
		
			for (int i = 0; i < 4; i++) {
				int nr = p.row + dirR[i];
				int nc = p.col + dirC[i];
				
				if(nr<0 || nc<0 || nr>=N || nc>=N 
						|| isVisited[nr][nc] 
						|| picture[nr][nc] != section) continue;
				
				q.offer(new point(nr, nc));
				isVisited[nr][nc] = true;
			}
		}
	}
}
