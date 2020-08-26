import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 *  1194. 달이 차오른다, 가자.
 *  https://www.acmicpc.net/problem/1194
 *  각 상태 노드가 어떤 key를 가지고 있는지 알고 있어야만 하는 문제이다.
 *  
 *  처음에는 key를 만났을 때 visited를 초기화하면 되는거 아닌가? 했는데
 *  어떤 key를 선택하느냐에 따라 최단거리가 나오지 않는 반례가 존재했다.
 *  각 상태 노드가 가진 key를 알아야 논리적으로 오류가 없다.
 *  
 *  각 상태 노드가 어떤 key를 가지고 있는지 알기 위한 방법으로 bit mask를 이용했다
 *  정수 하나로 a~f 6개 bit의 1/0을 체크할 수 있어서 편했다.
 *  
 *  방문을 체크하는 배열도 가진 key가 달라졌을 때는 다른 방문으로 구분해야하기 때문에
 *  각 row, column에 대해 다른 key들에 대해서도 체크할 3차원 visited를 만들었다.
 *    
 *  거리를 구할 때 최단 거리 문제이므로 상태 노드에 cnt를 두기 보다는
 *  큐의 크기만큼 돌고 cnt++해주면 공간을 절약할 수 있다.
 */
public class Dal {
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static boolean[][][] visited;
	static char[][] map;
	static int N, M, cnt, sz;
	static Queue<Point> q;
	static Point start;

	static class Point {
		int x, y, key;

		Point(int x, int y, int key) {
			this.x = x;
			this.y = y;
			this.key = key;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		visited = new boolean[N][M][1 << 6];
		q = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				if (map[i][j] == '0') {
					start = new Point(i, j, 0);
					map[i][j] = '.';
				}
			}
		}
		cnt = bfs();
		System.out.println(cnt);
	}

	static int bfs() {
		cnt = 0;
		visited[start.x][start.y][0] = true;
		q.offer(start);
		while (!q.isEmpty()) {
			sz = q.size();
			for (int i = 0; i < sz; i++) {
				Point p = q.poll();
				if (map[p.x][p.y] == '1')
					return cnt;
				for (int d = 0; d < 4; d++) {
					int nx = p.x + dx[d];
					int ny = p.y + dy[d];
					int nkey = p.key;
					if (nx < 0 || ny < 0 || nx >= N || ny >= M)
						continue;
					if (visited[nx][ny][nkey])
						continue;
					if (map[nx][ny] == '#')
						continue;
					if (map[nx][ny] >= 'a' && map[nx][ny] <= 'f') {
						nkey |= (1 << (map[nx][ny] - 'a'));
					} else if (map[nx][ny] >= 'A' && map[nx][ny] <= 'F') {
						if ((nkey & (1 << (map[nx][ny] - 'A'))) == 0)
							continue;
					}
					visited[nx][ny][nkey] = true;
					q.offer(new Point(nx, ny, nkey));
				}
			}
			cnt++;
		}
		return -1;
	}
}
