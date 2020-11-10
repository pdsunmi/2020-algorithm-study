import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/*
 *  14226. 이모티콘
 *  https://www.acmicpc.net/problem/14226
 *  BFS 방문체크시 클립보드 값마다 다른 방문이라고 체크해야하는데 생각하기 어려웠다.
 */
public class 이모티콘 {

	static final int MAX = 2000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int S = Integer.parseInt(br.readLine());
		int[] time = new int[MAX];
		boolean[][] visit = new boolean[MAX][MAX];
		Queue<State> q = new LinkedList<>();
		visit[1][0] = true;
		q.add(new State(1, 0));
		int t = 1;
		out: while (true) {
			int sz = q.size();
			for (int i = 0; i < sz; i++) {
				State s = q.poll();
				// 복사
				if (s.cur != s.copy)
					q.add(new State(s.cur, s.cur));
				// 붙여넣기
				int next = s.cur + s.copy;
				if (next < MAX && !visit[next][s.copy]) {
					if (time[next] == 0)
						time[next] = t;
					else if (time[next] > t)
						time[next] = t;
					visit[next][s.copy] = true;
					if (next == S)
						break out;
					q.add(new State(next, s.copy));
				}
				// 삭제
				next = s.cur - 1;
				if (next >= 0 && !visit[next][s.copy]) {
					if (time[next] == 0)
						time[next] = t;
					else if (time[next] > t)
						time[next] = t;
					visit[next][s.copy] = true;
					if (next == S)
						break out;
					q.add(new State(next, s.copy));
				}
			}
			t++;
		}
		time[1] = 0;
		System.out.println(time[S]);
	}

	static class State {
		int cur, copy;

		public State(int cur, int copy) {
			this.cur = cur;
			this.copy = copy;
		}
	}
}
