package day1229;

import java.util.Scanner;

public class BOJ_5904_Moo게임_201229 {
	
	static int N; // N번째

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		sc.close();
		
		int k = 3; // k = 0부터 moo
		long total = 3; // k번째까지의 수열 길이, 현재는 k = 0
		
		while(total < N) {
			k++;
			total += (total + k);
		}

		while(true) {
			// 현재 k번째 수열을 제외한 앞 뒤 길이
			long num = (total - k) / 2;
			
			if(N <= num) { // 이전 수열에 속하는 경우
				k--;
				total = num;
			}else if(N > num + k) { // 이전 수열에 속하는데 위치상 현재 k수열보다 뒤에 있는 경우
				N -= (num+k); // 이전 수열에 속하도록 이동
				k--;
				total = num;
			}else { // 현재 k수열에 속하는 경우
				N -= num;
				break;
			}
		}
		
		if(N == 1) System.out.println('m');
		else System.out.println('o');
	}

}
