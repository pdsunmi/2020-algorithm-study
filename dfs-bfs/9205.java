import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 *  9205. 맥주 마시면서 걸어가기
 *  https://www.acmicpc.net/problem/9205
 *  BackTracking, 가지치기 이해하기
 *  최적 경로 문제와 다르게 모든 편의점을 방문할 필요가 없다!
 *  for문을 도니까 그지점 다음 지점이 안된다고 해서 그지점이 실패한건 아니기 때문에
 *  visited를 false로 만들지 않는다는 점에 주의하자 
 */
public class Beer {
	static Point home, rock;
	static Point[] cv;
	static boolean[] visited;
	static int T, N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			N = Integer.parseInt(br.readLine());
			cv = new Point[N + 1];
			visited = new boolean[N + 1];
			int r, c;
			for (int i = 0; i < N + 2; i++) {
				st = new StringTokenizer(br.readLine());
				r = Integer.parseInt(st.nextToken());
				c = Integer.parseInt(st.nextToken());
				if (i == 0)
					home = new Point(r, c);
				else {
					if (i == N + 1)
						rock = new Point(r, c);
					cv[i - 1] = new Point(r, c);
				}
			}
			System.out.println(beer(home.x, home.y) ? "happy" : "sad");
		}
	}

	static boolean beer(int r, int c) {
		if (r == rock.x && c == rock.y)
			return true;
		for (int i = 0; i <= N; i++) {
			if (visited[i])
				continue;
			if (Math.abs(r - cv[i].x) + Math.abs(c - cv[i].y) <= 1000) {
				visited[i] = true;
				if (beer(cv[i].x, cv[i].y))
					return true;
			}
		}
		return false;
	}
}