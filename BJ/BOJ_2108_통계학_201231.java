package day1231;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_2108_통계학_201231 {
	
	static int N, maxCount, sum; // 수의 개수, 최빈수에 해당하는 횟수, 합계
	static int[] countOfNum = new int[8001]; // 최빈수 계산
	static int[] num; // 수 모음
	static Queue<Integer> q = new LinkedList<Integer>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		num = new int[N];
		
		// 초기화
		maxCount = Integer.MIN_VALUE;
		sum = 0;
		
		for (int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(br.readLine());
			sum += num[i]; // 총합에 더해줌
			countOfNum[num[i] + 4000]++; // 빈도수 계산을 위해 더해줌
			maxCount = Math.max(maxCount, countOfNum[num[i] + 4000]); // 바로 최빈수 확인
		}
		
		// 오름차순 정렬
		Arrays.sort(num);
		
		// 1. 산술평균
		System.out.println(Math.round((double) sum/N));
		
		// 2. 중앙값
		System.out.println(num[N/2]);
		
		// 3. 최빈값
		for (int i = 0; i <= 8000; i++) {
			if(countOfNum[i] == maxCount) {
				q.offer(i-4000);
			}
		}
		
		if(q.size() > 1) {
			q.poll();
			System.out.println(q.poll());
		}else System.out.println(q.poll());
		
		// 4. 범위 : 최대값 - 최소값
		System.out.println(num[N-1] - num[0]);
	}

}
