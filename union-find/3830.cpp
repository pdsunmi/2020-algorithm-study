//
//	https://www.acmicpc.net/problem/3830
//	Union-find를 이용하는 문제
//	Time limit 때문에 최적화로 rank 비교/size 비교 등을 시도했지만
//	오히려 추가된 배열의 초기화 때문에 time limit이 나는 문제였다
//	배열로 구현하여 path compression 하면 되는 문제이다
//	Find는 Update의 개념, Union은 한쪽 방향으로만
//
#include <iostream>

using namespace std;

int root[100001];			// idx값의 root를 기록
long long weight[100001];	// idx값과 root의 차이 기록

// find : root까지 recursive로 weight update (path compression)
int find(int x)	
{
	if (root[x] == x) return x;
	int r = find(root[x]);
	weight[x] += weight[root[x]];
	root[x] = r;
	return r;
}
// union : x group에 y group을 union
// root[x]와 x의 차이, root[y]와 y의 차이, x와 y의 차이인 z를 이용한다
void uni(int x, int y, long long z)
{
	int rx = root[x];
	int ry = root[y];

	if (rx == ry) return;
	root[ry] = rx;
	weight[ry] = weight[x] - weight[y] + z;
}
int main()
{
	ios::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
	while (1)
	{
		int n, m, a, b;
		long long w;
		cin >> n >> m;
		if (n == 0 && m == 0) break;

		// initialize
		for (int i = 1; i < n + 1; i++) root[i] = i, weight[i] = 0;
		for (int i = 0; i < m; i++)
		{
			char c;
			cin >> c >> a >> b;
			
			// a,b를 update한다
			find(a);
			find(b);
			if (c == '!')	// union
			{
				cin >> w;
				uni(a, b, w);	
			}
			else if (c == '?')	
			{
				if (root[a] != root[b])
					cout << "UNKNOWN\n";
				else
					cout << weight[b] - weight[a] << '\n';
			}
		}
	}
	return 0;
}