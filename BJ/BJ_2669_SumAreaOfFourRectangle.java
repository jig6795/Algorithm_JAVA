/*
 * BJ 2669 직사각형 네개의 합집합의 면적 구하기
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_2669_SumAreaOfFourRectangle {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int[][] map = new int[101][101];
		StringTokenizer st;
		int count = 0;
		for (int i = 0; i < 4; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			for (int j = x1; j < x2; j++) {
				for (int k = y1; k < y2; k++) {
					if(map[j][k] == 1) continue;
					map[j][k] = 1;
					count++;
				}
			}
		}
		System.out.println(count);
	}

}
