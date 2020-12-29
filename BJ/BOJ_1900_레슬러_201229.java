package day1229;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1900_레슬러_201229 {
	
	static int N; // 선수의 수
	static class player implements Comparable<player>{
		int power, ring, win, num;
		
		public player(int power, int ring, int win, int num) {
			this.power = power;
			this.ring = ring;
			this.win = win;
			this.num = num;
		}

		@Override
		public int compareTo(player o) {
			// 내림차순으로 만들기 위해서
			return o.win - this.win;
		}
	}
	static player[] players;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		players = new player[N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int p = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			players[i] = new player(p, r, 0, i+1);
		}

		// 승리 횟수 계산
		for (int i = 0; i < N-1; i++) {
			for (int j = i+1; j < N; j++) {
				int one = players[i].power + players[i].ring*players[j].power;
				int two = players[j].power + players[j].ring*players[i].power;
				
				// 승리 비교
				if(one > two) players[i].win++;
				else players[j].win++;
			}
		}
		
		// 승리 횟수에 따라 내림 차순 정렬
		Arrays.sort(players);
		
		for (int i = 0; i < N; i++) {
			System.out.println(players[i].num);
		}
	}

}
