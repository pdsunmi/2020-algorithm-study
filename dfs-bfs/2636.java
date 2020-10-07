import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 *  2636. 치즈 - DFS
 *  https://www.acmicpc.net/problem/2636
 */
public class Main {
	static int dx[] = { -1, 0, 1, 0 };
	static int dy[] = { 0, 1, 0, -1 };
	static boolean visited[][];
	static int map[][], N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		int cnt = 1;
		int p_cnt = 1;
		int time = 0;
		while (true) {
			visited = new boolean[N][M];
			out: for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] == 0) {
						dfs(i, j);
						break out;
					}
				}
			}
			p_cnt = cnt;
			cnt = melt();
			time++;
			if (cnt == 0)
				break;
		}
		System.out.println((time - 1) + "\n" + p_cnt);
	}

	static void dfs(int x, int y) {
		visited[x][y] = true;
		for (int d = 0; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			if (nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny])
				continue;
			if (map[nx][ny] == 1)
				map[nx][ny] = 2;
			else if (map[nx][ny] == 0)
				dfs(nx, ny);
		}
	}

	static int melt() {
		int ret = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 2) {
					map[i][j] = 0;
					ret++;
				}
			}
		}
		return ret;
	}
}
