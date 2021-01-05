package day0105;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_9375_패션왕신해빈_210105 {
	
	static int T, n; // 테스트 케이스 수, 의상 수
	static class costume {
		String category;
		int quantity;
		
		public costume(String category, int quantity) {
			this.category = category;
			this.quantity = quantity;
		}
	}
	static ArrayList<costume> costumes = new ArrayList<costume>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			costumes.clear();
			n = Integer.parseInt(br.readLine());
			
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				st.nextToken();
				String c = st.nextToken();
				
				int length = costumes.size();
				boolean check = false;
				
				for (int j = 0; j < length; j++) {
					// 똑같은 종류의 의상이 있다면 수량 +1
					if (costumes.get(j).category.equals(c)) {
						costumes.get(j).quantity++;
						check = true;
						break;
					}
				}

				if(!check) costumes.add(new costume(c, 1));
			}
			
			// 각 개수 +1을 모두 곱한 값에 -1
			long sum = 1;
			for (int i = 0; i < costumes.size(); i++) {
				sum *= (costumes.get(i).quantity+1);
			}
			
			System.out.println(sum-1);
		}
	}

}
