#include <iostream>

using namespace std;

bool map[51][51] = { false };
bool visit[51][51] = { false };
int n, m;

// visit and look around. if !visited, do dfs
void dfs(int x, int y)
{
	visit[x][y] = 1;
	if (x - 1 >= 0)
	{
		if (y - 1 >= 0)
		{
			if (visit[x - 1][y - 1] == 0)
				dfs(x - 1, y - 1);
		}
		if (y + 1 <= m - 1)
		{
			if (visit[x - 1][y + 1] == 0)
				dfs(x - 1, y + 1);
		}
		if (visit[x - 1][y] == 0)
			dfs(x - 1, y);
	}
	if (y - 1 >= 0)
	{
		if (visit[x][y - 1] == 0)
			dfs(x, y - 1);
	}
	if (y + 1 <= m - 1)
	{
		if (visit[x][y + 1] == 0)
			dfs(x, y + 1);
	}
	if (x + 1 <= n - 1)
	{
		if (y - 1 >= 0)
		{
			if (visit[x + 1][y - 1] == 0)
				dfs(x + 1, y - 1);
		}
		if (y + 1 <= m - 1)
		{
			if (visit[x + 1][y + 1] == 0)
				dfs(x + 1, y + 1);
		}
		if (visit[x + 1][y] == 0)
			dfs(x + 1, y);
	}
}

int main()
{
	int i, j, finished;
	
	while (1)
	{	
		cin >> m >> n;
		if (n == 0 && m == 0)
			break;
		else
		{
			for (i = 0; i < n; i++)
			{
				for (j = 0; j < m; j++)
				{
					int tmp;
					cin >> tmp;
					if (tmp == 1)
					{
						map[i][j] = 1; visit[i][j] = 0;
					}
					else
					{
						map[i][j] = 0; visit[i][j] = 1;
					}
				}
			}
			// if island and !visited, do dfs
			finished = 0;
			for (i = 0; i < n; i++)
			{
				for (j = 0; j < m; j++)
				{
					if (map[i][j]==1&&visit[i][j] == 0)
					{
						dfs(i, j);
						finished++;
					}
				}
			}
			cout << finished << endl;
		}
	}
}