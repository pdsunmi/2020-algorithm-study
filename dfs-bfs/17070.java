import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* 
 *  17070. 파이프 옮기기 1 - DFS
 *  https://www.acmicpc.net/problem/17070
 */
public class MovePipe1 {
	static int N, ans, map[][];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		dfs(0, 1, 0);
		System.out.println(ans);
	}

	static void dfs(int r, int c, int type) {
		if (r == N - 1 && c == N - 1) {
			ans++;
			return;
		}
		if (type == 0 || type == 1) {
			if (c + 1 < N && map[r][c + 1] != 1)
				dfs(r, c + 1, 0);
		}
		if (type == 1 || type == 2) {
			if (r + 1 < N && map[r + 1][c] != 1)
				dfs(r + 1, c, 2);
		}
		if (r + 1 < N && c + 1 < N && map[r][c + 1] != 1 && map[r + 1][c] != 1 && map[r + 1][c + 1] != 1)
			dfs(r + 1, c + 1, 1);
	}
}