package day1202;

import java.util.Scanner;

public class BOJ_1107_리모컨_201202 {
	
	static String N; // 이동하려는 채널 String
	static int M, min; // 이동하려는 채널, 망가지 버튼 개수, 최소 이동 횟수
	static int channel = 100; // 현재 채널
	static boolean[] isBroken = new boolean[10]; // 리모컨 버튼 고장 확인
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		N = sc.nextLine();
		M = sc.nextInt();
		min = Integer.MAX_VALUE;
		
		// 망가진 버튼 체크
		for (int i = 0; i < M; i++) {
			isBroken[sc.nextInt()] = true;
		}
		
		sc.close();
		
		// 단순히 +, -로 이동한 경우
		min = Math.min(min, Math.abs(Integer.parseInt(N)-channel));
		
		// 최대 2,000,000으로 설정하면 0 ~ 999,999까지 모든 범위를 커버할 수 있다.
		// -> 시간초과가 발생하지 않기 때문에 단순하게 풀이!
		for (int i = 0; i <= 2000000; i++) {
			int count = 0;
			String temp = sb.append(i).toString();
			// 모든 버튼이 고장나지 않았다면
			if(isAvailable(temp)) {
				count = (temp.length()+Math.abs(Integer.parseInt(N)-Integer.parseInt(temp)));
				if(min > count) min = count; 
			}
			sb.setLength(0);
		}

		System.out.println(min);
	}
	
	public static boolean isAvailable(String s) {
		for (int i = 0; i < s.length(); i++) {
			if(isBroken[s.charAt(i)-'0']) return false;
		}
		return true;
	}
}
