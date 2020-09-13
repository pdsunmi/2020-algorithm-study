import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 *  19535. ㄷㄷㄷㅈ
 *  https://www.acmicpc.net/problem/19535
 *  어떤 트리인지 판단하기
*/
public class Dududunga {
	static List<Integer>[] adjList;
	static long ans, DTree, GTree;
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		adjList = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++)
			adjList[i] = new ArrayList<>();
		for (int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adjList[a].add(b);
			adjList[b].add(a);
		}
		countDTree();
		countGTree();
		if (DTree > GTree * 3)
			System.out.println("D");
		else if (DTree == GTree * 3)
			System.out.println("DUDUDUNGA");
		else
			System.out.println("G");
	}

	static void countGTree() {
		// 모든 노드에 대해
		for (int i = 1; i <= N; i++) {
			int sz = adjList[i].size();
			if (sz < 3)
				continue;
			GTree += 1L * sz * (sz - 1) * (sz - 2) / 6;
		}
	}

	static void countDTree() {
		// 모든 노드에 대해
		for (int i = 1; i <= N; i++) {
			int sz = adjList[i].size();
			for (int j = 0; j < sz; j++) {
				int adjNode = adjList[i].get(j);
				int sz2 = adjList[adjNode].size();
				DTree += 1L * (sz - 1) * (sz2 - 1);
			}
		}
		DTree /= 2;
	}
}