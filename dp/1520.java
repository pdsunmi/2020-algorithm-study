import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 *  1520. 내리막 길 - DFS+DP
 *  dfs수행한 결과를 dp에 더하고 dp값을 return한다
 *  
 *  좋은 문제 ☆☆☆
 */
public class Main {
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static int N, M, map[][], dp[][];
	static boolean visited[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		dp = new int[N][M];
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		dp[N - 1][M - 1] = 1;
		System.out.println(dfs(0, 0));
	}

	static int dfs(int x, int y) {
		if (visited[x][y])
			return dp[x][y];
		for (int d = 0; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			if (nx < 0 || ny < 0 || nx >= N || ny >= M)
				continue;
			if (map[nx][ny] < map[x][y]) {
				visited[x][y] = true;
				dp[x][y] += dfs(nx, ny);
			}
		}
		return dp[x][y];
	}
}
