// https://www.hackerrank.com/challenges/game-of-thrones/problem
#include <iostream>
#include <string>
using namespace std;

// Complete the gameOfThrones function below.
string gameOfThrones(string s) {

	int alpha[26];
	int odd = 0;
	fill(alpha, alpha + 26, 0);
	for (int i = 0; i < s.size(); i++) {
		int idx = s[i] - 97;
		alpha[idx]++;		
	}
	for (int i = 0; i < 26; i++) {
		if (alpha[i] % 2 == 1) {
			if (odd > 0)
				return "NO";
			odd++;
		}
	}
	return "YES";
}

int main()
{
	string s = "aabbcc";
	string result = gameOfThrones(s);
	cout << result << endl;

	return 0;
}