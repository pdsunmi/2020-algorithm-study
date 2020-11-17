import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/*
 *  6198. ���� ���� �ٹ̱�
 *  https://www.acmicpc.net/problem/6198
 *  Stack�� ����ؼ� size�� ���� ������ ���� �� �ִ�
 *  80000*80000 = 64,000,000,000 -> long
 */
public class ���������ٹ̱� {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long ans = 0;
		Stack<Integer> s = new Stack<>();
		for (int i = 0; i < N; i++) {
			int h = Integer.parseInt(br.readLine());
			while (!s.isEmpty() && s.peek() <= h) {
				s.pop();
			}
			ans += s.size();
			s.add(h);
		}
		System.out.println(ans);
	}
}
