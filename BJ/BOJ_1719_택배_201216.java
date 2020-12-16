package day1216;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1719_택배_201216 {
	
	static int n, m; // 집하장의 수, 간선의 개수
	static int[][] map;
	static class house {
		int start, min;

		public house(int start, int min) {
			this.start = start;
			this.min = min;
		}
	}
	static house[][] houses;
	static Queue<house> q = new LinkedList<house>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n+1][n+1];
		houses = new house[n+1][n+1];
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			map[r][c] = map[c][r] = weight;
		}
		
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if(map[i][j] != 0) {
					// 아무것도 없는 경우
					if(houses[i][j] == null) houses[i][j] = new house(j, map[i][j]);
					else if(houses[i][j].min > map[i][j]) { // 기존에 존재하는 경로보다 짧은 경우
						houses[i][j].start = j;
						houses[i][j].min = map[i][j];
					}
					
					bfs(i, j, map[i][j]);
				}
			}
		}
		
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if(i == j) System.out.print("- ");
				else System.out.print(houses[i][j].start + " ");
			}
			System.out.println();
		}
	}

	public static void bfs(int same, int start, int weight) {
		q.offer(new house(start, weight));
		
		while(!q.isEmpty()) {
			house h = q.poll();
			
			for (int i = 1; i <= n; i++) {
				if(i == same) continue;
				
				if(map[h.start][i] != 0) {
					// 현재 자리에 아무것도 없는 경우
					if(houses[same][i] == null) {
						houses[same][i] = new house(start, h.min+map[h.start][i]);
						q.offer(new house(i, h.min+map[h.start][i]));
					}
					
					// 있는데 최소값보다 작은 경우
					if(houses[same][i].min > h.min+map[h.start][i]) {
						houses[same][i].start = start;
						houses[same][i].min = h.min+map[h.start][i];
						q.offer(new house(i,h.min+map[h.start][i]));
					}
				}
			}
		}
	}
}
