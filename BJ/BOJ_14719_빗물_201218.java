package day1218;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14719_빗물_201218 {
	
	static int H, W, result; // 세로, 가로의 길이, 고이는 빗물의 총량
	static int[] block; // 블록

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		result = 0;
		block = new int[W];
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < W; i++) {
			block[i] = Integer.parseInt(st.nextToken());
		}
		
		// 현 위치를 기준으로 좌, 우 최대값 중 작은 쪽에서 현재 값을 빼서 결과에 추가
		for (int i = 1; i < W-1; i++) {
			int rightMax = findRight(i);
			int leftMax = findLeft(i);
			
			// 둘 다 현재보다는 높아야함
			if(rightMax > block[i] && leftMax > block[i]) {
				if(rightMax >= leftMax) result += (leftMax - block[i]);
				else result += (rightMax - block[i]);
			}
		}
		
		System.out.println(result);
	}
	
	public static int findRight(int idx) {
		int max = block[idx];
		
		for (int i = idx+1; i < W; i++) {
			max = Math.max(max, block[i]);
		}
		
		return max;
	}
	
	public static int findLeft(int idx) {
		int max = block[idx];
		
		for (int i = idx-1; i >= 0; i--) {
			max = Math.max(max, block[i]);
		}
		
		return max;
	}
}
