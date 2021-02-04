package day0204;

import java.util.Scanner;

public class BOJ_1436_영화감독숌_210204 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		sc.close();

		int num = 0;
		while(N > 0) {
			if(Integer.toString(++num).contains("666")) N--;
		}
		
		System.out.println(num);
	}

}
