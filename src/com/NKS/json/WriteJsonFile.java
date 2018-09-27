package com.NKS.json;




import org.json.JSONObject;

import com.NKS.lists.Matrix;

//import com.NKS.Puntos.Linea;

public class WriteJsonFile {
	private JSONObject jsonObject;
	/**
	 * Curiosidad: el json se ordena según el nombre de los parámetros
	 * @param name
	 * @param nlist
	 * @param pts
	 */
	
	public WriteJsonFile(String name, Matrix nmatrix, int pts) {
//	public WriteJsonFile(String name, List<Integer> nlist, int pts) {
//		this.jsonObject = new JSONObject();
//		jsonObject.put("name", name);
//		jsonObject.put("list", nmatrix.printM());
//		jsonObject.put("points", pts);
	}
	
	public JSONObject getJsonObject() {
		return jsonObject;
	}
	public String getJson() {
		return jsonObject.toString();
	}
	
}

