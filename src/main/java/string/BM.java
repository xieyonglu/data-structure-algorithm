package string;

public class BM {

	public static void main(String[] args) {
		String a = "abbcdef";
		String b = "bc";
		System.out.println(bm(a, b));
	}
	
	private static final int SIZE = 256; // 全局变量或成员变量
	
	/**
	 * a,b表示主串和模式串；n，m表示主串和模式串的长度。
	 */
	public static int bm(String a, String b) {
		int[] bc = new int[SIZE]; // 记录模式串中每个字符最后出现的位置
		generateBC(b, bc); // 构建坏字符哈希表
		
		int[] suffix = new int[b.length()];
		boolean[] prefix = new boolean[b.length()];
		generateGS(b, suffix, prefix);
		
		int i = 0; // j 表示主串与模式串匹配的第一个字符
		while (i <= a.length() - b.length()) {
			int j;
			for (j = b.length() - 1; j >= 0; --j) { // 模式串从后往前匹配
				if (a.charAt(i + j) != b.charAt(j))
					break; // 坏字符对应模式串中的下标是 j
			}
			if (j < 0) {
				return i; // 匹配成功，返回主串与模式串第一个匹配的字符的位置
			}
			
			// 坏字符往后滑动的位数
			int x = j - bc[(int) a.charAt(i + j)];
			
			// 好后缀往后滑动的位数
			int y = 0;
			if (j < b.length() - 1) { // 如果有好后缀的话
				y = moveByGS(j, b.length(), suffix, prefix);
			}
			i = i + Math.max(x, y);
		}
		return -1;
	}
	
	private static void generateBC(String b, int[] bc) {
		for (int i = 0; i < SIZE; ++i)
			bc[i] = -1; // 初始化 bc
		for (int i = 0; i < b.length(); ++i) {
			int ascii = (int) b.charAt(i); // 计算 b[i] 的 ASCII 值
			bc[ascii] = i;
		}
	}
	
	private static void generateGS(String b,  int[] suffix, boolean[] prefix) {
		for (int i = 0; i < b.length(); ++i) { // 初始化
			suffix[i] = -1;
			prefix[i] = false;
		}
		for (int i = 0; i < b.length() - 1; ++i) { // b[0, i]
			int j = i;
			int k = 0; // 公共后缀子串长度
			while (j >= 0 && b.charAt(j) == b.charAt(b.length() - 1 - k)) { // 与b[0, b.length()-1]求公共后缀子串
				--j;
				++k;
				suffix[k] = j + 1; // j+1表示公共后缀子串在b[0, i]中的起始下标
			}
			if (j == -1)
				prefix[k] = true; // 如果公共后缀子串也是模式串的前缀子串
		}
	}

	/**
	 * j表示坏字符对应的模式串中的字符下标 ; m表示模式串长度
	 */
	private static int moveByGS(int j, int m, int[] suffix, boolean[] prefix) {
		int k = m - 1 - j; // 好后缀长度
		if (suffix[k] != -1)
			return j - suffix[k] + 1;
		for (int r = j + 2; r <= m - 1; ++r) {
			if (prefix[m - r] == true) {
				return r;
			}
		}
		return m;
	}

}
