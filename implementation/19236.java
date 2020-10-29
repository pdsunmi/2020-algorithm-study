import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 *  19236. 청소년 상어
 *  https://www.acmicpc.net/problem/19236
 *  시뮬레이션 문제! 배열을 인자로 넘길 때 주의하자!
 */
public class 청소년상어19236 {
	static int dx[] = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int dy[] = { 0, -1, -1, -1, 0, 1, 1, 1 };
	static int ans;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int[][] map = new int[4][4]; // 해당 위치에 위치하는 물고기의 번호를 저장하는 지도
		Fish[] fish = new Fish[16]; // 물고기의 번호로 접근하여 위치와 방향을 알 수 있는 배열
		for (int i = 0; i < 4; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 4; j++) {
				int a = Integer.parseInt(st.nextToken()) - 1;
				int b = Integer.parseInt(st.nextToken()) - 1;
				fish[a] = new Fish(i, j, b);
				map[i][j] = a;
			}
		}
		Fish shark = fish[map[0][0]];
		fish[map[0][0]] = null;
		simulation(map, fish, shark, map[0][0] + 1);
		System.out.println(ans);
	}

	static void simulation(int[][] map, Fish[] fish, Fish shark, int cnt) {
		// 상어가 더 갈 수 없다면 물고기 이동 전에 끝낸다
		int x = shark.x + dx[shark.d];
		int y = shark.y + dy[shark.d];
		if (x < 0 || y < 0 || x >= 4 || y >= 4) {
			ans = Math.max(ans, cnt);
			return;
		}
		// 물고기의 이동
		for (int i = 0; i < 16; i++) {
			if (fish[i] == null)
				continue;
			Fish f = fish[i];
			for (int d = f.d, k = 0; k < 8; d = (d + 1) % 8, k++) {
				int nx = f.x + dx[d];
				int ny = f.y + dy[d];
				if (nx < 0 || ny < 0 || nx >= 4 || ny >= 4 || (nx == shark.x && ny == shark.y))
					continue;
				// 물고기 방향 update
				fish[i].d = d;

				// 물고기 위치를 교환하자
				// 1. map 세팅
				int j = map[nx][ny];
				map[nx][ny] = i;
				map[f.x][f.y] = j;

				// 2. fish[] 세팅 : j번과 i번 좌표를 swap
				fish[i].x = nx;
				fish[i].y = ny;
				if (fish[j] != null) {
					fish[j].x = nx - dx[d];
					fish[j].y = ny - dy[d];
				}
				break;
			}
		}
		// 상어의 이동
		for (int i = 1; i <= 3; i++) {
			int nx = shark.x + (dx[shark.d] * i);
			int ny = shark.y + (dy[shark.d] * i);
			if (nx < 0 || ny < 0 || nx >= 4 || ny >= 4) // 범위를 벗어나면 종료
				break;
			if (fish[map[nx][ny]] == null) // 해당 위치에 물고기가 없다면 다음으로
				continue;

			// fish의 깊은 복사
			Fish[] nfish = new Fish[16];
			for (int j = 0; j < 16; j++) {
				if (fish[j] == null)
					nfish[j] = null;
				else
					nfish[j] = new Fish(fish[j]);
			}
			nfish[map[nx][ny]] = null; // 상어가 물고기를 먹음

			// map의 깊은 복사
			int[][] nmap = new int[4][4];
			for (int j = 0; j < 4; j++)
				nmap[j] = map[j].clone();
			simulation(nmap, nfish, fish[map[nx][ny]], cnt + map[nx][ny] + 1);
		}
		ans = Math.max(ans, cnt);
	}

	static class Fish {
		int x, y, d;

		public Fish(int x, int y, int d) {
			super();
			this.x = x;
			this.y = y;
			this.d = d;
		}

		public Fish(Fish fish) {
			this.x = fish.x;
			this.y = fish.y;
			this.d = fish.d;
		}
	}
}
