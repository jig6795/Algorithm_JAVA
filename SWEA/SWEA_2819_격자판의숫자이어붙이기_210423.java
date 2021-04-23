package day0423;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class SWEA_2819_격자판의숫자이어붙이기_210423 {

	// delta
	static int[] dirR = { -1, 1, 0, 0 };
	static int[] dirC = { 0, 0, -1, 1 };
	static int T;
	static int[][] map;
	static HashSet<String> number = new HashSet<String>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			number.clear();
			map = new int[4][4];

			for (int i = 0; i < 4; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < 4; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					// 7자리 수
					DFS(i, j, "");
				}
			}

			System.out.println("#" + t + " " + number.size());
		}

	}

	public static void DFS(int row, int col, String s) {
		s += map[row][col];
		
		if(s.length() == 7) {
			number.add(s);
			return;
		}

		for (int k = 0; k < 4; k++) {
			int nr = row + dirR[k];
			int nc = col + dirC[k];

			if (nr < 0 || nc < 0 || nr >= 4 || nc >= 4)continue;

			DFS(nr, nc, s);
		}

	}
}
