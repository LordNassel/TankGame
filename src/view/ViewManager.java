package view;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.InfoLabel;
import model.TANK;
import model.TankGameButton;
import model.TankGameSubScene;
import model.TankPicker;
import model.TimeLabel;


public class ViewManager {
	
	/*FullScreen Settings*/
	private static final int WIDTH = 1920;
	private static final int HEIGHT = 1000;
	private AnchorPane mainPane;
	private Scene mainScene;
	private Stage mainStage;
	
	/*Button Settings*/
	private final static int MENU_BUTTONS_START_X = 100;
	private final static int MENU_BUTTONS_START_Y = 270;
	List<TankGameButton> menuButtons;

	/*Background and MyLogo*/
	private final String BACKGROUND_PATH = "view/main_colored_forest.png";
	private final String LOGO_PATH = "view/logo.png";
	
	/*SubScene Settings*/
	//private TankGameSubScene singleSubScene;
	//private TankGameSubScene multiSubScene;
	//private TankGameSubScene testSubScene;
	private TankGameSubScene tutorialSubScene;
	
	private TankGameSubScene sceneToHide;
	private TankGameSubScene tankChooserScene;
	
	/*ShipChooser*/
	List<TankPicker> tankList;
	private TANK choosenTank;
	private TANK defaultTank;
	
	/*Initialization*/
	public ViewManager() {
		menuButtons = new ArrayList<>();
		mainPane = new AnchorPane(); 
		mainScene = new Scene(mainPane,WIDTH,HEIGHT);
		mainStage = new Stage();
		mainStage.setScene(mainScene);
		
		
		createDefaultTank();
		createSubScenes();
		createButtons();
		createBackground();
		createLogo();
	}

	private TANK createDefaultTank() {
		tankList = new ArrayList<>();
		int i=0;
		for(TANK tank: TANK.values()) {
			i++;
			if(i==3) {defaultTank=tank;}
		}
		return defaultTank;
	}
	

	private HBox createTanksToChoose() {
		HBox box = new HBox();
		box.setSpacing(15);
		tankList = new ArrayList<>();
		for(TANK tank: TANK.values()) {
			TankPicker tankToPick = new TankPicker(tank);
			tankList.add(tankToPick);
			box.getChildren().add(tankToPick);
			//Mouse Click Action::
			tankToPick.setOnMouseClicked(new EventHandler<MouseEvent>(){
				
				@Override
				public void handle(MouseEvent event) {
					for(TankPicker tank : tankList) {
						tank.setIsCircleChoosen(false);
					}
					tankToPick.setIsCircleChoosen(true);
					choosenTank = tankToPick.getTank();
				}
			});
		}
		box.setLayoutX(265-(118*2));
		box.setLayoutY(150);
		return box;
	}
	
