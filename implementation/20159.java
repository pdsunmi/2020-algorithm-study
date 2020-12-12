import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 *  20159. 동작 그만. 밑장 빼기냐?
 *  https://www.acmicpc.net/problem/20159
 */
public class Main {
	static int N, ans, num[], sum[], bsum[];

	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new FileReader("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		num = new int[N];
		sum = new int[N];
		bsum = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			num[i] = Integer.parseInt(st.nextToken());
		sum[0] = num[0];
		for (int i = 2; i < N; i += 2)
			sum[i] = sum[i - 2] + num[i];
		bsum[N - 1] = num[N - 1];
		for (int i = N - 3; i > 0; i -= 2)
			bsum[i] = bsum[i + 2] + num[i];

		// 나한테 밑장 빼기
		ans = sum[N - 2];
		for (int i = N - 1; i >= 3; i -= 2)
			ans = Math.max(ans, bsum[i] + sum[i - 3]);
		ans = Math.max(ans, bsum[1]);

		// 상대방한테 밑장 빼기
		for (int i = 0; i < N - 2; i += 2)
			ans = Math.max(ans, sum[i] + bsum[i + 1] - num[N - 1]);
		System.out.println(ans);
	}
}