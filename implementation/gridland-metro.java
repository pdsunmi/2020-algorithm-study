import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/*  Gridland Metro
 *  https://www.hackerrank.com/challenges/gridland-metro/problem
 *  track을 정렬하고 같은 행인경우 겹치는 부분을 하나의 track으로 처리해주었다
 *  이후 모든 track을 보면서 길이 계산을 하고 total에 음수로 저장한 후
 *  overflow 방지로 n*m을 하나씩 더해주었다
 */
public class Metro {

	// Complete the gridlandMetro function below.
	static long gridlandMetro(int n, int m, int k, int[][] track) {
		// 행과 첫번째 열에 대해서 sorting
		Arrays.sort(track, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if (o1[0] == o2[0])
					return Integer.compare(o1[1], o2[1]);
				return Integer.compare(o1[0], o2[0]);
			}
		});
		long total = 0;
		for (int i = 1; i < track.length; i++) {
			if (track[i - 1][0] == track[i][0]) {
				// 겹치는 경우
				if (track[i - 1][1] <= track[i][1] && track[i - 1][2] >= track[i][1]) {
					if (track[i][2] > track[i - 1][2]) {
						track[i][1] = track[i - 1][1];
						track[i - 1][2] = track[i - 1][1] - 1; // 앞에꺼 거리 0으로
					} else {
						track[i][1] = track[i - 1][1];
						track[i][2] = track[i - 1][2];
						track[i - 1][2] = track[i - 1][1] - 1;
					}
				}
			}
		}
		for (int i = 0; i < track.length; i++) 
			total -= (track[i][2] - track[i][1] + 1);
		for (int i = 0; i < m; i++)
			total += n;
		return total;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st;
		st = new StringTokenizer(br.readLine(), " ");

		int n = Integer.parseInt(st.nextToken());

		int m = Integer.parseInt(st.nextToken());

		int k = Integer.parseInt(st.nextToken());

		int[][] track = new int[k][3];

		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine(), " ");

			for (int j = 0; j < 3; j++) {
				int trackItem = Integer.parseInt(st.nextToken());
				track[i][j] = trackItem;
			}
		}

		long result = gridlandMetro(n, m, k, track);
		System.out.println(result);
	}
}
