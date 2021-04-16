
public class Sunday {
	
	public static void main(String[] args) {
		String str1 = new String("abc");
		String str2 = new String("abc");
		
		System.out.println(str1 == str2);
		System.out.println(str1 == str2.intern());
		System.out.println("abc" == str2.intern());
		System.out.println(str1.intern() == str2.intern());
		System.out.println(str1.intern());
	}
	public static void main2(String[] args) {
//		String str1 = new StringBuilder("计算机").append("软件").toString();
//		System.out.println(str1.intern() == str1);
//		
//		String str2 = new StringBuilder("ja").append("va").toString();
//		System.out.println(str2.intern() == str2);
		System.out.println("A".hashCode());
		System.out.println("B".hashCode());
	}

	public static void main0(String[] args) {
		String a = "xxabab";
		String b = "ba";
		System.out.println(sunday(a, b));
	}

	private static int SIZE = 126;

	public static int sunday(String a, String b) {
		int[] move = new int[SIZE];
		for (int i = 0; i < SIZE; i++) {
			move[i] = b.length();
		}
		for (int i = 0; i < b.length(); i++) {
			move[(int) b.charAt(i)] = b.length() - i;
		}

		int j = 0, s = 0;
		while (s < a.length() - b.length()) {
			j=0;
			while(a.charAt(s + j) == b.charAt(j)) {
				j++;
				if(j == b.length()) {
					return s;
				}
			}
			
			s = s + move[a.charAt(s + b.length())];
		}

		return -1;
	}
}
