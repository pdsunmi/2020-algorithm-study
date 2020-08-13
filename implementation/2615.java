import java.util.Scanner;
/*
 *  2615. 오목
 *  https://www.acmicpc.net/problem/2615
 */
public class Omok {
	static int dx[] = { 0, 1, 1, 1 };
	static int dy[] = { 1, 1, 0, -1 };
	static int map[][] = new int[19][19];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		boolean exit = false;
		// input map data
		for (int i = 0; i < 19; i++) {
			for (int j = 0; j < 19; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		for (int i = 0; i < 19; i++) {
			for (int j = 0; j < 19; j++) {
				if (map[i][j] != 0) {
					if(sol(i, j, map[i][j])) {
						exit=true;
						break;
					}
				}
			}
			if (exit)
				break;
		}
		if (!exit)
			System.out.println("0");
		sc.close();
	}

	public static boolean sol(int x, int y, int color) {
		// 오른쪽, 대각선 아래(왼/오), 아래에 대해서 탐색
		for (int i = 0; i < 4; i++) {
			int new_x = x + dx[i];
			int new_y = y + dy[i];
			int cnt = 1;
			// 연속된 같은 색의 수를 count 
			while (true) {
				if (new_x < 0 || new_y < 0 || new_x >= 19 || new_y >= 19)
					break;
				if (map[new_x][new_y] != color)
					break;
				new_x += dx[i];
				new_y += dy[i];
				cnt++;
			}
			// 오목일 경우
			if (cnt == 5) {
				new_x = x - dx[i];
				new_y = y - dy[i];
				// 반대방향의 좌표가 색이 다르거나 존재하지 않아야 오목
				if (new_x < 0 || new_y < 0 || new_x >= 19 || new_y >= 19 || map[new_x][new_y] != color) {
					System.out.println(color);
					// 왼쪽 아래방향의 경우 가장 왼쪽 좌표가 더 아래이기 때문에 맞게 출력해줌
					if (i == 3)
						System.out.println((x + 5) + " " + (y - 3));
					else
						System.out.println((x + 1) + " " + (y + 1));
					return true;
				}
			}
		}
		return false;
	}
}
