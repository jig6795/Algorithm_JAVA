package day1203;

import java.util.Scanner;

public class BOJ_14888_연산자끼워넣기_201203 {
	
	static int n, max, min; // 수의 개수, 최대값, 최소값
	static int[] number = new int[11]; // 주어지는 수
	static int[] operator = new int[10]; // +: 1, -: 2, *: 3, /: 4 
	static boolean[] isVisited = new boolean[10]; // 연산자를 사용했는지 여부

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		max = Integer.MIN_VALUE;
		min = Integer.MAX_VALUE;
		
		for (int i = 0; i < n; i++) {
			number[i] = sc.nextInt();
		}
		
		// 연산자 입력 -> 연산자를 갯수가 아닌 각각으로 생각
		int idx = 0;
		for (int i = 0; i < 4; i++) {
			int cnt = sc.nextInt();
			for (int j = 0; j < cnt; j++) {
				operator[idx++] = i+1;
			}
		}
		
		sc.close();
		
		// index를 1로 넘기는 이유 : 이미 0은 계산을 했기 때문
		dfs(0, 1, number[0], 0);
		
		System.out.println(max);
		System.out.println(min);
	}
	
	public static void dfs(int value, int index, int result, int length) {
		int temp = 0; // 중간 계산 결과
		
		// 탈출 조건 => number[0]을 제외한 나머지를 모두 계산한 경우
		if(length == n-1) {
			if(result > max) max = result;
			if(result < min) min = result;
		}else {
			for (int i = 0; i < n-1; i++) {
				// 아직 사용하지 않은 연산자를 사용!
				if (!isVisited[i]) {
					switch (operator[i]) {
					case 1:
						temp = result + number[index];
						break;
					case 2:
						temp = result - number[index];
						break;
					case 3:
						temp = result * number[index];
						break;
					case 4:
						temp = result / number[index];
						break;
					}
					isVisited[i] = true;
					dfs(i, index+1, temp, length+1);
				}
			}
		}
		// 방문하지 않은 상태로 되돌리기 => Backtracking
		isVisited[value] = false;
	}
}
