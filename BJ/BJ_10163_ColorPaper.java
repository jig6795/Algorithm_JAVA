/*
 * BJ 10163 색종이
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BJ_10163_ColorPaper {
	
	static class rect{
		int x1, y1, x2, y2;

		public rect(int x1, int y1, int x2, int y2) {
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int[][] map = new int[101][101];
		Stack<rect> s = new Stack<rect>();
		Stack<Integer> answer = new Stack<Integer>();
		StringTokenizer st;
		int N = Integer.parseInt(in.readLine());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			int x2 = x1 + w - 1;
			int y2 = y1 + h - 1;
			
			s.push(new rect(x1, y1, x2, y2));
		}
		
		for (int i = 0; i < N; i++) {
			rect r = s.pop();
			int count = 0;
			for (int j = r.x1; j <= r.x2; j++) {
				for (int k = r.y1; k <= r.y2; k++) {
					if(map[j][k] == 1) continue;
					map[j][k] = 1;
					count++;
				}
			}
			answer.push(count);
		}
		
		while(!answer.isEmpty()) System.out.println(answer.pop());
	}

}
