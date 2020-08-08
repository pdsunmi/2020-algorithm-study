import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/* 
 *  2812. 크게 만들기
 *  주어진 숫자에서 K개의 숫자를 제외하여 가장 큰 수를 출력한다
 *  stack에 숫자를 push하고 더 큰 숫자가 나온다면 K의 제한에 맞춰 pop을 시키게 되면
 *  조건에 맞는 가장 큰 수를 구할 수 있다.
 */
public class MakeNumberBigger {
	static String s;
	static char max;
	static int N, K, max_i, idx;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		Stack<Character> stack = new Stack<Character>();
		s = bf.readLine();
		stack.push(s.charAt(0));
		for (int i = 1; i < N; i++) {
			// 다 지웠을 때 남은 숫자를 스택에 넣고 종료한다
			if (K == 0) {
				for (int j = i; j < N; j++)
					stack.push(s.charAt(j));
				break;
			}
			// 스택이 비어있지 않을 때
			if (!stack.isEmpty()) {
				// 스택의 숫자가 넣을 숫자보다 작을때 최대한 빼고 숫자를 넣는다
				if (stack.peek() < s.charAt(i)) {
					while (stack.peek() < s.charAt(i)) {
						stack.pop();
						K--;
						if (K == 0 || stack.isEmpty())
							break;
					}
				}
			}
			stack.push(s.charAt(i));
		}
		// K가 남았다면 스택에서 갯수많큼 빼준다
		if(K!=0) {
			while(K>0) {
				stack.pop();
				K--;
			}
		}
		for (char x : stack)
			sb.append(x);
		System.out.println(sb);
		System.exit(0);
	}
}
