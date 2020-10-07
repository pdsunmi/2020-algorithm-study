import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 *  2638. 치즈 
 *  https://www.acmicpc.net/problem/2638
 */
public class Main {
	static int dx[] = { -1, 0, 1, 0 };
	static int dy[] = { 0, 1, 0, -1 };
	static int map[][], N, M;
	static boolean visited[][];

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
		int time = 0, cnt = 0;
		while (true) {
			visited = new boolean[N][M];
			dfs(0, 0);
			cnt = melt();
			if (cnt == 0)
				break;
			time++;
		}
		System.out.println(time);
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
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 2) {
					if (meltable(i, j)) {
						map[i][j] = 0;
						cnt++;
					} else
						map[i][j] = 1;
				}
			}
		}
		return cnt;
	}

	static boolean meltable(int x, int y) {
		int cnt = 0;
		for (int d = 0; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			if (nx < 0 || ny < 0 || nx >= N || ny >= M)
				continue;
			if (visited[nx][ny] && map[nx][ny] == 0)
				cnt++;
		}
		if (cnt < 2)
			return false;
		return true;
	}
}
