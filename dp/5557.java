import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 *  5557. 1학년 - DP
 *  https://www.acmicpc.net/problem/5557
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		long dp[][] = new long[N][21];
		int num[] = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			num[i] = Integer.parseInt(st.nextToken());
		dp[0][num[0]] = 1;
		for (int i = 1; i < N - 1; i++) {
			for (int j = 0; j <= 20; j++) {
				if (j - num[i] >= 0)
					dp[i][j] += dp[i - 1][j - num[i]];
				if (j + num[i] <= 20)
					dp[i][j] += dp[i - 1][j + num[i]];
			}
		}
		System.out.println(dp[N - 2][num[N - 1]]);
	}
}