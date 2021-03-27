package day0327;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1920_수찾기_210327 {
	
	static int N, M;
	static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < M; i++) {
			System.out.println(binarySearch(Integer.parseInt(st.nextToken())));
		}
	}

	public static int binarySearch(int n) {
		int low = 0;
		int high = N-1;
		int mid;
		
		while(low <= high) {
			mid = (high + low) / 2;
			
			if(arr[mid] == n) {
				return 1;
			}else if(arr[mid] > n) {
				high = mid-1;
			}else {
				low = mid+1;
			}
		}
		
		return 0;
	}
}
