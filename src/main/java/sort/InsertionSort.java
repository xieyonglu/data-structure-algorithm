package sort;

public class InsertionSort {

	public static int[] insertionSort(int[] array) {
		if (array.length == 0)
			return array;
		
		int current;
		for (int i = 0; i < array.length - 1; i++) {
			// 先起下一张牌
			current = array[i + 1];
			int preIndex = i;
			
			// 如果当前牌大于起牌，则当前牌往后移，直到找到插入的位置
			while (preIndex >= 0 && current < array[preIndex]) {
				array[preIndex + 1] = array[preIndex];
				preIndex--;
			}
			
			// 将牌插进去
			array[preIndex + 1] = current;
		}
		return array;
	}

}
