package day0222;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1859_백만장자프로젝트_210222 {

	static int T, N; // 테스트케이스 수, 예측 가능한 수,
	static long result; // 최대 이익
	static int[] price; // 가격
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			result = 0;
			
			st = new StringTokenizer(br.readLine(), " ");
			price = new int[N];
			for (int i = 0; i < N; i++) {
				price[i] = Integer.parseInt(st.nextToken());
			}
			
			int maxPrice = price[N-1];
			
			for (int i = N-2; i >= 0; i--) {
				if(price[i] >= maxPrice) {
					maxPrice = price[i];
				}else {
					result += (maxPrice - price[i]);
				}
			}
			
			System.out.println("#" + t + " " + result);
		}
	}

}
