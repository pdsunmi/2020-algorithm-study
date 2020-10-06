import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
 *  5582. 공통 부분 문자열
 *  연속해서 같아야 하므로 값을 유지하지는 않는다.
 *  연속해서 같은 값들 중 max를 출력한다.
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] s1 = br.readLine().toCharArray();
		char[] s2 = br.readLine().toCharArray();
		int n = s1.length;
		int m = s2.length;
		int ans = 0;
		int[][] dp = new int[n + 1][m + 1];
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				if (s1[i - 1] == s2[j - 1]) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
					ans = Math.max(ans, dp[i][j]);
				}
			}
		}
		System.out.println(ans);
	}
}
