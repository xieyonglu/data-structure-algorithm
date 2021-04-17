package test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingQueue<T> {

	private Lock lock = new ReentrantLock();
	private Condition notFull = lock.newCondition();
	private Condition notEmpty = lock.newCondition();
	private static int BUFFER_SIZE = 10;
	private T[] items = (T[]) new Object[BUFFER_SIZE];
	private int head = 0, tail = 0, count = 0;

	public void put(T t) throws InterruptedException {
		try {
			lock.lock();
			while (items.length == count)
				notFull.await();

			items[tail] = t;
			if(tail++ == items.length) tail=0;
			count++;
			notEmpty.signal();
		} finally {
			lock.unlock();
		}
	}

	public T take() throws InterruptedException {
		try {
			lock.lock();
			while(count == 0) notEmpty.await();
			
			T t = items[head];
			items[head] = null;
			if(head++ == items.length) head=0;
			count--;
			notFull.signal();
			return t;
		} finally {
			lock.unlock();
		}
	}

}
