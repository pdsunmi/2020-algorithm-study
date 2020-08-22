import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* 
 *  퇴사
 *  https://www.acmicpc.net/problem/14501
 *  가능한 상담 조합 중 최대 수익을 구한다.
 */
public class Resign {
	static Counsel[] counselList;
	static int N, ans;

	static void comb(int idx, int total) {
		if (idx >= N) {
			ans = Math.max(ans, total);
			return;
		}
		Counsel c = counselList[idx];
		if (idx + c.T <= N)
			comb(idx + c.T, total + c.P);
		comb(idx + 1, total);
	}

	static class Counsel {
		int T, P;

		public Counsel(int t, int p) {
			super();
			this.T = t;
			this.P = p;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		counselList = new Counsel[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int t = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			counselList[i] = new Counsel(t, w);
		}
		ans = 0;
		comb(0, 0);
		System.out.println(ans);
		System.exit(0);
	}
}
