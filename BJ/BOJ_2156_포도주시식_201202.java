package day1202;

import java.util.Scanner;

public class BOJ_2156_포도주시식_201202 {
	
	static int n; // 포도주 잔의 개수
	static int[] grape, dp;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		grape = new int[n+1];
		dp = new int[n+1];
		
		for (int i = 1; i <= n; i++) {
			grape[i] = sc.nextInt();
		}
		
		sc.close();
		
		dp[1] = grape[1];
		
		// 1이 넘어가면 계산되면 X
		if(n>1) dp[2] = grape[1] + grape[2];
		
		for (int i = 3; i <= n; i++) {
			dp[i] = Math.max(dp[i-2]+grape[i], dp[i-3]+grape[i-1]+grape[i]);
			
			// 반드시 마지막이 클 것이라는 보장 X
			dp[i] = Math.max(dp[i-1], dp[i]);
		}
		
		System.out.println(dp[n]);
	}
}