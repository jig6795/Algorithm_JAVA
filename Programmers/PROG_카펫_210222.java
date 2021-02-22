package day0222;

public class PROG_카펫_210222 {

	public static void main(String[] args) {
		int brown = 24;
		int yellow = 24;
		
		int[] result = solution(brown, yellow);
		
		System.out.println(result[0] + " " + result[1]);
	}

	public static int[] solution(int brown, int yellow) {
		int[] answer = new int[2]; // 가로, 세로 길이
		
		int sliceBrown = (brown-4)/2;
		
		int row = sliceBrown/2; // 세로
		int col = 0; // 가로
		if(sliceBrown%2 == 0) col = sliceBrown/2;
		else col = sliceBrown/2 + 1;
		
		while(row*col != yellow) {
			col++;
			row--;
		}
		
		answer[0] = col+2;
		answer[1] = row+2;
		
		return answer;
	}
}
