package day0204;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1010_다리놓기_210204 {
	
	static int T, N, M;
	static int[][] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			dp = new int[N+1][M+1];
			
			for (int i = 2; i <= N; i++) {
				dp[i][1] = 0;
			}
			
			for (int i = 1; i <= M; i++) {
				dp[1][i] = i;
			}
			
			for (int i = 2; i <= N; i++) {
				for (int j = 2; j <= M; j++) {
					dp[i][j] = dp[i][j-1] + dp[i-1][j-1];
				}
			}
			
			
			System.out.println(dp[N][M]);
		}
		
	}
}
