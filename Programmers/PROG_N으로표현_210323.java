package day0323;

public class PROG_N으로표현_210323 {
	
	static int answer = -1;

	public static void main(String[] args) {
		solution(2, 11);
		System.out.println(answer);
	}

	public static int solution(int N, int number) {
		DFS(N, number, 0, 0);
        return answer;
    }
	
	public static void DFS(int N, int number, int count, int prev) {
		int temp = N;
		
		// N의 개수인 count가 8을 넘었을 경우에는 종료
		if(count > 8) return;
		
		// 계산값인 prev가 number와 동일한 경우
		if(prev == number) {
			if(answer == -1 || answer > count) answer = count;
		}
		
		// N의 개수가 8개를 넘어갈 수 없으므로
		// N-count : N => NN이 되는 경우 처리
		for (int i = 0; i < 8 - count; i++) {
			// 사칙연산 처리
			DFS(N, number, count+i+1, prev+temp);
			DFS(N, number, count+i+1, prev-temp);
			DFS(N, number, count+i+1, prev*temp);
			DFS(N, number, count+i+1, prev/temp);
			
			// N -> NN이 되는 경우
			temp = doubleNum(temp, N);
		}
	}
	
	public static int doubleNum(int num, int N) {
		return num*10 + N;
	}
}
