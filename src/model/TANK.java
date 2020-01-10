package model;

public enum TANK {
	DESERT("model/resources/tankchooser/tanks_tankDesert1.png", "model/resources/tankchooser/green_life.png"),
	GREEN("model/resources/tankchooser/tanks_tankGreen1.png", "model/resources/tankchooser/green_life.png"),
	GREY("model/resources/tankchooser/tanks_tankGrey1.png", "model/resources/tankchooser/green_life.png");
	
	
	private String urlTank;
	private String urlLife;
	
	private TANK(String urlTank, String urlLife) {
		this.urlTank = urlTank;
		this.urlLife = urlLife;
	}

	public String getUrl() {
		return this.urlTank;
	}
	
	public String getUrlLife() {
		return urlLife;
	}
}
