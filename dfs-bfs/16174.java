import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 *  16174. 점프왕 쩰리 (Large) - BFS
 *  https://www.acmicpc.net/problem/16174
 */
public class JumpKing {
	static int[] dx = { 1, 0 };
	static int[] dy = { 0, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];
		boolean[][] visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		boolean haruharu = false;
		Queue<int[]> q = new LinkedList<>();
		visited[0][0] = true;
		q.add(new int[] { 0, 0 });
		while (!q.isEmpty()) {
			int[] p = q.poll();
			if (map[p[0]][p[1]] == -1) {
				haruharu = true;
				break;
			}
			for (int d = 0; d < 2; d++) {
				int dist = map[p[0]][p[1]];
				int nx = p[0] + dx[d] * dist;
				int ny = p[1] + dy[d] * dist;
				if (nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny])
					continue;
				visited[nx][ny] = true;
				q.add(new int[] { nx, ny });
			}
		}
		System.out.println(haruharu ? "HaruHaru" : "Hing");
	}
}
