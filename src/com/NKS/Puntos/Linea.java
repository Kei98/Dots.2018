package com.NKS.Puntos;
import com.NKS.lists.*;

public class Linea {
	
	@SuppressWarnings("rawtypes")
	List Punto1;
	@SuppressWarnings("rawtypes")
	List Punto2;
	@SuppressWarnings("rawtypes")
	public Linea(List punto1, List punto2) {
		this.Punto1 = punto1;
		this.Punto2 = punto2;
	}
//	public void Point(List lineas,int pos,Linea move, Linea conexion) {
//		if (pos<lineas.getLenght()){
//			Linea line = (Linea) lineas.getElement(pos);
//			if (move.Punto2==line.Punto1) {
//				conexion= new Linea(move.Punto1,line.Punto2);
//				point2(lineas,0,conexion,"Derecha",1);
//			
//				
//				
//			}else if(move.Punto1==line.Punto2) {
//				conexion= new Linea(line.Punto1,move.Punto2);
//				point2(lineas,0,conexion,"Izquierda",1);
//				
//			}else {
//				Point(lineas,++pos,move,conexion);
	@SuppressWarnings("rawtypes")
	public void figure(List lineas,int pos,Linea Turn, Linea conexion) {
		if (pos<lineas.getLenght()){
			Linea line = (Linea) lineas.getElement(pos);

			if (Turn.Punto2==line.Punto1) {
				conexion= new Linea(Turn.Punto1,line.Punto2);
				figure2(lineas,0,conexion,"Derecha",2);
			
				
				
			}else if(Turn.Punto1==line.Punto2) {
				conexion= new Linea(line.Punto1,Turn.Punto2);
				figure2(lineas,0,conexion,"Izquierda",2);
				
			}else {
				figure(lineas,++pos,Turn,conexion);
			}
		}else {
			System.out.println("no hay figura");
		}
	}
	@SuppressWarnings("rawtypes")
	public synchronized void  figure2(List lineas,int pos, Linea conexion,String Dir,int pts) {
		if (Dir=="Derecha") {
			if (pos<lineas.getLenght()) {
				Linea line= (Linea) lineas.getElement(pos);
				if(conexion.Punto1==line.Punto2) {
					System.out.println("1hay:"+pts+"puntos");
				}else {
					conexion.Punto2=line.Punto2;
					figure2(lineas,++pos,conexion,"Derecha",++pts);
						}
			}else {
				System.out.println("no hay figura");
					}
		}else {
			if (pos<lineas.getLenght()) {
				Linea line= (Linea) lineas.getElement(pos);
				if(conexion.Punto2==line.Punto1) {
					System.out.println("2hay:"+pts+"puntos");
				}else  {
					conexion.Punto1=line.Punto1;
					figure2(lineas,++pos,conexion,"Izquierda",++pts);
				}
			}else {
				System.out.println("no hay figura");
			}
		}
	}
}