package model;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class TankPicker extends VBox{

	private ImageView circleImage;
	private ImageView tankImage;
	
	//BOXES! REWRITE EVERYTHING TO BOXES!!!
	private String circleNotChoosen = "model/resources/tankchooser/grey_box.png";
	private String circleChoosen = "model/resources/tankchooser/green_boxCross.png";
	
	private TANK tank;
	
	private boolean isCircleChoosen;
	
	
	public TankPicker(TANK tank) {
		circleImage = new ImageView(circleNotChoosen);
		tankImage = new ImageView(tank.getUrl());
		this.tank = tank;
		isCircleChoosen = false;
		this.setAlignment(Pos.CENTER);
		this.setSpacing(20);
		
		this.getChildren().add(circleImage);
		this.getChildren().add(tankImage);
	}
	
	public TANK getTank() {
		return tank;
	}
	
	public boolean getIsCircleChoosen() {
		return isCircleChoosen;
	}
	
	public void setIsCircleChoosen(boolean isCircleChoosen) {
		this.isCircleChoosen = isCircleChoosen;
		String imageToSet = this.isCircleChoosen ? circleChoosen : circleNotChoosen;
		circleImage.setImage(new Image(imageToSet));		
	}
}
