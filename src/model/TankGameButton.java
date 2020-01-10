package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.event.EventHandler;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;


public class TankGameButton extends Button {

	
	private final String FONT_PATH = "src/model/resources/Kenney_Future_Narrow.ttf";
	private final String BUTTON_FREE_STYLE = "-fx-background-color: transparent; -fx-background-image: url('/model/resources/yellow_button00.png')";
	private final String BUTTON_PRESSED_STYLE = "-fx-background-color: transparent; -fx-background-image: url('/model/resources/yellow_button01.png')";
	
	public TankGameButton(String text) {
		setText(text);
		setButtonFont();
		setPrefWidth(190);
		setPrefHeight(49);
		setStyle(BUTTON_FREE_STYLE);
		initializeButtonListeners();
	}
	
	private void setButtonFont() {
		
		try {
			setFont(Font.loadFont(new FileInputStream(FONT_PATH), 23));
		} catch (FileNotFoundException e) {
			setFont(Font.font("Arial",23));
		}
	}
	
	
	private void setButtonPressedStyle() {
		setStyle(BUTTON_PRESSED_STYLE);
		setPrefHeight(45);
		setLayoutY(getLayoutY() + 4);
	}
	
	private void setButtonReleasedStyle() {
		setStyle(BUTTON_FREE_STYLE);
		setPrefHeight(45);
		setLayoutY(getLayoutY() - 4);
	}
	
	
	///LISTENERS///
	private void initializeButtonListeners() {
		setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if(event.getButton().equals(MouseButton.PRIMARY)) {
					setButtonPressedStyle();
				}
			}
		});

		setOnMouseReleased(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if(event.getButton().equals(MouseButton.PRIMARY)) {
					setButtonReleasedStyle();
				}
			}
		});
		
		setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				setEffect(new DropShadow());
			}
		});
		
		setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				setEffect(null);
			}
		});
		
	}
}
/*
 *                                                ____________
                            --)-----------|____________|
                                          ,'       ,'
            -)------========            ,'  ____ ,'
                     `.    `.         ,'  ,'__ ,'
                       `.    `.     ,'       ,'
                         `.    `._,'_______,'__
                           [._ _| ^--      || |
                   ____,...-----|__________ll_|\
  ,.,..-------"""""     "----'                 ||
.-""  |=========================== ______________ |
"-...l_______________________    |  |'      || |_]
                            [`-.|__________ll_|      Incom's T-65B X-wing
                          ,'    ,' `.        `.      Space Superiority
                        ,'    ,'     `.    ____`.    Star Fighter 
            -)---------========        `.  `.____`.
                                         `.        `.
                              			   `.________`.
            			   --)-------------|___________|

*/