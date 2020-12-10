import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 *  2174. 로봇 시뮬레이션
 *  https://www.acmicpc.net/problem/2174
 */
public class Robot {
	static int A, B, N, M, map[][];
	static int[][] robot;
	static int[][] d = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[B][A];
		robot = new int[N][];
		boolean flag = false;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int dir = 0;
			char c = st.nextToken().charAt(0);
			if (c == 'E')
				dir = 1;
			else if (c == 'S')
				dir = 2;
			else if (c == 'W')
				dir = 3;
			map[B - y - 1][x] = 1;
			robot[i] = new int[] { B - y - 1, x, dir };
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken()) - 1;
			char q = st.nextToken().charAt(0);
			int r = Integer.parseInt(st.nextToken());
			if (flag)
				continue;
			if (q == 'F') {
				while (r > 0) {
					if (!go(n)) {
						flag = true;
						break;
					}
					r--;
				}
			} else if (q == 'L') {
				robot[n][2] = (robot[n][2] - r + 100) % 4;
			} else {
				robot[n][2] = (robot[n][2] + r) % 4;
			}

		}
		if(!flag)
			System.out.println("OK");
	}

	static int find(int x, int y) {
		for (int i = 0; i < N; i++) {
			if (robot[i][0] == x && robot[i][1] == y)
				return i + 1;
		}
		return 0;
	}

	static boolean go(int n) {
		int nx = robot[n][0] + d[robot[n][2]][0];
		int ny = robot[n][1] + d[robot[n][2]][1];
		if (nx < 0 || ny < 0 || nx >= B || ny >= A) {
			System.out.println("Robot " + (n + 1) + " crashes into the wall");
			return false;
		}
		if (map[nx][ny] == 1) {
			System.out.println("Robot " + (n + 1) + " crashes into robot " + find(nx, ny));
			return false;
		}
		map[robot[n][0]][robot[n][1]] = 0;
		map[nx][ny] = 1;
		robot[n][0] = nx;
		robot[n][1] = ny;
		return true;
	}
}
