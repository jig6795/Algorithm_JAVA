package day0317;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class BOJ_1181_단어정렬_210317 {
	
	static int N; // 단어의 개수
	static ArrayList<String> list = new ArrayList<String>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < N; i++) {
			list.add(br.readLine());
		}
		
		Comparator<String> comparator = new Comparator<String>() {
			
			@Override
			public int compare(String o1, String o2) {
				if(o1.length() > o2.length()) return 1;
				else if(o1.length() < o2.length()) return -1;
				else {
					for (int i = 0; i < o1.length(); i++) {
						if(o1.charAt(i) > o2.charAt(i)) return 1;
						else if(o1.charAt(i) < o2.charAt(i)) return -1;
						else continue;
					}
					return 0;
				}
			}
		};
		
		Collections.sort(list, comparator);
		
		for (int i = 0; i < N; i++) {
			// 중복인 경우 출력 X
			if(i > 0 && list.get(i).equals(list.get(i-1))) continue;
			System.out.println(list.get(i));
		}
	}

}
