package com.NKS.Puntos;
import com.NKS.lists.*;

public class Linea {
	
	List Punto1;
	List Punto2;
	public Linea(List punto1, List punto2) {
		this.Punto1 = punto1;
		this.Punto2 = punto2;
	}
	public void Point(List lineas,int pos,Linea move, Linea conexion) {
		if (pos<lineas.getLenght()){
			Linea line = (Linea) lineas.getElement(pos);
			if (move.Punto2==line.Punto1) {
				conexion= new Linea(move.Punto1,line.Punto2);
				point2(lineas,0,conexion,"Derecha",1);
			
				
				
			}else if(move.Punto1==line.Punto2) {
				conexion= new Linea(line.Punto1,move.Punto2);
				point2(lineas,0,conexion,"Izquierda",1);
				
			}else {
				Point(lineas,++pos,move,conexion);
			}
		}else {
			System.out.println("no hay punto");
		}
	}
	public synchronized void  point2(List lineas,int pos, Linea conexion,String Dir,int pts) {
		if (Dir=="Derecha") {
			if (pos<lineas.getLenght()) {
				Linea line= (Linea) lineas.getElement(pos);
				if(conexion.Punto1==line.Punto2) {
					System.out.println("hay:"+pts+"puntos");
				}else {
					conexion.Punto2=line.Punto2;
					point2(lineas,++pos,conexion,"Derecha",++pts);
						}
			}else {
				System.out.println("no hay punto");
					}
	}else {
		if (pos<lineas.getLenght()) {
			Linea line= (Linea) lineas.getElement(pos);
			if(conexion.Punto2==line.Punto1) {
				System.out.println("hay:"+pts+"puntos");
			}else {
				conexion.Punto1=line.Punto1;
				point2(lineas,++pos,conexion,"Izquierda",++pts);
					}
		}else {
			System.out.println("no hay punto");
				}
	}
	}
	}