package com.NKS.Puntos;

public class Player {
	private String name;
	private boolean myTurn;
	
	public Player(String name) {
		this.name = name;
		this.setMyTurn(false);
	}

	public boolean isMyTurn() {
		return myTurn;
	}

	public void setMyTurn(boolean myTurn) {
		this.myTurn = myTurn;
	}

	public String getName() {
		return name;
	}
	
}
