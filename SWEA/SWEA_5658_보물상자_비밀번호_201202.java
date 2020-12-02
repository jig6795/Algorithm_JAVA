package day1202;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class SWEA_5658_보물상자_비밀번호_201202 {

	static int N, K, divN; // 숫자의 개수, 크기 순서, 4로 나눈 숫자의 개수
	static char[] clue; // 비밀번호를 만들 N개의 숫자
	static ArrayList<Integer> key = new ArrayList<Integer>(); // 비밀번호
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			divN = N/4;
			clue = new char[N];
			key.clear();
			clue = br.readLine().toCharArray();
			
			// 단서가 원래 순서와 같아질 동안 반복
			for (int i = 0; i < divN; i++) {
				for (int j = 0; j < N; j++) {
					// 비밀번호 조합하기
					if(j%divN == 0) {
						// 비밀번호를 먼저 만들고
						for (int k = j; k <= j+(divN-1); k++) {
							sb.append(clue[k]);
						}
						// 숫자로 바꿈
						changeToNum(sb.toString());
						// 초기화
						sb.setLength(0);
					}
				}
				
				// 회전
				char temp = clue[N-1];
				for (int j = N-2; j >= 0; j--) {
					clue[j+1] = clue[j];
				}
				clue[0] = temp;
			}
			
			// 내림차순으로 정렬
			Collections.sort(key);
			Collections.reverse(key);
			
			System.out.println("#"+t+" "+key.get(K-1));
		}
	}

	public static void changeToNum(String st) {
		int idx = 1; // 16진수 표현
		int num = 0;
		for (int i = st.length()-1; i >= 0; i--) {
			if(st.charAt(i) - '0' >= 0 && st.charAt(i) - '0' <= 9) {
				num += ((st.charAt(i) - '0')*idx);
			}else { // 10 ~ 16
				switch (st.charAt(i)) {
				case 'A':
					num += 10*idx;
					break;
				case 'B':
					num += 11*idx;
					break;
				case 'C':
					num += 12*idx;
					break;
				case 'D':
					num += 13*idx;
					break;
				case 'E':
					num += 14*idx;
					break;
				case 'F':
					num += 15*idx;
					break;
				}
			}
			idx *= 16;
		}
		// 중복이 있을 경우, 제외
		if(isNotIn(num)) key.add(num);
	}
	
	public static boolean isNotIn(int n) {
		for (int i = 0; i < key.size(); i++) {
			if(key.get(i) == n) return false;
		}
		return true;
	}
}
