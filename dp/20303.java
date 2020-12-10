import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 *  20303. 할로윈의 양아치
 *  https://www.acmicpc.net/problem/20303
 *  DFS로 캔디의 수와 아이들의 수를 세고 DP로 풀이했다.
 *  부분집합/제한/가치 --> 0/1 knapsack
 */
public class Main {
	static int N, M, K, ans, candy[], tmpT, tmpC, sum, dp[];
	static List<Integer> total, cnt;
	static List<Integer>[] adj;
	static boolean visit[];

	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new FileReader("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		candy = new int[N];
		adj = new ArrayList[N];
		dp = new int[K + 1];
		total = new ArrayList<>();
		cnt = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			candy[i] = Integer.parseInt(st.nextToken());
			adj[i] = new ArrayList<>();
			sum += candy[i];
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			adj[a].add(b);
			adj[b].add(a);
		}
		link();
		sol();
		System.out.println(dp[K - 1]);
	}

	static void sol() {
		int sz = total.size();
		for (int i = 0; i < sz; i++) {
			int t = total.get(i);
			int c = cnt.get(i);
			for (int j = K; j >= c; j--)
				dp[j] = Math.max(dp[j], dp[j - c] + t);
		}
	}

	static void link() {
		visit = new boolean[N];
		for (int i = 0; i < N; i++) {
			if (!visit[i]) {
				tmpT = tmpC = 0;
				dfs(i);
				total.add(tmpT);
				cnt.add(tmpC);
			}
		}
	}

	static void dfs(int a) {
		visit[a] = true;
		tmpT += candy[a];
		tmpC++;
		for (int b : adj[a]) {
			if (!visit[b]) {
				dfs(b);
			}
		}
	}
}