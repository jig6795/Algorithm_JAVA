package day1213;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_11403_경로찾기_201213 {
	
	static int N; // 지도의 크기
	static int[][] map, result; // 지도
	static boolean[] isVisited;
	static Queue<Integer> q = new LinkedList<Integer>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		result = new int[N][N];
		isVisited = new boolean[N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < N; i++) {
			// 방문 여부 초기화
			Arrays.fill(isVisited, false);
			bfs(i);
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(result[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public static void bfs(int start) {
		q.offer(start);
		
		while(!q.isEmpty()) {
			int temp = q.poll();
			
			for (int i = 0; i < N; i++) {
				// 이어져 있다면 고!
				if(map[temp][i] == 1 && !isVisited[i]) {
					q.offer(i);
					isVisited[i] = true;
					// i -> j 가 가능한지 확인하는 것
					result[start][i] = 1;
				}
			}
		}
		
	} 
}
