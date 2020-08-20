import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/*
 *  Hackerland Radio Transmitters
 *  https://www.hackerrank.com/challenges/hackerland-radio-transmitters/problem
 *  n개의 집이 있을때 송신기를 설치하는데 i번째 집에 설치하면 i-k와 i+k까지 커버가 가능하다
 *  단, 송신기는 집 위에만(집이 존재할때만) 설치 할 수 있다.
 *  송신기를 최소한으로 설치할 때 개수를 구하라.
 */
public class Radio {

	// x[] : 집의 위치 배열, k : 커버가능한 거리
	static int hackerlandRadioTransmitters(int[] x, int k) {
		Arrays.sort(x);
		int cnt = 1;
		int start = x[0]; 	// 시작점
		int end = x[0] + k; // 여기까지는 집이 없어도 보장됨
		// 시작점, 시작점+k, 시작점+2k를 고려해야한다
		for (int i = 0; i < x.length; i++) {
			// end는 시작점+k를 의미하며 end이전이면 start를 갱신해준다 (최대 시작점+2k까지 갈 수 있으니까)
			if (x[i] <= end) {
				start = x[i];
			}
			// 갱신된 start는 최대 시작점+k가 될 수 있고(end), start+k는 최대 시작점+2k가 되어 범위 설정이 가능하다
			else if (x[i] > start + k) {
				// 범위가 벗어났다면 새로 start,end를 설정하고 개수를 늘려준다
				start = x[i];
				end = x[i] + k;
				cnt++;
			}
		}
		return cnt;
	}
	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws IOException {
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		String[] nk = scanner.nextLine().split(" ");

		int n = Integer.parseInt(nk[0]);

		int k = Integer.parseInt(nk[1]);

		int[] x = new int[n];

		String[] xItems = scanner.nextLine().split(" ");
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

		for (int i = 0; i < n; i++) {
			int xItem = Integer.parseInt(xItems[i]);
			x[i] = xItem;
		}

		int result = hackerlandRadioTransmitters(x, k);

		bufferedWriter.write(String.valueOf(result));
		bufferedWriter.newLine();

		bufferedWriter.close();

		scanner.close();
	}
}
