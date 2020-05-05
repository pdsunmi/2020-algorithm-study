#include <iostream>
#include <cmath>
#include <vector>
using namespace std;


void v_copy(vector<vector<char>> &v, int num)
{
	int num2 = 2 * num;
	for (int i = 0; i < 3; i++)
	{
		for (int j = 0; j < num; j++) 
		{
			if (i != 0)
				copy(v[j].begin(), v[j].begin() + num, v[i*num + j].begin());
			if (i != 1)
				copy(v[j].begin(), v[j].begin() + num, v[i*num + j].begin() + num);
			copy(v[j].begin(), v[j].begin() + num, v[i*num + j].begin() + num2);
			
		}
	}
}


int main()
{
	int n, i, j;
	cin >> n;

	vector<vector<char>> v(n, vector<char>(n, ' '));

	for (i = 0; i < 3; i++)
	{
		for (j = 0; j < 3; j++)
		{
			if (i != 1 || j != 1)
				v[i][j] = '*';
		}
	}
	for (i=1;i<8;i++)
	{
		int tmp = pow(3, i);
		if (tmp == n)
			break;
		else
			v_copy(v, tmp);
	}
	for (i = 0;i < n;i++)
	{
		for (j = 0; j < n; j++)
			cout << v[i][j];
		if (i != n - 1)
			cout << endl;
	}
	return 0;
}
