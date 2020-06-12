//
//	https://www.acmicpc.net/problem/1753
//	Dijkstra 문제 (주어진 노드에서 모든 노드로의 최단경로 찾기)
//	priority queue로 구성된 fringe set에서 pop
//  pop한 노드 a와 a의 인접 노드 b에 대하여 
//	(~b의 현재 최단경로 dp[b])와 (~a까지의 거리)+(a~b 거리)를 비교함
//	※ endl을 쓰면 시간초과 주의
//
#include <iostream>
#include <vector>
#include <queue>

using namespace std;

#define INF 2147483647
#define MAX 20001

struct Node {
	int number;
	int weight; 
	Node(int t, int w) : number(t), weight(w) {}
};

struct comp {
	bool operator() (const Node &x, const Node &y)
	{
		return x.weight > y.weight;
	}
};

int main()
{
	priority_queue<Node, vector<Node>, comp> fringe;	// for dijkstra
	vector<Node> v[MAX];	// edges info
	int dp[MAX];	// result 

	int n, m, s;
	cin >> n >> m >> s;
	
	fill(dp, dp + n + 1, INF);
	for (int i = 0; i < m; i++)
	{
		int from, to, w;
		cin >> from >> to >> w;
		v[from].push_back(Node(to,w));
	}

	// dijkstra
	fringe.push(Node(s, 0));
	dp[s] = 0; 
	while (!fringe.empty())
	{
		int nodeNum = fringe.top().number;
		int nodeWei = fringe.top().weight;

		fringe.pop();
		if (dp[nodeNum] < nodeWei) continue;	
		for (int i = 0; i < v[nodeNum].size(); i++)
		{
			int newNum = v[nodeNum][i].number;	
			int newWei = v[nodeNum][i].weight + nodeWei;	

			if (dp[newNum] > newWei)	
			{
				dp[newNum] = newWei;
				fringe.push(Node(newNum, newWei));
			}
		}
	}

	// print
	for (int i = 1; i <= n; i++)
	{
		if (dp[i] == INF)	
			cout << "INF" << '\n';
		else	
			cout << dp[i] << '\n';
	}
	return 0;
}