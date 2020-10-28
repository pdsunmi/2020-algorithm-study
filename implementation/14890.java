import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 *  14890. °æ»ç·Î
 *  https://www.acmicpc.net/problem/14890
 */
public class Main {

	static int N, X, map[][], ans;
	static boolean visit[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		ans = 0;
		// °¡·Î¿¡ ´ëÇØ
		visit = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			boolean flag = true;
			for (int j = 0; j < N - 1; j++) {
				if (map[i][j] == map[i][j + 1])
					continue;
				// ³ô¾ÆÁü
				if (map[i][j] + 1 == map[i][j + 1]) {
					if (j - X + 1 < 0) {
						flag = false;
						continue;
					}
					for (int k = j - X + 1; k <= j; k++) {
						if (visit[i][k] || map[i][k] != map[i][j]) {
							flag = false;
							continue;
						}
					}
					for (int k = j - X + 1; k <= j; k++)
						visit[i][k] = true;

				}
				// ³·¾ÆÁü
				else if (map[i][j] == map[i][j + 1] + 1) {
					if (j + X >= N) {
						flag = false;
						continue;
					}
					for (int k = j + X; k >= j + 1; k--) {
						if (map[i][k] != map[i][j + 1] || visit[i][k]) {
							flag = false;
							continue;
						}
					}
					for (int k = j + X; k >= j + 1; k--)
						visit[i][k] = true;
				} else
					flag = false;
			}
			if (flag)
				ans++;
		}
		// ¼¼·Î¿¡ ´ëÇØ
		visit = new boolean[N][N];
		for (int j = 0; j < N; j++) {
			boolean flag = true;
			for (int i = 0; i < N - 1; i++) {
				if (map[i][j] == map[i + 1][j])
					continue;
				// ³ô¾ÆÁü
				if (map[i][j] + 1 == map[i + 1][j]) {
					if (i - X + 1 < 0) {
						flag = false;
						continue;
					}
					for (int k = i - X + 1; k <= i; k++) {
						if (map[k][j] != map[i][j] || visit[k][j]) {
							flag = false;
							continue;
						}
						visit[k][j] = true;
					}
					for (int k = i - X + 1; k <= i; k++)
						visit[k][j] = true;
				}
				// ³·¾ÆÁü
				else if (map[i][j] == map[i + 1][j] + 1) {
					if (i + X >= N) {
						flag = false;
						continue;
					}
					for (int k = i + X; k >= i + 1; k--) {
						if (map[k][j] != map[i + 1][j] || visit[k][j]) {
							flag = false;
							continue;
						}
						visit[k][j] = true;
					}
					for (int k = i + X; k >= i + 1; k--)
						visit[k][j] = true;
				} else
					flag = false;
			}
			if (flag)
				ans++;
		}
		System.out.println(ans);
	}
}