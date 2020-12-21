package day1221;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1766_문제집_201221 {
	
	static int N, M;
	static int[] sequence; // 진입 차수 정보
	static class Edge { // 간선 정보
		int start;
		ArrayList<Integer> end;
		
		public Edge(int start, ArrayList<Integer> end) {
			this.start = start;
			this.end = end;
		}
	}
	static Edge[] edges; // 연결된 간선을 확인하기 위한 배열
	static PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		sequence = new int[N+1];
		edges = new Edge[N+1];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			// A와 B
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			
			// 진입 차수 +
			sequence[B]++;
			
			// 간선 정보 삽입
			if(edges[A] == null) {
				ArrayList<Integer> temp = new ArrayList<Integer>();
				temp.add(B);
				edges[A] = new Edge(A, temp);
			}else {
				edges[A].end.add(B);
			}
		}
	
		// 진입 차수가 0인 것부터 우선 순위 큐에 넣는다.
		for (int i = 1; i <= N; i++) {
			if(sequence[i] == 0) {
				pq.offer(i);
				sequence[i] = -1;
			}
		}
		
		while(!pq.isEmpty()) {
			// 각 노드에 연결되어 있는 노드를 찾아 진입 차수를 뺀다.
			int node = pq.poll();
			System.out.print(node + " ");
			
			int length = 0;
			if(edges[node] != null) length = edges[node].end.size();
			
			for (int i = 0; i < length; i++) {
				sequence[edges[node].end.get(i)]--;
			}
			
			// 진입 차수가 0인 것부터 우선 순위 큐에 넣는다.
			for (int i = 1; i <= N; i++) {
				if(sequence[i] == 0) {
					pq.offer(i);
					sequence[i] = -1;
				}
			}
		}
	}

}
