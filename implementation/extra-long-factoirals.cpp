//https://www.hackerrank.com/challenges/extra-long-factorials/problem
#include <iostream>

using namespace std;

int number[160];
int carry, pos;

void factorial(int n) {
	// multiple
	for (int i = 0; i <= pos; i++) {
		int mul = number[i] * n + carry;
		if (carry < 10) carry = 0;

		number[i] = mul % 10;
		if (mul >= 10) carry = mul / 10;
	}
	// if carry exists
	if (carry != 0)	{
		if (carry < 10) {
			number[pos + 1] = carry;
			pos++;
		}
		else {
			number[pos + 2] = carry / 10;
			number[pos + 1] = carry % 10;
			pos += 2;
		}
		carry = 0;
	}
}

// Complete the extraLongFactorials function below.
void extraLongFactorials(int n) {
	number[0] = 1, pos = 0, carry = 0;
	// do factorial
	for (int i = 2; i <= n; i++)
		factorial(i);
	// print
	for (int i = pos; i >= 0; i--)
		cout << number[i];
}

int main()
{
	int n;
	cin >> n;
	cin.ignore(numeric_limits<streamsize>::max(), '\n');

	extraLongFactorials(n);

	return 0;
}
