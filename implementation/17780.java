import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/*
 *  17780. 새로운 게임
 *  https://www.acmicpc.net/problem/17780
 *  파란색을 만나면 이동방향을 반대로하고 한 칸 이동한다. 
 *  이동한다에서 바로 이동하면 안되고 다시 조건에 맞게 생각해야한다.
 *  
 *  remove(0)를 빠르게 하려면 LinkedList를 사용하자.
 *  아니면 스택에 담아서 이동하는 방법을 사용하자.
 *  
 *  map에 index들만 연결해주고 말들의 정보를 필요할 때 불러오면
 *  공간적으로 효율을 개선할 수 있다.
 */
public class NewGame {
	static int dir[][] = { { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 } };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		Horse[] h = new Horse[k];
		List<Integer> map[][] = new LinkedList[N][N];
		int[][] color = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++)
				map[i][j] = new LinkedList<>();
		}
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++)
				color[i][j] = Integer.parseInt(st.nextToken());
		}
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			h[i] = new Horse(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1,
					Integer.parseInt(st.nextToken()) - 1);
			map[h[i].x][h[i].y].add(i);
		}
		int turn = 0;
		out: while (turn <= 1000) {
			turn++;
			for (int i = 0; i < k; i++) {
				int idx = map[h[i].x][h[i].y].get(0);
				if (idx != i)
					continue;
				int x = h[idx].x;
				int y = h[idx].y;
				int nx = x + dir[h[idx].d][0];
				int ny = y + dir[h[idx].d][1];
				if (nx < 0 || ny < 0 || nx >= N || ny >= N || color[nx][ny] == 2) {
					if (h[idx].d == 0 || h[idx].d == 2)
						h[idx].d++;
					else
						h[idx].d--;
					nx = x + dir[h[idx].d][0];
					ny = y + dir[h[idx].d][1];
					if (nx < 0 || ny < 0 || nx >= N || ny >= N || color[nx][ny] == 2)
						continue;
					i--;
					continue;
				} else if (color[nx][ny] == 1) {
					Collections.reverse(map[x][y]);
					while (!map[x][y].isEmpty()) {
						int tmp = map[x][y].remove(0);
						h[tmp].x = nx;
						h[tmp].y = ny;
						map[nx][ny].add(tmp);
					}
				} else {
					while (!map[x][y].isEmpty()) {
						int tmp = map[x][y].remove(0);
						h[tmp].x = nx;
						h[tmp].y = ny;
						map[nx][ny].add(tmp);
					}
				}
				if (map[nx][ny].size() >= 4)
					break out;
			}
		}
		System.out.println(turn > 1000 ? -1 : turn);
	}

	static class Horse {
		int x, y, d;

		public Horse(int x, int y, int d) {
			super();
			this.x = x;
			this.y = y;
			this.d = d;
		}
	}
}
