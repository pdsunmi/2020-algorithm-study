import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
 *  KnightL on a Chessboard
 *  https://www.hackerrank.com/challenges/knightl-on-chessboard/problem
 *  BFS로 8방 탐색해서 목적지로 가는 문제
 */
public class KnightlOnAChessboard {

	static int[] dx = { 1, -1, -1, 1 };
	static int[] dy = { 1, 1, -1, -1 };
	static int[][] result;

	static class Point {
		int x, y, val;

		Point(int x, int y, int val) {
			this.x = x;
			this.y = y;
			this.val = val;
		}
	}

	static void bfs(int i, int j, int n) {
		Queue<Point> queue = new LinkedList<>();
		boolean[][] visited = new boolean[n][n];
		queue.offer(new Point(0, 0, 0));
		visited[0][0] = true;
		while (!queue.isEmpty()) {
			Point p = queue.poll();
			if (p.x == n - 1 && p.y == n - 1) {
				result[i - 1][j - 1] = p.val;
				result[j - 1][i - 1] = p.val;
			}
			for (int d = 0; d < 4; d++) {
				int nx = p.x + i * dx[d];
				int ny = p.y + j * dy[d];
				if (nx < 0 || ny < 0 || nx >= n || ny >= n || visited[nx][ny])
					continue;
				queue.offer(new Point(nx, ny, p.val + 1));
				visited[nx][ny] = true;
			}
			for (int d = 0; d < 4; d++) {
				int nx = p.x + j * dx[d];
				int ny = p.y + i * dy[d];
				if (nx < 0 || ny < 0 || nx >= n || ny >= n || visited[nx][ny])
					continue;
				queue.offer(new Point(nx, ny, p.val + 1));
				visited[nx][ny] = true;
			}
		}
	}

	// Complete the knightlOnAChessboard function below.
	static int[][] knightlOnAChessboard(int n) {
		result = new int[n - 1][n - 1];
		for (int i = 0; i < n - 1; i++)
			Arrays.fill(result[i], -1);
		for (int i = 1; i < n; i++) {
			for (int j = i; j < n; j++) {
				bfs(i, j, n);
			}
		}
		return result;
	}

	public static void main(String[] args) {

		int n = 5;
		int[][] result = knightlOnAChessboard(n);
		for (int i = 0; i < n - 1; i++)
			System.out.println(Arrays.toString(result[i]));

	}
}
