package day0504;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ_1141_접두사_210504 {
	
	static int N;
	static String[] words;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		sc.nextLine();
		
		words = new String[N];
		for (int i = 0; i < N; i++) {
			words[i] = sc.nextLine();
		}
		
		Arrays.sort(words);
		
		for (int i = 0; i < N-1; i++) {
			if(words[i+1].startsWith(words[i])) {
				words[i] = "";
			}
		}
		
		int answer = 0;
		for (int i = 0; i < N; i++) {
			if(words[i] != "") answer++;
		}
		
		sc.close();
		
		System.out.println(answer);
	}

}
