package com.NKS.lists;

/**
 * 
 * @author NKS
 *
 * @param <T>
 */
public class List<T> {
	private Node<T> head;
	private int lenght;

	public List() {
		this.head = null;
		this.lenght = 0;
	}
	
	public List(Node<T> head) {
		this.head = head;
		this.lenght = 0;
	}
	
	
	public void add(T element) {
		Node<T> node = new Node<>(element);
		if(this.head == null) {
			this.head = node;
			this.head.setPrev(null);
		} else {
			Node<T> temp = this.head;
			while(temp.getNext() != null) {
				temp = temp.getNext();
			}
			temp.setNext(node);
			node.setPrev(temp);
		}
		this.lenght++;
	}
	
	public void add(T element, int position) {
		if(position <= this.lenght) {
			Node<T> node = new Node<>(element);
			Node<T> temp = this.head;
			for(int i = 0; i < position; i++) {
				temp = temp.getNext();
			}
			if(this.head == null && position == 0) {
//				Node<T> node = new Node<>(element);
				this.head = node;
				this.head.setPrev(null);
				this.head.setNext(null);
			}else if(this.head != null && position == 0) {
				this.head.setPrev(node);
				node.setNext(this.head);
				this.head = node;
			}else {
				temp.getNext().setPrev(node);
				node.setNext(temp.getNext());
				node.setPrev(temp);
				temp.setNext(node);
			}
			this.lenght++;
		}else {
			System.out.println("Out of index");
		}
	}
	
	public void delete(T element) {
		Node<T> temp = this.head;
		while(temp.getElement() != element && temp.getNext() != null) {
			temp = temp.getNext();
		}
		if(temp.getElement().equals(element)) {
			if(this.head == element) {
				this.head = temp.getNext();
			} else {
				temp.getPrev().setNext(temp.getNext());
				temp.getNext().setPrev(temp.getPrev());
			}
			this.lenght--;
		} else {
			System.out.println("Out of index");
		}
	}
	
	public void delete(int position) {
		if(position < this.lenght) {
			Node<T> temp = this.head;
			for(int i = 0; i < position; i++) {
				temp = temp.getNext();
			}
			if(position == 0) {
				this.head = temp.getNext();
			} else {
				temp.getPrev().setNext(temp.getNext());
				if(temp.getNext() != null) {
					temp.getNext().setPrev(temp.getPrev());
				}
			}
			this.lenght--;
		}else {
			System.out.println("Out of index");
		}
	}
	
	@SuppressWarnings("unchecked")
	public T getElement(int position) {
		if(position < this.lenght) {
			Node<T> temp = this.head;
			for(int i = 0; i < position; i++) {
				temp = temp.getNext();
			}
			return temp.getElement();
		} else {
			return (T) "Out of index";
		}
	}
	
	public void printL() {
		Node<T> temp = this.head;
		System.out.print("[");
		while(temp.getNext() != null) {
			System.out.print(temp.getElement() + ", ");
			temp = temp.getNext();
		}
		System.out.print(temp.getElement() + "]");
		System.out.println(" ");
	}
	
	public int getLenght() {
		return lenght;
	}
}
