package day0104;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_20529_가장가까운세사람의심리적거리_210104 {
	
	static int T, N, minDist, combN, combR = 3; // 테스트 케이스, 학생의 수
	// MBTI 유형 목록
	static String[] MBTI = {
			"ISTJ", "ISFJ", "INFJ", "INTJ"
			, "ISTP", "ISFP", "INFP", "INTP"
			, "ESTP", "ESFP", "ENFP", "ENTP"
			, "ESTJ", "ESFJ", "ENFJ", "ENTJ"};
	static int[] isContained; // 포함되어 있는지 확인
	static String[] student; // 학생의 MBTI 유형 목록
	static String[] checkingThree;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());
			minDist = Integer.MAX_VALUE;
			isContained = new int[16];
			checkingThree = new String[combR];
			combN = 0;
			
			// 각 학생의 MBTI 확인
			int maxCount = Integer.MIN_VALUE;
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				String temp = st.nextToken();
				for (int j = 0; j < 16; j++) {
					if(MBTI[j].equals(temp)) {
						isContained[j]++;
						combN++;
						maxCount = Math.max(maxCount, isContained[j]);
					}
				}
			}
			
			// MBTI 유형이 같은 학생이 3명 이상일 경우 => 무조건 심리적 거리 0
			if(maxCount >= 3) { 
				minDist = 0;
			}else {
				student = new String[combN];
				int idx = 0;
				
				for (int i = 0; i < 16; i++) {
					if(isContained[i] != 0) {
						for (int j = 0; j < isContained[i]; j++) {
							student[idx++] = MBTI[i];
						}
					}
				}
				
				comb(0,0);
			}
			
			System.out.println(minDist);
		}
	}

	public static void comb(int idx, int tgtIdx) {
		// 3명이 되면 심리적 거리 확인
		if (tgtIdx == combR) {
			int sum = 0;
			for (int i = 0; i < combR-1; i++) {
				for (int j = i + 1; j < combR; j++) {
					sum += dist(checkingThree[i], checkingThree[j]);
				}
			}
			minDist = Math.min(minDist, sum);
			return;
		}

		if (idx == combN) return;

		checkingThree[tgtIdx] = student[idx];
		comb(idx + 1, tgtIdx + 1);
		comb(idx + 1, tgtIdx);
	}
	
	public static int dist(String a, String b) {
		int distance = 0;
		
		for (int i = 0; i < 4; i++) {
			if(a.charAt(i) != b.charAt(i)) distance++;
		}
		
		return distance;
	}
}
