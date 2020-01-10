package view;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.stage.Stage;
import model.SmallInfoLabel;
import model.TANK;


public class MultiGameViewManager {
	
	///INITIALIZATION///
	private AnchorPane gamePane;
	private Scene gameScene;
	private Stage gameStage;
	
	private static final int GAME_WIDTH = 1920;
	private static final int GAME_HEIGHT = 1000;
	private final static int MAXLIFE_POINTS = 10;
	
	private final static String BACKGROUND_PATH="view/colored_forest.png";
	private Stage menuStage;
	private AnimationTimer gameTimer;
	private final static int MAXSHOOTER_NUM=1;

	

	///CLOUDS///
	private final static String CloudOne_PATH = "model/resources/clouds/cloud1.png"; 
	private final static String CloudTwo_PATH = "model/resources/clouds/cloud2.png"; 
	private final static String CloudThree_PATH = "model/resources/clouds/cloud3.png"; 
	private ImageView[] cloudOne;
	private ImageView[] cloudTwo;
	private ImageView[] cloudThree;
	Random randomPositionGenerator;
	
	///MINES///
	private final static String MineOn_PATH = "model/resources/tankchooser/tanks_mineOn.png";
	private ImageView[] PlayerMinesOn;
	private ImageView[] EnemyMinesOn;
	private final static int TANKMINE_RADIUS = 36; //36-40?
	private final static int MAXMINE_NUMBER = 1;	//REWRITE CODE IF MORE THAN 1!
	
	//BULLETS///
	private final static String BULLET_PATH ="model/resources/tankchooser/tank_bullet1_turned.png";
	private final static int MAXBULLET_NUM = 10;
	private int playerBulletPoints = MAXBULLET_NUM;
	private int enemyBulletPoints = MAXBULLET_NUM;
	private ImageView[] playerTankBullet;
	private ImageView[] enemyTankBullet;
	
	///PLAYER///
	private ImageView playerTank;
	private ImageView palyerlife;
	private ImageView[] playerLifes;
	private ImageView[] playerShooterS;
	private int playerLifePoints = MAXLIFE_POINTS;
	private SmallInfoLabel pointsLabel;
	private int playerPoints;
	private final static int TANK_RADIUS = 86;			//CollosionDetection //85-90
	private final static int BULLET_RADIUS = 12;		//CollosionDetection
	private final static String LifePoint_PATH="model/resources/tankchooser/green_life.png";
	private final static String playerShooter_PATH ="model/resources/tankchooser/tank_arrowFull.png";
	private int playerShootAngle = 0;
	
	///PLAYER MOVEMENT///
	private boolean isWKeyPressed;
	private boolean isAKeyPressed;
	private boolean isSKeyPressed;
	private boolean isDKeyPressed;
	private boolean isSPACEKeyPressed;

	///ENEMY///
	private ImageView enemyTank;
	private ImageView enemylife;
	private ImageView[] enemyLifes;
	private ImageView[] enemyShooterS;
	private int enemyLifePoints = MAXLIFE_POINTS;
	private SmallInfoLabel enemypointsLabel;
	private int enemyPoints;
	private final static String EnemyTank_PATH = "model/resources/tankchooser/tanks_tankNavy1_turned.png";
	private final static int EnemyTank_RADIUS = 86; 	//CollosionDetection
	private final static String EnemyLifePoint_PATH="model/resources/tankchooser/red_life.png";
	private final static String enemyShooter_PATH ="model/resources/tankchooser/tank_arrowFull.png";
	private int enemyShootAngle = 0;
	
	///ENEMY MOVEMENT///
	private boolean isLeftKeyPressed;
	private boolean isRightKeyPressed;
	private boolean isUpKeyPressed;
	private boolean isDownKeyPressed;
	private boolean isOKeyPressed;
	
