package day0528;

import java.util.Scanner;
import java.util.Stack;

public class BOJ_2504_괄호의값_210528 {

	static String s;
	static int result = 0;
	static Stack<Integer> stack = new Stack<Integer>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		s = sc.nextLine();
		sc.close();

		if (s.length() == 1) {
			System.out.println(0);
			return;
		}

		for (int i = 0; i < s.length(); i++) {
			int temp = 0;

			switch (s.charAt(i)) {
			case '{':
				System.out.println(0);
				return;
			case '}':
				System.out.println(0);
				return;
			case '(':
				stack.push(0);
				break;
			case '[':
				stack.push(-1);
				break;
			case ')':
				if (!stack.isEmpty()) {
					while (true) {
						if (stack.isEmpty()) {
							System.out.println(0);
							return;
						}

						if (stack.peek() == 0) {
							stack.pop();

							if (temp == 0) {
								temp = 2;
							} else {
								temp *= 2;
							}

							break;
						} else if (stack.peek() == -1) {
							System.out.println(0);
							return;
						} else {
							temp += stack.pop();
						}
					}

					stack.push(temp);
				} else {
					System.out.println(0);
					return;
				}
				break;
			case ']':
				if (!stack.isEmpty()) {
					while (true) {
						if (stack.isEmpty()) {
							System.out.println(0);
							return;
						}

						if (stack.peek() == -1) {
							stack.pop();

							if (temp == 0) {
								temp = 3;
							} else {
								temp *= 3;
							}

							break;
						} else if (stack.peek() == 0) {
							System.out.println(0);
							return;
						} else {
							temp += stack.pop();
						}
					}

					stack.push(temp);
				} else {
					System.out.println(0);
					return;
				}
				break;
			}
		}

		while (!stack.isEmpty()) {
			if(stack.peek() == 0 || stack.peek() == -1) {
				System.out.println(0);
				return;
			}
			
			result += stack.pop();
		}

		System.out.println(result);
	}

}
