#include <string>
#include <iostream>
#include <vector>

using namespace std;

// �ùٸ� ��ȣ ���ڿ����� Ȯ��
bool right(string p) {
	int cnt = 0;
	for (int i = 0; i < p.length(); i++)
	{
		if (p[i] == '(')
			cnt++;
		else
			cnt--;
		if (cnt < 0)
			return false;
	}
	return true;
}

string solution(string p) {

	string answer = "";
	string u, v;
	int i, cnt;

	if (p == "")
		return p;
	else if (right(p) == true)
		return p;
	else
	{
		cnt = 0;
		for (i = 0; i < p.length(); i++)
		{
			if (p[i] == '(')
				cnt++;
			else
				cnt--;
			if (cnt == 0)
				break;
		}
		// ���ڿ� �и�
		u = p.substr(0, i + 1);
		v = p.substr(i + 1);

		// u�� �ùٸ� ��ȣ ���ڿ��� ����
		if (right(u))
			answer = u + solution(v);

		// u�� �ùٸ� ��ȣ ���ڿ��� �ƴ�
		else
		{
			string tmp = '(' + solution(v) + ')';
			if (u == "")
				return tmp;
			else
			{
				for (i = 1; i < u.length() - 1; i++)
				{
					if (u[i] == '(')
						tmp += ')';
					else if (u[i] == ')')
						tmp += '(';
				}
				return tmp;
			}
		}
	}
	return answer;
}