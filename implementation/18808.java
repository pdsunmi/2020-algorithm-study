import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* 
 *  18808. 스티커 붙이기
 *  https://www.acmicpc.net/problem/18808
 */
public class Sticker {
	static int[][] map, sticker, sticker_Rot;
	static int N, M, K, R, C, ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		ans = 0;
		for (int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine(), " ");
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			int area = R * C; // area : 스티커에서 1의 수
			sticker = new int[R][C];
			sticker_Rot = new int[R][C];
			for (int i = 0; i < R; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < C; j++) {
					sticker[i][j] = Integer.parseInt(st.nextToken());
					sticker_Rot[i][j] = sticker[i][j];
					if (sticker[i][j] == 0)
						area--;
				}
			}
			for (int i = 0; i < 4; i++) {
				if (simulate()) {
					ans += area;
					break;
				}
				if (i != 3)
					rotate(i);
			}
		}
		System.out.println(ans);
		System.exit(0);
	}

	static boolean simulate() {
		if (N < R || M < C)
			return false;
		for (int i = 0; i <= N - R; i++) {
			for (int j = 0; j <= M - C; j++) {
				if (!attachable(i, j))
					continue;
				attach(i, j);
				return true;
			}
		}
		return false;
	}

	static boolean attachable(int x, int y) {
		for (int i = x; i < x + R; i++) {
			for (int j = y; j < y + C; j++) {
				if (sticker_Rot[i - x][j - y] == 1 && map[i][j] == 1)
					return false;
			}
		}
		return true;
	}

	static void attach(int x, int y) {
		for (int i = x; i < x + R; i++) {
			for (int j = y; j < y + C; j++) {
				if (sticker_Rot[i - x][j - y] == 1)
					map[i][j] = sticker_Rot[i - x][j - y];
			}
		}
	}

	static void rotate(int x) {
		int tmp = R;
		R = C;
		C = tmp;
		sticker_Rot = new int[R][C];
		if (x == 0) {
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++)
					sticker_Rot[i][j] = sticker[C - j - 1][i];
			}
		} else if (x == 1) {
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++)
					sticker_Rot[i][j] = sticker[R - i - 1][C - j - 1];
			}
		} else {
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++)
					sticker_Rot[i][j] = sticker[j][R - i - 1];
			}
		}
	}
}
