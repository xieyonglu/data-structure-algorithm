package other;

import java.math.BigDecimal;
import java.util.Random;

public class HongBao {
	public static void main(String[] args) {
		int N = 10;
		BigDecimal amount = new BigDecimal(100);
		BigDecimal[] amounts = getHongBao(N, amount);

		BigDecimal sum = BigDecimal.ZERO;
		for (int i = 0; i < amounts.length; i++) {
			sum = sum.add(amounts[i]);
			System.out.println(amounts[i]);
		}
		System.out.println("sum==>" + sum);
	}

	private static BigDecimal[] getHongBao(int N, BigDecimal amount) {
		BigDecimal[] amounts = new BigDecimal[N];
		Integer total = (int) amount.subtract(new BigDecimal(N)).doubleValue() * 100;
		Random random = new Random();
		Integer max = (int) amount.multiply(new BigDecimal(0.3)).doubleValue() * 100;
		for (int i = 0; i < N - 1; i++) {
			int amt = random.nextInt(max - 100);
			while(total >=0 && total - amt < 0) {
				amt = random.nextInt(max);
			}
			
			total = total - amt;
			amounts[i] = (new BigDecimal(100).add(new BigDecimal(amt))).divide(new BigDecimal(100));
		}
		
		amounts[N - 1] = (new BigDecimal(100).add(new BigDecimal(total))).divide(new BigDecimal(100));
		return amounts;
	}
}
