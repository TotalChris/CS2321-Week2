package cs2321;

import java.util.Iterator;

import net.datastructures.List;

public class ArrayList<E> implements List<E> {
	public static final int defaultCapacity = 16;
	private int size;
	private E[] contents;
	
	public class ALIterator implements Iterator<E>{ //inner iterator class
		int index = 0;
		
		@Override
		public boolean hasNext() {
			return index <= size - 1;
		}

		@Override
		public E next() {
			return contents[index++];
		}
		
	}
	
	public ArrayList() {
		this(defaultCapacity); //overload the constructor
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList(int capacity) {
		contents = (E[])new Object[capacity];
		size = 0;
	}
	

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public E get(int i) throws IndexOutOfBoundsException {
		if(!this.isEmpty() && i >= 0 && i < this.size) { //if we're in acceptable bounds (we're using an index that has already been defined, and the list isn't empty)
			return contents[i]; //return what's at the index
		} else {
			throw new IndexOutOfBoundsException();
		}
	}

	@Override
	public E set(int i, E e) throws IndexOutOfBoundsException {
		if(!this.isEmpty() && i >= 0 && i < this.size) { // if we're in acceptable bounds (we're using an index that has already been defined, and the list isn't empty)
			E temp = contents[i]; //take out what's there right now
			contents[i] = e; //put in the new stuff
			return temp; //return what was there before
		} else {
			throw new IndexOutOfBoundsException();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void add(int i, E e) throws IndexOutOfBoundsException {
		if(i >= 0 && i <= this.size) { //make sure we're within acceptable bounds (The list can be empty as long as we add at zero, and we can add at the size index as well, but nothing out of bounds)
			if(contents.length == this.size) {
				E[] newContents = (E[])new Object[contents.length * 2];
				for(int j = 0; j < i; j++) {
					newContents[j] = contents[j]; //fill the first part up with currently existing values
				}
				newContents[i] = e; //insert our new element at the specified point
				for(int j = i + 1; j < this.size + 1; j++) {
					newContents[j] = contents[j - 1]; // fill the latter part with currently existing values
				}
				contents = newContents;
			} else {
				for(int j = this.size; j > i; j--) {
					contents[j] = contents[j-1]; //shift each element in the latter half of the array over one, since we have room
				}
				contents[i] = e; //insert our new element at the specified point
			}
			this.size++; //increment the size
		} else { //if we have an invalid index
			throw new IndexOutOfBoundsException();
		}
	}
	
	@Override
	public E remove(int i) throws IndexOutOfBoundsException {
		if(!this.isEmpty() && i >= 0 && i < this.size) { //make sure we're within acceptable bounds (The list can't be empty, and we can remove at both the size and 0 index as well, but nothing out of bounds)
			E temp = contents[i]; //get what we need
			for(int j = i; j < this.size - 1; j++) {
				contents[j] = contents[j + 1]; //move everything down
			}
			contents[this.size - 1] = null; //nullify the spot at the end
			this.size--; //decrement the size
			return temp;
		} else {
			throw new IndexOutOfBoundsException();
		}
	}

	
	@Override
	public Iterator<E> iterator() {
		return new ALIterator(); //throw out a new iterator
	}

	//We can do some shortcuts with these four because they're just syntactic sugar for add and remove 
	
	public void addFirst(E e)  {
		this.add(0, e);
	}
	
	public void addLast(E e)  {
		this.add(this.size, e);
	}
	
	public E removeFirst() throws IndexOutOfBoundsException {
		return this.remove(0);

	}
	
	public E removeLast() throws IndexOutOfBoundsException {
		return this.remove(this.size - 1);
	}
	
	// Return the capacity of array, not the number of elements.
	// Notes: The initial capacity is 16. When the array is full, the array should be doubled. 
	public int capacity() {
		return contents.length;
	}
	
}
