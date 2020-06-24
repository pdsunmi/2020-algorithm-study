// https://www.hackerrank.com/challenges/non-divisible-subset/problem
#include <iostream>
#include <vector>
#include <string>
#include <algorithm>

using namespace std;

int nonDivisibleSubset(int k, vector<int> s) {
	int mid_index, result = 0;
	vector <int> mod;
	mod.resize(k, 0);

	// count input as mod(k)
	for (int i = 0; i < s.size(); i++) {
		mod[s[i] % k]++;
	}

	// mod[0] and mod[k/2] has to be one
	if (mod[0] > 0) result++;
	if (k % 2 == 0 && mod[k / 2] > 0) result++;

	if (k % 2 == 0) mid_index = k / 2 - 1;
	else mid_index = k / 2;

	// include mod[i] or mod[k-i]
	for (int i = 1; i <= mid_index; i++)
		result += max(mod[i], mod[k - i]);
	return result;
}

int main()
{
	vector <int> s = { 1,7,2,4 };
	int k = 3;
	int result = nonDivisibleSubset(k, s);
	cout << result << endl;
	return 0;
}