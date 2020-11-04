import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 *  13460. 구슬 탈출 2
 *  https://www.acmicpc.net/problem/13460
 *  map을 복사하면서 공간낭비가 있었다. 문제를 풀고 나서 필요한지 검토해보자~!
 */
public class Main {
	static int[][] dir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static int N, M, ans;
	static char[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		int[] red = null, blue = null;
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 'B')
					blue = new int[] { i, j };
				else if (map[i][j] == 'R')
					red = new int[] { i, j };
			}
		}
		ans = Integer.MAX_VALUE;
		for (int d = 0; d < 4; d++)
			move(1, d, red, blue);
		System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
	}

	static void move(int cnt, int d, int[] red, int[] blue) {
		if (cnt > 10)
			return;
		int rx = red[0], ry = red[1], rd = 0;
		int bx = blue[0], by = blue[1], bd = 0;
		while (map[bx + dir[d][0]][by + dir[d][1]] != '#') {
			bx +=dir[d][0];
			by +=dir[d][1];
			if (map[bx][by] == 'O')
				return;
			bd++;
		}
		while (map[rx + dir[d][0]][ry + dir[d][1]] != '#') {
			rx +=dir[d][0];
			ry +=dir[d][1];
			if (map[rx][ry] == 'O') {
				ans = Math.min(ans, cnt);
				return;
			}
			rd++;
		}
		if (rx == bx && ry == by) {
			if (rd > bd) {
				rx -= dir[d][0];
				ry -= dir[d][1];
			} else {
				bx -= dir[d][0];
				by -= dir[d][1];
			}
		}
		if (red[0] == rx && red[1] == ry && blue[0] == bx && blue[1] == by)
			return;
		for (int i = 0; i < 4; i++)
			move(cnt + 1, i, new int[] { rx, ry }, new int[] { bx, by });
	}
}