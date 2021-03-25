package sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BucketSort {
	
	public static void main(String[] args) {
		Integer[] array = new Integer[] { 7, 12, 56, 23, 19, 33, 35, 42, 42, 2, 8, 22, 39, 26, 17 };

		List<Integer> result = bucketSort(Arrays.asList(array), 5);

		for (int i = 0; i < result.size(); i++) {
			System.out.print(result.get(i) + " ");
		}
	}

	/**
	 * 桶排序
	 */
	public static List<Integer> bucketSort(List<Integer> array, int bucketSize) {
		if (array == null || array.size() < 2)
			return array;
		int max = array.get(0), min = array.get(0);
		// 找到最大值最小值
		for (int i = 0; i < array.size(); i++) {
			if (array.get(i) > max)
				max = array.get(i);
			if (array.get(i) < min)
				min = array.get(i);
		}
		int bucketCount = (max - min) / bucketSize + 1;
		List<ArrayList<Integer>> bucketArr = new ArrayList<>(bucketCount);
		List<Integer> resultArr = new ArrayList<>();
		for (int i = 0; i < bucketCount; i++) {
			bucketArr.add(new ArrayList<>());
		}
		for (int i = 0; i < array.size(); i++) {
			bucketArr.get((array.get(i) - min) / bucketSize).add(array.get(i));
		}
		// [[2], [7, 8], [12], [19, 17], [23, 22, 26], [], [33, 35], [39], [42, 42], [], [56]]
		for (int i = 0; i < bucketCount; i++) {
			if (bucketSize == 1) { // 如果带排序数组中有重复数字时 感谢 @见风任然是风 朋友指出错误
				for (int j = 0; j < bucketArr.get(i).size(); j++)
					resultArr.add(bucketArr.get(i).get(j));
			} else {
				if (bucketCount == 1)
					bucketSize--;
				List<Integer> temp = bucketSort(bucketArr.get(i), bucketSize);
				for (int j = 0; j < temp.size(); j++)
					resultArr.add(temp.get(j));
			}
		}
		return resultArr;
	}

}
