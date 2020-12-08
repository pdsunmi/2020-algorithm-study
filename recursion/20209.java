import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 *  20209. 스트레이트 스위치 게임 
 *  https://www.acmicpc.net/problem/20209
 *  누르는 횟수를 늘려가면서 부분집합을 이용해 검사를 진행했다.
 */
public class Main {
	static int N, K, ans, cube[], switchs[][], sel[], newCube[];
	static boolean success;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		cube = new int[N];
		sel = new int[K];
		switchs = new int[K][N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			cube[i] = Integer.parseInt(st.nextToken());
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int B = Integer.parseInt(st.nextToken());
			for (int j = 0; j < B; j++) {
				int b = Integer.parseInt(st.nextToken()) - 1;
				switchs[i][b] = i + 1;
			}
		}
		while (ans <= 4 * K) {
			powerSet(0, ans);
			if (success)
				break;
			ans++;
		}
		if (!success)
			ans = -1;
		System.out.println(ans);
	}

	static void powerSet(int idx, int num) {
		if (success)
			return;
		if (idx == K) {
			if (num > 0)
				return;
			check();
			return;
		}
		for (int i = 0; i <= num; i++) {
			if (i >= 5)
				break;
			sel[idx] = i;
			powerSet(idx + 1, num - i);
		}
	}

	static void check() {
		newCube = cube.clone();
		for (int i = 0; i < K; i++) {
			if (sel[i] != 0) {
				for (int j = 0; j < N; j++) {
					if (switchs[i][j] == 0)
						continue;
					newCube[j] = (switchs[i][j] * sel[i] + newCube[j]) % 5;
				}
			}
		}
		for (int i = 1; i < N; i++) {
			if (newCube[i] != newCube[0])
				return;
		}
		success = true;
	}
}