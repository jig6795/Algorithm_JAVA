/*
 * BJ 3085 사탕 게임
 */

import java.util.Scanner;

public class BJ_3085_CandyGame {

	static char[][] candy;
	static int[] dirR = {-1,1,0,0};
	static int[] dirC = {0,0,-1,1};
	static int max = Integer.MIN_VALUE;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		sc.nextLine();
		candy = new char[N][N];
		for (int i = 0; i < N; i++) {
			candy[i] = sc.nextLine().toCharArray();
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < 4; k++) {
					int nr = i + dirR[k];
					int nc = j + dirC[k];
					// 범위를 벗어나는 경우
					if(nr<0 || nc<0 || nr>=N || nc>=N) continue;
					// 자리 바꿈
					swap(i,j,nr,nc);
					// 확인하기
					int count = 1;
					for (int l = 0; l < N-1; l++) {
						if(candy[i][l] != candy[i][l+1]) {
							max = Math.max(count, max);
							count = 1;
						}else {
							count++;
						}
					}
					max = Math.max(count, max);
					count = 1;
					for (int l = 0; l < N-1; l++) {
						if(candy[l][j] != candy[l+1][j]) {
							max = Math.max(count, max);
							count = 1;
						}else {
							count++;
						}
					}
					max = Math.max(count, max);
					// 되돌리기
					swap(i,j,nr,nc);
				}
			}
		}
		System.out.println(max);
		sc.close();
	}

	private static void swap(int r, int c, int nr, int nc) {
		char temp = candy[r][c];
		candy[r][c] = candy[nr][nc];
		candy[nr][nc] = temp;
	}
}
