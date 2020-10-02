import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 *  11048. 이동하기
 *  https://www.acmicpc.net/problem/11048
 *  현재 위치에 올 수 있는 가능한 이전 위치의 누적 사탕 개수의 최소값을 더해준다.
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N, M, miro[][];
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		miro = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				miro[i][j] = Integer.parseInt(st.nextToken());
				if (i == 0) {
					if (j != 0)
						miro[i][j] += miro[i][j - 1];
				} else if (j == 0)
					miro[i][j] += miro[i - 1][j];
				else {
					miro[i][j] += Math.max(miro[i - 1][j], Math.max(miro[i - 1][j - 1], miro[i][j - 1]));
				}
			}
		}
		System.out.println(miro[N - 1][M - 1]);
	}
}
