#include <iostream>
#include <cmath>
#include <vector>
using namespace std;


void v_copy(vector<vector<char>> &v, int n, int m)
{
	for (int i = 0; i < m; i++)
	{
		for (int j = n-m; j < n+m-1; j++)
		{
			if (i + j >= n - 1 && j - i < n)
			{
				v[i + m][j - m] = v[i][j];
				v[i + m][j + m] = v[i][j];
			}
		}
	}
}


int main()
{
	int n, i, j;
	cin >> n;

	vector<vector<char>> v(n, vector<char>(n*2-1, ' '));

	for (i = 0; i < 3; i++)
	{
		v[0][n-1] = '*';
		v[1][n-2] = '*';
		v[1][n] = '*';
		for (j = n-3; j <= n+1; j++)
		{
			v[2][j] = '*';
		}
	}

	int m = 3;
	for (i = 1; i <= 10; i++)
	{
		if (m == n)
			break;
		else
		{
			v_copy(v, n, m);
			m *= 2;
		}
	}
	for (i = 0; i < n; i++)
	{
		for (j = 0; j < 2*n-1; j++)
			cout << v[i][j];
		if (i != n - 1)
			cout << endl;
	}
	return 0;
}
