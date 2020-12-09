package day1209;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_15683_감시_201209 {

	// delta -> 0:상, 1:우, 2:하, 3:좌
	static int[] dirR = { -1, 0, 1, 0 };
	static int[] dirC = { 0, 1, 0, -1 };
	static int N, M, min, cctvCount; // row, column, 사각 지대의 최소 영역, cctv 개수
	static int[][] map; // 사무실 -> 7:1번 cctv, 8:2번 cctv, 9:3번 cctv, 10:4번 cctv, 11:5번 cctv가 감시하는 구역 표시
	static class CCTV {
		int r, c, number; // CCTV 위치, 번호
		
		public CCTV(int r, int c, int number) {
			this.r = r;
			this.c = c;
			this.number = number;
		}
	}
	static Queue<CCTV> cctvList = new LinkedList<CCTV>(); // visit 처리 대신 Queue를 사용하여 처리

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] != 0 && map[i][j] != 6) cctvList.offer(new CCTV(i, j, map[i][j]));
			}
		}
		
		// 5번 cctv는 우선적으로 표시!
		cctvCount = cctvList.size();
		for (int i = 0; i < cctvCount; i++) {
			CCTV cctv = cctvList.poll();
			if(cctv.number == 5) {
				for (int k = 0; k < 4; k++) {
					int nr = cctv.r + dirR[k];
					int nc = cctv.c + dirC[k];

					// 범위를 벗어나지 않고 벽에 닿지 않은 경우 -> 11:5번 cctv가 감시하는 구역 표시
					while (isOk(nr, nc) && map[nr][nc] != 6) {
						if(map[nr][nc] == 0) map[nr][nc] = 11;
						nr += dirR[k];
						nc += dirC[k];
					}
				}
			}else cctvList.offer(cctv);
		}
		
		// 5번 cctv가 확인한 시점까지 최소값을 우선적으로 구한다.
		min = Math.min(Integer.MAX_VALUE, zeroCount());

		// 1~4번 cctv 확인
		// 각 cctv을 시작점으로 두고 모든 cctv를 확인한다.
		cctvCount = cctvList.size();
		for (int i = 0; i < cctvCount; i++) {
			CCTV cctv = cctvList.poll();
			checking(cctv.number, cctv.r, cctv.c);
			cctvList.offer(cctv);
		}
		
		System.out.println(min);
	}

	public static void checking(int cctvNum, int row, int col) {
		boolean[][] isNowCheck = new boolean[N][M]; // 현재 상황에서 바뀐 것!
		
		switch (cctvNum) {
		case 1: // 1방향
			for (int i = 0; i < 4; i++) {
				// 범위를 벗어나지 않고 벽에 닿지 않은 경우 -> 7:1번 cctv가 감시하는 구역 표시
				int nr = row + dirR[i];
				int nc = col + dirC[i];
				
				while (isOk(nr, nc) && map[nr][nc] != 6) {
					if(map[nr][nc] == 0) {
						isNowCheck[nr][nc] = true;
						map[nr][nc] = 7;
					}
					nr += dirR[i];
					nc += dirC[i];
				}
				
				// Queue가 비어있다면, 모든 cctv를 처리한 것이기 때문에 최소값 계산
				if(cctvList.isEmpty()) min = Math.min(min, zeroCount());
				else {
					CCTV cctv = cctvList.poll(); // 다음 cctv 결정
					checking(cctv.number, cctv.r, cctv.c);
					cctvList.offer(cctv); // 확인한 cctv를 다시 Queue에 넣는다.
				}
				
				clearNowCheck(isNowCheck);
			}
			break;
		case 2: // 2방향 (평행)
			for (int i = 0; i < 2; i++) {
				// 범위를 벗어나지 않고 벽에 닿지 않은 경우 -> 8:2번 cctv가 감시하는 구역 표시
				int upNr = row + dirR[i];
				int upNc = col + dirC[i];
				while (isOk(upNr, upNc) && map[upNr][upNc] != 6) {
					if(map[upNr][upNc] == 0) {
						isNowCheck[upNr][upNc] = true;
						map[upNr][upNc] = 8;
					}
					upNr += dirR[i];
					upNc += dirC[i];
				}
				
				int downNr = row + dirR[i+2];
				int downNc = col + dirC[i+2];
				while (isOk(downNr, downNc) && map[downNr][downNc] != 6) {
					if(map[downNr][downNc] == 0) {
						isNowCheck[downNr][downNc] = true;
						map[downNr][downNc] = 8;
					}
					downNr += dirR[i+2];
					downNc += dirC[i+2];
				}
				
				// Queue가 비어있다면, 모든 cctv를 처리한 것이기 때문에 최소값 계산
				if(cctvList.isEmpty()) min = Math.min(min, zeroCount());
				else {
					CCTV cctv = cctvList.poll(); // 다음 cctv 결정
					checking(cctv.number, cctv.r, cctv.c);
					cctvList.offer(cctv); // 확인한 cctv를 다시 Queue에 넣는다.
				}
				
				clearNowCheck(isNowCheck);
			}
			break;
		case 3: // 2방향 (직각)
			for (int i = 0; i < 4; i++) {
				// 범위를 벗어나지 않고 벽에 닿지 않은 경우 -> 9:3번 cctv가 감시하는 구역 표시
				int upNr = row + dirR[i];
				int upNc = col + dirC[i];
				while (isOk(upNr, upNc) && map[upNr][upNc] != 6) {
					if(map[upNr][upNc] == 0) {
						isNowCheck[upNr][upNc] = true;
						map[upNr][upNc] = 9;
					}
					upNr += dirR[i];
					upNc += dirC[i];
				}
				
				int downNr = row + dirR[(i+1)%4];
				int downNc = col + dirC[(i+1)%4];
				while (isOk(downNr, downNc) && map[downNr][downNc] != 6) {
					if(map[downNr][downNc] == 0) {
						isNowCheck[downNr][downNc] = true;
						map[downNr][downNc] = 9;
					}
					downNr += dirR[(i+1)%4];
					downNc += dirC[(i+1)%4];
				}
				
				// Queue가 비어있다면, 모든 cctv를 처리한 것이기 때문에 최소값 계산
				if(cctvList.isEmpty()) min = Math.min(min, zeroCount());
				else {
					CCTV cctv = cctvList.poll(); // 다음 cctv 결정
					checking(cctv.number, cctv.r, cctv.c);
					cctvList.offer(cctv); // 확인한 cctv를 다시 Queue에 넣는다.
				}
				
				clearNowCheck(isNowCheck);
			}
			break;
		case 4: // 3방향 (ㅏ, ㅜ, ㅓ, ㅗ)
			for (int i = 0; i < 4; i++) {
				// 범위를 벗어나지 않고 벽에 닿지 않은 경우 -> 10:4번 cctv가 감시하는 구역 표시
				int upNr = row + dirR[i];
				int upNc = col + dirC[i];
				while (isOk(upNr, upNc) && map[upNr][upNc] != 6) {
					if(map[upNr][upNc] == 0) {
						isNowCheck[upNr][upNc] = true;
						map[upNr][upNc] = 10;
					}
					upNr += dirR[i];
					upNc += dirC[i];
				}
				
				int downNr = row + dirR[(i+1)%4];
				int downNc = col + dirC[(i+1)%4];
				while (isOk(downNr, downNc) && map[downNr][downNc] != 6) {
					if(map[downNr][downNc] == 0) {
						isNowCheck[downNr][downNc] = true;
						map[downNr][downNc] = 10;
					}
					downNr += dirR[(i+1)%4];
					downNc += dirC[(i+1)%4];
				}
				
				int tempNr = row + dirR[(i+2)%4];
				int tempNc = col + dirC[(i+2)%4];
				while (isOk(tempNr, tempNc) && map[tempNr][tempNc] != 6) {
					if(map[tempNr][tempNc] == 0) {
						isNowCheck[tempNr][tempNc] = true;
						map[tempNr][tempNc] = 10;
					}
					tempNr += dirR[(i+2)%4];
					tempNc += dirC[(i+2)%4];
				}
				
				// Queue가 비어있다면, 모든 cctv를 처리한 것이기 때문에 최소값 계산
				if(cctvList.isEmpty()) min = Math.min(min, zeroCount());
				else {
					CCTV cctv = cctvList.poll(); // 다음 cctv 결정
					checking(cctv.number, cctv.r, cctv.c);
					cctvList.offer(cctv); // 확인한 cctv를 다시 Queue에 넣는다.
				}
				
				clearNowCheck(isNowCheck);
			}
			break;
		}
	}
	
	// 방금 확인한 라인을 삭제해주는 Method
	public static void clearNowCheck(boolean[][] arr) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(arr[i][j]) {
					map[i][j] = 0;
					arr[i][j] = false;
				}
			}
		}
	}
	
	// 사각 지대가 얼마나 있는지 확인해주는 Method
	public static int zeroCount() {
		int count = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] == 0) count++;
			}
		}
		return count;
	}

	// 범위 확인만 해주는 Method
	public static boolean isOk(int r, int c) {
		if (r < 0 || c < 0 || r >= N || c >= M) return false;
		else return true;
	}
}
