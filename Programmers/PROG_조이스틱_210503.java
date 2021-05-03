package day0503;

public class PROG_조이스틱_210503 {

	public int solution(String name) {
        int answer = 0;
        int nameLength = name.length();
        int moveCount = nameLength-1; // 움직인 최소 횟수 => 문자 길이-1이 움직일 수 있는 최대
        
        for(int i=0;i<nameLength;i++){
            answer += Math.min((int)name.charAt(i)-65, 91-(int)name.charAt(i));
        }
        
        for(int i=0;i<nameLength;i++){
            int idx = i; // 연속된 A 중 끝나는 지점
            
            // 문자 중 연속된 A가 포함되어 있는 경우
            if(name.charAt(i) == 'A'){
                while(idx < nameLength && name.charAt(idx) == 'A'){
                    idx++;
                }
                
                // A가 시작되기 전까지 갔던 움직임X2 + 전체길이에서 A가 끝나는 지점을 뺀 길이
                moveCount = Math.min(moveCount, ((i-1)*2) + nameLength - idx);
            }
        }
        
        return answer + moveCount;
    }

}
