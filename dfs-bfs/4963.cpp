#include <iostream>

using namespace std;

bool map[51][51] = { false };
int dirX[] = { -1,-1,-1,0,0,1,1,1 };
int dirY[] = { -1,0,1,-1,1,-1,0,1 };
int n, m, i, j, finished, tmp;

// visit and look around. if !visited, do dfs
void dfs(int x, int y)
{
	map[x][y] = 0;
	int newX, newY;

	for (int i = 0; i < 8; i++) {
		newX = x + dirX[i];
		newY = y + dirY[i];
		if (newX >= 0 && newX < n && newY >= 0 && newY < m
			&& map[newX][newY] == 1)
			dfs(newX, newY);
	}
}

int main()
{
	while (1)
	{
		cin >> m >> n;
		if (n == 0 && m == 0)
			break;
		for (i = 0; i < n; i++) {
			for (j = 0; j < m; j++) {
				cin >> tmp;
				if (tmp == 1)
					map[i][j] = 1;
			}
		}

		// if island and !visited, do dfs
		finished = 0;
		for (i = 0; i < n; i++) {
			for (j = 0; j < m; j++) {
				if (map[i][j] == 1) {
					dfs(i, j);
					finished++;
				}
			}
		}
		cout << finished << endl;
	}
}