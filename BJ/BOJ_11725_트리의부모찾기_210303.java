package day0304;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_11725_트리의부모찾기_210303 {

	static int N; // 노드 개수
	static int[] parent; // 부모
	static boolean[] isVisited; // 방문 여부
	static ArrayList<Integer>[] list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		parent = new int[N+1];
		isVisited = new boolean[N+1];
		
		// 연결정보 & 초기화
		list = new ArrayList[N+1];
		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<Integer>();
		}
		
		// 각 간선 연결
		for (int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			list[a].add(b);
			list[b].add(a);
		}
		
		// 확인
		for (int i = 1; i <= N; i++) {
			if(!isVisited[i]) DFS(i);
		}
		
		// 출력
		for (int i = 2; i <= N; i++) {
			System.out.println(parent[i]);
		}
	}

	private static void DFS(int n) {
		if(isVisited[n]) return;
		
		isVisited[n] = true;
		
		for (int i : list[n]) {
			if(!isVisited[i]) {
				parent[i] = n;
				DFS(i);
			}
		}
	}
}
