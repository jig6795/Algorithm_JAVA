package day0311;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1405_미친로봇_210311 {
	
	// delta
	static int[] dirR = {-1,1,0,0};
	static int[] dirC = {0,0,-1,1};
	static int N; // 로봇의 N번 행동
	static double result;
	static double[] direction; // 동서남북 확률
	static boolean[][] isVisited = new boolean[29][29]; // 방문여부 - 최초 위치 (14,14)

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		
		// 방향 확률 입력 -> %단위이기 때문에 *0.01
		direction = new double[4];
		for (int i = 0; i < 4; i++) {
			direction[i] = Double.parseDouble(st.nextToken()) * 0.01;
		}
		
		DFS(14, 14, 1.0);

		System.out.println(result);
	}

	private static void DFS(int r, int c, double per) {
		// 정해진 횟수만큼 로봇이 이동했을 경우
		if(N == 0) {
			result += per; // 확률 누적
			return;
		}
		
		// 방문 처리
		isVisited[r][c] = true;
		
		for (int i = 0; i < 4; i++) {
			int nr = r + dirR[i];
			int nc = c + dirC[i];
			
			// 방문하지 않았고 해당 방향으로 갈 확률이 0보다 크다면
			if(!isVisited[nr][nc] && direction[i]>0) {
				N--;
				DFS(nr, nc, per*direction[i]);
				N++;
				isVisited[nr][nc] = false;
			}
		}
	}
}
