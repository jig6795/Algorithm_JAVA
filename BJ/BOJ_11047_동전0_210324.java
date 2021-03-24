package day0324;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11047_동전0_210324 {

	static int N, K; // 동전의 개수, 목적 값
	static int[] money; // 동전의 값
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		money = new int[N];
		for (int i = 0; i < N; i++) {
			money[i] = Integer.parseInt(br.readLine());
		}
		
		int count = 0; // 동전의 개수
		for (int i = N-1; i >= 0; i--) {
			// K를 모두 소진한 경우
			if(K == 0) break;
			else if(money[i] > K) continue;
			else { // K보다 크지 않은 상태에서 제일 크게 만들기
				while(money[i] <= K) {
					K -= money[i];
					count++;
				}
			}
			
		}
		
		System.out.println(count);
	}

}
