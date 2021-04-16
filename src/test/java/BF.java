
public class BF {

	public static void main(String[] args) {
		String a = "xxabab";
		String b = "ba";
		System.out.println(bf(a, b));
	}

	public static int bf(String a, String b) {
		int i = 0, j = 0;
		while (i < a.length() && j < b.length()) {
			if (a.charAt(i) == b.charAt(j)) {
				i++;
				j++;
			} else {
				j = 0;
				i = i - j + 1;
			}
			if (j == b.length()) {
				return i - b.length();
			}
		}
		return -1;
	}
}
