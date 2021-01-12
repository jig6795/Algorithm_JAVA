package day0112;

import java.util.Scanner;

public class BOJ_2011_암호코드_210112 {
	
	static String s;
	static char[] ch;
	static int[] code, dp; // 암호, 암호 개수
	static int length; // 암호의 길이

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		s = sc.nextLine();
		sc.close();

		length = s.length();
		dp = new int[length+1];
		
		// String to Char
		ch = new char[length];
		ch = s.toCharArray();
		
		// Char to Int
		code = new int[length];
		for (int i = 0; i < length; i++) {
			code[i] = ch[i] - '0';
		}
		
		// 무조건 처음은 0이 아닌 이상 1개
		if(code[0] == 0) {
			System.out.println(0);
			return;
		}else dp[1] = 1;
		
		if(length >= 2) {
			// 현재 수가 0이면 이전 수와 합이 26이하면 그대로
			// 초과면 종료
			if(code[1] == 0) {
				if(code[0]*10 + code[1] <= 26) dp[2] = 1;
				else {
					System.out.println(0);
					return;
				}
			}else if(code[0]*10 + code[1] <= 26) { // 이전 수*10 + 현재수 <= 26 : +1
				dp[2] = 2;
			}else if(code[0]*10 + code[1] > 26) { // 이전 수*10 + 현재수 <= 26 : +1
				dp[2] = 1;
			}
		}
		
		if(length >= 3) {
			for (int i = 2; i < length; i++) {
				// 0 등장 -> 이전 수 > 2 : 0출력
				//     	-> 이전수 <= 2 : 앞앞으로 돌아가기
				if(code[i] == 0) {
					if(code[i-1] == 0 || code[i-1] > 2) {
						System.out.println(0);
						return;
					}else if(code[i-1] <= 2) {
						dp[i+1] = dp[i-1];
					}
				}else {
					// 이전 수가 0이면 : 그대로
					// 이전 수*10 + 현재수 <= 26 : 피보나치 수열
					// 이전 수*10 + 현재수 > 26 : 그대로
					if(code[i-1] == 0) {
						dp[i+1] = dp[i]%1000000;
					}else if(code[i-1]*10 + code[i] <= 26) {
						dp[i+1] = (dp[i] + dp[i-1])%1000000;
					}else if(code[i-1]*10 + code[i] > 26) {
						dp[i+1] = dp[i]%1000000;
					}
				}
			}
		}
		
		System.out.println(dp[length]);
	}

}
