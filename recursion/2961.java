import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 *  2961. 도영이가 만든 맛있는 음식 - Subset
 *  https://www.acmicpc.net/problem/2961
 */
public class Doyoung {
	static boolean[] check;
	static Mat[] mats;
	static int N, answer;

	static class Mat {
		int sour;
		int bitter;

		public Mat(int sour, int bitter) {
			this.sour = sour;
			this.bitter = bitter;
		}

	}

	static void subset(int idx) {
		if (idx == N) {
			int total_sour = 1;
			int total_bitter = 0;
			for (int i = 0; i < N; i++) {
				if (check[i]) {
					total_sour *= mats[i].sour;
					total_bitter += mats[i].bitter;
				}
			}
			if (total_sour == 1 && total_bitter == 0)
				return;
			answer = Math.min(answer, Math.abs(total_sour-total_bitter));
			return;
		}
		check[idx] = true;
		subset(idx + 1);
		check[idx] = false;
		subset(idx + 1);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		mats = new Mat[N];
		check = new boolean[N];
		answer = Integer.MAX_VALUE;
		int sour, bitter;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			sour = Integer.parseInt(st.nextToken());
			bitter = Integer.parseInt(st.nextToken());
			mats[i] = new Mat(sour, bitter);
		}
		subset(0);
		System.out.println(answer);
		System.exit(0);
	}
}
