package day0105;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2503_숫자야구_210105 {
	
	static int N, result; // 질문의 수, 가능성이 있는 답의 개수
	static class question {
		String number;
		int strike, ball;
		
		public question(String number, int strike, int ball) {
			super();
			this.number = number;
			this.strike = strike;
			this.ball = ball;
		}
	}
	static question[] questions; // 질문 모음

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		questions = new question[N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			String num = st.nextToken();
			int s = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			questions[i] = new question(num, s, b);
		}

		// 100 ~ 999 까지 확인
		for (int i = 123; i <= 987; i++) {
			if(!Integer.toString(i).contains("0") 
					&& !hasSameElement(Integer.toString(i))
					&& checkingAnswer(Integer.toString(i))) result++;
		}
		
		System.out.println(result);
	}
	
	// 같은 숫자를 제외
	public static boolean hasSameElement(String i) {
		for (int j = 0; j < 2; j++) {
			for (int k = j+1; k < 3; k++) {
				if(i.charAt(j) == i.charAt(k)) return true;
			}
		}
		
		return false;
	}

	// 모든 질문에 만족하는지 확인
	public static boolean checkingAnswer(String n) {
		for (int i = 0; i < N; i++) {
			if(!isOk(n, i)) return false;
		}
		return true;
	}
	
	// 각 질문과 해당 숫자가 맞는지 확인
	public static boolean isOk(String num, int idx) {
		int s = 0, b = 0; // 스트라이크, 볼 수
		question q = questions[idx];
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if(q.number.charAt(i) == num.charAt(j)) {
					if(i == j) s++;
					else b++;
				}
			}
		}
		
		if(s == q.strike && b == q.ball) return true;
		else return false;
	}
}
