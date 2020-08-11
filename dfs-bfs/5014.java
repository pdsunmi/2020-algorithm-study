import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 *  5014. 스타트 링크 - BFS
 *  https://www.acmicpc.net/problem/5014
 *  시작점과 도착점이 같은 경우를 잘 처리해주자.
 */
public class StartLink {
	static boolean[] visited;
	static int F, S, G, U, D, ans;

	static class Floor {
		int floor;
		int cnt;

		public Floor(int floor, int cnt) {
			super();
			this.floor = floor;
			this.cnt = cnt;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		F = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		U = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		visited = new boolean[F + 1];
		ans = -1;
		Queue<Floor> queue = new LinkedList<>();
		if (S == G)
			ans = 0;
		else {
			queue.offer(new Floor(S, 0));
			visited[S] = true;
		}
		while (!queue.isEmpty()) {
			Floor f = queue.poll();
			if (f.floor + U == G || f.floor - D == G) {
				ans = f.cnt + 1;
				break;
			}
			if (f.floor + U <= F && !visited[f.floor + U]) {
				queue.offer(new Floor(f.floor + U, f.cnt + 1));
				visited[f.floor + U] = true;
			}
			if (f.floor - D >= 1 && !visited[f.floor - D]) {
				queue.offer(new Floor(f.floor - D, f.cnt + 1));
				visited[f.floor - D] = true;
			}
		}
		if (ans == -1)
			System.out.println("use the stairs");
		else
			System.out.println(ans);
		System.exit(0);
	}
}
