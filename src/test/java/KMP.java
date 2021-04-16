
public class KMP {

	public static void main(String[] args) {
		String a = "xxabab";
		String b = "ba";
		System.out.println(kmp(a, b));
	}

	private static int kmp(String a, String b) {
		int[] next = getNext(b);
		int j = 0;
		for (int i = 0; i < a.length(); i++) {
			if (j != 0 && a.charAt(i) != b.charAt(j)) {
				j = next[j - 1] + 1;
			}
			
			if(a.charAt(i) == b.charAt(j)) {
				j++;
			}
			
			if(j == b.length()) {
				return i - b.length() + 1;
			}
		}
		return -1;
	}

	private static int[] getNext(String b) {
		int[] next = new int[b.length()];
		int k = -1;
		next[0] = -1;
		for (int i = 1; i < b.length(); i++) {
			while (k != -1 && b.charAt(k + 1) != b.charAt(i)) {
				k = next[k];
			}

			if (b.charAt(k + 1) == b.charAt(i)) {
				k++;
			}

			next[i] = k;
		}
		return next;
	}

}
