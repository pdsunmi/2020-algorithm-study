import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 *  3190. 뱀
 *  https://www.acmicpc.net/problem/3190
 *  코드의 단순성과 구현의 편의성을 위해 방향 정보를 배열에 넣어버리자.
 */
public class Main {
	static int dir[][] = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
	static int N, map[][], move[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		map = new int[N][N];
		move = new int[10001];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			map[r][c] = 2;
		}
		int L = Integer.parseInt(br.readLine());
		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			int X = Integer.parseInt(st.nextToken());
			char C = st.nextToken().charAt(0);
			move[X] = (C == 'L' ? -1 : 1);
		}
		System.out.println(simulation());
	}

	static int simulation() {
		int hx, hy, hd, tx, ty, td, time, sz;
		hx = hy = hd = tx = ty = td = 0;
		sz = time = 1;
		map[hx][hy] = 1;
		while (true) {
			hx += dir[hd][0];
			hy += dir[hd][1];
			if (hx < 0 || hy < 0 || hx >= N || hy >= N || map[hx][hy] == 1)
				return time;
			time++;
			if (map[hx][hy] == 2)
				sz++;
			else {
				map[tx][ty] = 0;
				tx += dir[td][0];
				ty += dir[td][1];
			}
			map[hx][hy] = 1;
			if (move[time - 1] != 0)
				hd = (hd + move[time - 1] + 4) % 4;
			if (move[time - sz] != 0) {
				td = (td + move[time - sz] + 4) % 4;
				move[time - sz] = 0;
			}
		}
	}
}
