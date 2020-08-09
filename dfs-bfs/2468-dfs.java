import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/* 
 *  2468. 안전 영역 - DFS
 *  https://www.acmicpc.net/problem/2468
 *  잠기는 높이에 대한 안전 영역의 수 중 maximum을 구한다.
 */
public class SafeArea_DFS {
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static int[][] map;
	static boolean[][] visited;
	static int N, min, max, level, cnt, ans;

	static void dfs(int x, int y) {
		for (int d = 0; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			if (nx < 0 || ny < 0 || nx >= N || ny >= N || map[nx][ny] < level || visited[nx][ny] == true)
				continue;
			visited[nx][ny] = true;
			dfs(nx, ny);
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visited = new boolean[N][N];
		min = 101;
		max = -1;
		ans = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				min = Math.min(min, map[i][j]);
				max = Math.max(max, map[i][j]);
			}
		}
		for (int k = min; k <= max; k++) {
			level = k;
			cnt = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] >= level && !visited[i][j]) {
						visited[i][j] = true;
						dfs(i, j);
						cnt++;
					}
				}
			}
			ans = Math.max(ans, cnt);
			for (int i = 0; i < N; i++)
				Arrays.fill(visited[i], false);
		}
		System.out.println(ans);
		System.exit(0);
	}
}
