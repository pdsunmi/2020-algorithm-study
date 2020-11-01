import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 *  20056. 마법사 상어와 파이어볼
 *  https://www.acmicpc.net/problem/20056
 */
public class Main {
	static int dr[] = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int dc[] = { 0, 1, 1, 1, 0, -1, -1, -1 };
	static Ball map[][], next[][];
	static int ans, N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		map = new Ball[N][N];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			map[r][c] = new Ball(m, s, d);
		}
		for (int i = 0; i < K; i++)
			move();
		sol();
		System.out.println(ans);
	}

	static void move() {
		next = new Ball[N][N];
		// 모든 맵을 보면서
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == null)
					continue;
				// 위치에 파이어볼이 존재한다면 정보를 확인한다.
				int move = 1; // 이동할 파이어볼의 개수
				Ball b = map[i][j];
				// 여러개의 파이어 볼이 있다면 조건에 맞게 하나로 만들고 move만 4로 해준다.
				if (b.cnt > 1) {
					b.d = (!b.even || !b.odd) ? 0 : 1;
					b.m /= 5;
					b.s /= b.cnt;
					move = 4;
					if (b.m == 0)
						continue;
				}
				// 파이어볼의 방향으로 이동시켜준다.
				// 이때 move가 4라면 d부터 돌면서 4번 이동시킨다.
				for (int k = 0; k < move; k++, b.d += 2) {
					int nr = (i + dr[b.d] * b.s + (N * b.s)) % N;
					int nc = (j + dc[b.d] * b.s + (N * b.s)) % N;
					// 새로운 위치에 파이어볼이 없다면 그냥 대입하고
					if (next[nr][nc] == null)
						next[nr][nc] = new Ball(b.m, b.s, b.d);
					// 새로운 위치에 파이어볼이 존재한다면 합쳐준다.
					else {
						Ball b2 = next[nr][nc];
						b2.m += b.m;
						b2.s += b.s;
						b2.cnt++;
						if (b.d % 2 == 0)
							b2.even = true;
						else
							b2.odd = true;
					}
				}
			}
		}
		for (int i = 0; i < N; i++)
			map[i] = next[i].clone();
	}

	// 남은 파이어볼들의 질량을 더해준다.
	static void sol() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				Ball b = map[i][j];
				if (b == null)
					continue;
				if (b.cnt == 1)
					ans += b.m;
				else // 원래는 이동이 끝나면 바로 나누어지기 때문에 나눈 후의 질량을 더해준다.
					ans += b.m / 5 * 4;
			}
		}
	}

	static class Ball {
		int m, s, d, cnt;
		boolean odd, even;

		public Ball(int m, int s, int d) {
			this.m = m;
			this.s = s;
			this.d = d;
			this.cnt = 1;
			if (this.d % 2 == 0)
				even = true;
			else
				odd = true;
		}
	}
}