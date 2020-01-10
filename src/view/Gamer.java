package view;
import java.io.Serializable;

public class Gamer implements Serializable {

	///INITIALIZATION///
    private String gameVersion;
    private String gamerName;
    private int scorePoints;
    
    ///GETTER///    
    public int getScorePoints() {
    	return scorePoints;
    }
    
    public String getGameVersion() {
    	return gameVersion;
    }
    
    public String getGamerName() {
    	return gamerName;
    }
    ///SETTER///
    public void setGameVersion(String gameVersion) {
    	this.gameVersion=gameVersion;
    }
    public void setGamerName(String gamerName) {
    	this.gamerName=gamerName;
    }
    public void setScorePoints(int scorePoints) {
    	this.scorePoints=scorePoints;
    }
    
    ///NEW GAMER
    public Gamer(String gameVersion, String gamerName, int scorePoints) {
    	this.gameVersion=gameVersion;
    	this.gamerName=gamerName;
    	this.scorePoints=scorePoints;

    }
}
