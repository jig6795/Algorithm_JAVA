package day1203;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_9466_텀프로젝트_201203 {
	
	static int n, count; // 학생의 수
	// 선호하는 학생의 번호
	static int[] prefer;
	// 속해있는지 아닌지
	static boolean[] isJoined, isVisited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			n = Integer.parseInt(br.readLine());
			count = 0;
			prefer = new int[n+1];
			isJoined = new boolean[n+1];
			isVisited = new boolean[n+1];
			
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 1; i <= n; i++) {
				prefer[i] = Integer.parseInt(st.nextToken());
			}
			
			for (int i = 1; i <= n; i++) {
				dfs(i);
			}
			
			System.out.println(n - count);
		}
	}
	
	public static void dfs(int idx) {
		if (isVisited[idx]) return; 
		
		isVisited[idx] = true;
		int next = prefer[idx];
		
		if (!isVisited[next]) { // 아직까지 방문하지 않았다면
			dfs(next);
		}else {
			if(!isJoined[next]) {
				count++;
				for (int i = next; i != idx; i = prefer[i]) {
					count++;
				}
			}
		}
		
		// 이미 사용된 번호
		isJoined[idx] = true;
	}
}
