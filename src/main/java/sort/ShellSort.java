package sort;

public class ShellSort {

	public static void main(String[] args) {
		int[] array = new int[] { 34, 100, 3, 44, 89, 56, 120, 110, 0, 99, 33,
				35 };

		int[] result = shellSort02(array);

		for (int i = 0; i < result.length; i++) {
			System.out.print(result[i] + " ");
		}
	}

	public static int[] shellSort01(int[] array) {
		int len = array.length;
		/**
		 * gap间隔：6, 3, 1
		 */
		int temp, gap = 1;// len / 2;
		while (gap > 0) {
			for (int i = gap; i < len; i++) {
				temp = array[i];
				int preIndex = i - gap;
				while (preIndex >= 0 && array[preIndex] > temp) {
					array[preIndex + gap] = array[preIndex];
					preIndex -= gap;
				}
				// 12 5 7
				array[preIndex + gap] = temp;
			}
			gap /= 2;
		}
		return array;
	}

	public static int[] shellSort02(int[] array) {
		int gap = array.length / 2;
		while(gap > 0) {
			for(int i=gap; i<array.length; i++) {
				for(int j=i; j>=gap && array[j] < array[j - gap]; j = j - gap) {
					int tmp = array[j];
					array[j] = array[j - gap];
					array[j - gap] = tmp;
				}
			}
			gap = gap / 2;
		}
		return array;
	}
	
	public static int[] shellSort03(int[] array) {
		int N = array.length;
		int h = 1;
		while (h < N / 3)
			h = 3 * h + 1;

		while (h >= 1) {
			for (int i = h; i < N; i++) {
				for (int j = i; j >= h && array[j] < array[j - h]; j = j - h) {
					int temp = array[j];
					array[j] = array[j - h];
					array[j - h] = temp;
				}
			}
			h = h / 3;
		}

		return array;
	}
}
