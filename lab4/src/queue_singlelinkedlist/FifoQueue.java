package queue_singlelinkedlist;
import java.util.*;

public class FifoQueue<E> extends AbstractQueue<E> {
	private QueueNode<E> last;
	private int size;

	public FifoQueue() {
		super();
		last = null;
		size = 0;
	}

	/**	
	 * Inserts the specified element into this queue, if possible
	 * post:	The specified element is added to the rear of this queue
	 * @param	e the element to insert
	 * @return	true if it was possible to add the element 
	 * 			to this queue, else false
	 */
	public boolean offer(E e) {
		//For empty queue: insert element onto last node and make last.next point to itself
		if(last == null){
			last = new QueueNode<E>(e);
			last.next = last;
			size++;
		}
		//For non-empty queue: make new node point to first node, then make new node the last
		else{
			QueueNode<E> temp = new QueueNode<E>(e);
			temp.next = last.next;
			last.next = temp;
			last = temp;
			size++;
		}
		return true;
	}
	
	/**	
	 * Returns the number of elements in this queue
	 * @return the number of elements in this queue
	 */
	public int size() {		
		return size;
	}
	
	/**	
	 * Retrieves, but does not remove, the head of this queue, 
	 * returning null if this queue is empty
	 * @return 	the head element of this queue, or null 
	 * 			if this queue is empty
	 */
	public E peek() {
		if(last == null){
			return null;
		}
		else{
			return last.next.element;
		}
	}

	/**	
	 * Retrieves and removes the head of this queue, 
	 * or null if this queue is empty.
	 * post:	the head of the queue is removed if it was not empty
	 * @return 	the head of this queue, or null if the queue is empty 
	 */
	public E poll() {
		if(last == null){
			return null;
		}
		else if(last.next == last){
			E tmp = last.element;
			last = null;
			size--;
			return tmp;
		}
		else{
			E tmp = last.next.element;
			last.next = last.next.next;
			size--;
			return tmp;
		}
	}

	public void append(FifoQueue<E> q){
		if(this.equals(q))
			throw new IllegalArgumentException("Cannot append queue with itself");
		if(q.last != null && this.last != null){
			q.last.next = this.last.next;
			this.last.next = q.last.next;
			this.size += q.size;
			q.last = null;
			q.size = 0;
		}
		else if(this.last == null && q.last != null){
			q.last.next = this.last;
			this.last = q.last;
			this.size = q.size;
			q.last = null;
			q.size = 0;
		}
			
	}
	
	/**	
	 * Returns an iterator over the elements in this queue
	 * @return an iterator over the elements in this queue
	 */	
	public Iterator<E> iterator() {
		return new QueueIterator();
	}
	
	private static class QueueNode<E> {
		E element;
		QueueNode<E> next;

		private QueueNode(E x) {
			element = x;
			next = null;
		}
	}

	private class QueueIterator implements Iterator<E> {
		private QueueNode<E> pos;

		//Pos null if no elements exists, else saved as first node
		private QueueIterator(){
			if(last == null)
				pos = null;
			else
				pos = last.next;
		}
	
		public boolean hasNext(){
			return pos != null;
		}

		public E next(){
			if(!hasNext())
				throw new NoSuchElementException("No more next elements in iterator");
			//Return and stop advance once last element is reached
			else{
				QueueNode<E> tmp = pos;
				if(pos == last)
					pos = null;
				else
					pos = pos.next;

				return tmp.element;
			}
		}

	}

}
