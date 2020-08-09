import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 *  1260. DFS와 BFS
 *  https://www.acmicpc.net/problem/1260
 */
public class DFSBFS {
	static StringBuilder sb;
	static boolean[][] adjMatrix;
	static boolean[] visited;
	static int N, M, V;

	static void dfs(int x) {
		visited[x] = true;
		sb.append(x + " ");
		for (int i = 1; i <= N; i++) {
			if (adjMatrix[x][i] && !visited[i]) {
				dfs(i);
			}
		}
	}

	static void bfs(int x) {
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(x);
		visited[x] = true;
		sb.append(x + " ");
		while (!queue.isEmpty()) {
			int y = queue.poll();
			for (int i = 1; i <= N; i++) {
				if (adjMatrix[y][i] && !visited[i]) {
					queue.offer(i);
					visited[i] = true;
					sb.append(i + " ");
				}
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		StringTokenizer st;
		st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		adjMatrix = new boolean[N + 1][N + 1];
		visited = new boolean[N + 1];
		int x, y;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(bf.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			adjMatrix[x][y] = true;
			adjMatrix[y][x] = true;
		}
		dfs(V);
		Arrays.fill(visited, false);
		sb.append("\n");
		bfs(V);
		System.out.println(sb);
		System.exit(0);
	}
}
