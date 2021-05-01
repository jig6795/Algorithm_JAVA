package day0501;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_9290_틱택토이기기_210501 {

	static int[] dirR = {-1,-1,-1,0,0,1,1,1};
	static int[] dirC = {-1,0,1,-1,1,-1,0,1};
	static int T;
	static char player;
	static char[][] board;
	static final int boardSize = 3;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			board = new char[boardSize][boardSize];
			
			for (int i = 0; i < boardSize; i++) {
				board[i] = br.readLine().toCharArray();
			}
			
			player = br.readLine().charAt(0);
			
			for (int i = 0; i < boardSize; i++) {
				for (int j = 0; j < boardSize; j++) {
					if(i == 1 && j == 1) continue;
					if(board[i][j] == player) {
						for (int k = 0; k < 8; k++) {
							int nr = i;
							int nc = j;
							int count = 1;
							
							for (int l = 0; l < 2; l++) {
								nr += dirR[k];
								nc += dirC[k];
								
								if(nr<0||nc<0||nr>=3||nc>=3) break;
								
								if(board[nr][nc] == player) count++;
							}
							
							if(count == 2) {
								nr = i;
								nc = j;
								
								for (int l = 0; l < 2; l++) {
									nr += dirR[k];
									nc += dirC[k];
									
									if(nr<0||nc<0||nr>=3||nc>=3) break;
									
									board[nr][nc] = player;
								}
							}
						}
					}
				}
			}
			
			showAnswer(t);
		}

	}
	
	public static void showAnswer(int t) {
		System.out.println("Case " + t + ":");
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				System.out.print(board[i][j]);
			}
			System.out.println();
		}
	}

}
