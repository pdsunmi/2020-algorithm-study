import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/*
 *  14003. 가장 긴 증가하는 부분 수열 5
 *  https://www.acmicpc.net/problem/14003
 *  O(NlogN)로 LIS를 출력하는 문제였다.
 *  
 *  parent를 기록하려고 하다보니 막히는 부분이 있었는데
 *  해당 사이즈를 만나면 출력하고 사이즈를 줄이는 방식으로 접근하면 쉽다.
 */
public class Main {
	static int arr[], dp[], p[], sz;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		arr = new int[N];
		p = new int[N];
		dp = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		int max = 0;
		for (int i = 0; i < N; i++) {
			int idx = lowerBound(arr[i]);
			if (idx == sz) {
				sz++;
				max = i;
			}
			dp[idx] = arr[i];
			p[i] = idx + 1;
		}
		System.out.println(sz);
		Stack<Integer> s= new Stack<>();
		while (sz > 0) {
			if (sz == p[max]) {
				s.push(arr[max]);
				sz--;
			}
			max--;
		}
		while(!s.isEmpty())
			sb.append(s.pop()+" ");
		System.out.println(sb);
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
