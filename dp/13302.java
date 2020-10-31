import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 *  13302. 리조트 - 재귀+DP
 *  https://www.acmicpc.net/problem/13302
 *  DP의 접근 방법과 쿠폰 처리에 대해 생각하는 부분이 어려웠다.
 */
public class Main {
	static int N, M, dp[][];
	static boolean plan[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		plan = new boolean[N];
		dp = new int[N][N+1];
		for (int i = 0; i < N; i++)
			Arrays.fill(dp[i], Integer.MAX_VALUE);
		if (M > 0) {
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++)
				plan[Integer.parseInt(st.nextToken()) - 1] = true;
		}
		System.out.println(sol(0, 0));
	}

	static int sol(int day, int coupon) {
		if (day >= N)
			return 0;
		if (dp[day][coupon] != Integer.MAX_VALUE)
			return dp[day][coupon];
		if (plan[day])
			return dp[day][coupon] = Math.min(dp[day][coupon], sol(day + 1, coupon));
		dp[day][coupon] = Math.min(dp[day][coupon], sol(day + 1, coupon) + 10000);
		dp[day][coupon] = Math.min(dp[day][coupon], sol(day + 3, coupon + 1) + 25000);
		dp[day][coupon] = Math.min(dp[day][coupon], sol(day + 5, coupon + 2) + 37000);
		if (coupon >= 3)
			dp[day][coupon] = Math.min(dp[day][coupon], sol(day + 1, coupon - 3));
		return dp[day][coupon];
	}
}