package day0415;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class BOJ_1339_단어수학_210415 {
	
	static int N, sum; // 단어의 개수, 정답
	static ArrayList<String> word = new ArrayList<String>();
	static long[] wordCount = new long[26]; // 등장하는 수를 자리수로 계산
	static int[] wordSeq = new int[26]; // 단어의 번호
	static ArrayList<Integer> wordList = new ArrayList<Integer>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		sc.nextLine();
		for (int i = 0; i < N; i++) {
			word.add(sc.nextLine());
		}
		sc.close();

		// 단어에 해당하는 수 찾기 시작
		for (int i = 0; i < N; i++) {
			String w = word.get(i);
			
			// 해당 자리수에 해당하는 값 찾기
			for (int j = 0; j < w.length(); j++) {
				wordCount[(int)w.charAt(j) - 65] += (Math.pow(10, (w.length()-j-1)));
			}
		}
		
		for (int i = 0; i < 26; i++) {
			if(wordCount[i] > 0) {
				wordList.add((int) wordCount[i]);
			}
		}
		Collections.sort(wordList, Collections.reverseOrder());
		
		// 수 계산
		for (int i = 0; i < wordList.size(); i++) {
			sum += ((9-i) * wordList.get(i)); 
		}
		
		System.out.println(sum);
	}

}
