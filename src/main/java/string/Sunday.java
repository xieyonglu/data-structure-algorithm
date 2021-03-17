package string;

public class Sunday {
	
	public static void main(String[] args) {
		String a = "abbcdef";
		String b = "bc";
		System.out.println(sunday(a, b));
	}

	public static final int ASCII_SIZE = 126;

	public static int sunday(String a, String b) {
		int[] move = new int[ASCII_SIZE];
		// 主串参与匹配最末位字符移动到该位需要一定的位数
		for (int i = 0; i < ASCII_SIZE; i++) {
			move[i] = b.length() + 1;
		}
		for (int i = 0; i < b.length(); i++) {
			move[b.charAt(i)] = b.length() - i;
		}

		int s = 0; // 模式串头部在字符串位置
		int j; // 模式串已经匹配了的长度
		while (s <= a.length() - b.length()) {
			// 到达末尾之前
			j = 0; // 每一次匹配之前初始化为0
			while (a.charAt(s + j) == b.charAt(j)) {
				++j;
				if (j == b.length())
					return s;
			}
			s += move[a.charAt(s + b.length())];
		}
		return -1;
	}
	
	// a b a b a c
	// a b c
}
