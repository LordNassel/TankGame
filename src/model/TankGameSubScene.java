package model;

import javafx.animation.TranslateTransition;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.util.Duration;


public class TankGameSubScene extends SubScene {

	//private final String FONT_PATH = "src/model/resources/Kenney_Future_Narrow.ttf";
	private final static String BACKGROUND_IMAGE = "model/resources/yellow_panel.png";
	
	//HideAnimations
	private boolean isHidden;
	
	
	public TankGameSubScene() {
		super(new AnchorPane(), 600,600); 
		prefWidth(600);
		prefHeight(600);

		BackgroundImage image = new BackgroundImage(new Image(BACKGROUND_IMAGE,600,600,false,true), 
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
		
		AnchorPane root2 = (AnchorPane) this.getRoot();
		root2.setBackground(new Background(image));
		
		isHidden = true;
		setLayoutX(660); 
		setLayoutY(1050); 

	}

	//Logic to move subScenes:
	public void moveSubScene() {
		TranslateTransition transition = new TranslateTransition();
		transition.setDuration(Duration.seconds(0.5));
		transition.setNode(this);
		
		if(isHidden) {
		transition.setToY(-700);
		isHidden = false;
		}
		else {
			transition.setToY(0);
			isHidden=true;
		}
		
		transition.play();
	}
	
	public AnchorPane getPane() {
		return (AnchorPane) this.getRoot();
	}
}
