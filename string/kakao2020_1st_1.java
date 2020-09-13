public class kakao2020_1st_1 {
	public int solution(String s) {
		int len = s.length();
		int ans = len;
		for (int sz = 1; sz <= len / 2; sz++) {
			int cnt = len;
			int same = 1;
			for (int i = 0; i + 2 * sz <= len; i += sz) {
				boolean flag = true;
				for (int j = 0; j < sz; j++) {
					if (s.charAt(i + j) != s.charAt(i + sz + j)) {
						flag = false;
						if (same != 1) {
							cnt += Integer.toString(same).length();
							cnt -= (same - 1) * sz;
							same = 1;
						}
						break;
					}
				}
				if (flag) 
					same++;
			}
			if (same != 1) {
				cnt += Integer.toString(same).length();
				cnt -= (same - 1) * sz;
			}
			ans = Math.min(ans, cnt);
		}
		return ans;
	}
}
