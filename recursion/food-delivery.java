import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 *  SWEA. 음식 배달 (Hidden Problem) 
 *  음식점(>1)을 임의의 개수만큼 선택하고
 *  집(1)에서 음식점까지 가까운 거리합+운영비(해당숫자)의 최소값을 구해라
 *  BFS로 해서 메모리, 시간의 낭비가 있었는데
 *  BFS없이 거리만 구하고 업데이트 해주면 되는 문제였다.
 */
public class 배달1019_1 {
	static ArrayList<Store> stores, house;
	static int N, H, S, ans, map[][], dist[];
	static boolean sel[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			stores = new ArrayList<>();
			house = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 1)
						house.add(new Store(i, j, 1));
					else if (map[i][j] > 1)
						stores.add(new Store(i, j, map[i][j]));
				}
			}
			S = stores.size();
			H = house.size();
			ans = Integer.MAX_VALUE;
			sel = new boolean[S];
			powerSet(0);
			System.out.println("#" + tc + " " + ans);
		}
	}

	static void distance() {
		int cnt = 0;
		dist = new int[H];
		for (int i = 0; i < S; i++) {
			if (!sel[i])
				continue;
			Store s = stores.get(i);
			cnt += s.o;
			for (int j = 0; j < H; j++) {
				Store h = house.get(j);
				int tmp = Math.abs(h.x - s.x) + Math.abs(h.y - s.y);
				if (dist[j] == 0 || dist[j] > tmp)
					dist[j] = tmp;
			}
		}
		for (int i = 0; i < H; i++)
			cnt += dist[i];
		if (cnt != 0 && ans > cnt)
			ans = cnt;
	}

	static void powerSet(int idx) {
		if (idx == S) {
			distance();
			return;
		}
		sel[idx] = true;
		powerSet(idx + 1);
		sel[idx] = false;
		powerSet(idx + 1);
	}

	static class Store {
		int x, y, o;

		public Store(int x, int y, int o) {
			this.x = x;
			this.y = y;
			this.o = o;
		}
	}
}
