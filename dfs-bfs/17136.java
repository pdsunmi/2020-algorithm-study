import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 *  17136. 색종이 붙이기
 *  https://www.acmicpc.net/problem/17136
 *  이중 포문으로 1을 찾고 내부에 색종이를 붙이는 재귀를 넣었더니 무한루프를 돌고 제대로 동작하지 않았다.
 *  1을 찾고 값을 바꿔준 뒤 밖에서 DFS를 수행하도록 만들어야 한다.
 */
public class Paper {
	static int[][] map;
	static int[] quantity;
	static int ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input;
		map = new int[10][10];
		quantity = new int[5];
		for (int i = 0; i < 5; i++)
			quantity[i] = 5;
		for (int i = 0; i < 10; i++) {
			input = br.readLine();
			for (int j = 0; j < 10; j++) {
				if (input.charAt(j * 2) == '1')
					map[i][j] = 1;
				else
					map[i][j] = 0;
			}
		}

		ans = Integer.MAX_VALUE;
		findOne(0, 0, 0);
		if (ans == Integer.MAX_VALUE)
			ans = -1;
		System.out.println(ans);
	}

	static void findOne(int r, int c, int cnt) {
		if (ans <= cnt)
			return;
		boolean allZero = true;
		out: for (int i = r; i < 10; i++) {
			for (int j = (i == r ? c : 0); j < 10; j++) {
				if (map[i][j] == 1) {
					r = i;
					c = j;
					allZero = false;
					break out;
				}
			}

		}
		if (allZero) {
			ans = Math.min(ans, cnt);
			return;
		}
		for (int sz = 5; sz >= 1; sz--) {
			if (attachable(r, c, sz)) {
				attach(r, c, sz);
				findOne(r, c, cnt + 1);
				detach(r, c, sz);
			}
		}
	}

	static boolean attachable(int r, int c, int sz) {
		if (quantity[sz - 1] <= 0)
			return false;
		if (r + sz > 10 || c + sz > 10)
			return false;
		for (int i = r; i < r + sz; i++) {
			for (int j = c; j < c + sz; j++) {
				if (map[i][j] == 0)
					return false;
			}
		}
		return true;
	}

	static void attach(int r, int c, int sz) {
		quantity[sz - 1]--;
		for (int i = r; i < (r + sz < 10 ? r + sz : 10); i++) {
			for (int j = c; j < (c + sz < 10 ? c + sz : 10); j++)
				map[i][j] = 0;
		}
	}

	static void detach(int r, int c, int sz) {
		quantity[sz - 1]++;
		for (int i = r; i < (r + sz < 10 ? r + sz : 10); i++) {
			for (int j = c; j < (c + sz < 10 ? c + sz : 10); j++)
				map[i][j] = 1;
		}
	}
}