/*
 * BJ 14696 딱지놀이
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_14696_ScabPlay {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(in.readLine());
		int[] teamA = new int[5];
		int[] teamB = new int[5];
		boolean check = false;
		
		for (int i = 0; i < N; i++) {
			// 입력
			Arrays.fill(teamA, 0);
			Arrays.fill(teamB, 0);	
			check = false;
			
			for (int j = 0; j < 2; j++) {
				st = new StringTokenizer(in.readLine(), " ");
				int n = Integer.parseInt(st.nextToken());
				for (int k = 0; k < n; k++) {
					if(j == 0) {
						teamA[Integer.parseInt(st.nextToken())]++;
					}else if(j == 1) {
						teamB[Integer.parseInt(st.nextToken())]++;
					}
				}
			}
			
			for (int j = 4; j >= 1; j--) {
				if(teamA[j] > teamB[j]) {
					System.out.println("A");
					check = true;
					break;
				}else if(teamA[j] < teamB[j]) {
					System.out.println("B");
					check = true;
					break;
				}
			}
			
			if(!check) System.out.println("D");
		}
		
	}

}
