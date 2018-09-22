package com.NKS.lists;

public class Queue<T> {
	private List<T> list= new List<>();
	
	public void enqueue(T element) {
		list.add(element, 0);
	}
	
	public T dequeue() {
		T element = list.getElement(list.getLenght()-1);
		list.delete(list.getLenght()-1);
		return element;
		
	}
	
	public T peek() {
		return list.getElement(list.getLenght()-1);
		
	}
	
	public int getLenght() {
		return list.getLenght();
	}
}
