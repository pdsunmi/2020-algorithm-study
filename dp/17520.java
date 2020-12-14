import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 *  17520. Balanced String
 *  https://www.acmicpc.net/problem/17520
 *  일반적인 DP문제
 */
public class Main {
	static int n, dp[];

	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new FileReader("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		dp = new int[100001];
		dp[1] = 2;
		dp[2] = 2;
		for (int i = 3; i <= n; i++) {
			if (i % 2 == 0)
				dp[i] = dp[i - 1];
			else
				dp[i] = (dp[i - 1] * 2) % 16769023;
		}
		System.out.println(dp[n]);
	}
}