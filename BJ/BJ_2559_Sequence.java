import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_2559_Sequence {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		Queue<Integer> q = new LinkedList<Integer>();
		
		int sum = 0;
		int max = Integer.MIN_VALUE;
		st = new StringTokenizer(in.readLine(), " ");
		
		while(q.size() < K) {
			int num = Integer.parseInt(st.nextToken());
			sum += num;
			q.offer(num);
		}
		max = Math.max(max, sum);
		
		while(st.hasMoreTokens()) {
			int num = Integer.parseInt(st.nextToken());
			
			sum -= q.poll();
			sum += num;
			q.offer(num);
			
			max = Math.max(max, sum);
		}
		System.out.println(max);
	}

}
