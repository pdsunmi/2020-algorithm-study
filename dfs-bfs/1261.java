import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 *  1261. 알고스팟
 *  https://www.acmicpc.net/problem/1261
 *  100*100을 경로 탐색하기 때문에 DFS로 풀 경우 시간 초과가 날 수 밖에 없다.
 *  BFS로 탐색하면서 0이 적게나온 위치를 먼저 보기 위해 Priority Queue 사용했다.
 */
public class Main {
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static int[][] miro;
	static boolean[][] visited;
	static int N, M, ans;

	static class Point implements Comparable<Point> {
		int x, y, cnt;

		public Point(int x, int y, int cnt) {
			super();
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Point o) {
			return Integer.compare(this.cnt, o.cnt);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine(), " ");
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		miro = new int[N][M];
		visited = new boolean[N][M];
		ans = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++)
				miro[i][j] = line.charAt(j) - '0';
		}
		PriorityQueue<Point> queue = new PriorityQueue<>();
		visited[0][0] = true;
		queue.offer(new Point(0, 0, 0));
		out: while (!queue.isEmpty()) {
			Point p = queue.poll();
			if (p.x == N - 1 && p.y == M - 1) {
				ans = p.cnt;
				break out;
			}
			for (int d = 0; d < 4; d++) {
				int nx = p.x + dx[d];
				int ny = p.y + dy[d];
				if (nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny])
					continue;
				visited[nx][ny] = true;
				if (miro[nx][ny] == 1)
					queue.offer(new Point(nx, ny, p.cnt + 1));
				else
					queue.offer(new Point(nx, ny, p.cnt));
			}
		}
		System.out.println(ans);
		System.exit(0);
	}
}
