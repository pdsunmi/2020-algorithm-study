import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 *  1786. 찾기
 *  https://www.acmicpc.net/problem/1786
 *  KMP 알고리즘 구현
 */
public class KMP {
	static StringBuilder ans;
	static char[] T, P;
	static int[] Pi;
	static int cnt, Plen, Tlen;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		ans = new StringBuilder();
		T = br.readLine().toCharArray();
		P = br.readLine().toCharArray();
		Tlen = T.length;
		Plen = P.length;
		Pi = new int[Plen];

		makePi();
		patternMatch();

		System.out.println(cnt);
		System.out.println(ans);
	}

	static void patternMatch() {
		int j = 0;
		for (int i = 0; i < Tlen; i++) {
			while (j > 0 && T[i] != P[j]) {
				j = Pi[j - 1];
			}
			if (T[i] == P[j])
				j++;
			if (j == Plen) {
				ans.append((i - Plen + 2) + " ");
				cnt++;
				// 패턴이 겹쳐서 나타날 수 있기 때문에 0으로 초기화 하지 않고 Pi배열에서 가져온다.
				j = Pi[j - 1];
			}
		}
	}

	static void makePi() {
		int j = 0;
		for (int i = 1; i < Plen; i++) {
			// 패턴이 다르다면 Pi[j-1]로 점프시킨다
			while (j > 0 && P[i] != P[j]) {
				j = Pi[j - 1];
			}
			// 패턴이 같다면 i증가 j증가 (0에서 같았다면 1개 같은거니까 j+1을 기록)
			if (P[i] == P[j]) {
				Pi[i] = j + 1;
				j++;
			}
			// j가 0이라면 0을 기록한다
		}
	}
}