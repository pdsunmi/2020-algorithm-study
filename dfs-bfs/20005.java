import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 *  20005. 보스몬스터 전리품
 *  https://www.acmicpc.net/problem/20005
 *  보스와 플레이어 간의 거리를 구할 때 보스부터 출발하여 bfs를 하면 한번에 계산이 가능하다.
 *  HashMap을 잘 활용해서 입력값에 맞게 활용하자.
 *  isEmpty나 long 등 런타임에러가 날 부분이 없는지 검토하자.
 */
public class Main {
	static final int dx[] = { -1, 0, 1, 0 };
	static final int dy[] = { 0, 1, 0, -1 };
	static int N, M, P, HP, ans, boss[];
	static char map[][];
	static HashMap<Character, Integer> h;
	static List<Player> player;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		map = new char[M][N];
		player = new LinkedList<>();
		h = new HashMap<>();
		for (int i = 0; i < M; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 'B')
					boss = new int[] { i, j };
			}
		}
		for (int i = 0; i < P; i++) {
			st = new StringTokenizer(br.readLine());
			char id = st.nextToken().charAt(0);
			int power = Integer.parseInt(st.nextToken());
			h.put(id, power);
		}
		HP = Integer.parseInt(br.readLine());

		bfs();
		Collections.sort(player);
		attack();
		System.out.println(ans);
	}

	static void attack() {
		int dist, second;
		long sub = 0;
		while (HP > 0 && !player.isEmpty()) {
			dist = player.get(0).dist;
			sub += h.get(player.get(0).id);
			player.remove(0);
			ans++;
			while (!player.isEmpty() && player.get(0).dist == dist) {
				sub += h.get(player.get(0).id);
				player.remove(0);
				ans++;
			}
			second = 1;
			if (!player.isEmpty())
				second = player.get(0).dist - dist;
			HP -= (sub * second);
		}
	}

	static void bfs() {
		Queue<int[]> q = new LinkedList<>();
		boolean[][] visit = new boolean[M][N];
		q.add(boss);
		visit[boss[0]][boss[1]] = true;
		int dist = 0;
		int players = 0;
		while (!q.isEmpty() && players <= P) {
			int sz = q.size();
			for (int j = 0; j < sz; j++) {
				int[] p = q.poll();
				if (map[p[0]][p[1]] >= 'a' && map[p[0]][p[1]] <= 'z') {
					player.add(new Player(map[p[0]][p[1]], p[0], p[1], dist));
					players++;
				}
				for (int d = 0; d < 4; d++) {
					int nx = p[0] + dx[d];
					int ny = p[1] + dy[d];
					if (nx < 0 || ny < 0 || nx >= M || ny >= N || visit[nx][ny] || map[nx][ny] == 'X')
						continue;
					q.add(new int[] { nx, ny });
					visit[nx][ny] = true;
				}
			}
			dist++;
		}
	}

	static class Player implements Comparable<Player> {
		char id;
		int x, y, dist;

		public Player(char id, int x, int y, int dist) {
			super();
			this.id = id;
			this.x = x;
			this.y = y;
			this.dist = dist;
		}

		@Override
		public int compareTo(Player o) {
			return this.dist - o.dist;
		}
	}
}
