package test;

public class ShellSort {

	public static void main(String[] args) {
		int[] array = new int[] { 34, 12, 3, 44, 89, 56, 120, 5, 0, 99, 33, 35 };

		shellSort(array);

		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
	}

	public static void shellSort(int[] nums) {
		int gap = nums.length / 2;
		while (gap > 0) {
			for (int i = gap; i < nums.length; i++) {
				for (int j = i; j >= gap && nums[j - gap] > nums[j]; j = j - gap) {
					int temp = nums[j];
					nums[j] = nums[j - gap];
					nums[j - gap] = temp;
				}
			}

			gap = gap / 2;
		}
	}

}
