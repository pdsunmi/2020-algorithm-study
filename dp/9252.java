import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
 *  9252. LCS (2)
 *  테이블의 끝에서 부터 보면서 답을 찾아가야만
 *  최장일때의 문자열을 출력할 수 있다.
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] s1 = br.readLine().toCharArray();
		char[] s2 = br.readLine().toCharArray();
		int n = s1.length;
		int m = s2.length;
		int[][] dp = new int[n + 1][m + 1];
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				if (s1[i - 1] == s2[j - 1])
					dp[i][j] = dp[i - 1][j - 1] + 1;
				else
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
			}
		}
		System.out.println(dp[n][m]);
		String ans = "";
		int i = n, j = m;
		while (dp[n][m] > 0) {
			if (dp[i][j] == dp[i - 1][j])
				i--;
			else if (dp[i][j] == dp[i][j - 1])
				j--;
			else {
				ans = s1[i - 1] + ans;
				dp[n][m]--;
				i--;
				j--;
			}
		}
		if (ans.length() > 0)
			System.out.println(ans);
	}
}
