package com.NKS.json;
import java.io.IOException;


import org.json.JSONObject;

import com.NKS.lists.List;
import com.NKS.lists.Matrix;


public class ReadJsonFile {
	private String name;
	private Matrix nmatrix;
	private int pts;
//	private String str2 = null;
	

	public ReadJsonFile(String str) throws IOException {
		JSONObject obj = new JSONObject(str);
		this.name = obj.getString("name");
		String smatrix = obj.getString("list");
		System.out.println(smatrix);
		this.pts = obj.getInt("points");
		this.nmatrix = toMatrix(smatrix);
//		str2 = this.name + " "+ this.nmatrix.printM() + " " + this.pts;
	}

	public String getName() {
		return name;
	}
	
//	public String getStr2() {
//		return str2;
//	}


	public Matrix getNmatrix() {
		return nmatrix;
	}

	public int getPts() {
		return pts;
	}
	
//	public static void main(String[] args) throws IOException {
//		
//		List<Integer> l = new List<Integer>(5,2);
//		List<Integer> l2 = new List<Integer>(1,9);
//		Matrix m = new Matrix(l,l2);
//		
//		WriteJsonFile json = new WriteJsonFile("Kei", m, 0);
//		ReadJsonFile rj = new ReadJsonFile(json.getJson());
////		rj.getStr2();
//		
//	}
	private static Matrix toMatrix(String m) {
		List<Character> l = new List<Character>();
		List<Character> l2 = new List<Character>();
		int count = 0;
		for(int i = 0; i < m.length(); i++) {
			if(notNumber(m.charAt(i))) {
				count++;
				if((count % 2) == 1 && count > 4) {
					Matrix matrix = new Matrix(l, l2);
					return matrix;
				}
			}else {
				if(m.charAt(i) == ',') {
				}else {
					if(count < 3) {
						l.add(m.charAt(i));
					}else {
						l2.add(m.charAt(i));
					}	
					
				}
			}
		}
		return null;
		
		
	}
	private static boolean notNumber(char s) {
		if(s == '[' || s == ']') {
			return true;
		} else {
			return false;
		}
	}
}

