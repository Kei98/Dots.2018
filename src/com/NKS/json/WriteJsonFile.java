package com.NKS.json;

import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import org.json.simple.JSONObject;

import com.NKS.Puntos.Linea;

public class WriteJsonFile {
	private JSONObject obj;

	@SuppressWarnings("unchecked")
	public WriteJsonFile(String name,Linea jugada, int pts) {
		this.obj=new JSONObject();
		obj.put("name: ", name);
		obj.put("play: ", jugada);
		obj.put("points: ", pts);
		}
		
//	private void send() {
//		Socket s = new Socket("192.168.0.100", 7777);
//		try (OutputStreamWriter out = new OutputStreamWriter(
//		        s.getOutputStream(), StandardCharsets.UTF_8)) {
//		    out.write(obj.toString());
//		
//	}
//	}
}

