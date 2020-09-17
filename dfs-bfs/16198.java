import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 *  16198. 에너지 모으기 
 *  https://www.acmicpc.net/problem/16198
 */
public class Energy {
	static boolean[] visited;
	static int N, ans, w[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		w = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			w[i] = Integer.parseInt(st.nextToken());
		visited = new boolean[N];

		dfs(0, 0);
		System.out.println(ans);
	}

	static void dfs(int cnt, int energy) {
		if (cnt == N - 2) {
			ans = Math.max(ans, energy);
			return;
		}
		for (int i = 1; i < N - 1; i++) {
			if (!visited[i]) {
				visited[i] = true;
				int left = i, right = i;
				while (true) {
					if (!visited[left])
						break;
					left--;
				}
				while (true) {
					if (!visited[right])
						break;
					right++;
				}
				dfs(cnt + 1, energy + w[left] * w[right]);
				visited[i] = false;
			}
		}
	}
}
