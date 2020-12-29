package day1229;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_10814_나이순정렬_201229 {

	static int N; // 회원의 수
	static class person implements Comparable<person>{
		int age;
		String name;
		int sequence;
		
		public person(int age, String name, int sequence) {
			this.age = age;
			this.name = name;
			this.sequence = sequence;
		}

		// 나이 순 -> 가입 순
		@Override
		public int compareTo(person o) {
			if(this.age > o.age) return 1;
			else if(this.age < o.age) return -1;
			else {
				if(this.sequence > o.sequence) return 1;
				else return -1;
			}
		}
	}
	static person[] persons;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		persons = new person[N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			persons[i] = new person(Integer.parseInt(st.nextToken()), st.nextToken(), i);
		}
		
		// 나이, 가입 순으로 정렬
		Arrays.sort(persons);

		for (int i = 0; i < N; i++) {
			System.out.println(persons[i].age + " " + persons[i].name);
		}
	}

}
