package day0428;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_15900_나무탈출_210428 {

	static int N, answer; // 트리의 정점 개수
	static ArrayList<ArrayList<Integer>> tree; // 인접리스트
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		visited = new boolean[N + 1]; // 1번부터 시작하기 때문

		tree = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			tree.add(new ArrayList<Integer>());
		}

		// 간선의 개수는 정점-1개
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine(), " ");

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			tree.get(a).add(b);
			tree.get(b).add(a);
		}

		DFS(1, 0, visited);

		if (answer % 2 == 0) {
			System.out.println("No");
		} else {
			System.out.println("Yes");
		}
	}

	public static void DFS(int node, int count, boolean[] visited) {
		visited[node] = true;

		for (int next : tree.get(node)) {
			if (!visited[next]) {
				DFS(next, count + 1, visited);
			}
		}
		
		/* 루트 노드가 아니면서 사이즈가 1인 것은 리프 노드 */
		if (node != 1 && tree.get(node).size() == 1) {
			answer += count;
		}
	}
}
