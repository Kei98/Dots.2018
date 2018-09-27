package com.NKS.lists;

public class Matrix {
	@SuppressWarnings("rawtypes")
	private List head;
	@SuppressWarnings("unused")
	private int lenght;
	
	public Matrix() {
		this.head = null;
		this.lenght = 0;
	}
	
	@SuppressWarnings("rawtypes")
	public Matrix(List l1, List l2) {
		this.lenght = 0;
		this.add(l1);
		this.add(l2);
	}
	
	@SuppressWarnings("rawtypes")
	public void add(List list) {
		if(this.head == null) {
			this.head = list;
			this.lenght++;
		}else {
			if(this.head.getLenght() == list.getLenght()) {
				List temp = this.head;
				while(temp.getNext() != null) {
					temp = temp.getNext();
				}
				temp.setNext(list);
				this.lenght++;
			}
			else {
				System.out.println("No se puede tener diferente cantidad de entradas");
			}
			}
			
	}
	
	
	
	@SuppressWarnings("rawtypes")
	public String printM() {
		List temp = this.head;
		String matrix = "[";
		while(temp.getNext() != null) {
			matrix += temp.list() + ",";
			temp = temp.getNext();
		}
		matrix += temp.list() + "]";
//		System.out.println(matrix);
		return matrix;
	}
	
//	public static void main(String[] args) {
//		
//		List<Integer> l = new List<Integer>();
//		l.add(1);
//		l.add(2);
//		l.add(3);
//		List<Integer> l2 = new List<Integer>();
//		l2.add(4);
//		l2.add(5);
//		l2.add(6);
//		Matrix m = new Matrix();
//		m.add(l);
//		m.add(l2);
////		Matrix m = new Matrix(l, l2);
//		m.printM();
////		List<Integer> l3 = new List<Integer>();
////		l3.add(7);
////		l3.add(8);
////		l3.add(9);
////		l3.add(10);
////		
////		m.add(l);
////		m.add(l2);
////		m.add(l3);
////		m.printM();
//	}


}
