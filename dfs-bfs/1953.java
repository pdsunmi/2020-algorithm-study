import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 *  1953. [모의 SW 역량테스트] 탈주범 검거
 */
public class 탈주범검거 {
	static int dir[][] = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static int numToDirs[][] = { {}, { 0, 1, 2, 3 }, { 0, 2 }, { 1, 3 }, { 0, 1 }, { 1, 2 }, { 2, 3 }, { 0, 3 } };
	static int N, M, R, C, L, ans, map[][];
	static boolean visit[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			map = new int[N][M];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++)
					map[i][j] = Integer.parseInt(st.nextToken());
			}
			ans = 0;
			sol();
			System.out.println("#" + tc + " " + ans);
		}
	}

	static void sol() {
		Queue<Point> q = new LinkedList<>();
		visit = new boolean[N][M];
		if (map[R][C] == 0)
			return;
		int dirs[] = numToDirs[map[R][C]];
		for (int j = 0; j < dirs.length; j++)
			q.add(new Point(R, C, dirs[j]));
		visit[R][C] = true;
		ans++;
		int cnt = 1;
		while (!q.isEmpty() && cnt < L) {
			int sz = q.size();
			for (int i = 0; i < sz; i++) {
				Point p = q.poll();
				dirs = numToDirs[map[p.x][p.y]];
				for (int j = 0; j < dirs.length; j++) {
					int nx = p.x + dir[dirs[j]][0];
					int ny = p.y + dir[dirs[j]][1];
					if (nx < 0 || ny < 0 || nx >= N || ny >= M || visit[nx][ny] || map[nx][ny] == 0)
						continue;
					int nd = (dirs[j] < 2 ? dirs[j] + 2 : dirs[j] - 2);
					if (!check(nx, ny, nd)) // 연결 가능한지
						continue;
					q.add(new Point(nx, ny, nd));
					visit[nx][ny] = true;
					ans++;
				}
			}
			cnt++;
		}
	}

	static boolean check(int x, int y, int d) {
		int dirs[] = numToDirs[map[x][y]];
		for (int i = 0; i < dirs.length; i++) {
			if (d == dirs[i])
				return true;
		}
		return false;
	}

	static class Point {
		int x, y, d;

		public Point(int x, int y, int d) {
			this.x = x;
			this.y = y;
			this.d = d;
		}
	}
}
