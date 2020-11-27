import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 *  2458. 키 순서
 *  https://www.acmicpc.net/problem/2458
 *  dfs를 하면서 자식의 수를 셀 때 dp로 구현이 될듯 했는데
 *  노드에서 노드로의 경로가 여러가지 존재할 수 있기 때문에 불가능했다.
 */
public class Main {
	static int N, M, cnt, ans;
	static List<Integer>[] adj, adjT;
	static boolean visit[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		adj = new ArrayList[N];
		adjT = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			adj[i] = new ArrayList<>();
			adjT[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			adj[a].add(b);
			adjT[b].add(a);
		}
		ans = 0;
		for (int i = 0; i < N; i++) {
			cnt = 0;
			visit = new boolean[N];
			dfs(i);
			visit = new boolean[N];
			dfsT(i);
			if (cnt == N + 1)
				ans++;
		}
		System.out.println(ans);

	}

	static void dfs(int x) {
		visit[x] = true;
		cnt++;
		for (int next : adj[x]) {
			if (!visit[next])
				dfs(next);
		}
	}

	static void dfsT(int x) {
		visit[x] = true;
		cnt++;
		for (int next : adjT[x]) {
			if (!visit[next])
				dfsT(next);
		}
	}
}
