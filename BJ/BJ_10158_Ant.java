/*
 * BJ 10158 개미
 */

import java.util.Scanner;

public class BJ_10158_Ant {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// 가로, 세로
		int w = sc.nextInt();
		int h = sc.nextInt();
		// 개미의 최초 위치
		int antX = sc.nextInt();
		int antY = sc.nextInt();
		// 총 주어진 시간
		int T = sc.nextInt();
		sc.close();
		
		int x = (antX + T) / w;
		int y = (antY + T) / h;
		int a = 0, b = 0;
		
		if(x % 2 == 0) a = (antX + T) % w;
		else a = w - (antX + T) % w;
		
		if(y % 2 == 0) b = (antY + T) % h;
		else b = h - (antY + T) % h;
		
		System.out.println(a + " " + b);
	}
}
