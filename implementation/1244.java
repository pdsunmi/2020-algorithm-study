import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 *  1244. 스위치 켜고 끄기
 *  https://www.acmicpc.net/problem/1244
 */
public class 스위치켜고끄기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int[] light = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++)
			light[i] = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int g = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());
			if (g == 1) {
				for (int j = n; j <= N; j += n)
					light[j] = (light[j] + 1) % 2;
			} else {
				light[n] = (light[n] + 1) % 2;
				for (int j = 1; j < N; j++) {
					if (n + j <= N && n - j > 0 && light[n - j] == light[n + j]) {
						light[n - j] = (light[n - j] + 1) % 2;
						light[n + j] = (light[n + j] + 1) % 2;
						continue;
					}
					break;
				}
			}
		}
		for (int i = 1; i <= N; i++) {
			sb.append(light[i] + " ");
			if (i % 20 == 0)
				sb.append("\n");
		}
		System.out.println(sb);
	}
}