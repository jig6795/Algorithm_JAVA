package day1231;

import java.util.Scanner;

public class BOJ_10870_피보나치수5_201231 {
	
	static int n; // n번째 수
	static int[] fibo; // 피보나치 수열을 담을 배열

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		sc.close();

		fibo = new int[n+1];
		
		fibo[0] = 0;
		
		if(n > 0) {
			fibo[1] = 1;
			
			for (int i = 2; i <= n; i++) {
				fibo[i] = fibo[i-1] + fibo[i-2];
			}
		}
		
		System.out.println(fibo[n]);
	}
}
