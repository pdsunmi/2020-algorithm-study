import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 *  11727. 2×n 타일링 2
 *  https://www.acmicpc.net/problem/11727
 *  일반적인 DP문제! mod 연산은 매번 해줘야한다
 */
public class Main {
	static int n, dp[];

	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new FileReader("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		dp = new int[1001];
		dp[1] = 1;
		dp[2] = 3;
		for (int i = 3; i <= n; i++)
			dp[i] = (dp[i - 2] * 2 + dp[i - 1])% 10007;
		System.out.println(dp[n]);
	}
}