	///Constructor Definition///
	public MultiGameViewManager() {
		initializeStage();
		createKeyListeners();
		randomPositionGenerator = new Random();
	}
	
	
	///KEYLISTENERS///
	private void createKeyListeners() {
		gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			
			
			@Override
			public void handle(KeyEvent event) {
				///ENEMY///
				if(event.getCode()==KeyCode.LEFT) {
					isLeftKeyPressed = true;
				}
				if (event.getCode() == KeyCode.RIGHT) {
					isRightKeyPressed = true;
				}
				if (event.getCode() == KeyCode.UP) {
					isUpKeyPressed = true;
				}
				if (event.getCode() == KeyCode.DOWN) {
					isDownKeyPressed = true;
				}
				if (event.getCode() == KeyCode.O) {
					isOKeyPressed = true;
				}
				
				///PLAYER///
				if (event.getCode() == KeyCode.W) {
					isWKeyPressed = true;
				}
				if (event.getCode() == KeyCode.A) {
					isAKeyPressed = true;
				}
				if (event.getCode() == KeyCode.S) {
					isSKeyPressed = true;
				}
				if (event.getCode() == KeyCode.D) {
					isDKeyPressed = true;
				}
				else if (event.getCode() == KeyCode.SPACE) {
					isSPACEKeyPressed = true;
				}
				
			}
		});
		
		gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				///ENEMY///
				if(event.getCode()==KeyCode.LEFT) {
					isLeftKeyPressed = false;
				}
				if (event.getCode() == KeyCode.RIGHT) {
					isRightKeyPressed = false;
				}
				if (event.getCode() == KeyCode.UP) {
					isUpKeyPressed = false;
				}
				if (event.getCode() == KeyCode.DOWN) {
					isDownKeyPressed = false;
				}
				if (event.getCode() == KeyCode.O) {
					isOKeyPressed = false;
				}
				
				///PLAYER///
				if (event.getCode() == KeyCode.W) {
					isWKeyPressed = false;
				}
				if (event.getCode() == KeyCode.A) {
					isAKeyPressed = false;
				}
				if (event.getCode() == KeyCode.S) {
					isSKeyPressed = false;
				}
				if (event.getCode() == KeyCode.D) {
					isDKeyPressed = false;
				}
				else if (event.getCode() == KeyCode.SPACE) {
					isSPACEKeyPressed = false;
				}
				
			}
		});
	}
	
	///INITIALIZATION///
	private void initializeStage() {
		gamePane = new AnchorPane();
		gameScene = new Scene(gamePane, GAME_WIDTH, GAME_HEIGHT);
		gameStage = new Stage();
		gameStage.setScene(gameScene);	
	}
	
	private void createSingleGameLoop() {
		gameTimer = new AnimationTimer() {

			@Override
			public void handle(long now) {
				moveGameElements();
				checkIfElementsAreBehindTheTankAndRelocate();
				checkIfElementsCollide();
				movePlayerTank();		
				moveEnemyTank();
				isGameFinished();
			}		
		};
		gameTimer.start();
	}
	
	private void createBackground() {
		Image backgroundImage = new Image(BACKGROUND_PATH, 1024,1024,false,true);
		BackgroundImage background = new BackgroundImage(backgroundImage,BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
		gamePane.setBackground(new Background(background));
	}
	
	public void createNewMultiGame(Stage menuStage, TANK choosenTank) {
		this.menuStage = menuStage;
		//HIDE the menustage, and so the gamestage::
		this.menuStage.hide();
		gameStage.setTitle("World of Tanks v2.0 Beta");
		createBackground();
		createTank(choosenTank);
		createEnemyTank();
		createSingleGameElements(choosenTank); 
		createSingleGameLoop();
		gameStage.show();
	}
		
	private void createSingleGameElements(TANK choosenTank) {
		
		///CLOUDS///
		cloudOne = new ImageView[6];
		for(int i = 0; i<cloudOne.length; i++) {
			cloudOne[i] = new ImageView(CloudOne_PATH);
			setNewCloudPosition(cloudOne[i]);
			gamePane.getChildren().add(cloudOne[i]);
		}
		
		cloudTwo = new ImageView[6];
		for(int i = 0; i<cloudTwo.length; i++) {
			cloudTwo[i] = new ImageView(CloudTwo_PATH);
			setNewCloudPosition(cloudTwo[i]);
			gamePane.getChildren().add(cloudTwo[i]);
		}
		
		cloudThree = new ImageView[6];
		for(int i = 0; i<cloudThree.length; i++) {
			cloudThree[i] = new ImageView(CloudThree_PATH);
			setNewCloudPosition(cloudThree[i]);
			gamePane.getChildren().add(cloudThree[i]);
		}
		
		///PLAYER BULLETS///
		playerTankBullet = new ImageView[MAXBULLET_NUM];
		for(int i=0; i<playerTankBullet.length; i++) {
			playerTankBullet[i] = new ImageView(BULLET_PATH);
			setNewPlayerBulletPosition(playerTankBullet[i]);
			gamePane.getChildren().add(playerTankBullet[i]);
		}	
		for(int i = 0; i < playerTankBullet.length; i++) {
			playerTankBullet[i] = new ImageView(BULLET_PATH);
			playerTankBullet[i].setLayoutX(20 + (i*50));
			playerTankBullet[i].setLayoutY(130);
			gamePane.getChildren().add(playerTankBullet[i]);
		}
		
		///ENEMY BULLETS///
		enemyTankBullet = new ImageView[MAXBULLET_NUM];
		for(int i=0; i<enemyTankBullet.length; i++) {
			enemyTankBullet[i] = new ImageView(BULLET_PATH);
			setNewEnemyBulletPosition(enemyTankBullet[i]);
			gamePane.getChildren().add(enemyTankBullet[i]);
		}
		for(int i = 0; i < enemyTankBullet.length; i++) {
			enemyTankBullet[i] = new ImageView(BULLET_PATH);
			enemyTankBullet[i].setLayoutX(1864 - (i*50));
			enemyTankBullet[i].setLayoutY(130);
			gamePane.getChildren().add(enemyTankBullet[i]);
		}
		
		///PLAYER SHOOTER///
		playerShooterS = new ImageView[MAXSHOOTER_NUM];
		for(int i=0; i<playerShooterS.length; i++) {
			playerShooterS[i] = new ImageView(playerShooter_PATH);
			setNewPlayerBulletPosition(playerShooterS[i]);
			gamePane.getChildren().add(playerShooterS[i]);
		}
		for(int i = 0; i < playerShooterS.length; i++) {
			playerShooterS[i] = new ImageView(playerShooter_PATH);
			playerShooterS[i].setLayoutX(playerTank.getLayoutX()+171); 	//171 a tank mérete hosszban
			playerShooterS[i].setLayoutY(playerTank.getLayoutY()+100);	///-136 a tank mérete magasságban
			gamePane.getChildren().add(playerShooterS[i]);
		}
		
		///ENEMY SHOOTER///
		enemyShooterS = new ImageView[MAXSHOOTER_NUM];
		for(int i=0; i<enemyShooterS.length; i++) {
			enemyShooterS[i] = new ImageView(playerShooter_PATH);
			setNewPlayerBulletPosition(enemyShooterS[i]);
			gamePane.getChildren().add(enemyShooterS[i]);
		}
		for(int i = 0; i < enemyShooterS.length; i++) {
			enemyShooterS[i] = new ImageView(enemyShooter_PATH);
			enemyShooterS[i].setLayoutX(enemyTank.getLayoutX()-40); 		///171 a tank mérete hosszban
			enemyShooterS[i].setLayoutY(enemyTank.getLayoutY()+100);	///-136 a tank mérete magasságban
			gamePane.getChildren().add(enemyShooterS[i]);
		}
		
		///PLAYERMINES///
		PlayerMinesOn = new ImageView[MAXMINE_NUMBER];
		for(int i=0; i<PlayerMinesOn.length; i++) {
			PlayerMinesOn[i] = new ImageView(MineOn_PATH);
			setNewPlayerMinePosition(PlayerMinesOn[i]);
			gamePane.getChildren().add(PlayerMinesOn[i]);
		}
		
		///ENEMYMINES///
		EnemyMinesOn = new ImageView[MAXMINE_NUMBER];
		for(int i=0; i<EnemyMinesOn.length; i++) {
			EnemyMinesOn[i] = new ImageView(MineOn_PATH);
			setNewEnemyMinePosition(EnemyMinesOn[i]);
			gamePane.getChildren().add(EnemyMinesOn[i]);
		}
		
		
		///PLAYERLIFE///
		palyerlife = new ImageView(LifePoint_PATH);
		setNewLifePosition(palyerlife);
		gamePane.getChildren().add(palyerlife);
		
		String playerTextToSet = ("Points: 0");
		pointsLabel = new SmallInfoLabel(playerTextToSet);
		pointsLabel.setLayoutX(20);
		pointsLabel.setLayoutY(20);
		gamePane.getChildren().add(pointsLabel);
		playerLifes = new ImageView[playerLifePoints]; //playerLifes[playerLifePoints];
		
		for(int i = 0; i < playerLifes.length; i++) {
			playerLifes[i] = new ImageView(choosenTank.getUrlLife());
			playerLifes[i].setLayoutX(20 + (i*50));
			playerLifes[i].setLayoutY(80);
			gamePane.getChildren().add(playerLifes[i]);
		}
		
		///ENEMYLIFE/// 
		enemylife = new ImageView(EnemyLifePoint_PATH);
		setNewLifePosition(enemylife);
		gamePane.getChildren().add(enemylife);
		
		enemypointsLabel = new SmallInfoLabel("Points: 0");
		enemypointsLabel.setLayoutX(1770);
		enemypointsLabel.setLayoutY(20);
		gamePane.getChildren().add(enemypointsLabel);
		enemyLifes = new ImageView[enemyLifePoints];
		
		for(int i = 0; i < enemyLifes.length; i++) {
			enemyLifes[i] = new ImageView(EnemyLifePoint_PATH);
			enemyLifes[i].setLayoutX(1864 - (i*50));
			enemyLifes[i].setLayoutY(80);
			gamePane.getChildren().add(enemyLifes[i]);
		}
		

	}
	
	private void moveGameElements() {
		for(int i=0; i<cloudOne.length; i++) {
			cloudOne[i].setLayoutX(cloudOne[i].getLayoutX()+2);
		}
		
		for(int i=0; i<cloudTwo.length; i++) {
			cloudTwo[i].setLayoutX(cloudTwo[i].getLayoutX()+2);
		}
		
		for(int i=0; i<cloudThree.length; i++) {
			cloudThree[i].setLayoutX(cloudThree[i].getLayoutX()+2);
		}
		
	}
	
	private void checkIfElementsAreBehindTheTankAndRelocate() {
		for(int i=0; i< cloudOne.length; i++) {
			if(cloudOne[i].getLayoutX()>2500) {
				setNewCloudPosition(cloudOne[i]);
			}
		}
		for(int i=0; i< cloudTwo.length; i++) {
			if(cloudOne[i].getLayoutX()>2500) {
				setNewCloudPosition(cloudTwo[i]);
			}
		}
		for(int i=0; i< cloudThree.length; i++) {
			if(cloudOne[i].getLayoutX()>2500) {
				setNewCloudPosition(cloudThree[i]);
			}
		}
	}
	
	private void setNewLifePosition(ImageView image) {
		image.setLayoutX(-(randomPositionGenerator.nextInt(3000)));
		image.setLayoutY(randomPositionGenerator.nextInt(300));
	}
	
	private void setNewCloudPosition(ImageView image) {
		image.setLayoutX(-(randomPositionGenerator.nextInt(3000)));
		image.setLayoutY(randomPositionGenerator.nextInt(300));
	}
	
	private void setNewPlayerMinePosition(ImageView image) {
		image.setLayoutX(randomPositionGenerator.nextInt(640));  //GAME_WIDTH/3
		image.setLayoutY(GAME_HEIGHT-50); //1000-150=850 is the height
	}
	
	private void setNewEnemyMinePosition(ImageView image) {
		image.setLayoutX(960+randomPositionGenerator.nextInt(640));  //GAME_WIDTH/3
		image.setLayoutY(GAME_HEIGHT-50); //1000-150=850 is the height
	}
	
	private void setNewPlayerBulletPosition(ImageView image){
		image.setLayoutX(-3000);
		image.setLayoutY(-3000);
	}
	
	private void setNewEnemyBulletPosition(ImageView image){
		image.setLayoutX(-3000);
		image.setLayoutY(-3000);
	}
	
	private void createTank(TANK choosenTank) {
		playerTank = new ImageView(choosenTank.getUrl());
		playerTank.setLayoutX(randomPositionGenerator.nextInt(640)); //GAME_WIDTH/3 = 640
		playerTank.setLayoutY(GAME_HEIGHT-150); //1000-150=850 is the height
		gamePane.getChildren().add(playerTank);
		
	}
	
	private void createEnemyTank() {
		//TANK enemyTank;
		enemyTank = new ImageView(EnemyTank_PATH);
		enemyTank.setLayoutX(960 + randomPositionGenerator.nextInt(640)); //(GAME_WIDTH/2) + GAME_WIDTH/3 = 960+640
		enemyTank.setLayoutY(GAME_HEIGHT-150); //1000-150=850 is the height
		gamePane.getChildren().add(enemyTank);
	}
	
	///MOVE TANKS///
	private void movePlayerTank() {
		if(isAKeyPressed && !isDKeyPressed) {
			if(playerTank.getLayoutX()>10) {
				playerTank.setLayoutX(playerTank.getLayoutX()-3); 
				gamePane.getChildren().remove(playerShooterS[0]);
				playerShooterS[0].setLayoutX(playerTank.getLayoutX()+171+(playerShootAngle*10)-3); 	
				gamePane.getChildren().add(playerShooterS[0]);
			}
		}
		
		if(isDKeyPressed && !isAKeyPressed) {
			if(playerTank.getLayoutX()<700) {
				playerTank.setLayoutX(playerTank.getLayoutX()+3); 
				gamePane.getChildren().remove(playerShooterS[0]);
				playerShooterS[0].setLayoutX(playerTank.getLayoutX()+171+(playerShootAngle*10)+3); 	
				gamePane.getChildren().add(playerShooterS[0]);
			}
		}
		
		//W-vel megy a kereszt hátra
		if(isWKeyPressed && !isSKeyPressed) {
			if(playerShootAngle >= 0) {
				playerShootAngle -= 5;
				double myInfo  = playerShooterS[0].getLayoutX();
				gamePane.getChildren().remove(playerShooterS[0]);
				playerShooterS[0].setLayoutX(myInfo-50); 
				gamePane.getChildren().add(playerShooterS[0]);
			}
		}
		
		//S-el megy a kereszt elõre
		if(isSKeyPressed && !isWKeyPressed) {
			if(playerShootAngle <= 90) {
				playerShootAngle += 5;				
				double myInfo  = playerShooterS[0].getLayoutX();
				gamePane.getChildren().remove(playerShooterS[0]);
				playerShooterS[0].setLayoutX(myInfo+50); 
				gamePane.getChildren().add(playerShooterS[0]);

			}
		}
		if(isSPACEKeyPressed) {
			timeIsOut();
			removePlayerBullet();
			checkIfPlayerShot();
		}
		else;
	}
	
	private void moveEnemyTank() {
		if(isLeftKeyPressed && !isRightKeyPressed) {
			if(enemyTank.getLayoutX()>1120) {
				enemyTank.setLayoutX(enemyTank.getLayoutX()-3); 
				gamePane.getChildren().remove(enemyShooterS[0]);
				enemyShooterS[0].setLayoutX(enemyTank.getLayoutX()-40+(enemyShootAngle*10)-3); 	
				gamePane.getChildren().add(enemyShooterS[0]);
				
			}
		}
		
		if(isRightKeyPressed && !isLeftKeyPressed) {
			if(enemyTank.getLayoutX()<1910-2*TANK_RADIUS) {
				enemyTank.setLayoutX(enemyTank.getLayoutX()+3); 
				gamePane.getChildren().remove(enemyShooterS[0]);
				enemyShooterS[0].setLayoutX(enemyTank.getLayoutX()-40+(enemyShootAngle*10)+3); 	
				gamePane.getChildren().add(enemyShooterS[0]);
			}
		}
		
		if(isDownKeyPressed && !isUpKeyPressed) {
			if(enemyShootAngle <= 0 && enemyShootAngle > -90) {
				enemyShootAngle -= 5;
				double myInfo  = enemyShooterS[0].getLayoutX();
				gamePane.getChildren().remove(enemyShooterS[0]);
				enemyShooterS[0].setLayoutX(myInfo-50); 
				gamePane.getChildren().add(enemyShooterS[0]);
			}
		}
		
		if(isUpKeyPressed && !isDownKeyPressed) {
			if(enemyShootAngle >= -90 && enemyShootAngle <0) {
				enemyShootAngle += 5;
				double myInfo  = enemyShooterS[0].getLayoutX();
				gamePane.getChildren().remove(enemyShooterS[0]);
				enemyShooterS[0].setLayoutX(myInfo+50); 
				gamePane.getChildren().add(enemyShooterS[0]);
			}			
		}
		
		if(isOKeyPressed) {
			removeEnemyBullet();
			timeIsOut();
			checkIfEnemyShot();
		}
		else;
	}

	///LIFE REMOVAL///
	private void removePlayerLife(int remove) {
		
		playerLifePoints-=remove;		
		for(int i = 0; i < playerLifes.length; i++) {
			gamePane.getChildren().remove(playerLifes[i]);
		}
		
		for(int i = 0; i < playerLifePoints; i++) {
			playerLifes[i] = new ImageView(LifePoint_PATH);
			playerLifes[i].setLayoutX(20 + (i*50));
			playerLifes[i].setLayoutY(80);
			gamePane.getChildren().add(playerLifes[i]);
		}
		
		if(playerLifePoints == 0) {
			gameIsFinished();
		}
	}
	
	public void removeEnemyLife(int remove) {
		enemyLifePoints -= remove;
		for(int i = 0; i < enemyLifes.length; i++) {
			gamePane.getChildren().remove(enemyLifes[i]);
		}
		
		for(int i = 0; i < enemyLifePoints; i++) {
			enemyLifes[i] = new ImageView(EnemyLifePoint_PATH);
			enemyLifes[i].setLayoutX(1864 - (i*50));
			enemyLifes[i].setLayoutY(80);
			gamePane.getChildren().add(enemyLifes[i]);
		}
		
		if(enemyLifePoints == 0) {
			gameIsFinished();
		}
	}
	public int getEnemyLife() {
		return enemyLifePoints;
	}
	
	///BULLET REMOVAL///
	private void removePlayerBullet() {
		if(playerBulletPoints >0) {
		playerBulletPoints-=1;		
			for(int i = 0; i < playerTankBullet.length; i++) {
				gamePane.getChildren().remove(playerTankBullet[i]);
			}
		
			for(int i = 0; i < playerBulletPoints; i++) {
				playerTankBullet[i] = new ImageView(BULLET_PATH);
				playerTankBullet[i].setLayoutX(20 + (i*50));
				playerTankBullet[i].setLayoutY(130);
				gamePane.getChildren().add(playerTankBullet[i]);
			}
		}
	}
	
	public void removeEnemyBullet() {
		if(enemyBulletPoints >0) {
		enemyBulletPoints-=1;		
			for(int i = 0; i < enemyTankBullet.length; i++) {
				gamePane.getChildren().remove(enemyTankBullet[i]);
			}
		
			for(int i = 0; i < enemyBulletPoints; i++) {
				enemyTankBullet[i] = new ImageView(BULLET_PATH);
				enemyTankBullet[i].setLayoutX(1864 - (i*50));
				enemyTankBullet[i].setLayoutY(130);
				gamePane.getChildren().add(enemyTankBullet[i]);
			}
		}
		
	}
	
	public int getEnemyBullets() {
		return enemyBulletPoints;
	}
	
	///MINE REMOVAL///
	private void removePlayerMine(int remove) {
		
		///PLAYERMINE REMOVAL///
		for(int i = 0; i < PlayerMinesOn.length; i++) {
			gamePane.getChildren().remove(PlayerMinesOn[i]);
		}
		
		PlayerMinesOn = new ImageView[MAXMINE_NUMBER-1];
		for(int i=0; i<PlayerMinesOn.length; i++) {
			PlayerMinesOn[i] = new ImageView(MineOn_PATH);
			setNewPlayerMinePosition(PlayerMinesOn[i]);
			gamePane.getChildren().add(PlayerMinesOn[i]);
		}
		
	}
	
	private void removeEnemyMine(int remove) {
		
		///ENEMYMINE REMOVAL///
		for(int i = 0; i < EnemyMinesOn.length; i++) {
			gamePane.getChildren().remove(EnemyMinesOn[i]);
		}
		
		EnemyMinesOn = new ImageView[MAXMINE_NUMBER-1];
		for(int i=0; i<EnemyMinesOn.length; i++) {
			EnemyMinesOn[i] = new ImageView(MineOn_PATH);
			setNewPlayerMinePosition(EnemyMinesOn[i]);
			gamePane.getChildren().add(EnemyMinesOn[i]);
		}
	}
	
	///TIMER///
	private void timeIsOut() {
        try {
        	// delay 0.5 second
        	TimeUnit.SECONDS.sleep(1);
            //TimeUnit.MICROSECONDS.sleep(15000);
        } catch (InterruptedException e) {
            System.err.format("IOException: %s%n", e);
        }
	}
	
	///COLLOSION CALC.///
	private double calculateDistance(double x1, double x2, double y1, double y2) {
		return Math.sqrt(Math.pow(x1-x2, 2) + Math.pow(y1-y2,2));
	}

	private void checkIfElementsCollide() {
		
		///PLAYER MINES COLLOSION///
		for(int i=0; i<PlayerMinesOn.length; i++) {
			//Ha a kiszámított távolság kisebb, veszítsen életpontot
			if(TANK_RADIUS + TANKMINE_RADIUS > calculateDistance(playerTank.getLayoutX()+85,PlayerMinesOn[i].getLayoutX()+36,playerTank.getLayoutY(), PlayerMinesOn[i].getLayoutY()-100)) {
				removePlayerLife(3);
				removePlayerMine(i);
			}
		}
		
		///ENEMY MINES COLLOSION/// --REMOVES LIFE --REMOVES COLLIDED MINE?
		for(int i=0; i<EnemyMinesOn.length; i++) {
			if(TANK_RADIUS + TANKMINE_RADIUS > calculateDistance(enemyTank.getLayoutX()+85,EnemyMinesOn[i].getLayoutX()+36,enemyTank.getLayoutY(), EnemyMinesOn[i].getLayoutY()-100)) {
				removeEnemyLife(3);
				removeEnemyMine(i);
			}
		}
	}

	private void checkIfPlayerShot() {
		for(int i = 0; i < playerShooterS.length; i++ ) {
			if(EnemyTank_RADIUS + BULLET_RADIUS > calculateDistance(enemyTank.getLayoutX()+85,playerShooterS[i].getLayoutX(),enemyTank.getLayoutY(),playerShooterS[i].getLayoutY()-100)) {
				removeEnemyLife(1);
				playerPoints += (5+playerPoints)+playerBulletPoints;	
				
				//FIRST
				gamePane.getChildren().remove(pointsLabel);
				String textToSet = "Points:";
				//SECOND
				pointsLabel.setText(textToSet + playerPoints);
				//THIRD
				pointsLabel.setLayoutX(20);
				pointsLabel.setLayoutY(20);
				gamePane.getChildren().add(pointsLabel);
			}
		}
	}
	
	private void checkIfEnemyShot() {
		for(int i = 0; i < enemyShooterS.length; i++ ) {
			if(TANK_RADIUS + BULLET_RADIUS > calculateDistance(playerTank.getLayoutX()+85,enemyShooterS[i].getLayoutX(),playerTank.getLayoutY(),enemyShooterS[i].getLayoutY()-100)) {
				removePlayerLife(1);
				enemyPoints += (5+enemyPoints)+enemyBulletPoints; 
				//FIRST
				gamePane.getChildren().remove(enemypointsLabel);
				String textToSet = "Points:";
				//SECOND
				enemypointsLabel.setText(textToSet + enemyPoints);
				//THIRD
				pointsLabel.setLayoutX(20);
				pointsLabel.setLayoutY(20);
				gamePane.getChildren().add(enemypointsLabel);
			}
		}
	}
	
	private void isGameFinished() {
		if(enemyBulletPoints == 0 && playerBulletPoints == 0) {
			gameIsFinished();
		}
	}
	private void gameIsFinished() {
		gameStage.close();
		gameTimer.stop();
    	ScoreBoard sb = new ScoreBoard();
    	if(playerPoints>=enemyPoints) {
    	sb.addGamerData("SinglePlayer", "PlayerOne", playerPoints);
    	}
    	if(playerPoints < enemyPoints) {
    	sb.addGamerData("SinglePlayer", "Enemy", enemyPoints);
    	}
    	sb.setVisible(true);
		menuStage.show();
		
	}
}
