package day0301;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_20055_컨베이어벨트위의로봇_210301 {
	
	static int N, K, totalLength; // 컨베이어 벨트 길이, 내구도가 0인 칸의 개수
	static class belt {
		int durability; // 내구도
		boolean onRobot; // 로봇이 올라와 있는지
		
		public belt(int durability, boolean onRobot) {
			this.durability = durability;
			this.onRobot = onRobot;
		}
	}
	static belt[] belts; // 컨베이어 벤트
	static Queue<Integer> robots = new LinkedList<Integer>(); // 로봇의 위치

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		totalLength = N*2;
		
		belts = new belt[totalLength];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < totalLength; i++) {
			belts[i] = new belt(Integer.parseInt(st.nextToken()), false);
		}
		
		int stage = 0; // 진행되는 단계
		int startIdx = 0; // 벨트의 시작점
		int endIdx = 0; // 로봇이 내려오는 위치 
		while(!checkingBelt()) { // 4. 벨트 전체 내구도 검사
			stage++;
			
			// 1. 벨트 한칸 회전
			startIdx--;
			if(startIdx < 0) startIdx = totalLength-1;
			
			endIdx = (startIdx + N) % totalLength;
			
			// 모든 로봇의 위치를 확인 -> 내려오는 로봇이 있는지
			if(!robots.isEmpty()) {
				int robotNum = robots.size();
				for (int i = 0; i < robotNum; i++) {
					int robotLoc = robots.poll();
					
					if(endIdx != robotLoc) {
						robots.offer(robotLoc);
					}else {
						belts[robotLoc].onRobot = false;
					}
				}
			}
			
			// 2. 가장 먼저 벨트에 올라간 로봇부터 회전 방향으로 이동
			// 단, 로봇이 없으면서, 내구도가 1 이상 남아있어야 함
			if(!robots.isEmpty()) {
				int robotNum = robots.size();
				for (int i = 0; i < robotNum; i++) {
					int robotLoc = robots.poll();
					int newRobotLoc = (robotLoc+1)%totalLength;
					
					if(endIdx != robotLoc) {
						belts[robotLoc].onRobot = false;
						
						if(endIdx != newRobotLoc) {
							if(!belts[newRobotLoc].onRobot 
									&& belts[newRobotLoc].durability > 0) {
								// 이동 가능
								belts[newRobotLoc].durability--;
								belts[newRobotLoc].onRobot = true;
								robots.offer(newRobotLoc);
							}else {
								// 이동 불가능
								robots.offer(robotLoc);
								belts[robotLoc].onRobot = true;
							}
						}
					}
				}
			}
			
			// 3. 시작 위치에 로봇이 없으면 추가
			if(!belts[startIdx].onRobot && belts[startIdx].durability > 0) {
				robots.offer(startIdx);
				belts[startIdx].durability--;
				belts[startIdx].onRobot = true;
			}
		}
		
		System.out.println(stage);
	}

	// 컨베이어 벨트 내구도 검사
	// K개 이상으로 넘어갈 경우 return true
	private static boolean checkingBelt() {
		int countZero = 0;
		
		for (int i = 0; i < totalLength; i++) {
			if(belts[i].durability == 0) countZero++;
		}
		
		if(countZero >= K) return true;
		else return false;
	}
}
