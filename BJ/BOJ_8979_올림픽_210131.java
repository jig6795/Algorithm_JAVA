package day0131;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_8979_올림픽_210131 {
	
	static int N, K; // 국가의 수, 등수를 알고 싶은 국가
	static class country implements Comparable<country>{
		int num, gold, silver, copper;

		public country(int num, int gold, int silver, int copper) {
			this.num = num;
			this.gold = gold;
			this.silver = silver;
			this.copper = copper;
		}

		@Override
		public int compareTo(country o) {
			if(this.gold != o.gold) {
				return o.gold - this.gold;
			}else {
				if(this.silver != o.silver) {
					return o.silver - this.silver;
				}else {
					return o.copper - this.copper;
				}
			}
		}
	}
	static country[] countrys; // 나라들
	static int[] rank;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		countrys = new country[N];
		rank = new int[N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			int num = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			countrys[i] = new country(num, g, s, c);
		}
		
		// 내림차순으로 정렬
		Arrays.sort(countrys);
		
		rank[0] = 1;
		int rankCount = 2;
		if(countrys[0].num != K) {
			for (int i = 1; i < N; i++) {
				if(isSame(i)) rank[i] = rank[i-1];
				else rank[i] = rankCount;
				
				if(countrys[i].num == K) {
					System.out.println(rank[i]);
					return;
				}
				
				rankCount++;
			}
		}else System.out.println(rank[0]);
	}
	
	private static boolean isSame(int i) {
		if(countrys[i-1].gold == countrys[i].gold
				&& countrys[i-1].silver == countrys[i].silver
				&& countrys[i-1].copper == countrys[i].copper) return true;
		else return false;
	}

}
