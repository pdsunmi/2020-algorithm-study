import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 *  3745. 오름세
 *  https://www.acmicpc.net/problem/3745
 *  Java EOF 처리시 trim()을 해주자.
 */
public class Main {
	static int sz, dp[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		String input = "";
		while ((input = br.readLine()) != null) {
			int N = Integer.parseInt(input.trim());
			int []arr = new int[N];
			dp = new int[N];
			sz = 0;
			st = new StringTokenizer(br.readLine().trim());
			for (int i = 0; i < N; i++)
				arr[i] = Integer.parseInt(st.nextToken());
			for (int i = 0; i < N; i++) {
				int idx = lowerBound(arr[i]);
				if (idx == sz)
					sz++;
				dp[idx] = arr[i];
			}
			System.out.println(sz);
		}
	}

	static int lowerBound(int n) {
		int front = 0;
		int rear = sz;
		int mid;
		while (front < rear) {
			mid = (front + rear) / 2;
			if (dp[mid] < n)
				front = mid + 1;
			else
				rear = mid;
		}
		return rear;
	}
}
