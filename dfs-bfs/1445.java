import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 *  1445. 일요일 아침의 데이트
 *  https://www.acmicpc.net/problem/1445
 *  많이 풀어본 문제인데도 visit을 3차원으로 해야하는지 2차원으로 해야하는지 헷갈렸다.
 *  PQ를 썼기 때문에 해당 위치의 우선순위 가장 높은 상태를 알기 때문에 2차원 visit처리만 해주면된다.
 *  그 이후는 같은 방식으로 길을 가기 때문이다!
 */
public class Main {
	static int dir[][] = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
	static int N, M, map[][];
	static boolean visit[][];
	static State start, end;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		char[][] input = new char[N][];
		List<int[]> gs = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			input[i] = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				if (input[i][j] == 'g') {
					gs.add(new int[] { i, j });
					map[i][j] = 2;
				}
				else if (input[i][j] == 'S')
					start = new State(i, j, 0, 0);
				else if (input[i][j] == 'F')
					end = new State(i, j, 0, 0);
			}
		}
		for (int[] g : gs) {
			for (int d = 0; d < 4; d++) {
				int nx = g[0] + dir[d][0];
				int ny = g[1] + dir[d][1];
				if (nx < 0 || ny < 0 || nx >= N || ny >= M)
					continue;
				if (map[nx][ny] == 0)
					map[nx][ny] = 1;
			}
		}
		visit = new boolean[N][M];
		bfs();
	}

	static void bfs() {
		PriorityQueue<State> pq = new PriorityQueue<>();
		pq.add(start);
		map[end.x][end.y] = 0;
		visit[start.x][start.y] = true;
		while (!pq.isEmpty()) {
			State s = pq.poll();
			if (s.x == end.x && s.y == end.y) {
				System.out.println(s.t + " " + s.s);
				return;
			}
			for (int d = 0; d < 4; d++) {
				int nx = s.x + dir[d][0];
				int ny = s.y + dir[d][1];
				if (nx < 0 || ny < 0 || nx >= N || ny >= M || visit[nx][ny])
					continue;
				int nt = s.t;
				int ns = s.s;
				if (map[nx][ny] == 2)
					nt++;
				else
					ns += map[nx][ny];
				visit[nx][ny] = true;
				pq.add(new State(nx, ny, nt, ns));
			}
		}
	}

	static class State implements Comparable<State> {
		int x, y, t, s;

		public State(int x, int y, int t, int s) {
			this.x = x;
			this.y = y;
			this.t = t;
			this.s = s;
		}

		@Override
		public int compareTo(State o) {
			if (this.t == o.t)
				return this.s - o.s;
			return this.t - o.t;
		}
	}
}
