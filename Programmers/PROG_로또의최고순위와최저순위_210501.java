package day0501;

public class PROG_로또의최고순위와최저순위_210501 {
	public static int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = new int[2];
        
        int[] rank = {6,6,5,4,3,2,1}; // 등수
        int lottoLength = 6; // 로또의 길이
        int count = 0; // 맞은 로또 번호의 개수
        int zeroCount = 0; // 모르는 로또 번호의 개수
        
        for(int i=0; i<lottoLength; i++){
            if(lottos[i] == 0){
                zeroCount++;
                continue;
            }
            
            for(int j=0; j<lottoLength; j++){
                if(lottos[i] == win_nums[j]){
                    count++;
                    break;
                }
            }
        }
        
        answer[0] = rank[count];
        answer[1] = rank[count];
        
        if(zeroCount > 0){
            answer[0] = rank[count+zeroCount];
        }
        
        return answer;
    }
}
