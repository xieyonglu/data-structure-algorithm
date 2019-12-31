package sort;

public class QuickSort {

	private static int getPosition(int[] array, int low, int high) {
		int temp = array[low];
		while (low < high) {
			while (low < high && temp < array[high]) {
				high--;
			}

			array[low] = array[high];

			while (low < high && temp > array[low]) {
				low++;
			}

			array[high] = array[low];
		}
		array[low] = temp;

		return low;
	}

	private static void quickSort(int[] array, int low, int high) {
		if (low < high) {
			int position = getPosition(array, low, high);

			quickSort(array, low, position - 1);

			quickSort(array, position + 1, high);
		}
	}

	public static void main(String[] args) {
		int[] array = new int[] { 34, 12, 3, 44, 89, 56, 120, 5, 0, 99, 33, 35 };

		quickSort(array, 0, array.length - 1);

		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
	}

}
