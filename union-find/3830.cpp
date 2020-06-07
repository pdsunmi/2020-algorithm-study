//
//	https://www.acmicpc.net/problem/3830
//	Union-find�� �̿��ϴ� ����
//	Time limit ������ ����ȭ�� rank ��/size �� ���� �õ�������
//	������ �߰��� �迭�� �ʱ�ȭ ������ time limit�� ���� ��������
//	�迭�� �����Ͽ� path compression �ϸ� �Ǵ� �����̴�
//	Find�� Update�� ����, Union�� ���� �������θ�
//
#include <iostream>

using namespace std;

int root[100001];			// idx���� root�� ���
long long weight[100001];	// idx���� root�� ���� ���

// find : root���� recursive�� weight update (path compression)
int find(int x)	
{
	if (root[x] == x) return x;
	int r = find(root[x]);
	weight[x] += weight[root[x]];
	root[x] = r;
	return r;
}
// union : x group�� y group�� union
// root[x]�� x�� ����, root[y]�� y�� ����, x�� y�� ������ z�� �̿��Ѵ�
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
			
			// a,b�� update�Ѵ�
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