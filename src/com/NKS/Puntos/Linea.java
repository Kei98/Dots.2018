package com.NKS.Puntos;
import com.NKS.lists.*;

public class Linea {
	
	@SuppressWarnings("rawtypes")
	public List punto1;
	@SuppressWarnings("rawtypes")
	public List punto2;
	@SuppressWarnings("unused")
	private int lenght;
	
	
	public Linea() {
		this.punto1 = null;
		this.lenght = 0;
	}
	
	@SuppressWarnings("rawtypes")
	public Linea(List punto1, List punto2) {
		this.lenght = 0;
		this.punto1 = punto1;
		this.add(punto2);
		this.punto2 = punto2;
	}
	
	@SuppressWarnings("rawtypes")
	public void add(List list) {
		if(this.punto1 == null) {
			this.punto1 = list;
			this.lenght++;
		}else {
			if(this.punto1.getLenght() == list.getLenght()) {
				List temp = this.punto1;
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
	public void figure(List lineas,int pos,Linea Turn, Linea conexion) {
		if (pos<lineas.getLenght()){
			Linea line = (Linea) lineas.getElement(pos);

			if (Turn.punto2==line.punto1) {
				conexion= new Linea(Turn.punto1,line.punto2);
				figure2(lineas,0,conexion,"Derecha",2);
			
				
				
			}else if(Turn.punto1==line.punto2) {
				conexion= new Linea(line.punto1,Turn.punto2);
				figure2(lineas,0,conexion,"Izquierda",2);
				
			}else {
				figure(lineas,++pos,Turn,conexion);
			}
		}else {
			System.out.println("no hay figura1");
		}
	}
	@SuppressWarnings("rawtypes")
	public synchronized void  figure2(List lineas,int pos, Linea conexion,String Dir,int pts) {
		if (Dir=="Derecha") {
			if (pos<lineas.getLenght()) {
				Linea line= (Linea) lineas.getElement(pos);
				if(conexion.punto1==line.punto2) {
					System.out.println("1hay:"+pts+"puntos");
				}else {
					conexion.punto2=line.punto2;
					figure2(lineas,++pos,conexion,"Derecha",++pts);
						}
			}else {
				System.out.println("no hay figura");
					}
		}else {
			if (pos<lineas.getLenght()) {
				Linea line= (Linea) lineas.getElement(pos);
				if(conexion.punto2==line.punto1) {
					System.out.println("2hay:"+pts+"puntos");
				}else  {
					conexion.punto1=line.punto1;
					figure2(lineas,++pos,conexion,"Izquierda",++pts);
				}
			}else {
				System.out.println("no hay figura2");
			}
		}
	}
	
	
	
	public void printLine() {
		punto1.printL();
		punto2.printL();
	}
	
	@SuppressWarnings("rawtypes")
	public String printLn() {
		List temp = this.punto1;
		String line = "[";
		while(temp.getNext() != null) {
			line += temp.list() + ",";
			temp = temp.getNext();
		}
		line += temp.list() + "]";
		System.out.println(line);
		return line;
	}
			
}
