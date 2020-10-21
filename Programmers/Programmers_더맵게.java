import java.util.PriorityQueue;

public class Programmers_더맵게 {

	public static void main(String[] args) {
		int[] scoville = new int[] {2,9,1,12,3,10};
		int K = 7;
		int result = solution(scoville,K);
		System.out.println(result);
	}
	
    public static int solution(int[] scoville, int K) {
        int answer = 0;
        
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        
        for (int i = 0; i < scoville.length; i++) {
			pq.add(scoville[i]);
		}
        
        while(true) {
        	if(pq.peek() < K) {
        		if(pq.size() == 1) {
        			answer = -1;
        			break;
        		}
        		int min = pq.poll();
        		int min2 = pq.poll();
        		int sum = min + min2*2;
        		pq.add(sum);
        		answer++;
        	}else if(pq.peek() >= K) {
        		break;
        	}
        }
        
        return answer;
    }
}