	private TankGameButton createSingleButtonToStart() {
		TankGameButton singleStartButton = new TankGameButton("START");
		singleStartButton.setLayoutX(205);
		singleStartButton.setLayoutY(500);
		
		singleStartButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if(choosenTank != null) {
					SingleGameViewManager singleGameManager = new SingleGameViewManager();
					singleGameManager.createNewSingleGame(mainStage, choosenTank);
				}
			}
		});
		return singleStartButton;
	}
	
	private void createButtons() {
		createSingleButton();
		createMultiButton();
		createTestButton();
		createTutorialButton();
		createScoresButton();
		createExitButton();
		
		///TEST///
		/*Button button = new Button();
		mainPane.getChildren().add(button);*/
	}
	
	private void showSubScene(TankGameSubScene subScene) {
		if(sceneToHide != null) {
			sceneToHide.moveSubScene();
		}
		subScene.moveSubScene();
		sceneToHide = subScene;
	}
	
	private void createSubScenes() {
		//ForFurtherDevelopement//
		//singleSubScene = new TankGameSubScene();
		//multiSubScene = new TankGameSubScene();
		//testSubScene = new TankGameSubScene();
		//mainPane.getChildren().add(singleSubScene);
		//mainPane.getChildren().add(multiSubScene);
		//mainPane.getChildren().add(testSubScene);
		
		///TutorialSubScene///
		tutorialSubScene = new TankGameSubScene();
		mainPane.getChildren().add(tutorialSubScene);
		
		InfoLabel TutorialLabel0 = new InfoLabel("P1: WASD moves");
		TutorialLabel0.setLayoutX(110);
		TutorialLabel0.setLayoutY(25);
		tutorialSubScene.getPane().getChildren().add(TutorialLabel0);
		
		InfoLabel TutorialLabel1 = new InfoLabel("P1. Space4Shoot");
		TutorialLabel1.setLayoutX(110);
		TutorialLabel1.setLayoutY(75);
		tutorialSubScene.getPane().getChildren().add(TutorialLabel1);
		
		InfoLabel TutorialLabel2 = new InfoLabel("P2: ArrowKeys moves");
		TutorialLabel2.setLayoutX(110);
		TutorialLabel2.setLayoutY(125);
		tutorialSubScene.getPane().getChildren().add(TutorialLabel2);
		
		InfoLabel TutorialLabel3 = new InfoLabel("P2:O ForShoot");
		TutorialLabel3.setLayoutX(110);
		TutorialLabel3.setLayoutY(175);
		tutorialSubScene.getPane().getChildren().add(TutorialLabel3);
		
		InfoLabel TutorialLabel4 = new InfoLabel("Collosion = LifePoints--");
		TutorialLabel4.setLayoutX(110);
		TutorialLabel4.setLayoutY(225);
		tutorialSubScene.getPane().getChildren().add(TutorialLabel4);
		
		InfoLabel TutorialLabel5 = new InfoLabel("Start: Starts Single");
		TutorialLabel5.setLayoutX(110);
		TutorialLabel5.setLayoutY(275);
		tutorialSubScene.getPane().getChildren().add(TutorialLabel5);
		
		InfoLabel TutorialLabel6 = new InfoLabel("Multi: Starts Multi");
		TutorialLabel6.setLayoutX(110);
		TutorialLabel6.setLayoutY(325);
		tutorialSubScene.getPane().getChildren().add(TutorialLabel6);
		
		InfoLabel TutorialLabel7 = new InfoLabel("Test: Starts Test");
		TutorialLabel7.setLayoutX(110);
		TutorialLabel7.setLayoutY(375);
		tutorialSubScene.getPane().getChildren().add(TutorialLabel7);
		
		InfoLabel TutorialLabel8 = new InfoLabel("Scores: In New Window");
		TutorialLabel8.setLayoutX(110);
		TutorialLabel8.setLayoutY(425);
		tutorialSubScene.getPane().getChildren().add(TutorialLabel8);
		
		InfoLabel TutorialLabel9 = new InfoLabel("Exit: By By!");
		TutorialLabel9.setLayoutX(110);
		TutorialLabel9.setLayoutY(475);
		tutorialSubScene.getPane().getChildren().add(TutorialLabel9);
		
		InfoLabel TutorialLabel10 = new InfoLabel("BME - 2019");
		TutorialLabel10.setLayoutX(110);
		TutorialLabel10.setLayoutY(525);
		tutorialSubScene.getPane().getChildren().add(TutorialLabel10);

		
		
		///TankChooserScene///
		tankChooserScene = new TankGameSubScene();
		mainPane.getChildren().add(tankChooserScene);
		
		InfoLabel chooseTankLabel = new InfoLabel("Choose your tank and time");
		chooseTankLabel.setLayoutX(110);
		chooseTankLabel.setLayoutY(25);
		
		TimeLabel hardTimeOne = new TimeLabel("Easy");
		hardTimeOne.setLayoutX(30);
		hardTimeOne.setLayoutY(370);
		
		TimeLabel hardTimeTwo = new TimeLabel("Normal");
		hardTimeTwo.setLayoutX(225);
		hardTimeTwo.setLayoutY(370);
		
		TimeLabel hardTimeThree = new TimeLabel("Hard");
		hardTimeThree.setLayoutX(420);
		hardTimeThree.setLayoutY(370);
		
		tankChooserScene.getPane().getChildren().add(hardTimeOne);
		tankChooserScene.getPane().getChildren().add(hardTimeTwo);
		tankChooserScene.getPane().getChildren().add(hardTimeThree);
		
		tankChooserScene.getPane().getChildren().add(chooseTankLabel);
		tankChooserScene.getPane().getChildren().add(createTanksToChoose());
		tankChooserScene.getPane().getChildren().add(createSingleButtonToStart());
	}
	
	private void addMenuButton(TankGameButton button) {
		button.setLayoutX(MENU_BUTTONS_START_X + menuButtons.size()*300);
		button.setLayoutY(MENU_BUTTONS_START_Y);
		menuButtons.add(button);
		mainPane.getChildren().add(button);
	}
		
	private void createSingleButton() {
		TankGameButton SingleButton = new TankGameButton("Single");
		addMenuButton(SingleButton);
		//MOVES THE SubScene::
		SingleButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				showSubScene(tankChooserScene);
			}
		});
	}
	
	private void createMultiButton() {
		TankGameButton MultiButton = new TankGameButton("Multi");
		addMenuButton(MultiButton);
		//MOVES THE SubScene::
		MultiButton.setOnAction(new EventHandler<ActionEvent>() {
		
			@Override
			public void handle(ActionEvent event) {
				//showSubScene(multiSubScene); //ForFurtherDevelopement
				if(defaultTank != null) {
				MultiGameViewManager gameManager = new MultiGameViewManager();
				gameManager.createNewMultiGame(mainStage, defaultTank);
				}
			}
		});
	}
	
	private void createTestButton() {
		TankGameButton TestButton = new TankGameButton("Test");
		addMenuButton(TestButton);
		//MOVES THE SubScene::
		TestButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				//showSubScene(testSubScene);//ForFurtherDevelopement
				if(defaultTank != null) {
				TestGameViewManager gameManager = new TestGameViewManager();
				gameManager.createNewTestGame(mainStage, defaultTank);
				}
			}
		});
	}
	
	///TUTORIAL///
	private void createTutorialButton() {
		TankGameButton TutorialButton = new TankGameButton("Tutorial");
		addMenuButton(TutorialButton);
		//MOVES THE SubScene::
		TutorialButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				showSubScene(tutorialSubScene);
			}
		});
	}
	
	///SCORES///
	private void createScoresButton() {
		TankGameButton ScoresButton = new TankGameButton("Scores");
		addMenuButton(ScoresButton);		
		ScoresButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				//showSubScene(testSubScene);//ForFurtherDevelopement
		    	ScoreBoard sb = new ScoreBoard();
		    	//sb.addGamerData("Test", "Test", 666);
		    	sb.setVisible(true);
			}
		});
		
		
	}
	
	private void createExitButton() {
		TankGameButton ExitButton = new TankGameButton("Exit");
		addMenuButton(ExitButton);
		
		ExitButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				mainStage.close();
			}
		});
	}
	
	/*BackGround*/
	private void createBackground() {
		Image backgroundImage = new Image(BACKGROUND_PATH, 1024,1024,false,true);
		BackgroundImage background = new BackgroundImage(backgroundImage,BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,BackgroundPosition.DEFAULT, null);
		mainPane.setBackground(new Background(background));
	}
	
	/*MyLogo*/
	private void createLogo() {
		ImageView logo = new ImageView(LOGO_PATH);
		logo.setLayoutX(740);
		logo.setLayoutY(0);
		
		logo.setOnMouseEntered(new EventHandler<MouseEvent>(){
			
			@Override
			public void handle(MouseEvent event) {
				logo.setEffect(new DropShadow());
			}
		});
		
		logo.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				logo.setEffect(null);
			}
		});
		mainPane.getChildren().add(logo);
	}
	
	/*Main.java calls this to RETURN mainStage*/
	public Stage getMainStage() {
		return mainStage;
	}
}
