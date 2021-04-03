package day0402;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_2075_N번째큰수_210402 {
	
	static int N;
	static PriorityQueue<Integer> pq = new PriorityQueue<Integer>(Collections.reverseOrder());
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			for (int j = 0; j < N; j++) {
				pq.add(Integer.parseInt(st.nextToken()));
			}
		}
		
		for (int i = 0; i < N; i++) {
			if(i == N-1) {
				System.out.println(pq.poll());
				return;
			}
			pq.poll();
		}	
	}	

}
