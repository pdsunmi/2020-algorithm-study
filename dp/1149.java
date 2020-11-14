import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 *  1149. RGB거리
 *  https://www.acmicpc.net/problem/1149
 *  현재 집을 칠할 수 있기 위한 가능한 이전 위치들의 최소값을 더해준다.
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N, cost[][];
		N = Integer.parseInt(br.readLine());
		cost = new int[N + 1][3];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) 
				cost[i][j] = Integer.parseInt(st.nextToken())
						+ Math.min(cost[i - 1][(j + 1) % 3], cost[i - 1][(j + 2) % 3]);
		}
		System.out.println(Math.min(cost[N][0], Math.min(cost[N][1], cost[N][2])));
	}
}