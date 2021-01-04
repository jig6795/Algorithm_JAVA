package day0104;

import java.util.Scanner;

public class BOJ_9251_LCS_210104 {
	
	static String string1, string2; // 문자열 1,2
	static int[][] dp;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		string1 = "#" + sc.nextLine();
		string2 = "#" + sc.nextLine();
		sc.close();
		
		int length1 = string1.length();
		int length2 = string2.length();
		
		dp = new int[length1][length2];
		
		for (int i = 1; i < length1; i++) {
			for (int j = 1; j < length2; j++) {
				if(string1.charAt(i) == string2.charAt(j)) {
					// 이전 길이에 +1
					dp[i][j] = dp[i-1][j-1] + 1;
				}else {
					// 다를 경우, 더 긴 쪽으로 따라간다.
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
				}
			}
		}
		
		System.out.println(dp[length1-1][length2-1]);
	}

}
