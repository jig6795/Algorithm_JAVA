package day0410;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2529_부등호_210410 {

	static int k;
	static long max, min;
	static String[] sign;
	static int[] result;
	static boolean[] isVisited = new boolean[10];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		k = Integer.parseInt(br.readLine());
		sign = new String[k];
		result = new int[k + 1];

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < k; i++) {
			sign[i] = st.nextToken();
		}

		max = Long.MIN_VALUE;
		min = Long.MAX_VALUE;

		for (int i = 0; i <= 9; i++) {
			result[0] = i;
			isVisited[i] = true;
			BFS(0);
			isVisited[i] = false;
		}
		
		System.out.println(max);
		
		if(Math.pow(10, k) > min) {
			System.out.println("0" + min);
		}else {
			System.out.println(min);
		}
	}

	public static void BFS(int index) {
		if (index == k) {
			check();
			return;
		}

		if (sign[index].equals("<")) {
			for (int i = result[index] + 1; i <= 9; i++) {
				if (!isVisited[i]) {
					result[index+1] = i;
					isVisited[i] = true;
					BFS(index+1);
					isVisited[i] = false;
				}
			}
		} else if (sign[index].equals(">")) {
			for (int i = result[index] - 1; i >= 0; i--) {
				if (!isVisited[i]) {
					result[index+1] = i;
					isVisited[i] = true;
					BFS(index+1);
					isVisited[i] = false;
				}
			}
		}
	}
	
	public static void check() {
		long ten = 1;
		long sum = 0;
		for (int i = k; i >= 0; i--) {
			sum += (result[i]*ten);
			ten *= 10;
		}
		
		max = Math.max(sum, max);
		min = Math.min(sum, min);
	}
}
