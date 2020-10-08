import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 *  2579. 계단 오르기 
 *  https://www.acmicpc.net/problem/2579
 *  한 계단 전에서 오는 경우 dp[i][0]
 *  두 계단 전에서 오는 경우 dp[i][1]으로 Memoization
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] scores = new int[N];
		int[][] dp = new int[N][2];
		for (int i = 0; i < N; i++)
			scores[i] = Integer.parseInt(br.readLine());
		dp[0][0] = scores[0];
		dp[0][1] = 0;
		if (N > 1) {
			dp[1][0] = scores[0] + scores[1];
			dp[1][1] = scores[1];
		}
		for (int i = 2; i < N; i++) {
			dp[i][0] = dp[i - 1][1] + scores[i];
			dp[i][1] = Math.max(dp[i - 2][0], dp[i - 2][1]) + scores[i];
		}
		System.out.println(Math.max(dp[N - 1][0], dp[N - 1][1]));
	}
}
