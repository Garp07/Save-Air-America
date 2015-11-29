package com.airamerica.list;

import java.util.Comparator;
import java.util.Iterator;

public class SortedList<T> implements Iterable<T> {
	
	private SortedListNode head = null;
	private SortedListNode end = null;
	private int currentSize = 0;
	private Comparator<T> comparator;
	
	public SortedList(Comparator<T> comparator) {
		this.comparator = comparator;
	}
	
	public boolean isEmpty() {
		return head == null;
	}
	
	public class SortedListNode {
		
		private SortedListNode next = null;
		private SortedListNode previous = null;
		private T t;
		
		public SortedListNode(T t) {
			this.t = t;
		}
		
		public void setNext(SortedListNode s) {
			this.next = s;
		}
		
		public void setPrevious(SortedListNode s) {
			this.previous = s;
		}
		
		public SortedListNode getNext() {
			return this.next;
		}
		
		public SortedListNode getPrevious() {
			return this.previous;
		}
		
		public T getObject() {
			return this.t;
		}
	}
	
	public int getSize() {
		return currentSize;
	}
	
	public void add(T t) {
		SortedListNode node = new SortedListNode(t);
		
		// empty list
		if(head == null) {
			this.addToStart(t);
		} 
		// first element
		else if(this.comparator.compare(head.getObject(), node.getObject()) < 0) {
			this.addToStart(t);
		} 
		// loop through list trying to find where the node goes
		else {
			SortedListNode newNode = head.getNext();
			while(newNode != null) {
				if(this.comparator.compare(newNode.getObject(), node.getObject()) < 0) {
					newNode = newNode.getNext();
				} else {
					SortedListNode previous = newNode.getPrevious();
					previous.setNext(node);
					node.setPrevious(previous);
					
					newNode.setPrevious(node);
					node.setNext(newNode);
				}
			}
			// if node goes at the end of the list
			if(newNode == null) {
				this.addToEnd(t);
			}
		}
		
		
		
		
	}
	
	public void addToEnd(T t) {
		SortedListNode node = new SortedListNode(t);
		currentSize++;
		
		if(head == null) {
			head = node;
			end = head;
		} else {
			node.setPrevious(end);
			end.setNext(node);
			end = node;
		}
	}
	
	public void addToStart(T t) {
		SortedListNode node = new SortedListNode(t);
		currentSize++;
		
		if(head == null) {
			head = node;
			end = head;
		} else {
			head.setPrevious(node);
			node.setNext(head);
			head = node;
		}
	}
	
	public void remove(int positiion) {
		
	}
	
	public T getObject(int position) {
		return getNode(position).getObject();
	}
	
	public SortedListNode getNode(int position) {
		SortedListNode node = head;
		
		for(int i=0; i<position; i++) {
			if(node.getNext() == null) {
				return null;
			}
			
			node = node.getNext();
		}
		
		return node.getNext();
	}
	
	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			SortedListNode current = head;
			
			@Override
			public boolean hasNext() {
				return current != null;
			}

			@Override
			public T next() {
				T t = current.getObject();
				current = current.getNext();
				
				return t;
			}

			@Override
			public void remove() {
				// TODO Auto-generated method stub
				
			}
		};
	}
	
	
}
