import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/* 
 *  2667. 단지번호붙이기 - DFS
 *  https://www.acmicpc.net/problem/2667
 */
public class ComplexNumber_DFS {
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	static int[][] map;
	static int N, ans, cnt;

	static void dfs(int x, int y) {
		for (int d = 0; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			if (nx < 0 || nx >= N || ny < 0 || ny >= N || map[nx][ny] == 0)
				continue;
			map[nx][ny] = 0;
			dfs(nx, ny);
		}
		cnt++;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PriorityQueue<Integer> queue = new PriorityQueue<>();
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = s.charAt(j) - '0';
			}
		}
		ans = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 1) {
					cnt = 0;
					map[i][j] = 0;
					dfs(i, j);
					queue.offer(cnt);
					ans++;
				}
			}
		}
		System.out.println(ans);
		while (!queue.isEmpty()) {
			System.out.println(queue.poll());
		}
		System.exit(0);
	}
}
