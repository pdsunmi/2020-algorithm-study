import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 *  1697. 숨바꼭질 - BFS
 *  N에서 *2, +1, -1의 연산을 해서 K에 도달하는 데 걸리는 최소 연산의 수 구하기  
 *  https://www.acmicpc.net/problem/1697
 */
public class HideAndSeek {
	static boolean[] visited;
	static int N, K, min, max, ans;

	// 점의 위치와 몇번째 연산인지 저장한다
	static class Point {
		int pos, cnt;	

		public Point(int num, int cnt) {
			this.pos = num;
			this.cnt = cnt;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		visited = new boolean[100001];
		min = 0;
		max = 100000;

		Queue<Point> queue = new LinkedList<>();
		if (N != K) {
			queue.offer(new Point(N, 1));
			visited[N] = true;
		}
		while (!queue.isEmpty()) {
			Point p = queue.poll();
			ans = p.cnt;
			// 동생 위치에 도달했을 때
			if (p.pos * 2 == K || p.pos + 1 == K || p.pos - 1 == K)
				break;
			// 안갔던 곳이라면 방문처리하고 enqueue
			if (p.pos * 2 <= max && !visited[p.pos * 2]) {
				queue.offer(new Point(p.pos * 2, p.cnt + 1));
				visited[p.pos * 2] = true;
			}
			if (p.pos + 1 <= max && !visited[p.pos + 1]) {
				queue.offer(new Point(p.pos + 1, p.cnt + 1));
				visited[p.pos + 1] = true;
			}
			if (p.pos - 1 >= min && !visited[p.pos - 1]) {
				queue.offer(new Point(p.pos - 1, p.cnt + 1));
				visited[p.pos - 1] = true;
			}
		}
		System.out.println(ans);
		System.exit(0);
	}
}
