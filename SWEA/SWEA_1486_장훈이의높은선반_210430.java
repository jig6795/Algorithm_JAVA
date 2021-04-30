package day0430;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1486_장훈이의높은선반_210430 {
	
	static int T, N, B, min;
	static int[] heights, combArray;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			min = Integer.MAX_VALUE;
			
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			
			heights = new int[N];
			combArray = new int[N];
			
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				heights[i] = Integer.parseInt(st.nextToken());
			}
			
			comb(0,0);
			
			System.out.println("#" + t + " " + min);
		}
	}
	
	public static void comb(int idx, int combIdx) {
		int sum = sumComb(combArray);
		
		if(sum >= B) {
			min = Math.min(min, sum - B);
			return;
		}
		
		if(idx == N) return;
		
		combArray[combIdx] = heights[idx];
		comb(idx+1, combIdx+1);
		combArray[combIdx] = 0;
		comb(idx+1, combIdx);
	}

	public static int sumComb(int[] arr) {
		int sum = 0;
		
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
		}
		
		return sum;
	}
}
