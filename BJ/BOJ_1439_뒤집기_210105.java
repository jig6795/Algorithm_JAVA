package day0105;

import java.util.Scanner;

public class BOJ_1439_뒤집기_210105 {
	
	static String s; // 0, 1로 이루어진 문자열
	static int zero = 0, one = 0; // 0과 1로 이루어진 문자열의 갯수
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		s = sc.nextLine();
		sc.close();
		
		int length = s.length();
		
		if(s.charAt(0) == '0') zero++;
		else one++;
		
		for (int i = 1; i < length; i++) {
			if(s.charAt(i) != s.charAt(i-1)) {
				if(s.charAt(i) == '0') zero++;
				else one++;
			}
		}
		
		System.out.println(Math.min(one, zero));
	}
	
}
