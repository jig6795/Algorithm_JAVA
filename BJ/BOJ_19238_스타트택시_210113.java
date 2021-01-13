package day0113;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_19238_스타트택시_210113 {
	
	// delta
	static int[] dirR = {-1,1,0,0};
	static int[] dirC = {0,0,-1,1};
	static int N, M, totalFuel, minDist; // 영역의 크기, 승객의 수, 초기 연료의 양, 택시와의 최소거리
	static int[][] startMap; // 활동할 영역의 지도, 0:빈칸, 1:벽
	static int[][] endMap;
	static class Passenger implements Comparable<Passenger>{
		int num, dist;
		int startR, startC;
		int endR, endC;
		
		public Passenger(int num, int dist, int startR, int startC, int endR, int endC) {
			this.num = num;
			this.dist = dist;
			this.startR = startR;
			this.startC = startC;
			this.endR = endR;
			this.endC = endC;
		}

		@Override
		public int compareTo(Passenger o) {
			if(this.startR > o.startR) return 1;
			else if(this.startR < o.startR) return -1;
			else {
				if(this.startC > o.startC) return 1;
				else return -1;
			}
		}
	}
	static Passenger[] passengers; // 승객의 출발지와 목적지
	static PriorityQueue<Passenger> pq = new PriorityQueue<Passenger>();
	static class location {
		int row, col;
		int distance;
		
		public location(int row, int col, int distance) {
			this.row = row;
			this.col = col;
			this.distance = distance;
		}
	}
	static location taxiLocation;
	static Queue<location> q = new LinkedList<location>();
	static boolean[][] isVisited; // 방문 여부 확인

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		totalFuel = Integer.parseInt(st.nextToken());

		startMap = new int[N+1][N+1];
		endMap = new int[N+1][N+1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j <= N; j++) {
				int n = Integer.parseInt(st.nextToken());
				startMap[i][j] = endMap[i][j] = n;
			}
		}
		
		// 최초의 택시 위치
		st = new StringTokenizer(br.readLine(), " ");
		int taxiR = Integer.parseInt(st.nextToken());
		int taxiC = Integer.parseInt(st.nextToken());
		taxiLocation = new location(taxiR, taxiC, 0);
		
		// 승객 등록 및 지도에 승객 번호 표시
		passengers = new Passenger[M+2];
		for (int i = 2; i <= M+1; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int startR = Integer.parseInt(st.nextToken());
			int startC = Integer.parseInt(st.nextToken());
			int endR = Integer.parseInt(st.nextToken());
			int endC = Integer.parseInt(st.nextToken());
			
			passengers[i] = new Passenger(i, 0, startR, startC, endR, endC);
			startMap[startR][startC] = i;
			endMap[endR][endC] = i;
		}
		
		// 승객의 수만큼 반복
		for (int i = 0; i < M; i++) {
			minDist = Integer.MAX_VALUE; 
			q.clear();
			pq.clear();
			
			// 모든 손님을 이동시킬 수 없는 경우
			if(totalFuel < 0) {
				totalFuel = -1;
				break;
			}
			
			isVisited = new boolean[N+1][N+1];
			BFS();
		}
		
		System.out.println(totalFuel);
	}

	public static void BFS() {
		q.offer(taxiLocation);
		isVisited[taxiLocation.row][taxiLocation.col] = true;
		
		// 승객이 존재하는 경우
		if(startMap[taxiLocation.row][taxiLocation.col] >= 2 && passengers[startMap[taxiLocation.row][taxiLocation.col]] != null) {
			pq.offer(new Passenger(startMap[taxiLocation.row][taxiLocation.col], 0, taxiLocation.row, taxiLocation.col, 
					passengers[startMap[taxiLocation.row][taxiLocation.col]].endR, passengers[startMap[taxiLocation.row][taxiLocation.col]].endC));
			q.poll();
		}
		
		while(!q.isEmpty()) {
			location l = q.poll();
			
			// 거리가 더 먼 곳은 방문 X
			if(l.distance < minDist) {
				for (int i = 0; i < 4; i++) {
					int nr = l.row + dirR[i];
					int nc = l.col + dirC[i];
					
					if(nr<1 || nc<1 || nr>N || nc>N
							|| isVisited[nr][nc] || startMap[nr][nc] == 1) continue;
					
					// 승객이 존재하는 경우
					if(startMap[nr][nc] >= 2 && passengers[startMap[nr][nc]] != null) {
						if(minDist >= l.distance+1) {
							pq.offer(new Passenger(startMap[nr][nc], l.distance+1, nr, nc, 
									passengers[startMap[nr][nc]].endR, passengers[startMap[nr][nc]].endC));
							minDist = Math.min(minDist, l.distance+1);
						}
					}
					
					q.offer(new location(nr, nc, l.distance+1));
					isVisited[nr][nc] = true;
				}
			}
		}
		
		// 택시 이동!
		moveTaxi();
	}
	
	public static void moveTaxi() {
		int recharge = 0;
		
		// 가장 가까운 승객 찾기
		Passenger p = null;
		while(!pq.isEmpty()) {
			p = pq.poll();
			
			if(p.dist == minDist) break;
		}
		
		if(p == null) {
			totalFuel = -1;
			return;
		}
		
		// 승객 삭제
		passengers[p.num] = null;
		
		// 연료 소모
		// 출발지까지 이동
		totalFuel -= p.dist;
		
		if(totalFuel < 0) return;
		
		// 택시 현 승객의 출발지 위치로 이동
		taxiLocation.row = p.startR;
		taxiLocation.col = p.startC;
		taxiLocation.distance = 0;
		
		// 도착지까지 이동
		q.clear();
		isVisited = new boolean[N+1][N+1];
		int endFuel = endBFS(p.endR, p.endC);
		
		if(endFuel == 0) {
			totalFuel = -1;
			return;
		}
		
		totalFuel -= endFuel;
		recharge += endFuel;
		
		if(totalFuel < 0) return;
		
		// 택시 현 승객의 목적지 위치로 이동
		taxiLocation.row = p.endR;
		taxiLocation.col = p.endC;
		taxiLocation.distance = 0;
		
		// 연료 충전
		totalFuel += (recharge*2);
	}
	
	public static int endBFS(int r, int c) {
		int fuel = 0;
		q.offer(taxiLocation);
		isVisited[taxiLocation.row][taxiLocation.col] = true;
		
		O: while (!q.isEmpty()) {
			location l = q.poll();
			
			// 거리가 더 먼 곳은 방문 X
			for (int i = 0; i < 4; i++) {
				int nr = l.row + dirR[i];
				int nc = l.col + dirC[i];

				if (nr<1 || nc<1 || nr>N || nc>N 
						|| isVisited[nr][nc] || endMap[nr][nc] == 1) continue;

				if(nr == r && nc == c) {
					fuel = l.distance + 1;
					break O;
				}
				
				q.offer(new location(nr, nc, l.distance + 1));
				isVisited[nr][nc] = true;
			}
		}
		
		return fuel;
	}
}
