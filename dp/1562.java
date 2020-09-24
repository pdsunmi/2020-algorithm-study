import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 *  1562. 계단 수 
 *  https://www.acmicpc.net/problem/1562
 *  상태 체크 DP!!!
 *  모든 가능한 삐긋 수를 만들고 flag를 통해 0~9의 모든 숫자를 썼는지 검사한다.
 *  dp[][][] : 어디에(자리수), 어떤 숫자를 쓴다, flag
 */
public class 계단수 {
	static final int MOD = 1_000_000_000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N, ans, dp[][][];
		N = Integer.parseInt(br.readLine());
		dp = new int[N+1][10][1 << 10];
		// 첫번째 자리에 0~9 숫자를 쓴것부터 시작한다.
		for (int i = 1; i < 10; i++)
			dp[1][i][1 << i] = 1;
		// 삐긋수 만들기
		for (int i = 2; i <= N; i++) {
			for (int j = 0; j < 10; j++) {
				for (int k = 0; k < 1024; k++) {
					// 0이라면 다음 숫자 1만 가능
					if (j == 0)
						dp[i][j][k | (1 << j)] += dp[i - 1][j + 1][k];
					// 9라면 다음 숫자 8만 가능
					else if (j == 9)
						dp[i][j][k | (1 << j)] += dp[i - 1][j - 1][k];
					// 가운데 숫자는 양쪽 가능
					else {
						dp[i][j][k | (1 << j)] += dp[i - 1][j + 1][k];
						dp[i][j][k | (1 << j)] += dp[i - 1][j - 1][k];
					}
					dp[i][j][k | (1 << j)] %= MOD;
				}
			}
		}
		ans = 0;
		
		// N개의 자리수로 모든 숫자를 썼다면(flag:1111111111) 더해준다
		for (int i = 0; i < 10; i++)
			ans = (ans + dp[N][i][1023]) % MOD;
		System.out.println(ans);
	}
}