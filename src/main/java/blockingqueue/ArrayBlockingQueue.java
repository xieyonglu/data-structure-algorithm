package blockingqueue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ArrayBlockingQueue<T> {

	protected final Lock lock = new ReentrantLock();
	private final Condition notFull = lock.newCondition();
	private final Condition notEmpty = lock.newCondition();
	private static int BUFFER_SIZE = 10;
	@SuppressWarnings("unchecked")
	private final T[] items = (T[]) new Object[BUFFER_SIZE];
	private int tail, head, count;

	public void put(T t) throws InterruptedException {
		lock.lock();
		try {
			while (items.length == count) // 循环
				notFull.await();

			items[tail] = t; // 塞值
			if (tail++ == items.length) // 判断
				tail = 0;
			count++; // count
			notEmpty.signal(); // 通知
		} finally {
			lock.unlock();
		}
	}

	public T take() throws InterruptedException {
		lock.lock();
		try {
			while (count == 0) // 循环
				notEmpty.await();

			T t = items[head]; // 取值
			items[head] = null;
			if (head++ == items.length) // 判断
				head = 0;
			count--; // count
			notFull.signal(); // 通知
			return t;
		} finally {
			lock.unlock();
		}
	}
}
