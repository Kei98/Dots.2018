package com.NKS.Puntos;
import com.NKS.lists.*;

public class Mainpuntos {
	static List Lineas=new List<>();
	
	public static void main(String[] args) {
	List Punto1 = new List<>();
	Punto1.add(1);
	Punto1.add(1);
	List Punto2 = new List<>();
	Punto2.add(1);
	Punto2.add(2);
	List Punto3 = new List<>();
	Punto3.add(2);
	Punto3.add(1);
	List Punto4 = new List<>();
	Punto4.add(2);
	Punto4.add(2);

//	Me di cuenta que todo se lo añadía a "Punto1", lo cambié pero sigue parecido
	
	List Malla = new List<>();
	Malla.add(Punto1);
	Malla.add(Punto2);
	Malla.add(Punto3);
	Malla.add(Punto4);
	
	MakeLine(Punto1,Punto2);
	System.out.println("holo");
	MakeLine(Punto2,Punto3);
	System.out.println("holo");
	MakeLine(Punto3,Punto1);
	
	
	
	}
	public static void MakeLine(List Punto1,List Punto2) {
		Linea jugada=new Linea(Punto1,Punto2);
		Lineas.add(jugada);
		jugada.Point(Lineas,0,jugada, null);
//		Lineas.add(jugada);
		
	}

}
