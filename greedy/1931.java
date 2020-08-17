import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/*
 *  1931. 회의실배정
 *  https://www.acmicpc.net/problem/1931
 *  우선순위 끝나는 시간>시작시간 으로 정렬 후 그리디로
 *  끝나는 시간이 짧은 것을 고르면 남은 시간의 양이 많아져서 정답이 된다.
 *  Comparator 사용
 */
public class SessionRoom {

	static Session[] sessionList;
	static int N, cnt;

	static class Session {
		long start;
		long end;

		public Session(long start, long end) {
			this.start = start;
			this.end = end;
		}
	}

	static class SessionComparator implements Comparator<Session> {
		public int compare(Session s1, Session s2) {
			if (s1.end == s2.end && s1.start < s2.start)
				return -1;
			return Long.compare(s1.end, s2.end);
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		sessionList = new Session[N];
		long start, end;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			start = Integer.parseInt(st.nextToken());
			end = Integer.parseInt(st.nextToken());
			sessionList[i] = new Session(start, end);
		}
		Arrays.sort(sessionList, new SessionComparator());
		start = 0;
		Session s;
		for (int i = 0; i < N; i++) {
			s = sessionList[i];
			if (s.start >= start) {
				cnt++;
				start = s.end;
			}
		}
		System.out.println(cnt);
		System.exit(0);
	}
}
