import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 *  2096. 내려가기
 *  https://www.acmicpc.net/problem/2096
 *  DP로 최소와 최대를 동시에 구한다.
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N, num[], min[][], max[][], k = 0;
		N = Integer.parseInt(br.readLine());
		num = new int[3];
		max = new int[2][3];
		min = new int[2][3];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				num[j] = Integer.parseInt(st.nextToken());
				if (j == 0) {
					min[(k + 1) % 2][0] = num[0] + Math.min(min[k][0], min[k][1]);
					max[(k + 1) % 2][0] = num[0] + Math.max(max[k][0], max[k][1]);
				} else if (j == 1) {
					min[(k + 1) % 2][1] = num[1] + Math.min(min[k][0], Math.min(min[k][1], min[k][2]));
					max[(k + 1) % 2][1] = num[1] + Math.max(max[k][0], Math.max(max[k][1], max[k][2]));
				} else {
					min[(k + 1) % 2][2] = num[2] + Math.min(min[k][1], min[k][2]);
					max[(k + 1) % 2][2] = num[2] + Math.max(max[k][1], max[k][2]);
				}
			}
			k = (k + 1) % 2;
		}
		System.out.print(Math.max(max[k][0], Math.max(max[k][1], max[k][2])) + " "
				+ Math.min(min[k][0], Math.min(min[k][1], min[k][2])));
	}
}