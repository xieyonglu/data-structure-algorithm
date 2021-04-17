package other;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Test1 {

	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>();
		list = math(20, 6);
		System.out.println(list);
	}

	public static List<Integer> math(int mmm, int number) {
		Random random = new Random();
		// 随机数总额
		double count = 0;
		// 每人获得随机点数
		double[] arrRandom = new double[number];
		// 每人获得钱数
		List<Integer> arrMoney = new ArrayList<Integer>(number);
		// 循环人数 随机点
		for (int i = 0; i < arrRandom.length; i++) {
			int r = random.nextInt((number) * 99) + 1;
			count += r;
			arrRandom[i] = r;
		}
		// 计算每人拆红包获得金额
		int c = 0;
		for (int i = 0; i < arrRandom.length; i++) {
			// 每人获得随机数相加 计算每人占百分比
			Double x = new Double(arrRandom[i] / count);
			// 每人通过百分比获得金额
			int m = (int) Math.floor(x * mmm);
			// 如果获得 0 金额，则设置最小值 1分钱
			if (m == 0) {
				m = 1;
			}
			// 计算获得总额
			c += m;
			// 如果不是最后一个人则正常计算
			if (i < arrRandom.length - 1) {
				arrMoney.add(m);
			} else {
				// 如果是最后一个人，则把剩余的钱数给最后一个人
				arrMoney.add(mmm - c + m);
			}
		}
		// 随机打乱每人获得金额
		Collections.shuffle(arrMoney);
		return arrMoney;
	}

}
