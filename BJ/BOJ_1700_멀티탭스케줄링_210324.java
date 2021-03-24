package day0324;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.StringTokenizer;

public class BOJ_1700_멀티탭스케줄링_210324 {

	static int N, K; // 구멍의 개수, 전기 용품의 총 사용횟수
	static int[] seq; // 사용 순서
	static HashSet<Integer> set = new HashSet<Integer>(); // 콘센트
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		seq = new int[K];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < K; i++) {
			seq[i] = Integer.parseInt(st.nextToken());
		}	
		
		int disconnectCount = 0;
		
		for (int i = 0; i < K; i++) {
			// 만약에 이미 연결되어 있다면 skip
			if(set.contains(seq[i])) continue;
			
			// 콘센트 자리가 남아있다면
			if(set.size() < N) {
				set.add(seq[i]);
				continue;
			}
			
			Iterator<Integer> it = set.iterator();
			
			int farNum = 0; // 가장 늦게 연결된 번호
			int farIndex = 0; // 가장 늦게 연결된 번호의 index
			
			while(it.hasNext()) {
				int temp = it.next();
				
				boolean isSame = false;
				
				for (int j = i+1; j < K; j++) {
					if(seq[j] == temp) {
						if(j > farIndex) {
							farIndex = j;
							farNum = temp;
						}
						isSame = true;
						break;
					}
				}
				
				if(!isSame) {
					farNum = temp;
					break;
				}
			}
			
			// 가장 늦게 연결된 번호 제거
			set.remove(farNum);
			// 다음 기기 연결
			set.add(seq[i]);
			disconnectCount++;
		}
		
		System.out.println(disconnectCount);
	}
}
