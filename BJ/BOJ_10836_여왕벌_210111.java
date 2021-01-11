package day0111;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10836_여왕벌_210111 {
	
	static int M, N; // 벌집의 크기, 날짜
	static int[][] honeycomb; // 벌집
	static int[] growth; // 자라는 양

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		growth = new int[2*M];
		honeycomb = new int[M][M];
		
		for (int d = 0; d < N; d++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			// 0, 1, 2를 순서대로 입력
			int zero = Integer.parseInt(st.nextToken());
			int one = Integer.parseInt(st.nextToken());
			int two = Integer.parseInt(st.nextToken());
			
			// +1이 시작되는 지점
			growth[zero]++;
			
			// +2가 시작되는 지점
			growth[zero+one]++;
		}
		
		int sumGrowth = 0, index = 0;
		
		// (M-1,0) -> (0,0)
		for (int r = M-1; r >= 0; r--) {
			sumGrowth += growth[index++];
			honeycomb[r][0] = sumGrowth;
		}
		
		// (0,1) -> (0,M-1)
		for (int c = 1; c < M; c++) {
			sumGrowth += growth[index++];
			honeycomb[0][c] = sumGrowth;
		}
		
		// 기본적으로 1이 있었다고 생각하고 출력
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < M; j++) {
				if(i == 0 || j == 0) System.out.print((honeycomb[i][j]+1) + " ");
				// 0 -> 1 -> 2 순서로 진행되기 때문에 좌, 좌상, 상 중에 상이 가장 크다.
				else System.out.print((honeycomb[0][j]+1) + " ");
			}
			System.out.println();
		}
	}
}
