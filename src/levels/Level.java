package levels;

public class Level {
	private LevelManager levelManager;
	
	public Level(LevelManager levelManager) {
		this.levelManager=levelManager;
	}
	
	public LevelManager getLevelManager(){
		return levelManager;
	} 

}
