import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 *  13398. 연속합2
 *  https://www.acmicpc.net/problem/13398
 *  Math.max(0, sum[i - 1]) 을 놓쳐서 풀이하는데 오래걸렸다.
 *  제거를 안했을때도 이전의 누적값이 음수라면 더해줄 필요가 없다!
 */
public class Main {
	static int n, ans, num[], sum[], sumR[];

	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new FileReader("src/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		num = new int[n];
		sum = new int[n];
		sumR = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		num[0] = Integer.parseInt(st.nextToken());
		ans = sum[0] = sumR[0] = num[0];
		for (int i = 1; i < n; i++) {
			num[i] = Integer.parseInt(st.nextToken());
			sum[i] = Math.max(0, sum[i - 1]) + num[i];
			sumR[i] = Math.max(sumR[i - 1] + num[i], sum[i - 1]);
			ans = Math.max(ans, Math.max(sum[i], sumR[i]));
		}
		System.out.println(ans);
	}
}