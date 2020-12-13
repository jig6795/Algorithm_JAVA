package day1214;

import java.util.Scanner;
import java.util.Stack;

public class BOJ_10773_제로_201214 {
	
	static int K, n; // 개수
	static Stack<Integer> st = new Stack<Integer>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		K = sc.nextInt();
		long result = 0;
		
		for (int i = 0; i < K; i++) {
			n = sc.nextInt();
			
			// 0이면 Stack에서 pop, 아니면 push
			if(n == 0) st.pop();
			else st.push(n);
		}
		
		sc.close();
		
		while(!st.isEmpty()) {
			result += st.pop();
		}
		
		System.out.println(result);
	}

}
