package day0224;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

public class PROG_디스크컨트롤러_210224 {

	public static void main(String[] args) {
		int[][] jobs = {{0,9}, {0,4}, {0,5}, {0, 7}, {0, 3}};
		
		System.out.println(solution(jobs));
	}

	public static int solution(int[][] jobs) {
        int answer = 0;
        
        // 들어온 시간을 기준으로 정렬
        ArrayList<job> jobList = new ArrayList<job>();
        for (int i = 0; i < jobs.length; i++) {
			jobList.add(new job(jobs[i][0], jobs[i][1]));
		}
        
        Comparator<job> comparator = new Comparator<job>() {
			@Override
			public int compare(job o1, job o2) {
				return o1.time - o2.time;
			}
		};
		Collections.sort(jobList, comparator);
		
		// 들어온 일의 수와 끝난 일의 수가 같을 때까지 반복
		int finishWork = 0; // 끝난 일의 수
		int totalTime = 0; // 총 시간
		int inTime = 0; // 들어온 시간
		job now = null; // 현재 작업
		boolean isWorking = false; // 현재 일을 하고 있는지
		PriorityQueue<job> pq = new PriorityQueue<job>();
		while(jobs.length != finishWork) {
			// 일을 하고 있는지 확인
			if (isWorking) {
				while (jobList.size() != 0 && jobList.get(0).time <= totalTime) {
					pq.offer(jobList.get(0));
					jobList.remove(0);
				}
			}else {
				while(jobList.size() != 0 && jobList.get(0).time == totalTime) {
					pq.offer(jobList.get(0));
					jobList.remove(0);
				}
				
				if(!pq.isEmpty()) {
					now = pq.poll();
					inTime = totalTime;
					isWorking = true;
				}
			}
			
			totalTime++;
			
			// 일이 끝났는지 확인
			if(now != null && inTime + now.workTime == totalTime) {
				answer += (totalTime - now.time);
				isWorking = false;
				finishWork++;
			}
		}
        
		// 평균을 return
        return answer/jobs.length;
    }
	
	public static class job implements Comparable<job>{
		int time, workTime;

		public job(int time, int workTime) {
			this.time = time;
			this.workTime = workTime;
		}

		@Override
		public int compareTo(job o) {
			return this.workTime - o.workTime;
		}
	}
}
