import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/*
 *  18267. Milk Visits
 *  두 지점 사이에 선호하는 우유를 마실 수 있는지 묻는 문제였다.
 *  주어진 정점의 경로에 G 혹은 H가 존재하는지 판단해야하는데 BFS로 접근했다가 시간초과가 발생했다
 *  H끼리 집합을 묶고 G끼리 근처의 집합을 묶은 후 쿼리를 판단할때 다른집합이면 둘다 존재하므로 1을 출력한다
 *  다른 집합이면 시작점의 종류를 확인하고 맞게 출력하면 된다.
 *  집합을 묶는 과정은 인접리스트로 저장 후 DFS를 수행하였다. (굳이 Union할필요는 없음)
 *  
 *  결론은 Disjoint sets!
 */
public class Milk {
	static List<Integer>[] adj;
	static int[] parent;
	static char[] milk;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		milk = br.readLine().toCharArray();
		parent = new int[N];
		Arrays.fill(parent, -1);
		adj = new ArrayList[N];
		for (int i = 0; i < N; i++)
			adj[i] = new ArrayList<>();
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int f1 = Integer.parseInt(st.nextToken()) - 1;
			int f2 = Integer.parseInt(st.nextToken()) - 1;
			adj[f1].add(f2);
			adj[f2].add(f1);
		}
		for (int i = 0; i < N; i++) {
			if (parent[i] == -1)
				dfs(i, i);
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int f1 = Integer.parseInt(st.nextToken()) - 1;
			int f2 = Integer.parseInt(st.nextToken()) - 1;
			char pref = st.nextToken().charAt(0);
			if (parent[f1] == parent[f2]) {
				if (milk[f1] == pref)
					sb.append(1);
				else
					sb.append(0);
			} else
				sb.append(1);
		}
		System.out.println(sb);
	}

	static void dfs(int idx, int val) {
		parent[idx] = val;
		for (int i = 0; i < adj[idx].size(); i++) {
			int next = adj[idx].get(i);
			if (parent[next] == -1 && milk[next] == milk[val])
				dfs(next, val);
		}
	}
}
