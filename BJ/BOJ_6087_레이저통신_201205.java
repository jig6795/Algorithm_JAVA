package day1205;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_6087_레이저통신_201205 {
	
	// delta
	static int[] dirR = {-1,1,0,0};
	static int[] dirC = {0,0,-1,1};
	static int W, H, min; // 너비(column), 높이(row), 거울 최소
	static int destR, destC;
	static char[][] map; // 지도
	static int[][] mirrorCount; // 거울 수 
	static class pos{
		int r, c; // 기본 위치 정보
		int direction; // 방향 정보
		int mirror; // 지나오면서 마주친 거울의 수
		
		public pos(int r, int c, int direction, int mirror) {
			this.r = r;
			this.c = c;
			this.direction = direction;
			this.mirror = mirror;
		}
	}
	static Queue<pos> q = new LinkedList<pos>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		W = sc.nextInt();
		H = sc.nextInt();
		sc.nextLine();
		map = new char[H][W];
		mirrorCount = new int[H][W];
		min = Integer.MAX_VALUE;
		
		for (int i = 0; i < H; i++) {
			map[i] = sc.nextLine().toCharArray();
		}
		sc.close();
		
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				// 거울의 수를 최소로 저장하기 위해 최대로 초기화
				mirrorCount[i][j] = Integer.MAX_VALUE;
				
				// 레이저 확인 => Queue 미리 사용
				if(map[i][j] == 'C') q.offer(new pos(i, j, -1, 0));
			}
		}
		
		// 목적지 생성
		pos p = q.poll();
		destR = p.r;
		destC = p.c;
		
		p = q.poll();
		bfs(p.r, p.c);
		
		System.out.println(min);
	}

	public static void bfs(int row, int col) {
		// 시작 레이저
		q.offer(new pos(row, col, -1, 0));
		mirrorCount[row][col] = 0;
		
		while(!q.isEmpty()) {
			pos now = q.poll();
			
			// 끝지점에 도달했을 경우 => 거울의 최소값 도출
			if(now.r == destR && now.c == destC) min = Math.min(min, now.mirror);
		
			// 0: 상, 1: 하, 2: 좌, 3: 우
			for (int i = 0; i < 4; i++) {
				int nr = now.r + dirR[i];
				int nc = now.c + dirC[i];
				
				if(nr<0 || nc<0 || nr>=H || nc>= W 
						|| map[nr][nc] == '*') continue;
				
				// 이전 방향과 비교해서 다를 경우, 거울 개수 +1
				int count = now.mirror;
				if(i != now.direction && now.direction != -1) count++;
				
				// 증가된 거울의 수가 기존의 위치에서의 거울 수보다 많은 경우
				if(count > mirrorCount[nr][nc]) continue;
				
				mirrorCount[nr][nc] = count;
				q.offer(new pos(nr, nc, i, count));
			}
		}
	}
}
