package day0303;

public class PROG_네트워크_210303 {

	public static void main(String[] args) {
		int n = 4;
		int[][] computers = {{1,0,0,1},{0,1,1,0},{0,1,1,0},{1,1,0,1}};
		
		System.out.println(solution(n, computers));
	}
	
	static int[] computerNum; // 컴퓨터 번호
	static boolean[] sameNet; // 같은 네트워크에 있는 경우

	public static int solution(int n, int[][] computers) {
		int answer = 0;
		
		computerNum = new int[n];
		for (int i = 0; i < n; i++) {
			computerNum[i] = i;
		}
		
		// 네트워크 연결
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				// 연결은 되어있지만 같은 네트워크에 속해있지 않는 경우
				if(i != j && computers[i][j] == 1 && computerNum[i] != computerNum[j]) {
					// 가장 앞 컴퓨터로 연결
					union(i, j);
				}
			}
		}
		
		// 네트워크 그룹 확인
		sameNet = new boolean[n];
		for (int i = 0; i < n; i++) {
			if(!sameNet[computerNum[i]]) {
				answer++;
				sameNet[computerNum[i]] = true;
			}
		}
		
		return answer;
	}
	
	private static int find(int a) {
		if(computerNum[a] == a) return a;
		return computerNum[a] = find(computerNum[a]);
	}
	
	private static void union(int a, int b) {
		int ga = find(a);
		int gb = find(b);
		
		if(ga != gb) {
			int compare = computerNum[b];
			for (int i = 0; i < computerNum.length; i++) {
				if(computerNum[i] == compare) {
					computerNum[i] = computerNum[a];
				}
			}
		}
	}
}
