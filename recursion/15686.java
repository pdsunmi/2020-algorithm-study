import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 *  15686. 치킨 배달
 *  https://www.acmicpc.net/problem/15686
 */
public class ChickenDelivery {
	static List<Point> chicken, home;
	static int N, M, C, H, ans;
	static int[] sel;

	static class Point {
		int x, y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static void comb(int idx, int s_idx) {
		if (s_idx == M) {
			distance();
			return;
		}
		if (idx == C)
			return;
		sel[s_idx] = idx;
		comb(idx + 1, s_idx + 1);
		comb(idx + 1, s_idx);
	}

	static void distance() {
		int city_dist = 0;
		for (int i = 0; i < H; i++) {
			int dist = Integer.MAX_VALUE;
			for (int j = 0; j < M; j++) {
				dist = Math.min(dist, Math.abs(home.get(i).x - chicken.get(sel[j]).x)
						+ Math.abs(home.get(i).y - chicken.get(sel[j]).y));
			}
			city_dist += dist;
		}
		ans = Math.min(ans, city_dist);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		sel = new int[M];
		chicken = new ArrayList<>();
		home = new ArrayList<>();
		ans = Integer.MAX_VALUE;
		int input;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				input = Integer.parseInt(st.nextToken());
				if (input == 2)
					chicken.add(new Point(i, j));
				else if (input == 1)
					home.add(new Point(i, j));
			}
		}
		C = chicken.size();
		H = home.size();
		comb(0, 0);
		System.out.println(ans);
	}
}