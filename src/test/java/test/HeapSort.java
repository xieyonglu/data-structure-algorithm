package test;

public class HeapSort {

	public static void main(String[] args) {
		int[] array = new int[] { 34, 12, 3, 44, 89, 56, 120, 5, 0, 99, 33, 35 };

		heapSort(array);

		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
	}

	static int LENGTH;

	public static void heapSort(int[] nums) {
		LENGTH = nums.length;
		buildMaxHeap(nums);

		while (LENGTH > 0) {
			swap(nums, 0, LENGTH - 1);
			LENGTH--;
			adjustHeap(nums, 0);
		}
	}

	private static void buildMaxHeap(int[] nums) {
		for (int i = LENGTH / 2 - 1; i >= 0; i--) {
			adjustHeap(nums, i);
		}
	}

	private static void adjustHeap(int[] nums, int index) {
		int maxIndex = index;
		if (2 * index < LENGTH && nums[2 * index] > nums[maxIndex])
			maxIndex = 2 * index;
		if (2 * index + 1 < LENGTH && nums[2 * index + 1] > nums[maxIndex])
			maxIndex = 2 * index + 1;

		if (index != maxIndex) {
			swap(nums, index, maxIndex);
			adjustHeap(nums, maxIndex);
		}
	}

	private static void swap(int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}

}
