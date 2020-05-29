#include <iostream>
#include <vector>
using namespace std;

#define INF INT32_MAX

int main()
{
	int i, j, k, n, m, a, b, c;
	cin >> n >> m;	
	vector<vector<int>> v(n, vector<int>(n, INF));

	// initialize
	for (i = 0; i < n; i++)
	{
		v[i][i] = 0;
	}
	for (i = 0; i < m; i++)
	{
		cin >> a >> b >> c;
		if (v[a-1][b-1] > c)
			v[a-1][b-1] = c;
	}

	//floyd-warshall
	for (k = 0; k < n; k++)
	{
		for (i = 0; i < n; i++)
		{
			for (j = 0; j < n; j++)
			{
				if (i == j || k == i || k == j)
					continue;
				if (v[i][k] == INF || v[k][j] == INF)
					continue;
				if (v[i][k] + v[k][j] < v[i][j])
					v[i][j] = v[i][k]+v[k][j];
			}
		}
	}

	//print
	for (i = 0; i < n; i++)
	{
		for (j = 0; j < n; j++)
		{
			if (v[i][j] == INF)
				cout << "0 ";
			else
				cout << v[i][j] << " ";
		}
		cout << endl;
	}
}
