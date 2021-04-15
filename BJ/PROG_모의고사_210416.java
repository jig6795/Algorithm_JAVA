package day0416;

import java.util.ArrayList;

public class PROG_모의고사_210416 {

	public static void main(String[] args) {
		int[] answers = {1,3,2,4,2};

		ArrayList<Integer> result = solution(answers);
		
		for (int i = 0; i < result.size(); i++) {
			System.out.print(result.get(i) + " ");
		}
	}

	static public ArrayList<Integer> solution(int[] answers) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		
        int[] answer = new int[3];
        
        int[] one = {1,2,3,4,5};
        int[] two = {2,1,2,3,2,4,2,5};
        int[] three = {3,3,1,1,2,2,4,4,5,5};
        
        for (int i = 0; i < answers.length; i++) {
			if(one[i%5] == answers[i]) answer[0]++;
			if(two[i%8] == answers[i]) answer[1]++;
			if(three[i%10] == answers[i]) answer[2]++;
		}
        
        int max = answer[0];
        for (int i = 1; i <= 2; i++) {
			max = Math.max(max, answer[i]);
		}
        
        for (int i = 0; i < 3; i++) {
			if(max == answer[i]) result.add(i+1);
		}
        
        return result;
    }
}
