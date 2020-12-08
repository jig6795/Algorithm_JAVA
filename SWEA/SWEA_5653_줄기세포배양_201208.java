package day1208;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_5653_줄기세포배양_201208 {

	// Row, Column, Time
	static int N, M, K;
	// delta -> 0:상, 1:하, 2:좌, 3:우
	static int[] dirR = { -1, 1, 0, 0 };
	static int[] dirC = { 0, 0, -1, 1 };

	// 줄기 세포
	static class cell {
		// 위치(r,c), 활성 상태가 되는 시간, 활성 상태 지속 시간
		int r, c, activeTime, life;
		boolean state; // 활성 상태

		public cell(int r, int c, int activeTime, int life, boolean state) {
			this.r = r;
			this.c = c;
			this.activeTime = activeTime;
			this.life = life;
			this.state = state;
		}
	}

	// 줄기 세포가 분포되어 있는 영역을 나타내는 Queue
	static Queue<cell> q = new LinkedList<cell>();
	// 줄기 세포가 배양되는 영역 => 3번째 영역 [0] : 생명력, [1] : 번식 시점
	static int[][][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new int[N + K * 2][M + K * 2][2];
			q.clear();

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < M; j++) {
					int tempTime = Integer.parseInt(st.nextToken());
					// 0인 경우, 줄기 세포가 존재 X
					if (tempTime != 0) {
						map[i+K][j+K][0] = tempTime;
						q.offer(new cell(i+K, j+K, 0, tempTime, false));
					}
				}
			}

			for (int i = 1; i <= K; i++) {
				bfs(i);
			}
			
			// 비활성 + 활성 상태 줄기 세포 개수 확인
			int count = 0; // 비활성 + 활성 상태의 줄기 세포 수
			while(!q.isEmpty()) {
				cell c = q.poll();
				if(c.life == map[c.r][c.c][0]) count++;
			}
			System.out.println("#" + t + " " + count);
		}
	}
	
	public static void bfs(int time) {
		// Queue에 있는 것을 전부 깨내는 것이 아니기 때문
		int length = q.size();
		
		for (int i = 0; i < length; i++) {
			cell now = q.poll();
			int nextTime = now.activeTime + 1;
			
			// 현재 확인해야할 줄기 세포보다 더 높은 생명력을 가진 줄기 세포가 이미 자리를 차지하고 있는 경우 
			if(now.life != map[now.r][now.c][0]) continue;
			
			// 활성 상태이면서 이제 막 확성 상태가 된 경우
			if(now.state && now.activeTime == 0) {
				for (int j = 0; j < 4; j++) {
					int nr = now.r + dirR[j];
					int nc = now.c + dirC[j];
					
					// 땅에 이미 줄기 세포가 있는 경우
					if(map[nr][nc][0] != 0) {
						// 이전에 땅에 번식한 경우
						if(map[nr][nc][1] < time) continue;
						// 이번에 번식한 경우
						else if(map[nr][nc][1] == time){
							// 현재 맵에 번식한 생명력보다 지금 세포의 생명력이 높은 경우
							if(map[nr][nc][0] < now.life) {
								map[nr][nc][0] = now.life;
								q.offer(new cell(nr, nc, 0, now.life, false));
							}
						}
					}else { // 현재 땅에 줄기 세포가 없는 경우
						map[nr][nc][0] = now.life;
						map[nr][nc][1] = time;
						q.add(new cell(nr, nc, 0, now.life, false));
					}
				}
			}
			
			// 다음 시기에 어떻게 될지 결정
			if(nextTime == now.life) {
				// 이미 활성화 상태인 경우, 다음 시점에서는 죽어있다.
				if(now.state) continue;
				// 활성화되지 않은 경우, 활성화 시켜준다.
				else q.offer(new cell(now.r, now.c, 0, now.life, true));
			}else { // 굳이 안바꿔도 되는 경우
				q.offer(new cell(now.r, now.c, nextTime, now.life, now.state));
			}
		}
	}
}
