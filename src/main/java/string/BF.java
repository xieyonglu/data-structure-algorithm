package string;

public class BF {

	public static void main(String[] args) {
		String a = "abbcdef";
		String b = "bc";
		System.out.println(bf(a, b));
	}

	public static int bf(String s, String p) {
		int sLen = s.length(); // 文本串长度
		int pLen = p.length(); // 模式串长度
		int i = 0;
		int j = 0;
		while (i < sLen && j < pLen) {
			// 如果当前字符匹配成功（即S[i] == P[j]），则i++，j++
			if (s.charAt(i) == p.charAt(j)) {
				i++;
				j++;
			} else {
				// 如果失配（即S[i]! = P[j]），令i = i - (j - 1)，j = 0
				i = i - j + 1;
				j = 0;
			}
		}
		
		// 匹配成功，返回模式串p在文本串s中的位置，否则返回-1
		if (j == pLen)
			return i - j;
		else
			return -1;
	}

}
