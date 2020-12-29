package day1229;

import java.util.Scanner;

public class BOJ_5212_지구온난화_201229 {

	// delta
	static int[] dirR = {-1,1,0,0};
	static int[] dirC = {0,0,-1,1};
	static int R, C; // 세로, 가로
	static char[][] map; //지도
	static boolean[][] isChecked;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		R = sc.nextInt();
		C = sc.nextInt();
		sc.nextLine();
		
		isChecked = new boolean[R][C];
		map = new char[R][C];
		for (int i = 0; i < R; i++) {
			map[i] = sc.nextLine().toCharArray();
		}
		sc.close();
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				// 땅인 경우
				if(map[i][j] == 'X') {
					// 주변을 확인해서 바다인 면이 3개 이상이면 
					if(isChanged(i, j)) isChecked[i][j] = true;
				}
			}
		}
		
		// 바다로 바꾼다
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if(isChecked[i][j]) map[i][j] = '.';
			}
		}
		
		// 지도 자르기
		int startR = R, startC = C;
		int endR = 0, endC = 0;
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if(map[i][j] == 'X') {
					startR = Math.min(startR, i);
					startC = Math.min(startC, j);
					endR = Math.max(endR, i);
					endC = Math.max(endC, j);
				}
			}
		}
		
		for (int i = startR; i <= endR; i++) {
			for (int j = startC; j <= endC; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}

	public static boolean isChanged(int r, int c) {
		int count = 0;
		
		for (int i = 0; i < 4; i++) {
			int nr = r + dirR[i];
			int nc = c + dirC[i];
			
			// 지도 밖이거나 바다이면
			if(nr<0 || nc<0 || nr>=R || nc>=C || map[nr][nc] == '.') count++;
		}
		
		if(count >= 3) return true;
		else return false;
	}
}
