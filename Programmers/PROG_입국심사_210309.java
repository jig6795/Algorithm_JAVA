package day0309;

import java.util.Arrays;

public class PROG_입국심사_210309 {

	public static void main(String[] args) {
		int n = 6;
		int[] times = {7, 10};
		
		System.out.println(solution(n, times));
	}
	
	public static long solution(int n, int[] times) {
        long answer = 0;
        
        // 이분 탐색을 위해 배열을 먼저 오름차순 정렬한다.
        Arrays.sort(times);
        
        long start = 0;       
        long end = (long)times[times.length-1]*n; // 가장 오랜 시간 심사를 보는 심사관이 n명을 심사하는 것
        long mid = 0; // 심사 받는데 주어진 시간
        long sum = 0; // 주어진 시간동안 심사를 받을 수 있는 사람의 수
        
        answer = end;
        while(start <= end) {
        	mid = (start + end)/2;
        	sum = 0;
        	
        	// 주어진 시간동안 몇명을 검사할 수 있는지 누적
        	for (int i = 0; i < times.length; i++) {
				sum += mid/times[i];
			}
        	
        	if(sum < n) { // 시간을 늘린다.
        		start = mid + 1;
        	}else { // 시간을 줄인다.
        		end = mid - 1;
        		answer = mid;
        	}
        }
        
        return answer;
    }

}
