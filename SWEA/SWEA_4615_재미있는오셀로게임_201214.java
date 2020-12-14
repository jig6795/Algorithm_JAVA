package day1214;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_4615_재미있는오셀로게임_201214 {

	// delta -> 8방향
	static int[] dirR = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] dirC = { -1, 0, 1, -1, 1, -1, 0, 1 };
	static int N, M; // 보드 크기, 놓는 횟수
	static int[][] board; // 오셀로 게임 보드

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			int black = 0, white = 0;
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			board = new int[N + 1][N + 1];
			
			// 초기 돌 두기
			board[N/2+1][N/2] = board[N/2][N/2+1] = 1;
			board[N/2][N/2] = board[N/2+1][N/2+1] = 2;

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine(), " ");

				// 돌 위치, 색상(흑:1, 백:2)
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());

				// 돌 놓기
				board[r][c] = Integer.parseInt(st.nextToken());

				// 주변 확인하기
				for (int j = 0; j < 8; j++) {
					int nr = r + dirR[j];
					int nc = c + dirC[j];

					// 게임판 범위에서 벗어나거나 아무것도 놓이지 않은 경우
					if (nr < 1 || nc < 1 || nr > N || nc > N 
							|| board[nr][nc] == 0) continue;

					// 자신과 다른 색상의 돌이 놓여있는 경우
					if (board[nr][nc] != board[r][c]) {
						// 자신과 같은 색상이 나올때까지 확인
						if(isSameColor(r, c, j, board[r][c])) {
							// 자신과 같은 색상이 나올때까지 현재 색으로 변경
							while(board[r][c] != board[nr][nc]) {
								board[nr][nc] = board[r][c];
								nr += dirR[j];
								nc += dirC[j];
							}
						}
					}
				}
			}
			
			// 정답 확인
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if(board[i][j] == 1) black++;
					else if(board[i][j] == 2) white++;
				}
			}
			
			System.out.println("#" + t + " " + black + " " + white);
		}
	}

	public static boolean isSameColor(int row, int col, int idx, int color) {
		int nr = row + dirR[idx];
		int nc = col + dirC[idx];
		
		while(true) {
			nr += dirR[idx];
			nc += dirC[idx];
			
			if (nr < 1 || nc < 1 || nr > N || nc > N 
					|| board[nr][nc] == 0) break;
			
			// 같은 색상이 있는 경우
			if(board[nr][nc] == color) return true;
		}
		
		return false;
	} 
}
