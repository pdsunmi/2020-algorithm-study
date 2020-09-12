import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 *  1767. [SW Test 샘플문제] 프로세서 연결하기
 *  DFS로 코어를 연결해가며 시뮬레이션을 한다.
 */
public class ConnectProcessor {
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static List<Point> core;
	static int[][] map;
	static int N, min_wire, max_core, sz;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			core = new ArrayList<>();
			max_core = min_wire = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 1 && j != 0 && j != N - 1 && i != 0 && i != N - 1)
						core.add(new Point(i, j));
				}
			}
			sz = core.size();
			connectWire(0, 0, 0);
			System.out.println("#" + tc + " " + min_wire);
		}
	}

	static void connectWire(int idx, int core_cnt, int wire_len) {
		if (core_cnt + sz - idx < max_core)
			return;
		if (idx == sz) {
			if (core_cnt > max_core) {
				max_core = core_cnt;
				min_wire = wire_len;
			} else if (core_cnt == max_core && wire_len < min_wire) {
				min_wire = wire_len;
			}
			return;
		}
		for (int d = 0; d < 4; d++) {
			int nx = core.get(idx).x + dx[d];
			int ny = core.get(idx).y + dy[d];
			int len = 1, cnt = 1;
			boolean connectable = true;
			// 전선 설치
			while (true) {
				if (map[nx][ny] == 1) {
					connectable = false;
					break;
				}
				if (nx <= 0 || ny <= 0 || nx >= N - 1 || ny >= N - 1) {
					map[nx][ny] = 1;
					break;
				}
				map[nx][ny] = 1;
				nx += dx[d];
				ny += dy[d];
				len++;
			}
			// 전선을 설치하지 못하는 부분이었다면 설치했던 부분 되돌림
			if (!connectable) {
				len--;
				nx = core.get(idx).x + dx[d];
				ny = core.get(idx).y + dy[d];
				while (len > 0) {
					map[nx][ny] = 0;
					nx += dx[d];
					ny += dy[d];
					len--;
				}
				cnt = 0;
			}

			// 다음 core 전선 연결
			connectWire(idx + 1, core_cnt + cnt, wire_len + len);

			// 설치했던 부분을 되돌림
			nx = core.get(idx).x + dx[d];
			ny = core.get(idx).y + dy[d];
			while (len > 0) {
				map[nx][ny] = 0;
				nx += dx[d];
				ny += dy[d];
				len--;
			}
		}
	}
}