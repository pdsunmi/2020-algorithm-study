#include <iostream>
#include <vector>
using namespace std;

int main()
{
	int i, j, k, n, m, a, b, c;
	int inf = 1000000000;

	cin >> n >> m;	
	vector<vector<int>> v(n, vector<int>(n, inf));

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
				if (i == j)
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
			if (v[i][j] == inf)
				cout << "0 ";
			else
				cout << v[i][j] << " ";
		}
		cout << endl;
	}
}