#include <iostream>
#include <string>
#include <vector>

using namespace std;

vector<int> split(string);

int main()
{
	vector <int> numbers;
	string s = "10 100 200 30 5 20";

	numbers = split(s);

	for (int i = 0; i < numbers.size(); i++)
		cout << numbers[i] << " ";
}

vector<int> split(string input_string)
{
	vector<int> result;
	char delimiter = ' ';
	int i = 0;
	int pos = input_string.find(delimiter);

	while (pos != string::npos) {
		result.push_back(stoi(input_string.substr(i, pos - i)));
		i = pos + 1;
		pos = input_string.find(delimiter, i);
	}
	result.push_back(stoi(input_string.substr(i)));
	return result;
}

