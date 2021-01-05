package day0105;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1932_정수삼각형_210105 {
	
	static int n; // 삼각형의 크기
	static int[][] input, dp; // 입력되는 삼각형
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		n = Integer.parseInt(br.readLine());
		input = new int[n][n];
		dp = new int[n][n];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j <= i; j++) {
				input[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dp[0][0] = input[0][0];
		
		if(n > 1) {
			for (int i = 1; i < n; i++) { // 층
				for (int j = 0; j <= i; j++) {
					if(j == 0) { // / 무조건 맨 왼쪽 바로 위 선택
						dp[i][j] = dp[i-1][j] + input[i][j];
					}else if(j == i) { // 무조건 맨 오른쪽 바로 위 선택
						dp[i][j] = dp[i-1][j-1] + input[i][j];
					}else { // 바로 위 두 가지 중 최대값 선택
						dp[i][j] = Math.max(dp[i-1][j-1], dp[i-1][j]) + input[i][j];
					}
				}
			}
		}
		
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < n; i++) {
			max = Math.max(max, dp[n-1][i]);
		}
		
		System.out.println(max);
	}
}
