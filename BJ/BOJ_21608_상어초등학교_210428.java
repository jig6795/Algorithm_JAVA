package day0428;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_21608_상어초등학교_210428 {

	static int[] dirR = { -1, 1, 0, 0 };
	static int[] dirC = { 0, 0, -1, 1 };
	static int N, result;
	static int[][] map; // 반의 자리 위치
	static int[][] students; // 각 학생이 좋아하는 4명
	static int[] sequence; // 선생님이 정해주는 순서
	static int[] score = { 0, 1, 10, 100, 1000 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		map = new int[N + 1][N + 1];
		students = new int[N * N + 1][4];
		sequence = new int[N * N + 1];

		for (int i = 1; i <= N * N; i++) { // 총 명수는 N^2명
			st = new StringTokenizer(br.readLine(), " ");

			int num = Integer.parseInt(st.nextToken());
			sequence[i] = num;

			for (int j = 0; j < 4; j++) {
				students[num][j] = Integer.parseInt(st.nextToken());
			}
		}

		/* 1. 자리 배치 */
		/* 1-1. 가장 처음 사람은 무조건 (2,2)에 앉는다. */
		map[2][2] = sequence[1];

		for (int i = 2; i <= N * N; i++) {
			int cnt = 0;
			/* 1-2. 비어있는 칸 중 좋아하는 학생이 인접한 칸이 가장 많은 칸으로 자리 정한다. */
			int[][] likeCount = new int[N + 1][N + 1];

			/* 각 자리 인접한 칸 확인 */
			for (int j = 1; j <= N; j++) {
				for (int k = 1; k <= N; k++) {
					if (map[j][k] == 0) { // 만약 해당 자리가 비어 있다면 주변 확인
						for (int l = 0; l < 4; l++) {
							int nr = j + dirR[l];
							int nc = k + dirC[l];

							if (nr < 1 || nc < 1 || nr > N || nc > N || map[nr][nc] == 0)
								continue;

							for (int m = 0; m < 4; m++) {
								if (map[nr][nc] == students[sequence[i]][m]) {
									likeCount[j][k]++;
									break;
								}
							}
						}
					}
				}
			}

			/* 인접한 칸의 갯수 최대값 확인 */
			int max = Integer.MIN_VALUE;
			for (int j = 1; j <= N; j++) {
				for (int k = 1; k <= N; k++) {
					if (likeCount[j][k] != 0)
						max = Math.max(max, likeCount[j][k]);
				}
			}

			/* 여러 개가 존재할 수 있는 경우 확인 */
			for (int j = 1; j <= N; j++) {
				for (int k = 1; k <= N; k++) {
					if (likeCount[j][k] != max) {
						likeCount[j][k] = 0;
					} else {
						cnt++;
					}
				}
			}

			/* 1-3. 1-2.를 만족하는 칸 중 비어있는 칸이 가장 많은 칸 찾기 */
			if (cnt == 1) {
				a: for (int j = 1; j <= N; j++) {
					for (int k = 1; k <= N; k++) {
						if (likeCount[j][k] > 0) {
							map[j][k] = sequence[i];
							break a;
						}
					}
				}
			} else {
				int[][] emptyCount = new int[N + 1][N + 1];
				for (int j = 1; j <= N; j++) {
					for (int k = 1; k <= N; k++) {
						if (likeCount[j][k] > 0) { // 인접한 칸이 가장 많은 칸들 확인
							for (int l = 0; l < 4; l++) {
								int nr = j + dirR[l];
								int nc = k + dirC[l];

								if (nr < 1 || nc < 1 || nr > N || nc > N)
									continue;

								if (map[nr][nc] == 0) {
									emptyCount[j][k]++;
								}
							}
						}
					}
				}

				max = Integer.MIN_VALUE;
				for (int j = 1; j <= N; j++) {
					for (int k = 1; k <= N; k++) {
						if (emptyCount[j][k] != 0) {
							max = Math.max(max, emptyCount[j][k]);
						}
					}
				}

				/* 여러 개가 존재할 수 있는 경우 확인 */
				for (int j = 1; j <= N; j++) {
					for (int k = 1; k <= N; k++) {
						if (emptyCount[j][k] != max)
							emptyCount[j][k] = 0;
					}
				}

				/* 1-4. 좌상단에 최대한 가까운 위치에 배치 */
				b: for (int j = 1; j <= N; j++) {
					for (int k = 1; k <= N; k++) {
						if (emptyCount[j][k] > 0) {
							map[j][k] = sequence[i];
							break b;
						}
					}
				}
			}
		}

		/* 2. 만족도 계산 */
		int count = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				count = 0;

				for (int k = 0; k < 4; k++) {
					int nr = i + dirR[k];
					int nc = j + dirC[k];

					if (nr < 1 || nc < 1 || nr > N || nc > N)
						continue;

					for (int m = 0; m < 4; m++) {
						if (map[nr][nc] == students[map[i][j]][m]) {
							count++;
							break;
						}
					}
				}

				result += score[count];
			}
		}

		System.out.println(result);
	}

}
