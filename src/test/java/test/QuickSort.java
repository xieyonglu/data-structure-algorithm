package test;

public class QuickSort {

	public static void main(String[] args) {
		int[] array = new int[] { 34, 12, 3, 44, 89, 56, 120, 5, 0, 99, 33, 35 };

		quickSort(array, 0, array.length - 1);

		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
	}

	public static void quickSort(int[] nums, int low, int high) {
		if (low < high) {
			int position = getPosition(nums, low, high);

			quickSort(nums, low, position - 1);

			quickSort(nums, position + 1, high);
		}
	}

	private static int getPosition(int[] nums, int low, int high) {
		int pivot = nums[low];
		while (low < high) {
			while (low < high && nums[high] > pivot)
				high--;
			nums[low] = nums[high];

			while (low < high && nums[low] < pivot)
				low++;
			nums[high] = nums[low];
		}
		nums[low] = pivot;
		return low;
	}

}
