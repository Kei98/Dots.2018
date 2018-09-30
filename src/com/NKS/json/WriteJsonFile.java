package com.NKS.json;


import org.json.JSONObject;

import com.NKS.Puntos.Linea;
//import com.NKS.lists.Matrix;

//import com.NKS.Puntos.Linea;

public class WriteJsonFile {
	private JSONObject jsonObject;
	/**
	 * Curiosidad: el json se ordena según el nombre de los parámetros
	 * @param name
	 * @param nmatrix
	 * @param pts
	 */
	
	public WriteJsonFile(String name, Linea nmatrix, int opPts, int pts, String tmessage) {
		this.jsonObject = new JSONObject();
		jsonObject.put("name", name);
		jsonObject.put("adversaryPts", opPts);
		jsonObject.put("points", pts);
		jsonObject.put("message", tmessage);
		if(nmatrix != null) {
			jsonObject.put("list", nmatrix.printLn());
		}else {
			jsonObject.put("list", " ");
		}

	}
	
	public JSONObject getJsonObject() {
		return jsonObject;
	}
	public String getJson() {
		return jsonObject.toString();
	}
//	public static void main(String[] args) {
//		WriteJsonFile w = new WriteJsonFile("Holi", null, 0);
//		System.out.println(w.getJsonObject());
//		System.out.println(w.getJson());
//	}
//	
}

