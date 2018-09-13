package com.NKS.Puntos;
import com.NKS.lists.*;

public class Mainpuntos {
	static List Lineas=new List<>();
	
	public static void main(String[] args) {
	List Punto1 = new List<>();
	Punto1.add(1);
	Punto1.add(1);
	List Punto2 = new List<>();
	Punto1.add(2);
	Punto1.add(1);
	List Punto3 = new List<>();
	Punto1.add(2);
	Punto1.add(1);
	List Punto4 = new List<>();
	Punto1.add(2);
	Punto1.add(2);

	List Malla = new List<>();
	Malla.add(Punto1);
	Malla.add(Punto2);
	Malla.add(Punto3);
	Malla.add(Punto4);
	
	MakeLine(Punto1,Punto2);
	MakeLine(Punto2,Punto3);
	MakeLine(Punto3,Punto1);
	System.out.println("hola");
	
	
	
	}
	public static void MakeLine(List Punto1,List Punto2) {
		Linea jugada=new Linea(Punto1,Punto2);
		jugada.Point(Lineas,0,jugada, null);
		Lineas.add(jugada);
		
	}

}
