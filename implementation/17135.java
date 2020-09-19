import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 *  17135. 캐슬 디펜스
 *  https://www.acmicpc.net/problem/17135
 */
public class CastleDefense {
	static int N, M, D, ans, sel[], map[][];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		sel = new int[3];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// M개의 열 중 궁수의 위치 3자리를 뽑는다.
		comb(0, 0);
		System.out.println(ans);
	}

	static void comb(int idx, int s_idx) {
		if (s_idx == 3) {
			ans = Math.max(ans, simulation());
			return;
		}
		if (idx == M)
			return;
		sel[s_idx] = idx;
		comb(idx + 1, s_idx + 1);
		comb(idx + 1, s_idx);
	}

	// 선택된 궁수 위치에서 시뮬레이션을 한다.
	static int simulation() {
		boolean[][] visited = new boolean[N][M];
		int[][] pos = new int[3][];
		int row = N;
		int cnt = 0;
		while (row > 0) {
			for (int k = 0; k < 3; k++) {
				// 각 궁수 위치(row, sel[k])에서 가장 가까운 적을 찾는다.
				out: for (int dist = 1; dist <= D; dist++) {
					// 위치 기준 삼각형 모양이 dist거리인 적들이되며, 왼쪽부터 탐색한다.
					for (int j = sel[k] - dist - 1; j < sel[k] + dist; j++) {
						int i = row - dist + Math.abs(sel[k] - j);
						if (j < 0 || j >= M || i < 0 || i >= row)
							continue;
						if (map[i][j] == 1 && !visited[i][j]) {
							pos[k] = new int[] { i, j };
							break out;
						}
					}
				}
			}
			// 카운트하고 방문처리를 한다
			for (int i = 0; i < 3; i++) {
				if (pos[i] == null)
					continue;
				if (!visited[pos[i][0]][pos[i][1]]) {
					visited[pos[i][0]][pos[i][1]] = true;
					cnt++;
				}
			}
			row--;
		}
		return cnt;
	}
}