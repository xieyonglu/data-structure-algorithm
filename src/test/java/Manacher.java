
public class Manacher {

	public static void main(String[] args) {
		String str = "abcbcb";
		System.out.println(manacher(str));
	}

	public static String manacher(String str) {
		String t = init(str);
		int id = 0, mx = 0, center = 0, radius = 0;
		int[] p = new int[t.length()];
		
		for(int i=1; i<t.length(); i++) {
			p[i] = mx > i ? Math.min(mx - i, p[2 * id - i]) : 1;
			
			while(i + p[i] < t.length() && t.charAt(i + p[i]) == t.charAt(i - p[i])) p[i]++;
			
			if(i + p[i] > mx) {
				mx = i + p[i];
				id = i;
			}
			
			if(p[i] > radius) {
				center = i;
				radius = p[i];
			}
		}
		
		int index = (center - radius) / 2;
		return str.substring(index, index + radius - 1);
	}

	private static String init(String str) {
		String t = "$#";
		for(int i=0; i<str.length(); i++) {
			t = t + str.charAt(i) + "#";
		}
		return t;
	}

}
