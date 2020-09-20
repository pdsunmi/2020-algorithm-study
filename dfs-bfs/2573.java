import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 *  2573. 빙산
 *  https://www.acmicpc.net/problem/2573
 *  문제를 잘 읽자... 
 *  1. 다 녹을 때까지 빙산이 한개이면 0을 출력한다.
 *  2. 처음부터 빙산이 없다면 0을 출력한다.
 */
public class Iceberg {
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static boolean[][] visited;
	static int[][] map;
	static int N, M;

	static void melt() {
		// 섬이 녹는데 없어진 부분은 -1로 표시해둠
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if (map[i][j] > 0) {
					for (int d = 0; d < 4; d++) {
						if (map[i + dx[d]][j + dy[d]] == 0)
							map[i][j]--;
						if (map[i][j] == 0) {
							map[i][j] = -1;
							break;
						}
					}
				}
			}
		}
		// -1 표시해둔 부분을 0으로 돌려줌
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if (map[i][j] == -1) {
					map[i][j] = 0;
				}
			}
		}
	}

	static void dfs(int x, int y) {
		for (int d = 0; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			if (map[nx][ny] > 0 && !visited[nx][ny]) {
				visited[nx][ny] = true;
				dfs(nx, ny);
			}
		}
	}

	// 섬의 개수를 센다
	static int countIsland() {
		int cnt = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if (map[i][j] != 0 && !visited[i][j]) {
					cnt++;
					if (cnt > 1)
						return cnt;
					visited[i][j] = true;
					dfs(i, j);
				}
			}
		}
		if (cnt == 0)
			return -1;
		for (int i = 1; i <= N; i++)
			Arrays.fill(visited[i], false);
		return cnt;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N + 2][M + 2];
		visited = new boolean[N + 2][M + 2];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(bf.readLine());
			for (int j = 1; j <= M; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		int ans = 0;
		int ret = countIsland();
		if (ret == 1) {
			while (ret < 2) {
				melt();
				ret = countIsland();
				if (ret == -1) {
					ans = 0;
					break;
				}
				ans++;
			}
		}
		System.out.println(ans);
		System.exit(0);
	}
}
