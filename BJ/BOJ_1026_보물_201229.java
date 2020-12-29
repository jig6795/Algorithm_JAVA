package day1229;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1026_보물_201229 {
	
	static int N; // 수의 개수
	static int[] A, B;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		A = new int[N];
		B = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringTokenizer next_st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
			B[i] = Integer.parseInt(next_st.nextToken());
		}
		
		// 둘 다 오름차순으로 정렬
		Arrays.sort(A);
		Arrays.sort(B);
		
		int sum = 0; // 최소 합
		for (int i = 0; i < N; i++) {
			sum += (A[i]*B[N-i-1]);
		}
		
		System.out.println(sum);
	}
}
