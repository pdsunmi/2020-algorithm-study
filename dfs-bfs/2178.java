import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 *  2178. 미로 탐색
 *  https://www.acmicpc.net/problem/2178
 *  BFS로 주변을 가본다. 이미 가본 곳은 갈 필요 없으므로 visited를 체크한다.
 *  queue에 add할때 visited를 해주자
 */
public class MiroExplore {
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	static class Point {
		int x, y, cnt;

		Point(int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt; // depth
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(bf.readLine());
		Queue<Point> queue = new LinkedList<Point>();
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] miro = new int[N + 2][M + 2];
		boolean[][] visited = new boolean[N][M];
		int ans = 0;
		for (int i = 1; i <= N; i++) {
			String s = bf.readLine();
			for (int j = 1; j <= M; j++) {
				miro[i][j] = s.charAt(j - 1) - '0';
			}
		}
		queue.add(new Point(1, 1, 1));
		visited[1][1] = true;
		out: while (!queue.isEmpty()) {
			Point p = queue.poll();
			for (int k = 0; k < 4; k++) {
				if (miro[p.x + dx[k]][p.y + dy[k]] == 0 || visited[p.x + dx[k]][p.y + dy[k]] == true)
					continue;
				if (p.x + dx[k] == N - 1 && p.y + dy[k] == M - 1) {
					ans = p.cnt;
					break out;
				}
				queue.add(new Point(p.x + dx[k], p.y + dy[k], p.cnt + 1));
				visited[p.x + dx[k]][p.y + dy[k]] = true;
			}
		}
		System.out.println(ans + 1);
		System.exit(0);
	}
}
