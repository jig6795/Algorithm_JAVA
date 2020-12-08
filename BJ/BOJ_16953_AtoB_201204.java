package day1204;

import java.util.Scanner;

public class BOJ_16953_AtoB_201204 {
	
	static int A, B;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		A = sc.nextInt();
		B = sc.nextInt();
		sc.close();
		
		int count = 1; // 몇 개의 수를 거치는지, B를 이미 포함
		// B -> A로 풀이
		while(B > A) {
			count++;
			if(B % 2 == 0) { // 짝수는 2로 나눈다.
				B /= 2;
			}else { // 홀수의 경우
				// 마지막 자리의 수가 1이면 10을 나눈다.
				if(B % 10 == 1) B /= 10;
				// 다른 홀수의 경우, 더 이상 진행 X
				else break;
			}
			if(B == A) {
				System.out.println(count);
				return;
			}
		}
		
		System.out.println(-1);
	}
}
