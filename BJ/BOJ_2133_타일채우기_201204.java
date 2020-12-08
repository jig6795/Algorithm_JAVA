package day1204;

import java.util.Scanner;

public class BOJ_2133_타일채우기_201204 {

	static int N;
	static int[] dp;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		sc.close();
		
		dp = new int[N+1];
		dp[0] = 1; // 0인 경우, 경우의 수는 1이므로
		
		// N이 홀수일 경우, 타일을 채울 수 없다.
		if(N % 2 == 0) {
			dp[2] = 3;
			
			for (int i = 4; i <= N; i += 2) {
				// 직전 타일에 dp[2]의 타일 모양을 추가
				dp[i] = dp[i-2] * 3;
				
				// dp[2]와는 다른 모습인 경우 (4 이상)
				for (int j = 4; j <= i; j += 2) {
					dp[i] += dp[i-j]*2;
				}			
			}	
		}
		
		System.out.println(dp[N]);
	}

}
