import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/*
 *  3954. Brainf**k 인터프리터
 *  https://www.acmicpc.net/problem/3954
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int m = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int i = Integer.parseInt(st.nextToken());
			int[] arr = new int[m];
			char[] code = br.readLine().toCharArray();
			char[] input = br.readLine().toCharArray();
			int[] jump = new int[c];
			Stack<Integer> s = new Stack<>();
			for (int j = 0; j < c; j++) {
				if (code[j] == '[')
					s.add(j);
				else if (code[j] == ']') {
					jump[j] = s.pop();
					jump[jump[j]] = j;
				}
			}
			boolean loop = false;
			int p = 0, idx = 0, cnt = 0, max_j = 0;
			for (int j = 0; j < c; j++) {
				if (code[j] == '-')
					arr[p] = (arr[p] + 255) % 256;
				else if (code[j] == '+')
					arr[p] = (arr[p] + 1) % 256;
				else if (code[j] == '<')
					p = (p + m - 1) % m;
				else if (code[j] == '>')
					p = (p + 1) % m;
				else if (code[j] == ',') {
					if (idx >= i)
						arr[p] = 255;
					else {
						arr[p] = input[idx];
						idx++;
					}
				} else if (code[j] == '[') {
					if (arr[p] == 0)
						j = jump[j];
				} else if (code[j] == ']') {
					if (arr[p] != 0) {
						max_j = Math.max(max_j, j);
						j = jump[j];
					}
				}
				cnt++;
				if (cnt >= 50000000) {
					loop = true;
					System.out.println("Loops " + jump[max_j] + " " + max_j);
					break;
				}
			}
			if (!loop)
				System.out.println("Terminates");
		}
	}
}