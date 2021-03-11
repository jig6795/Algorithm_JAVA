package day0311;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_11724_연결요소의개수_210311 {
	
	static int N, M, result; // 정점의 개수, 간선의 개수
	static boolean[] isVisited; // 정점의 방문 여부
	static boolean[][] graph; // 방향이 없는 그래프
	static Queue<Integer> q = new LinkedList<Integer>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		isVisited = new boolean[N+1];
		graph = new boolean[N+1][N+1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			graph[a][b] = graph[b][a] = true;
		}
		
		for (int i = 1; i <= N; i++) {
			if(!isVisited[i]) {
				BFS(i);
				result++;
			}
		}
		
		System.out.println(result);
	}

	private static void BFS(int i) {
		q.offer(i);
		isVisited[i] = true;
		
		while(!q.isEmpty()) {
			int num = q.poll();
			
			for (int j = 1; j <= N; j++) {
				if(graph[num][j] && !isVisited[j]) {
					q.offer(j);
					isVisited[j] = true;
				}
			}
		}
	}
}
