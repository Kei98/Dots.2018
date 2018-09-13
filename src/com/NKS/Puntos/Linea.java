package com.NKS.Puntos;
import com.NKS.lists.*;

public class Linea {
	
	List Punto1;
	List Punto2;
	public Linea(List punto1, List punto2) {
		this.Punto1 = punto1;
		this.Punto2 = punto2;
	}
	public void Point(List lineas,int pos,Linea Turn, Linea conexion) {
		if (pos<lineas.getLenght()){
			Linea line = (Linea) lineas.getElement(pos);
			if (Turn.Punto2==line.Punto1) {
				conexion= new Linea(Turn.Punto1,line.Punto2);
				
			
				
				
			}if (Turn.Punto1==line.Punto2) {
				conexion= new Linea(line.Punto1,Turn.Punto2);
				
				
			}else {
				Point(lineas,++pos,Turn,conexion);
				
			}
		} else {
			System.out.println("no hay punto");
		}
	}
	public void point2(List lineas,int pos, Linea conexion) {
		if (pos<lineas.getLenght()) {
			Linea line= (Linea) lineas.getElement(pos);
			if(conexion.Punto1!=line.Punto2) {
				if (conexion.Punto2==line.Punto1) {
					conexion.Punto2=line.Punto2;
					point2(lineas,++pos,conexion);
					
				} else {
				System.out.println("hay punto");
					}
				}
		}else {
			System.out.println("no hay punto");
		}

	}	

}