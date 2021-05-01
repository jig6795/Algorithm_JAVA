package day0501;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class BOJ_2164_카드2_210501 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		sc.close();
		
		Deque<Integer> deque = new LinkedList<Integer>();
		for (int i = 1; i <= N; i++) {
			deque.offerLast(i);
		}
		
		for (int i = 0; i < N; i++) {
			if(deque.size() == 1) break;
			
			// 1. 맨 앞 버린다.
			deque.pollFirst();
			
			// 2. 맨 뒤 앞으로 이동
			int n = deque.pollFirst();
			deque.offerLast(n);
		}
		
		System.out.println(deque.poll());
	}

}
