import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1244_SwitchOnOff {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		// 스위치 세팅
		int switchNum = Integer.parseInt(in.readLine());
		int[] Switch = new int[switchNum+1];
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		for (int i = 1; i <= switchNum; i++) {
			Switch[i] = Integer.parseInt(st.nextToken());
		}
		// 학생
		int studentNum = Integer.parseInt(in.readLine());
		for (int i = 0; i < studentNum; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int sex = Integer.parseInt(st.nextToken()); // 성별 => 1: 남, 2: 여
			int num = Integer.parseInt(st.nextToken()); // 받은 번호
			
			// 남학생인 경우 => 배수 확인
			if(sex == 1) {
				for (int j = num; j <= switchNum; j++) {
					if(j % num == 0) {
						switch (Switch[j]) {
						case 0:
							Switch[j] = 1;
							break;
						case 1:
							Switch[j] = 0;
							break;
						}
					}
				}
			}
			
			// 여학생인 경우 => 자신을 기준으로 대칭 확인, 자신도 바꿔 준다.
			if(sex == 2) {
				// 자기 자신 변경
				switch (Switch[num]) {
				case 0:
					Switch[num] = 1;
					break;
				case 1:
					Switch[num] = 0;
					break;
				}
				
				// 대칭 확인
				int prev = num-1;
				int next = num+1;
				while(true) {
					if(prev == 0 || next == switchNum+1 || Switch[prev] != Switch[next]) break;
					
					switch (Switch[prev]) {
					case 0:
						Switch[prev] = 1;
						Switch[next] = 1;
						break;
					case 1:
						Switch[prev] = 0;
						Switch[next] = 0;
						break;
					}
					
					prev--;
					next++;
				}
			}
		}
		
		for (int i = 1; i <= switchNum; i++) {
			System.out.print(Switch[i] + " ");
			if(i % 20 == 0) System.out.println();
		}
	}
}
