import java.util.Scanner;
import java.util.Stack;

public class Programmers_짝지어제거하기 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String s = sc.nextLine();
		
		Stack<Character> st = new Stack<Character>();
		
		st.push(s.charAt(0));
		
		for (int i = 1; i < s.length(); i++) {
			if(!st.isEmpty() && (st.peek() == s.charAt(i))) {
				st.pop();
			}else {
				st.push(s.charAt(i));
			}
		}
		
		if(st.isEmpty()) {
			System.out.println(1);
		}else {
			System.out.println(0);
		}

		sc.close();
	}

}
