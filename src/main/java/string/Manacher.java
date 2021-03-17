package string;

public class Manacher {
	
	public static void main(String[] args) {
		System.out.println(manacher("rxababaxe"));
	}

	public static String manacher(String str) {
		String t = init(str);
		/**
		 * 设定重要参数
		 * 		mx：某回文串延伸到的最右边下标
		 * 		id：mx所属回文串中心下标
		 * 		center：结果最大回文串中心下标
		 * 		radius：最大长回文长度
		 */
		int mx = 0, id = 0, center = 0, radius = 0;
		
		// 新建p数组，大小和t串一致，初始化为0 ，p[i]表示以t[i]为中心的回文串半径
		int[] p = new int[t.length()];

		for (int i = 1; i < t.length(); i++) {
			// 核心算法
			p[i] = mx > i ? Math.min(mx - i, p[2 * id - i]) : 1;

			// 上面的语句只能确定i~mx的回文情况，至于mx之后的部分是否对称，就只能老老实实去匹配了，匹配一个p[i]++
			while (i + p[i] < t.length() && t.charAt(i + p[i]) == t.charAt(i - p[i]))
				p[i]++;

			// 当t[i]匹配的 右边界超过mx时mx和id就更新
			if (p[i] + i > mx) {
				mx = p[i] + i;
				id = i;
			}

			if (p[i] > radius) {
				center = i;
				radius = p[i];
			}
		}

		int index = (center - radius) / 2;
		return str.substring(index, index + radius - 1);
	}

	/**
	 * 预处理源串
	 */
	private static String init(String str) {
		String t = "$#";
		for (int i = 0; i < str.length(); i++) {
			t = t + str.charAt(i) + "#";
		}
		return t;
	}

}
