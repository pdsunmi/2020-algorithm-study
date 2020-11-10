import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 *  1824. 혁진이의 프로그램 검증 D4
 *  방향과 메모리에 대한 방문처리를 해야하고
 *  BFS이기 때문에 실패했을때 return false가 아니라 continue를 했어야했다.
 */
public class Solution {
	static int dir[][] = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
	static int R, C;
	static char map[][];
	static boolean visit[][][][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			map = new char[R][C];
			visit = new boolean[R][C][16][4];
			boolean flag = false;
			for (int i = 0; i < R; i++) {
				map[i] = br.readLine().toCharArray();
				for (int j = 0; j < C; j++) {
					if (map[i][j] == '@')
						flag = true;
				}
			}
			if (flag && bfs())
				System.out.println("#" + tc + " YES");
			else
				System.out.println("#" + tc + " NO");
		}
	}

	static boolean bfs() {
		Queue<State> q = new LinkedList<>();
		q.add(new State(0, 0, 0, 0));
		visit[0][0][0][0] = true;
		while (!q.isEmpty()) {
			State s = q.poll();
			char c = map[s.x][s.y];
			if (c == '>')
				s.d = 0;
			else if (c == '<')
				s.d = 2;
			else if (c == '^')
				s.d = 3;
			else if (c == 'v')
				s.d = 1;
			else if (c == '_')
				s.d = (s.m == 0 ? 0 : 2);
			else if (c == '|')
				s.d = (s.m == 0 ? 1 : 3);
			else if (c == '@')
				return true;
			else if (c == '+')
				s.m = (s.m + 17) % 16;
			else if (c == '-')
				s.m = (s.m + 15) % 16;
			else if (c >= '0' && c <= '9')
				s.m = c - '0';
			else if (c == '?') {
				for (int d = 0; d < 4; d++) {
					int nx = (s.x + dir[d][0] + R) % R;
					int ny = (s.y + dir[d][1] + C) % C;
					if (!visit[nx][ny][s.m][d]) {
						visit[nx][ny][s.m][0] = true;
						visit[nx][ny][s.m][1] = true;
						visit[nx][ny][s.m][2] = true;
						visit[nx][ny][s.m][3] = true;
						q.add(new State(nx, ny, s.m, d));
					}
				}
				continue;
			}
			int nx = (s.x + dir[s.d][0] + R) % R;
			int ny = (s.y + dir[s.d][1] + C) % C;
			if (visit[nx][ny][s.m][s.d])
				continue;
			visit[nx][ny][s.m][s.d] = true;
			q.add(new State(nx, ny, s.m, s.d));
		}
		return false;
	}

	static class State {
		int x, y, m, d;

		public State(int x, int y, int m, int d) {
			super();
			this.x = x;
			this.y = y;
			this.m = m;
			this.d = d;
		}
	}
}