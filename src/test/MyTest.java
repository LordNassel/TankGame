package test;

import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Test;

import view.MultiGameViewManager;
import view.SingleGameViewManager;
import view.TestGameViewManager;

public class MyTest {

	//CLASS:: TestGameViewManager
	//1
	@Test
	public void CollosionTest() throws Exception{
		
		TestGameViewManager myTest = new TestGameViewManager();
		//pitagorasziSzamharmas::
		double result = myTest.calculateDistance(0.0,3.0,5.0,0.0);
		Assert.assertNotEquals(4.0,result,0.0);
	}
	
	//CLASS:: TestGameViewManager
	//2
	@Test
	public void removePlayerLifeTest() throws Exception{
		TestGameViewManager myTest = new TestGameViewManager();
		myTest.removePlayerLife(1);
		int result=myTest.getPlayerLife();
		Assert.assertEquals(9,result);
	}
	
	//CLASS:: MultiGameViewManager
	//3
	@Test
	public void removeEnemyLifeTest() throws Exception{
		MultiGameViewManager myTest = new MultiGameViewManager();
		myTest.removeEnemyLife(1);
		int result=myTest.getEnemyLife();
		Assert.assertEquals(9,result,0);
	}
	
	//CLASS:: TestGameViewManager
	//4
	@Test
	public void removePlayerBulletTest() throws Exception{
		TestGameViewManager myTest = new TestGameViewManager();
		int result = myTest.getPlayerBulletNum();
		Assert.assertEquals(9,result,0);
	}
	
	//CLASS:: MultiGameViewManager
	//5
	@Test
	public void removeEnemyBulletTest() throws Exception{
		MultiGameViewManager myTest = new MultiGameViewManager();
		myTest.removeEnemyBullet();
		int result = myTest.getEnemyBullets();
		Assert.assertEquals(9,result,0);
	}
	/*
	//CLASS:: MultiGameViewManager ---SHIT!%!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	//6
	@Test
	public void collisionMinePlayerTest() throws Exception{
		MultiGameViewManager myTest = new MultiGameViewManager();
		Assert.assertEquals(,result,0);
		fail("Not yet implemented");
	}
	
	//CLASS::SingleGameViewManager
	//7
	@Test
	public void moveRightTest() throws Exception{
		SingleGameViewManager myTest = new SingleGameViewManager();
		myTest(enemyTank.getLayoutX());
		double movement; //+3
		myTest.setisDKeyPressed(true);
		Assert.assertEquals(,result,0);
	}
	
	//CLASS::SingleGameViewManager
	//8
	@Test
	public void moveLeftTest() throws Exception{
		SingleGameViewManager myTest = new SingleGameViewManager();
		double movement; //-3
		myTest.setisDKeyPressed(true);
		Assert.assertEquals(,result,0);
	}
	
	//CLASS::SingleGameViewManager
	//9
	@Test
	public void moveUpTest() throws Exception{
		SingleGameViewManager myTest = new SingleGameViewManager();
		double movement; //-50
		myTest.setisDKeyPressed(true);
		Assert.assertEquals(,result,0);
	}
	
	//CLASS:: SingleGameViewManager
	//10
	@Test
	public void moveDownTest() throws Exception{
		SingleGameViewManager myTest = new SingleGameViewManager();
		double movement; //+50
		myTest.setisDKeyPressed(true);
		Assert.assertEquals(,result,0);
	}*/

}
