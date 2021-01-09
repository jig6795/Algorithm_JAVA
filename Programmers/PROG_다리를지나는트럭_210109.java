package day0109;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class PROG_다리를지나는트럭_210109 {
	
	static int truckNum, weightSum; // 다리 위의 트럭 무게 총합
	static ArrayList<Integer> arriveTruck = new ArrayList<Integer>(); // 도착한 트럭	
	static class truck {
		int weight, time;

		public truck(int weight, int time) {
			this.weight = weight;
			this.time = time;
		}
	}
	static Queue<truck> q = new LinkedList<truck>(); // 다리

	public static void main(String[] args) {
		int bridgeLength = 2;
		int weight = 10;
		int[] truckWeights = {7,4,5,6};

		System.out.println(solution(bridgeLength, weight, truckWeights));
	}

	public static int solution(int bridge_length, int weight, int[] truck_weights) {
		int answer = 1; // 모든 트럭이 지나가는데 걸리는 시간
		int truckNum = 0;
		
		// 마지막으로 도착한 트럭의 무게가 같은지 확인
		while(arriveTruck.size() != truck_weights.length) {
			// 다리 위의 트럭이 지나가는 경우
			if(!q.isEmpty() && answer - q.peek().time == bridge_length) {
				weightSum -= q.peek().weight;
				arriveTruck.add(q.poll().weight);
			}
			
			// 다리 위의 트럭 무게에 다음 트럭의 무게를 더한 값이 weight보다 낮은 경우
			if(truckNum < truck_weights.length && weightSum + truck_weights[truckNum] <= weight) {
				q.offer(new truck(truck_weights[truckNum], answer));
				weightSum += truck_weights[truckNum];
				truckNum++;
			}
			
			answer++;
		}
		
		return answer-1;
	}
	
}
