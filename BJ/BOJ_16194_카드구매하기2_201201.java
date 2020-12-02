package day1201;

import java.util.Scanner;

public class BOJ_16194_카드구매하기2_201201 {
	
	static int N; // 사려는 카드 개수
	static int[] dp, price; // 카드 팩의 가격

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		dp = new int[N+1];
		price = new int[N+1];
		
		for (int i = 1; i <= N; i++) {
			dp[i] = price[i] = sc.nextInt();
		}
		
		sc.close();
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= i; j++) {
				// 카드가 쌓이는 방식으로 해결
				dp[i] = Math.min(dp[i], dp[i-j]+price[j]);
			}
		}
		
		System.out.println(dp[N]);
	}

}
