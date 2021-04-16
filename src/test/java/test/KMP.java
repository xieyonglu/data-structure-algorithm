package test;

public class KMP {

	public static void main(String[] args) {

	}

	public static int kmp(String b) {
		int[] next = getNext(b);
		int j = 0;
		for (int i = 0; i < b.length(); i++) {
			if (b.charAt(i) == b.charAt(j)) {
				j = next[j - 1] + 1;
			}

			if (b.charAt(i) == b.charAt(j))
				j++;

			if (j == b.length() - 1) {
				return i - b.length() + 1;
			}
		}
		return -1;
	}

	private static int[] getNext(String b) {
		int[] next = new int[b.length()];
		int k = -1;
		next[0] = -1;
		for(int i=1; i<b.length(); i++) {
			if(k != -1 && b.charAt(k + 1) != b.charAt(i)) {
				k = next[k];
			}
			
			if(b.charAt(i) == b.charAt(k + 1)) k++;
			
			next[i] = k;
		}
		return next;
	}

}
