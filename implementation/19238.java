import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 *  19238. 스타트 택시
 *  https://www.acmicpc.net/problem/19238
 */
public class Main {
	static int dir[][] = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };
	static int N, M, F, map[][];
	static Point taxi, info[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		F = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		taxi = new Point(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);
		info = new Point[M];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int sx = Integer.parseInt(st.nextToken()) - 1;
			int sy = Integer.parseInt(st.nextToken()) - 1;
			int dx = Integer.parseInt(st.nextToken()) - 1;
			int dy = Integer.parseInt(st.nextToken()) - 1;
			map[sx][sy] = i + 2;
			info[i] = new Point(dx, dy);
		}
		System.out.println(sol());
	}

	static int sol() {
		int cnt = 0;
		while (cnt < M) {
			int idx = findPassenger();
			if (idx == -1)
				return -1;
			if (!move(idx))
				return -1;
			cnt++;
		}
		return F;
	}

	static int findPassenger() {
		boolean visit[][] = new boolean[N][N];
		Queue<Point> q = new LinkedList<>();
		Point sel = null;
		q.add(taxi);
		visit[taxi.x][taxi.y] = true;
		int dist = 0;
		while (!q.isEmpty()) {
			int sz = q.size();
			for (int i = 0; i < sz; i++) {
				Point p = q.poll();
				if (map[p.x][p.y] > 1) {
					if (sel == null)
						sel = new Point(p.x, p.y);
					else if (sel.x > p.x)
						sel = new Point(p.x, p.y);
					else if (sel.x == p.x && sel.y > p.y)
						sel = new Point(p.x, p.y);
				}
				if (dist >= F)
					return -1;
				for (int d = 0; d < 4; d++) {
					int nx = p.x + dir[d][0];
					int ny = p.y + dir[d][1];
					if (nx < 0 || ny < 0 || nx >= N || ny >= N || map[nx][ny] == 1 || visit[nx][ny])
						continue;
					q.add(new Point(nx, ny));
					visit[nx][ny] = true;
				}
			}
			if (sel != null) {
				int idx = map[sel.x][sel.y] - 2;
				map[sel.x][sel.y] = 0;
				taxi.x = sel.x;
				taxi.y = sel.y;
				F -= dist;
				return idx;
			}
			dist++;
		}
		return -1;
	}

	static boolean move(int idx) {
		Queue<Point> q = new LinkedList<>();
		boolean visit[][] = new boolean[N][N];
		q.add(taxi);
		visit[taxi.x][taxi.y] = true;
		Point dest = info[idx];
		int dist = 0;
		while (!q.isEmpty()) {
			int sz = q.size();
			for (int i = 0; i < sz; i++) {
				Point p = q.poll();
				if (p.x == dest.x && p.y == dest.y) {
					if (F < dist)
						return false;
					taxi.x = p.x;
					taxi.y = p.y;
					F += dist;
					return true;
				}
				if (dist > F)
					return false;
				for (int d = 0; d < 4; d++) {
					int nx = p.x + dir[d][0];
					int ny = p.y + dir[d][1];
					if (nx < 0 || ny < 0 || nx >= N || ny >= N || map[nx][ny] == 1 || visit[nx][ny])
						continue;
					q.add(new Point(nx, ny));
					visit[nx][ny] = true;
				}
			}
			dist++;
		}
		return false;
	}

	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}