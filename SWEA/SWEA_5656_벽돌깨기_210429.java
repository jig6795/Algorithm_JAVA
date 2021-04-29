package day0429;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_5656_벽돌깨기_210429 {

	static int[] dirR = { -1, 1, 0, 0 };
	static int[] dirC = { 0, 0, -1, 1 };
	static int T, N, C, R, min;
	static int[][] board;

	static class location {
		int r, c, num;

		public location(int r, int c, int num) {
			this.r = r;
			this.c = c;
			this.num = num;
		}
	}

	static Queue<location> q = new LinkedList<location>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine(), " ");

			N = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			min = Integer.MAX_VALUE;

			board = new int[R][C];
			for (int i = 0; i < R; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < C; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			// 1. N번 만큼 돌았을 때, 남은 벽돌의 개수를 최소로 만들기 위한 경우를 찾는다.
			for (int c = 0; c < C; c++) {
				int[][] copyBoard = new int[R][C];

				for (int i = 0; i < R; i++) {
					for (int j = 0; j < C; j++) {
						copyBoard[i][j] = board[i][j];
					}
				}

				DFS(c, copyBoard, 0);
			}

			System.out.println("#" + t + " " + min);
		}
	}

	public static void DFS(int col, int[][] subBoard, int cnt) {
		if (cnt == N) {
			// 남은 벽돌의 최소값 확인
			int count = 0;
			for (int r = 0; r < R; r++) {
				for (int c = 0; c < C; c++) {
					if (subBoard[r][c] != 0)
						count++;
				}
			}
			min = Math.min(min, count);
			return;
		}

		// 주어진 col의 위치로 구슬을 떨어뜨린다.
		for (int r = 0; r < R; r++) {
			if (subBoard[r][col] != 0) {
				// Queue에 깨지는 위치 넣는다.
				for (int i = 0; i < 4; i++) {
					int nr = r;
					int nc = col;

					// 벽돌의 번호-1만큼 queue에 넣는다.
					for (int j = 0; j < subBoard[r][col] - 1; j++) {
						nr += dirR[i];
						nc += dirC[i];

						if (nr < 0 || nc < 0 || nr >= R || nc >= C || subBoard[nr][nc] == 0)
							continue;

						q.offer(new location(nr, nc, subBoard[nr][nc]));
						subBoard[nr][nc] = 0;
					}
				}
				subBoard[r][col] = 0;
				break;
			}
		}

		// 차례대로 깨뜨린다.
		while (!q.isEmpty()) {
			location l = q.poll();

			for (int i = 0; i < 4; i++) {
				int nr = l.r;
				int nc = l.c;

				// 벽돌의 번호-1만큼 queue에 넣는다.
				for (int j = 0; j < l.num - 1; j++) {
					nr += dirR[i];
					nc += dirC[i];

					if (nr < 0 || nc < 0 || nr >= R || nc >= C || subBoard[nr][nc] == 0)
						continue;

					q.offer(new location(nr, nc, subBoard[nr][nc]));
					subBoard[nr][nc] = 0;
				}
			}
		}

		// 모든 깨뜨림이 끝났을 때, 벽돌을 모두 내린다.
		for (int j = 0; j < C; j++) {
			ArrayList<Integer> list = new ArrayList<>();
			for (int k = R - 1; k >= 0; k--) {
				if (subBoard[k][j] > 0) {
					list.add(subBoard[k][j]);
					subBoard[k][j] = 0;
				}
			}

			int temp = R - 1;
			for (int k = 0; k < list.size(); k++) {
				subBoard[temp--][j] = list.get(k);
			}
		}

		// 다음 DFS를 돈다.
		for (int c = 0; c < C; c++) {
			int[][] tempBoard = new int[R][C];

			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					tempBoard[i][j] = subBoard[i][j];
				}
			}

			DFS(c, subBoard, cnt + 1);

			// 다시 되돌려주는 과정!
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					subBoard[i][j] = tempBoard[i][j];
				}
			}
		}

	}
}
