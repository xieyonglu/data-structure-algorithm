package test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ArrayBlockingQueue<T> {
	
	protected Lock lock = new ReentrantLock();
	protected Condition notFull = lock.newCondition();
	protected Condition notEmpty = lock.newCondition();
	private int head, tail, count;
	private static int BUFFER_SIZE = 10;
	private T[] items = (T[]) new Object[BUFFER_SIZE];
	private int[] x = new int[3];
	
	public void put(T t) throws InterruptedException {
		try {
			lock.lock();
			while(count == items.length) notFull.await();
			
			items[tail] = t;
			if(tail++ == items.length) tail=0;
			count++;
			notEmpty.signal();
		} finally {
			lock.unlock();
		}
	}
	
	public void take() throws InterruptedException {
		try {
			lock.lock();
			
			while(count == 0) notEmpty.await();
			
			T t = items[head];
			items[head] = null;
			if(head++ == items.length) head=0;
			count--;
			notFull.signal();
		} finally {
			lock.unlock();
		}
	}
}
