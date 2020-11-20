import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 *  17281. ⚾
 *  https://www.acmicpc.net/problem/17281
 *  시뮬레이션
 */
public class Main {
	static boolean check[];
	static int order[], result[][], N, ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		result = new int[N][9];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++)
				result[i][j] = Integer.parseInt(st.nextToken());
		}
		order = new int[9];
		check = new boolean[9];
		order[3] = 0;
		check[3] = true;

		// 순열을 만든다
		perm(1);

		System.out.println(ans);
	}

	static void perm(int idx) {
		if (idx == 9) {
			// simulation
			int total = 0;
			int j = 0;
			for (int i = 0; i < N; i++) {
				int[] ru = new int[4];
				int out = 3;
				while (out > 0) {
					int score = result[i][order[j]];
					if (score == 0)
						out--;
					else {
						ru[0] = 1;
						for (int k = 3; k >= 0; k--) {
							if (k + score > 3) {
								total += ru[k];								
								ru[k] = 0;
							} else {
								ru[k + score] = ru[k];
								ru[k] = 0;
							}
						}
					}
					j=(j+1)%9;
				}
			}
			ans = Math.max(ans, total);
		}
		for (int i = 0; i < 9; i++) {
			if (!check[i]) {
				order[i] = idx;
				check[i] = true;
				perm(idx + 1);
				check[i] = false;
			}
		}

	}
}
