package com.NKS.json;
//import java.io.BufferedReader;

//import java.io.FileNotFoundException;
//import java.io.FileReader;
import java.io.IOException;

import org.json.JSONObject;

import com.NKS.lists.List;

//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;

public class ReadJsonFile {
	private String name;
	private List<Integer> nlist;
	private int pts;
	private String str2 = null;
	

//	@SuppressWarnings("unchecked")
	public ReadJsonFile(String str) throws IOException {
//		String str = reader.readLine();
		JSONObject obj = new JSONObject(str);
		this.name = obj.getString("name");
//		Crear nuevo método
//		this.nlist = obj.get("list");
		this.pts = obj.getInt("points");
		str2 = this.name + " "+ this.nlist.toString() + " " + this.pts;
	}

	public String getName() {
		return name;
	}
	
	public String getStr2() {
		return str2;
	}

	public List<Integer> getNlist() {
		return nlist;
	}

	public int getPts() {
		return pts;
	}
	
	
}
