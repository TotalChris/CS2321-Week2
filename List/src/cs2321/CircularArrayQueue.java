package cs2321;

import net.datastructures.Queue;

public class CircularArrayQueue<E> implements Queue<E> {
	
	private E[] contents;
	private int size;
	private int front;
	
	@SuppressWarnings("unchecked")
	public CircularArrayQueue(int queueSize) {
		this.contents = (E[])new Object[queueSize];
		this.size = 0;
		this.front = 0;
	}
	
	@Override
	public int size() {
		return this.size;
	}

	@Override
	public boolean isEmpty() {
		return this.size == 0;
	}

	@Override
	public void enqueue(E e) {
		if(size < this.contents.length) {
			contents[front + size] = e;
			size++;
		}
	}

	@Override
	public E first() {
		return contents[front];
	}

	@Override
	public E dequeue() {
		if(this.size != 0) {
			E temp = contents[front];
			contents[front] = null;
			if(this.front == this.contents.length - 1) {
				front = 0;
			} else {
				front++;
			}
			size--;
			return temp;
		} else {
			return null;
		}
	}
    
}
