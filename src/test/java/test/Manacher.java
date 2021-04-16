package test;

public class Manacher {

	public static void main(String[] args) {
		System.out.println(manacher("abbcccb"));
	}

	public static String manacher(String s) {
		String t = init(s);
		int id = 0, mx = 0, center = 0, radius = 0;
		int[] p = new int[t.length()];
		for(int i=0; i<t.length(); i++) {
			p[i] = i < mx ? Math.min(mx - i, p[2 * id - i]) : 1;
			
			while(i - p[i] > 0 && i + p[i] < t.length() && t.charAt(i + p[i]) == t.charAt(i - p[i])) p[i]++;
			
			if(i + p[i] > mx) {
				id = i;
				mx = i + p[i];
			}
			
			if(p[i] > radius) {
				center = i;
				radius = p[i];
			}
		}
		
		int index = (center - radius) / 2;
		return s.substring(index, index + radius - 1);
	}

	private static String init(String s) {
		String t = "$#";
		for (int i = 0; i < s.length(); i++) {
			t = t + s.charAt(i) + "#";
		}
		return t;
	}

}
