package day0118;

import java.util.Scanner;

public class BOJ_1543_문서검색_210118 {
	
	static String doc, find; // 문서, 찾고자하는 단어 
	static int docLength, findLength, answer;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		doc = sc.nextLine();
		find = sc.nextLine();
		sc.close();
		
		answer = 0;
		docLength = doc.length();
		findLength = find.length();
		
		for (int i = 0; i < docLength; i++) {
			// 확인할 문서의 길이가 찾고자하는 단어보다 짧은 경우, 종료
			if(docLength-i < findLength) break;
			
			// 비교해서 같으면 개수+1, 단어 길이만큼 뒤로 이동
			if(isContained(i)) {
				answer++;
				i += (findLength-1);
			}
		}
		
		System.out.println(answer);
	}

	public static boolean isContained(int index) {
		for (int i = 0; i < findLength; i++) {
			if(doc.charAt(i+index) != find.charAt(i)) return false;
		}
		return true;
	}
}
