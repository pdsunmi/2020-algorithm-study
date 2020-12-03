import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 *  2383. [모의 SW 역량테스트] 점심 식사시간
 *  끝나는 시간들을 모아서 계산하려 했지만
 *  같은 시간에 6명 정도가 올 경우 계산이 불가능했다.
 *  계단의 수는 2개로 고정이였다.
 */
public class 점심식사시간{

	static int N, P, ans, sel[];
	static List<Stair> stairs;
	static List<People> people;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			stairs = new ArrayList<>();
			people = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					int m = Integer.parseInt(st.nextToken());
					if (m == 0)
						continue;
					if (m == 1)
						people.add(new People(i, j));
					else
						stairs.add(new Stair(i, j, m));
				}
			}
			P = people.size();
			ans = Integer.MAX_VALUE;
			sel = new int[P];
			for (int i = 0; i < P; i++) {
				for (int j = 0; j < 2; j++) {
					people.get(i).dist[j] = Math.abs(people.get(i).r - stairs.get(j).r)
							+ Math.abs(people.get(i).c - stairs.get(j).c) + 1;
				}
			}
			powerSet(0);
			System.out.println("#" + tc + " " + ans);
		}
	}

	static void powerSet(int idx) {
		if (idx == P) {
			ans = Math.min(ans, solve());
			return;
		}
		people.get(idx).sel = 0;
		powerSet(idx + 1);
		people.get(idx).sel = 1;
		powerSet(idx + 1);
	}

	static int solve() {
		int max = 0;
		int[][] time = new int[2][200];
		PriorityQueue<People>[] stair = new PriorityQueue[2];
		stair[0] = new PriorityQueue<>();
		stair[1] = new PriorityQueue<>();
		for (int i = 0; i < P; i++)
			stair[people.get(i).sel].add(people.get(i));
		for (int i = 0; i < 2; i++) {
			int from, to = 0;
			int h = stairs.get(i).h;
			while (!stair[i].isEmpty()) {
				People p = stair[i].poll();
				from = p.dist[p.sel];
				to = from + h;
				for (int j = from; j < to; j++) {
					if (time[p.sel][j] == 3) {
						to++;
						continue;
					}
					time[p.sel][j]++;
				}
			}
			max = Math.max(max, to);
		}
		return max;
	}

	static class Stair {
		int r, c, h;

		public Stair(int r, int c, int h) {
			this.r = r;
			this.c = c;
			this.h = h;
		}
	}

	static class People implements Comparable<People> {
		int r, c, sel, dist[];

		public People(int r, int c) {
			this.r = r;
			this.c = c;
			dist = new int[2];
		}

		@Override
		public int compareTo(People o) {
			return this.dist[sel] - o.dist[sel];
		}
	}
}
