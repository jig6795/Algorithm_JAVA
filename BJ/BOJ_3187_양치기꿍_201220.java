package day1220;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_3187_양치기꿍_201220 {
	
	static int R, C, sheep, wolf; // 세로, 가로, 양의 수, 늑대의 수
	static char[][] map; // 영역
	static boolean[][] isVisited;
	// delta
	static int[] dirR = {-1,1,0,0};
	static int[] dirC = {0,0,-1,1};
	static class pos {
		int r, c;

		public pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	static Queue<pos> q = new LinkedList<pos>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		sheep = 0;
		wolf = 0;
		
		map = new char[R][C];
		isVisited = new boolean[R][C];
		
		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
		}

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				// 늑대나 양인 경우, 아직 확인하지 않았다면 확인해보기
				if(map[i][j] == 'k' || map[i][j] == 'v') {
					if(!isVisited[i][j]) bfs(i, j);
				}
			}
		}
		
		System.out.println(sheep + " " + wolf);
	}

	public static void bfs(int row, int col) {
		int cntSheep = 0, cntWolf = 0;
		
		if(map[row][col] == 'v') cntWolf++;
		else cntSheep++;
		
		q.offer(new pos(row, col));
		isVisited[row][col] = true;
		
		while(!q.isEmpty()) {
			pos p = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int nr = p.r + dirR[i];
				int nc = p.c + dirC[i];
				
				if(nr<0 || nc<0 || nr>=R || nc>=C
						|| isVisited[nr][nc]
						|| map[nr][nc] == '#') continue;
				
				q.offer(new pos(nr, nc));
				isVisited[nr][nc] = true;
				
				if(map[nr][nc] == 'v') cntWolf++;
				else if(map[nr][nc] == 'k') cntSheep++;
			}
		}
		
		if(cntWolf < cntSheep) sheep += cntSheep;
		else wolf += cntWolf;
	}
}
