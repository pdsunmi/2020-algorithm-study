import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 *  18270. Livestock Lineup
 *  그래프로 접근하여 연결되어야 한다면 인접리스트로 연결하고
 *  순서대로 연결된 간선이 0개 이면 출력, 2개이면 가운데에 위치하므로 패스, 1개이면 dfs를 수행한다.
 *  dfs로 연결된 이름들을 모두 출력하고 이어간다.
 */
public class Milk {
	static String[] names = { "Beatrice", "Belinda", "Bella", "Bessie", "Betsy", "Blue", "Buttercup", "Sue" };
	static List<Integer>[] adj;
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		sb = new StringBuilder();
		adj = new ArrayList[8];
		for (int i = 0; i < 8; i++)
			adj[i] = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String s1, s2 = "";
			s1 = st.nextToken();
			for (int j = 0; j < 5; j++)
				s2 = st.nextToken();
			int n1 = 0, n2 = 0;
			for (int j = 0; j < 8; j++) {
				if (s1.equals(names[j]))
					n1 = j;
				else if (s2.equals(names[j]))
					n2 = j;
			}
			adj[n1].add(n2);
			adj[n2].add(n1);
		}
		for (int i = 0; i < 8; i++) {
			if (adj[i].size() == 0)
				sb.append(names[i] + "\n");
			else if (adj[i].size() == 1) {
				sb.append(names[i] + "\n");
				dfs(adj[i].get(0), i);
			}
		}
		System.out.println(sb);
	}

	static void dfs(int idx, int prev) {
		sb.append(names[idx] + "\n");
		if (adj[idx].size() == 1) {
			adj[idx].add(0);
			return;
		}
		for (int i = 0; i < 2; i++) {
			if (adj[idx].get(i) != prev)
				dfs(adj[idx].get(i), idx);
		}
	}
}
