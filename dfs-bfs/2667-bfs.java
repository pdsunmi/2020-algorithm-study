import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/* 
 *  2667. 단지번호붙이기 - BFS
 *  https://www.acmicpc.net/problem/2667
 */
public class ComplexNumber_BFS {
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	static int[][] map;
	static int N, ans, cnt;

	static class Point {
		int x, y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		Queue<Point> queue = new LinkedList<>();
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
					ans++;
					cnt = 1;
					queue.offer(new Point(i, j));
					map[i][j] = 0;
					while (!queue.isEmpty()) {
						Point p = queue.poll();
						for (int d = 0; d < 4; d++) {
							int nx = p.x + dx[d];
							int ny = p.y + dy[d];
							if (nx < 0 || ny < 0 || nx >= N || ny >= N || map[nx][ny] == 0)
								continue;
							queue.offer(new Point(nx, ny));
							cnt++;
							map[nx][ny] = 0;
						}
					}
					pq.add(cnt);
				}
			}
		}
		System.out.println(ans);
		while (!pq.isEmpty()) {
			System.out.println(pq.poll());
		}
		System.exit(0);
	}
}
