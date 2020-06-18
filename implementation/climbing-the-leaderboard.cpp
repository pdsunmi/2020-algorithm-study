// https://www.hackerrank.com/challenges/climbing-the-leaderboard/problem
#include <bits/stdc++.h>

using namespace std;

vector<string> split_string(string);

// Complete the climbingLeaderboard function below.
vector<int> climbingLeaderboard(vector<int> scores, vector<int> alice) {
       
    int scores_count = scores.size();
    int alice_count = alice.size();
    vector<int> result;

    // remove duplicated component
    int new_idx = 0;
    bool shifted = false;
    for (int i = 0; i < scores_count-1; i++)
    {
        if (scores[i] != scores[i + 1])
        {
            new_idx++;
            if (shifted)
                scores[new_idx] = scores[i + 1];
        }
        else
            shifted = true;
    }
    scores.erase(scores.begin() + new_idx + 1, scores.end());
    scores_count = scores.size();
   
    // find alice's rank
    int from = scores_count - 1;
    for (int j = 0; j < alice_count; j++)
    {
        for (int i = from; i >= 0; i--)
        {
            if (alice[j] < scores[i])
            {
                result.push_back(i + 2);
                from = i;
                break;
            }
            else if (alice[j] == scores[i])
            {
                result.push_back(i + 1);
                from = i;
                break;
            }
        }
        if (alice[j] > scores[0])
            result.push_back(1);
    }
    return result;
}

int main()
{
    ofstream fout(getenv("OUTPUT_PATH"));

    int scores_count;
    cin >> scores_count;
    cin.ignore(numeric_limits<streamsize>::max(), '\n');

    string scores_temp_temp;
    getline(cin, scores_temp_temp);

    vector<string> scores_temp = split_string(scores_temp_temp);

    vector<int> scores(scores_count);

    for (int i = 0; i < scores_count; i++) {
        int scores_item = stoi(scores_temp[i]);

        scores[i] = scores_item;
    }

    int alice_count;
    cin >> alice_count;
    cin.ignore(numeric_limits<streamsize>::max(), '\n');

    string alice_temp_temp;
    getline(cin, alice_temp_temp);

    vector<string> alice_temp = split_string(alice_temp_temp);

    vector<int> alice(alice_count);

    for (int i = 0; i < alice_count; i++) {
        int alice_item = stoi(alice_temp[i]);

        alice[i] = alice_item;
    }

    vector<int> result = climbingLeaderboard(scores, alice);

    for (int i = 0; i < result.size(); i++) {
        fout << result[i];

        if (i != result.size() - 1) {
            fout << "\n";
        }
    }

    fout << "\n";

    fout.close();

    return 0;
}

vector<string> split_string(string input_string) {
    string::iterator new_end = unique(input_string.begin(), input_string.end(), [] (const char &x, const char &y) {
        return x == y and x == ' ';
    });

    input_string.erase(new_end, input_string.end());

    while (input_string[input_string.length() - 1] == ' ') {
        input_string.pop_back();
    }

    vector<string> splits;
    char delimiter = ' ';

    size_t i = 0;
    size_t pos = input_string.find(delimiter);

    while (pos != string::npos) {
        splits.push_back(input_string.substr(i, pos - i));

        i = pos + 1;
        pos = input_string.find(delimiter, i);
    }

    splits.push_back(input_string.substr(i, min(pos, input_string.length()) - i + 1));

    return splits;
}
