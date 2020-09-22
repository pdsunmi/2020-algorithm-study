import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 *  7579. 앱
 *  https://www.acmicpc.net/problem/7579
 *  일정 이상 바이트 수에 대한 최소 비용을 찾는 문제이기 때문에 반대로 생각해보자
 *  각 비용에 대해 얻을 수 있는 최대 바이트로 생각하면 일반적인 dp문제가 된다
 */
public class App {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int dp[], m[], c[], N, M, total = 0;
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		m = new int[N];
		c = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			m[i] = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			c[i] = Integer.parseInt(st.nextToken());
			total += c[i];
		}
		dp = new int[total + 1];
		for (int i = 0; i < N; i++) {
			for (int j = total; j >= c[i]; j--) {
				dp[j] = Math.max(dp[j], dp[j - c[i]] + m[i]);
			}
		}
		int ans = -1;
		for (int i = 0; i <= total; i++) {
			if (dp[i] >= M) {
				ans = i;
				break;
			}
		}
		System.out.println(ans);
	}
}
