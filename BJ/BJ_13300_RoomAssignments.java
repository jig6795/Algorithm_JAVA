/*
 * BJ 13300  방 배정
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_13300_RoomAssignments {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] boy = new int[7];
		Arrays.fill(boy, 0);
		int[] girl = new int[7];
		Arrays.fill(girl, 0);
		int roomCount = 0;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int s = Integer.parseInt(st.nextToken());
			int grade = Integer.parseInt(st.nextToken());
			if(s == 0) boy[grade]++;
			else if(s == 1) girl[grade]++;
		}
		
		for (int i = 1; i <= 6; i++) {
			while(boy[i] > 0) {
				boy[i] -= K;
				roomCount++;
			}
			
			while(girl[i] > 0) {
				girl[i] -= K;
				roomCount++;
			}
		}
		
		System.out.println(roomCount);
	}

}
