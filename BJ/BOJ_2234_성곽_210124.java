package day0124;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2234_성곽_210124 {
	
	// delta => 남, 동, 북, 서
	static int[] dirR = {1,0,-1,0};
	static int[] dirC = {0,1,0,-1};
	static int n, m, roomCount, maxRoomSize, maxDoubleRoomSize; // m: row, n: col, 방 개수, 최대 방 크기, 두개의 방 최대 크기
	static int[][] castle; // 성의 벽 정보, 각 방 사이즈 정보
	static boolean[][] isVisited;
	static class room { 
		int r, c;

		public room(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	static Queue<room> q = new LinkedList<room>();
	static Queue<room> roomQ = new LinkedList<room>();
	static class roomSize {
		int size, num;

		public roomSize(int size, int num) {
			this.size = size;
			this.num = num;
		}
	}
	static roomSize[][] roomSizes;
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		roomCount = 0;
		maxRoomSize = Integer.MIN_VALUE;
		maxDoubleRoomSize = Integer.MIN_VALUE;
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		roomSizes = new roomSize[m][n];
		isVisited = new boolean[m][n];
		
		castle = new int[m][n];
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < n; j++) {
				castle[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				// 아직 방문하지 않은 방일 경우
				if(!isVisited[i][j]) {
					roomCount++;
					int num = BFS(i,j);
					maxRoomSize = Math.max(maxRoomSize, num);
					
					// 지나온 길에 방의 크기를 입력
					while(!roomQ.isEmpty()) {
						room ro = roomQ.poll();
						roomSizes[ro.r][ro.c] = new roomSize(num, roomCount); 
					}
				}
			}
		}
		
		// 이 성에 있는 방의 개수
		System.out.println(roomCount);
		
		// 가장 넓은 방의 넓이
		System.out.println(maxRoomSize);
		
		// 하나의 벽을 제거하여 얻을 수 있는 가장 넓은 방의 크기
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				// 각 방의 벽을 2진수로 표현
				String s = Integer.toBinaryString(castle[i][j]);
				while(s.length() < 4) {
					s = "0" + s;
				}
				
				for (int k = 0; k < 4; k++) {
					// 벽이 있을 경우
					if(s.charAt(k) == '1') {
						int nr = i + dirR[k];
						int nc = j + dirC[k];
						
						if(nr<0 || nr>=m || nc<0 || nc>=n
								|| roomSizes[i][j].num == roomSizes[nr][nc].num) continue;
						
						int sum = roomSizes[i][j].size + roomSizes[nr][nc].size;
						maxDoubleRoomSize = Math.max(maxDoubleRoomSize, sum);
					}
				}
			}
		}
		
		System.out.println(maxDoubleRoomSize);
	}
	
	public static int BFS(int row, int col) {
		int count = 0;
		
		q.offer(new room(row, col));
		roomQ.offer(new room(row, col));
		isVisited[row][col] = true;
		
		while(!q.isEmpty()) {
			room ro = q.poll();
			count++;
			
			// 각 방의 벽을 2진수로 표현
			String s = Integer.toBinaryString(castle[ro.r][ro.c]);
			while(s.length() < 4) {
				s = "0" + s;
			}
			
			for (int i = 0; i < 4; i++) {
				// 벽이 없는 경우
				if(s.charAt(i) == '0') {
					int nr = ro.r + dirR[i];
					int nc = ro.c + dirC[i];
					
					if(nr<0 || nr>=m || nc<0 || nc>=n
							|| isVisited[nr][nc]) continue;
					
					q.offer(new room(nr, nc));
					roomQ.offer(new room(nr, nc));
					isVisited[nr][nc] = true;
				}
			}
		}
		
		return count;
	}
}
