// https://www.acmicpc.net/problem/6118
#include <iostream>
#include <vector>
#include <queue>

using namespace std;

int main()
{
	vector<vector<int>> v;
	queue <int> q;
	bool *visited;
	int n, m, num, cnt, sz;

	cin >> n >> m;
	v.resize(n+1);
	visited = new bool[n+1];

	// make bidirection
	for (int i = 0; i < m; i++)
	{
		int a, b;
		cin >> a >> b;
		v[a].push_back(b);
		v[b].push_back(a);
	}

	// start from 1 
	q.push(1);
	visited[1] = 1;
	cnt = -1;

	// bfs
	while (!q.empty())
	{
		sz = q.size();
		if (!q.empty()) num = q.front();

		for (int i = 0; i < sz; i++)
		{
			int tmp = q.front();
			if (tmp < num) num = tmp;

			// dequeue
			q.pop();
			// adjacent
			for (int j = 0; j < v[tmp].size(); j++)
			{
				// visit
				if (visited[v[tmp][j]] != 1)
				{
					q.push(v[tmp][j]);
					visited[v[tmp][j]] = 1;
				}
			}
		}
		cnt++;
	}
	cout << num << " " << cnt << " " << sz;

	delete[] visited;
}