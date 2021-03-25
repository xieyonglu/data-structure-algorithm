package sort;

import java.util.ArrayList;
import java.util.List;

public class RadixSort {

	public static void main(String[] args) {
		int[] array = new int[] { 34, 12, 3, 44, 89, 56, 120, 5, 0, 99, 33, 35 };

		int[] result = radixSort(array);

		for (int i = 0; i < result.length; i++) {
			System.out.print(result[i] + " ");
		}
	}

	/**
	 * 基数排序
	 */
	public static int[] radixSort(int[] array) {
		if (array == null || array.length < 2)
			return array;
		// 1.先算出最大数的位数；
		int max = array[0];
		for (int i = 1; i < array.length; i++) {
			max = Math.max(max, array[i]);
		}

		int maxDigit = 0;
		while (max != 0) {
			max /= 10;
			maxDigit++;
		}

		int mod = 10, div = 1;
		List<List<Integer>> bucketList = new ArrayList<>();
		for (int i = 0; i < 10; i++)
			bucketList.add(new ArrayList<>());
		for (int i = 0; i < maxDigit; i++, mod *= 10, div *= 10) {
			// [[120, 0], [], [12], [3, 33], [34, 44], [5, 35], [56], [], [], [89, 99]]
			// [[0, 3, 5], [12], [120], [33, 34, 35], [44], [56], [], [], [89], [99]]
			for (int j = 0; j < array.length; j++) {
				int num = (array[j] % mod) / div;
				bucketList.get(num).add(array[j]);
			}
			
			int index = 0;
			for (int j = 0; j < bucketList.size(); j++) {
				for (int k = 0; k < bucketList.get(j).size(); k++)
					array[index++] = bucketList.get(j).get(k);
				bucketList.get(j).clear();
			}
		}
		return array;
	}

}
