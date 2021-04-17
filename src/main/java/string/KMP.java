package string;

public class KMP {

	public static void main(String[] args) {
		String a = "abbcdef";
		String b = "bc";
		System.out.println(kmp(a, b));
	}

	/**
	 * a, b分别是主串和模式串；n,m分别是主串和模式串的长度。
	 */
	public static int kmp(String a, String b) {
		int[] next = getNexts(b);
		int j = 0;
		for (int i = 0; i < a.length(); ++i) {
			// j>0说明有好前缀
			while (j > 0 && a.charAt(i) != b.charAt(j)) { // 一直找到主串和模式串中不匹配的坏字符 也就是a[i]和b[j]
				j = next[j - 1] + 1;
			}
			if (a.charAt(i) == b.charAt(j))
				j++;
			if (j == b.length())
				return i - b.length() + 1; // 找到匹配模式串的了
		}
		return -1;
	}

	/**
	 * b表示模式串，m表示模式串的长度
	 */
	private static int[] getNexts(String b) {
		int[] next = new int[b.length()];
		next[0] = -1;
		int k = -1;
		for (int i = 1; i < b.length(); i++) {
			while (k != -1 && b.charAt(k + 1) != b.charAt(i)) {
				k = next[k];
			}
			if (b.charAt(k + 1) == b.charAt(i))
				k++;
			next[i] = k;
		}
		return next;
	}
}
