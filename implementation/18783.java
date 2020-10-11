import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 *  18783. Swapity Swapity Swap
 *  https://www.acmicpc.net/problem/18783
 *  각 자리수마다 순환되는 길이를 구해서 답을 구하는데
 *  한 번 reverse된 것과 원래 배열을 비교해서 
 *  같은 길이로 순환되는 부분을 그룹화할 수 있다
 *  x[] : 그룹의 번호, y[] : 그룹에서의 index
 *  
 *  각 그룹에서 자신의 index인 y[]부터 시작하여 길이만큼씩 순환하게 되고
 *  이를 이용하여 K mod 연산으로 결과를 O(N)에 구할 수 있다.
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];
		for (int i = 0; i < N; i++)
			arr[i] = i + 1;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int L = Integer.parseInt(st.nextToken()) - 1;
			int R = Integer.parseInt(st.nextToken()) - 1;
			while (L < R) {
				int tmp = arr[L];
				arr[L] = arr[R];
				arr[R] = tmp;
				L++;
				R--;
			}
		}
		int[] x = new int[N];
		int[] y = new int[N];
		List<List<Integer>> group = new ArrayList<>();
		group.add(new ArrayList<Integer>());
		int sz = 1;
		for (int i = 0; i < N; i++) {
			if (x[i] != 0)
				continue;
			x[i] = sz;
			group.add(new ArrayList<Integer>());
			group.get(sz).add(i + 1);
			int j = arr[i];
			if (j - 1 != i)
				y[j - 1] = 1;
			while (j - 1 != i) {
				group.get(sz).add(j);
				x[j - 1] = sz;
				if (arr[j - 1] != i + 1)
					y[arr[j - 1] - 1] = y[j - 1] + 1;
				j = arr[j - 1];
			}
			sz++;
		}
		for (int i = 0; i < N; i++)
			sb.append(group.get(x[i]).get((y[i] + K) % group.get(x[i]).size()) + "\n");
		System.out.print(sb);
	}
}