#include <string>
#include <vector>
#include <cmath>
#include <iostream>

using namespace std;

int solution(string s) {
	int len = s.length();
	int answer = len;
	int alpha, num, count, result;

	for (int i = 1; i <= len / 2; i++)
	{
		alpha = 0; num = 0; count = 1;
		for (int j = 0; j < len - i; j += i)
		{
			if (j + i + i > len)
				break;

			// ���ٸ�
			if (s.substr(j, i).compare(s.substr(j + i, i)) == 0)
			{
				alpha += i;
				count++;
			}
			// �ٸ����� �������� ���Ҵٸ� ����
			else if (count > 1)
			{
				num += log10(count) + 1;
				count = 1;
			}
		}
		// ���⸸ �ϴ� ���� ��� ����
		if (count > 1)	
			num += log10(count) + 1;

		result = len - alpha + num;
		if (answer > result)
			answer = result;
	}
	return answer;
}