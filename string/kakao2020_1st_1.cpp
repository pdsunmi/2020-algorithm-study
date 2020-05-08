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

			// 같다면
			if (s.substr(j, i).compare(s.substr(j + i, i)) == 0)
			{
				alpha += i;
				count++;
			}
			// 다르지만 이전까지 같았다면 압축
			else if (count > 1)
			{
				num += log10(count) + 1;
				count = 1;
			}
		}
		// 같기만 하다 끝난 경우 압축
		if (count > 1)	
			num += log10(count) + 1;

		result = len - alpha + num;
		if (answer > result)
			answer = result;
	}
	return answer;
}