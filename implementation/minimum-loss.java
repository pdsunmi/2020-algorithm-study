import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

/*
 *  Minimum Loss
 *  https://www.hackerrank.com/challenges/minimum-loss/problem
 *  price value와 index를 2차원 배열에 저장하고 value 기준으로 sorting 하여
 *  index의 대소비교가 맞는 경우에만 minimum loss를 구하였다.
 */
public class MinimumLoss {

	// Complete the minimumLoss function below.
	static long minimumLoss(long[] price) {
		long ans = Integer.MAX_VALUE;
		int n = price.length;
		long[][] arr = new long[n][2];
		for (int i = 0; i < n; i++) {
			arr[i][0] = price[i];
			arr[i][1] = i;
		}
		Arrays.sort(arr, new Comparator<long[]>() {
			@Override
			public int compare(long[] o1, long[] o2) {
				return Long.compare(o2[0], o1[0]);
			}
		});
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				if (arr[i][1] > arr[j][1])
					continue;
				ans = Math.min(ans, arr[i][0] - arr[j][0]);
				break;
			}
		}
		return ans;
	}

	public static void main(String[] args) throws IOException {
		int m = 5;
		long[] price = { 20, 7, 8, 2, 5 };

		Arrays.asList(price);

		Integer[] intArray = { 4, 3, 5, 1, 2 };
		Stream<Integer> stream = Arrays.stream(intArray).map(n -> n * 10);
		//Virus[] virusArray = { new Virus("V1", 10), new Virus("V2", 20), new Virus("V3", 30) };
		//Stream<String> stream = Arrays.stream(virusArray).map(virus -> virus.getName());

		print(stream);

		long result = minimumLoss(price);
		System.out.println(result);

		List<Integer> list = new ArrayList<>();
		Collections.sort(list, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return Integer.compare(o1, o2);
			}
		});
	}

	public static void print(Stream<?> stream) {
		// Stream forEach with functional interface consumer
		stream.forEach(a -> System.out.print(a + " "));
		System.out.println();
	}

}
