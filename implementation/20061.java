import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 *  20061. 모노미노도미노 2
 *  https://www.acmicpc.net/problem/20061
 *  삽질 1. moveBlock 옮기고 맨위 초기화 안해줌
 *  삽질 2. 범위에 대한 런타임 에러, 보드 꽉찼을때 예외처리
 *  파란색 보드를 돌려서 코드 간소화를 했다!
 */
public class 모노미노도미노2 {
	static int green[][], blue[][], score, cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		green = new int[6][4];
		blue = new int[6][4];
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			// 초록, 파랑에 채운다.
			putBlock(true, t, x, y, green);
			putBlock(false, t, x, y, blue);
			// 한 행/열이 차있다면 제거한다. 내린다.
			removeRC(green);
			removeRC(blue);
			// 연한 색에 차있다면 제거한다. 내린다.
			removeLight(green);
			removeLight(blue);
		}
		count();
		// 점수
		// 칸의 개수 출력
		System.out.println(score + "\n" + cnt);
	}

	static void putBlock(boolean isGreen, int t, int x, int y, int[][] map) {
		if (!isGreen) {
			if (t >= 2) {
				t = (t == 2 ? 3 : 2);
				y = t - x;
			} else
				y = 3 - x;
		}
		int dx = (t == 2 ? 0 : 1);
		int dy = (t == 3 ? 0 : 1);
		boolean flag = true;
		for (int i = 0; i < 6; i++) {
			if (map[i][y] != 0 || (t >= 2 && i + dx < 6 && map[i + dx][y + dy] != 0)) {
				flag = false;
				map[i - 1][y] = 1;
				if (t >= 2)
					map[i + dx - 1][y + dy] = 1;
				break;
			}
		}
		if (flag) {
			map[5][y] = 1;
			if (t >= 2)
				map[5 - dx][y + dy] = 1;
		}
	}

	static void removeRC(int[][] map) {
		for (int i = 0; i < 6; i++) {
			boolean flag = true;
			for (int j = 0; j < 4; j++) {
				if (map[i][j] == 0)
					flag = false;
			}
			if (flag) {
				score++;
				for (int j = 0; j < 4; j++)
					map[i][j] = 0;
				moveBlock(map, i, 1);
			}
		}
	}

	static void moveBlock(int[][] map, int rc, int sz) {
		for (int i = rc; i >= sz; i--) {
			for (int j = 0; j < 4; j++)
				map[i][j] = map[i - sz][j];
		}
		for (int i = 0; i < sz; i++) {
			for (int j = 0; j < 4; j++)
				map[i][j] = 0;
		}
	}

	static void removeLight(int[][] map) {
		int sz = 0;
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 4; j++) {
				if (map[i][j] == 1) {
					sz++;
					break;
				}
			}
		}
		moveBlock(map, 5, sz);
	}

	static void count() {
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 4; j++) {
				if (green[i][j] == 1)
					cnt++;
				if (blue[i][j] == 1)
					cnt++;
			}
		}
	}
}
