package day0420;

import java.util.ArrayList;
import java.util.HashSet;

public class PROG_순위_210420 {

	static class Player {
		int num;

		HashSet<Integer> win = new HashSet<Integer>();
		HashSet<Integer> lose = new HashSet<Integer>();

		public Player(int num) {
			this.num = num;
		}
	}

	public static void main(String[] args) {
		int[][] results = { { 4, 3 }, { 4, 2 }, { 3, 2 }, { 1, 2 }, { 2, 5 } };

		System.out.println(solution(5, results));
	}

	public static int solution(int n, int[][] results) {
		int answer = 0;

		ArrayList<Player> players = new ArrayList<Player>();

		// n만큼 플레이어 생성, 0번 사람은 사용 X
		for (int i = 0; i <= n; i++) {
			players.add(new Player(i));
		}

		// 각각 이긴 경우와 진 경우 결과 넣어줌
		for (int i = 0; i < results.length; i++) {
			players.get(results[i][0]).win.add(results[i][1]);
			players.get(results[i][1]).lose.add(results[i][0]);
		}

		// 전체적인 확인을 위한 반복문
		for (int i = 0; i < n; i++) {
			for (int j = 1; j <= n; j++) {
				Player p = players.get(j);

				// 이긴 경우
				HashSet<Integer> winSet = new HashSet<Integer>();

				for (Integer win : p.win) { // 현재 플레이어가 이긴 리스트
					// 현재 플레이어가 이긴 리스트들이 이긴 리스트
					for (Integer w : players.get(win).win) {
						winSet.add(w);
					}
				}

				p.win.addAll(winSet);

				// 진 경우

				HashSet<Integer> loseSet = new HashSet<Integer>();

				for (Integer lose : p.lose) { // 현재 플레이어가 진 리스트
					// 현재 플레이어가 진 리스트들이 진 리스트
					for (Integer l : players.get(lose).lose) {
						loseSet.add(l);
					}
				}

				p.lose.addAll(loseSet);
			}
		}
		
		// 확인
		for (Player player : players) {
			// 본인을 제외하기 때문에 n-1
			if(player.win.size() + player.lose.size() == n-1) answer++;
		}

		return answer;
	}

}
