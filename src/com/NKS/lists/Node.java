package com.NKS.lists;
/**
 * 
 * @author NKS
 *
 * @param <T>
 */
public class Node<T> {
	/**
	 * Atributos
	 */
	private T element;
	private Node<T> next;
	private Node<T> prev;
	
	/**
	 * Constructor
	 * @param element
	 */
	public Node(T element) {
		this.element = element;
	}

	/**
	 * Constructor
	 * @param element
	 * @param next
	 * @param prev
	 */
	public Node(T element, Node<T> next, Node<T> prev) {
		this.element = element;
		this.next = next;
		this.prev = prev;
	}

	/**
	 * Getters y setters
	 */
	
	public T getElement() {
		return element;
	}

	public void setElement(T element) {
		this.element = element;
	}

	public Node<T> getNext() {
		return next;
	}

	public void setNext(Node<T> next) {
		this.next = next;
	}

	public Node<T> getPrev() {
		return prev;
	}

	public void setPrev(Node<T> prev) {
		this.prev = prev;
	}
		
}
