import java.util.Scanner;

public class BJ_2635_NumContinue {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int startNum = sc.nextInt();
		sc.close();
		int max = Integer.MIN_VALUE;
		int maxNum = Integer.MIN_VALUE;
		
		for (int i = startNum; i >= 0; i--) {
			if(max < check(startNum, i)) {
				max = check(startNum, i);
				maxNum = i;
			}
		}
		System.out.println(max);
		System.out.print(startNum + " ");
		int prev = startNum;
		int now = maxNum;
		int temp = 0;
		while(temp >= 0) {
			System.out.print(now + " ");
			temp = prev - now;
			prev = now;
			now = temp;
		}
	}

	private static int check(int start, int select) {
		int count = 1;
		int prev = start;
		int now = select;
		int temp = 0;
		
		while(temp >= 0) {
			temp = prev - now;
			prev = now;
			now = temp;
			count++;
		}
		return count;
	}
}