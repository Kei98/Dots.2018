package com.NKS.Puntos;
import com.NKS.lists.*;

public class Mainpuntos {
	@SuppressWarnings("rawtypes")
	static List Lineas=new List<>();
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) {
	List Punto1 = new List<>();
	Punto1.add(1);
	Punto1.add(1);
	List Punto2 = new List<>();
	Punto2.add(2);
	Punto2.add(1);
	List Punto3 = new List<>();
	Punto3.add(2);
	Punto3.add(2);
	List Punto4 = new List<>();
	Punto4.add(1);
	Punto4.add(2);
	List Punto5 = new List<>();
	Punto5.add(1);
	Punto5.add(2);
	List Punto6 = new List<>();
	Punto6.add(2);
	Punto6.add(2);
	List Punto7 = new List<>();
	Punto7.add(3);
	Punto7.add(2);
	List Punto8 = new List<>();
	Punto8.add(4);
	Punto8.add(2);
//	Me di cuenta que todo se lo a�ad�a a "Punto1", lo cambi� pero sigue parecido
	
	
	
	List Malla = new List<>();
	Malla.add(Punto1);
	Malla.add(Punto2);
	Malla.add(Punto3);
	Malla.add(Punto4);
	Malla.add(Punto5);
	Malla.add(Punto6);
	Malla.add(Punto7);
	Malla.add(Punto8);


	System.out.println(Punto1.getElement(0));
	System.out.println(Punto1.getElement(1));
	System.out.println(Punto2.getElement(0));
	System.out.println(Punto2.getElement(1));
	MakeLine(Punto1,Punto2);
	System.out.println(Punto2.getElement(0));
	System.out.println(Punto2.getElement(1));
	System.out.println(Punto1.getElement(0));
	System.out.println(Punto1.getElement(1));
	MakeLine(Punto2,Punto1);
	
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void MakeLine(List Punto1,List Punto2) {
		Linea jugada=new Linea(Punto1,Punto2);
		jugada.figure(Lineas,0,jugada, null);
		Lineas.add(jugada);
		
	}

}
